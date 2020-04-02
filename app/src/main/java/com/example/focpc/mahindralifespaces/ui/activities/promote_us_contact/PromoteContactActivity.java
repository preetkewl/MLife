package com.example.focpc.mahindralifespaces.ui.activities.promote_us_contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactItem;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.SelectContactPresenter;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.SelectContactPresenterImple;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.SelectContactView;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.UserStatusItem;
import com.example.focpc.mahindralifespaces.ui.dialogs.PermissionDialog;
import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.focpc.mahindralifespaces.utils.MlsConstants.CONTACT_PICK_RESULT;

public class PromoteContactActivity extends BaseActivity implements PromoteContactAdapter.OnContactClickInformer,
        SelectContactView,View.OnClickListener{
    private RecyclerView contactRV;
    private EditText searchET;
    private TextView emptyContView;
    private ImageView closeImg,searchImg;
    private PromoteContactAdapter promoteContactAdapter ;
    private List<ContactItem> contactItems = new ArrayList<>();
    private List<ContactItem> contactListCopy = new ArrayList<>();
    private SelectContactPresenter selectContactPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_contact);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(this.getResources().getColor(R.color.colorMain), android.graphics.PorterDuff.Mode.MULTIPLY);
        emptyContView = findViewById(R.id.emptyContView);
        searchImg = findViewById(R.id.searchImg);
        closeImg = findViewById(R.id.closeImg);
        initToolbar();
        initRV();
        selectContactPresenter = new SelectContactPresenterImple(this);
        setSearchField();
        closeImg.setOnClickListener(this);

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MlsConstants.GET_CONTACTS_FLAG);
        } else {
            selectContactPresenter.fetchContacts(this);
        }

    }

    private void setSearchField(){

        searchET = findViewById(R.id.searchET);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    contactItems.clear();
                    contactItems.addAll(contactListCopy);
                    promoteContactAdapter.notifyDataSetChanged();
                    emptyContView.setVisibility(View.GONE);
                    closeImg.setVisibility(View.GONE);
                    searchImg.setVisibility(View.VISIBLE);
                } else {
                    closeImg.setVisibility(View.VISIBLE);
                    searchImg.setVisibility(View.GONE);
                    List<ContactItem> searchListContact = new ArrayList<>();
                    if (contactItems != null) {
                        for (ContactItem contactItem : contactListCopy) {
                            if (contactItem.getNew_user_name() != null && contactItem.getNew_user_name().toLowerCase().contains(charSequence.toString().toLowerCase()))
                                searchListContact.add(contactItem);
                            else if (contactItem.getNew_user_phone()!=null && contactItem.getNew_user_phone().contains(charSequence.toString()))
                                searchListContact.add(contactItem);
                        }
                        contactItems.clear();
                        contactItems.addAll(searchListContact);
                        promoteContactAdapter.notifyDataSetChanged();
                        if (searchListContact.isEmpty()) emptyContView.setVisibility(View.VISIBLE);
                        else emptyContView.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void initRV(){
        promoteContactAdapter = new PromoteContactAdapter(contactItems,this);
        contactRV = findViewById(R.id.contactRV);
        contactRV.setLayoutManager(new LinearLayoutManager(this));
        contactRV.setHasFixedSize(true);
        contactRV.setAdapter(promoteContactAdapter);

    }

    private void initToolbar(){
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContactClicked(ContactItem contactItem) {
        Intent intent = new Intent();
        intent.putExtra(MlsConstants.PICKED_NAME , contactItem.getNew_user_name());
        intent.putExtra(MlsConstants.PICKED_NUMBER,contactItem.getNew_user_phone());
        setResult(CONTACT_PICK_RESULT,intent);
        finish();

    }

    @Override
    public void onContactsFetched(List<ContactItem> contactItemList,List<ContactItem> contactItemListOriginal) {
        contactItems.clear();
        contactItems.addAll(contactItemList);
        promoteContactAdapter.notifyDataSetChanged();
        contactListCopy.addAll(contactItemList);

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onContactFetchFailed() {
        MlsUtils.showToast(this, "Sorry, Failed to fetch contacts!", false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MlsConstants.GET_CONTACTS_FLAG){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectContactPresenter.fetchContacts(this);
            } else {
                new PermissionDialog(this,"Application needs permission to read contacts, please Go to Settings and grant permission").show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.closeImg) {
            searchET.setText("");
            MlsUtils.hideSoftKey(this,closeImg);
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void userExistCheckResult(int newUserCount, int oldUserCount, int invalidUserCount,List<ContactItem> contactItemList) {

    }

    @Override
    public void onReferSuccess(List<UserStatusItem> userStatusArray) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }
}
