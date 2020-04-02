package com.example.focpc.mahindralifespaces.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.focpc.mahindralifespaces.MainActivity;
import com.mlife.R;
import com.example.focpc.mahindralifespaces.ui.activities.refer_now.ReferalDataStoreAsync;
import com.example.focpc.mahindralifespaces.ui.activities.reward_wallet.RewardDataStoreAsync;
import com.example.focpc.mahindralifespaces.ui.dialogs.LoadingDialog;

public class MlsBaseActivty extends AppCompatActivity {
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
    }

    public void onSessionExpired() {
        logout();
        MlsUtils.showToast(this, "Current session has expired", true);
    }

    public void showLoading() {
        if (loadingDialog == null) loadingDialog = new LoadingDialog(this);
        if (!loadingDialog.isShowing()) loadingDialog.show();
    }

    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                    loadingDialog = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
//        else if (item.getItemId() == R.id.logout) {
//            logout();
//        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void logout() {
        new RewardDataStoreAsync(this, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new ReferalDataStoreAsync(this, "").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        SharedPreferencesHelper.clearPreferences(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(MlsConstants.FRESH_FLAG);
        startActivity(intent);
        this.finish();
    }
}
