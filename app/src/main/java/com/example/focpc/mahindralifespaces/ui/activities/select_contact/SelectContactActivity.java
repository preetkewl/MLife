package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.dialogs.CommentDialog;
import com.example.focpc.mahindralifespaces.ui.dialogs.InputContactDialog;
import com.example.focpc.mahindralifespaces.ui.dialogs.LoadingDialog;
import com.example.focpc.mahindralifespaces.ui.dialogs.PermissionDialog;
import com.example.focpc.mahindralifespaces.ui.dialogs.ReferSuccessDialog;
import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectContactActivity extends BaseActivity implements ContactsAdapter.ContactSelectionListener,
        SelectContactView, View.OnClickListener, CommentDialog.DialogClickHandler, InputContactDialog.DialogClickHandler,
        TextWatcher {
    private RecyclerView contactRV;
    private RelativeLayout inputContactRL;
    private RadioButton inputRadio;
    private RelativeLayout continueBtn;
    private EditText searchET;
    private TextView emptyContView, selectedCountTV;
    private ImageView searchImg, closeImg;
    private CommentDialog commentDialog;
    private ContactsAdapter contactsAdapter;
    private List<ContactItem> contactList = new ArrayList<>();
    private List<ContactItem> contactListCopy = new ArrayList<>();
    private List<ContactItem> contactListOriginal = new ArrayList<>();
    private List<ContactItem> referUserItemList = new ArrayList<>();
    private SelectContactPresenter selectContactPresenter;
    private ProgressBar progressBar;
    private int selectedContactNum = 0;
    private boolean isInputMode = true;
    private InputContactDialog inputContactDialog;
    private ReferSuccessDialog referSuccessDialog;
    private String referalId = "", referralName = "", referralUserId = "";
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);
        initToolbar();
        initViews();
        selectContactPresenter = new SelectContactPresenterImple(this);
        loadingDialog = new LoadingDialog(this);


        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MlsConstants.GET_CONTACTS_FLAG);
        } else {
            selectContactPresenter.fetchContacts(this);
        }
        if (getIntent() != null && getIntent().getStringExtra(MlsConstants.REFERAL_ID) != null
                && getIntent().getStringExtra(MlsConstants.REFERAL_PRJCT_NAME) != null
                && getIntent().getStringExtra(MlsConstants.REFERAL_USER_ID) != null) {
            this.referalId = getIntent().getStringExtra(MlsConstants.REFERAL_ID);
            this.referralName = getIntent().getStringExtra(MlsConstants.REFERAL_PRJCT_NAME);
            this.referralUserId = getIntent().getStringExtra(MlsConstants.REFERAL_USER_ID);
        }

    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        inputContactRL = findViewById(R.id.inputContactRL);
        inputRadio = findViewById(R.id.inputRadio);
        continueBtn = findViewById(R.id.continueBtn);
        searchET = findViewById(R.id.searchET);
        emptyContView = findViewById(R.id.emptyContView);
        selectedCountTV = findViewById(R.id.selectedCountTV);
        searchImg = findViewById(R.id.searchImg);
        closeImg = findViewById(R.id.closeImg);
        initRV();
        inputRadio.setChecked(true);
        searchET.addTextChangedListener(this);
        inputContactRL.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        closeImg.setOnClickListener(this);

        inputRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) selectedCountTV.setText("");
                else selectedCountTV.setText("( 0 )");
            }
        });

        progressBar.getIndeterminateDrawable().setColorFilter(this.getResources().getColor(R.color.colorMain), android.graphics.PorterDuff.Mode.MULTIPLY);

    }


    private void initRV() {
        contactRV = findViewById(R.id.contactRV);
        contactRV.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ContactsAdapter(this, contactList, this);
        contactRV.setHasFixedSize(true);
        contactRV.setAdapter(contactsAdapter);
    }

    @Override
    public void onContactSelected(boolean selected, ContactItem referUserItem) {
        isInputMode = false;
        inputRadio.setChecked(false);
        if (selected) {
            referUserItemList.add(referUserItem);
            selectedContactNum += 1;
            selectedCountTV.setText("( " + String.valueOf(selectedContactNum) + " )");
            if (selectedContactNum == 10) {
                contactsAdapter.onMaximumLimitOfContacts(false);
                MlsUtils.showToast(this, "Maximum referal limit reached, press Done to continue...", true);
            }
        } else {
            referUserItemList.remove(referUserItem);
            selectedContactNum -= 1;
            selectedCountTV.setText("( " + String.valueOf(selectedContactNum) + " )");
            if (selectedContactNum < 10) contactsAdapter.onMaximumLimitOfContacts(true);
        }

    }

    @Override
    public void onContactsFetched(List<ContactItem> contactItemList, List<ContactItem> contactItemListOriginal) {
        contactList.clear();
        contactList.addAll(contactItemList);
        contactsAdapter.notifyDataSetChanged();
        contactListCopy.addAll(contactItemList);
        contactListOriginal.clear();
        contactListOriginal.addAll(contactItemListOriginal);


    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onContactFetchFailed() {
        MlsUtils.showToast(this, "Sorry, Failed to fetch contacts!", false);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inputContactRL:
                isInputMode = true;
                referUserItemList.clear();
                contactsAdapter.onMaximumLimitOfContacts(true);
                selectedContactNum = 0;
                inputRadio.setChecked(!inputRadio.isChecked());
                contactList.clear();
                contactListCopy.clear();
                for (ContactItem contactItem : contactListOriginal) {
                    try {
                        ContactItem contact = (ContactItem) contactItem.clone();
                        contactList.add(contact);
                        contactListCopy.add(contact);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                contactsAdapter.notifyDataSetChanged();

                break;
            case R.id.continueBtn:
                if (isInputMode) {
                    if (inputRadio.isChecked()) {
                        inputContactDialog = new InputContactDialog(this, this);
                        inputContactDialog.show();
                    } else {
                        MlsUtils.showToast(this, "Please select a contact...", true);
                    }
                } else {
                    if (!referUserItemList.isEmpty()) {
                        if (isNetworkAvailable())
                            selectContactPresenter.checkIfAnyUserEligible(referUserItemList, referalId, referralUserId);
                        else
                            MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
                    } else {
                        MlsUtils.showToast(this, "Please select a contact...", true);
                    }
                }

                break;
            case R.id.closeImg:
                if (view.getId() == R.id.closeImg) searchET.setText("");
                MlsUtils.hideSoftKey(this, closeImg);
                break;

        }
    }

    @Override
    public void onCommentSubMitClicked(String comment, List<ContactItem> contactItemList) {
        if (isNetworkAvailable())
            selectContactPresenter.referUsers(contactItemList, referalId, comment, referralUserId);
        else MlsUtils.showToast(this, getString(R.string.network_unavailable), false);


    }

    @Override
    public void onClickAfterMaxLimit() {
        MlsUtils.showToast(this, "Maximum referal limit reached, press Done to continue...", true);
    }

    @Override
    public void onSubMitClicked(String name, String number, String email) {
        List<ContactItem> contactItems = new ArrayList<>();
        contactItems.add(new ContactItem(name, number, email));
        if (isNetworkAvailable())
            selectContactPresenter.checkIfAnyUserEligible(contactItems, referalId, referralUserId);
        else MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MlsConstants.GET_CONTACTS_FLAG) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectContactPresenter.fetchContacts(this);
            } else {
                new PermissionDialog(this, "Application needs permission to read contacts, please Go to Settings and grant permission").show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (TextUtils.isEmpty(charSequence.toString())) {
            contactList.clear();
            contactList.addAll(contactListCopy);
            contactsAdapter.notifyDataSetChanged();
            emptyContView.setVisibility(View.GONE);
            closeImg.setVisibility(View.GONE);
            searchImg.setVisibility(View.VISIBLE);
        } else {
            closeImg.setVisibility(View.VISIBLE);
            searchImg.setVisibility(View.GONE);
            List<ContactItem> searchListContact = new ArrayList<>();
            if (contactList != null) {
                for (ContactItem referUserItem : contactListCopy) {
                    if (referUserItem.getNew_user_name() != null && referUserItem.getNew_user_name().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        searchListContact.add(referUserItem);
                    else if (referUserItem.getNew_user_phone() != null && referUserItem.getNew_user_phone().contains(charSequence.toString()))
                        searchListContact.add(referUserItem);
                }
                contactList.clear();
                contactList.addAll(searchListContact);
                contactsAdapter.notifyDataSetChanged();
                if (searchListContact.isEmpty()) emptyContView.setVisibility(View.VISIBLE);
                else emptyContView.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onError(String error) {
        MlsUtils.showToast(this, error, false);
    }

    @Override
    public void userExistCheckResult(int newUserCount, int oldUserCount, int invalidUserCount, List<ContactItem> contactItemList) {
        if (inputContactDialog != null && inputContactDialog.isShowing())
            inputContactDialog.dismiss();
        if (newUserCount > 0) {
            if (isNetworkAvailable()) {
                commentDialog = new CommentDialog(this, contactItemList, this);
                commentDialog.show();
            } else MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
        } else if (newUserCount == 0 && oldUserCount > 0) {
            String userPrefixx = "";
            if (oldUserCount == 1) userPrefixx = "Selected user";
            else userPrefixx = "All selected users";
            MlsUtils.showToast(this, userPrefixx + " part of this program or already referred ", false);
        } else if (newUserCount == 0 && oldUserCount == 0 && invalidUserCount > 0) {
            MlsUtils.showToast(this, "Selected contact has invalid phone number ", false);
        }
    }

    @Override
    public void onReferSuccess(List<UserStatusItem> userStatusArray) {
        if (commentDialog != null && commentDialog.isShowing()) commentDialog.dismiss();
        referSuccessDialog = new ReferSuccessDialog(this, userStatusArray, referralName);
        referSuccessDialog.show();
        referSuccessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setResult(MlsConstants.REFER_NOW_RESULT);
                SelectContactActivity.this.finish();
            }
        });
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }


}
