package com.mlife.activities.My_Community.Classified;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.ActivityWebView;
import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddClassified;

import java.io.ByteArrayOutputStream;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_PostNewClassified extends AppCompatActivity implements DataHolder, Observer {

    int x = 0;
    Intent intent;
    String picture = "";
    String picture2 = "";
    String picture3 = "";
    String callback = "";
    int SELECT_FILE = 123;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.cb_terms)
    CheckBox cb_terms;

    @BindView(R.id.iv_ImageOne)
    ImageView ImageOne;

    @BindView(R.id.iv_ImageTwo)
    ImageView ImageTwo;

    @BindView(R.id.iv_ImageThree)
    ImageView ImageThree;

    @OnClick(R.id.iv_ImageOne)
    public void selectImageOne() {
        x = 1;
        selectImage();
    }

    @OnClick(R.id.iv_ImageTwo)
    public void selectImageTwo() {
        x = 2;
        selectImage();
    }

    @OnClick(R.id.iv_ImageThree)
    public void selectImageThree() {
        x = 3;
        selectImage();
    }

    @BindView(R.id.et_ClassifiedName)
    EditText etClassifiedName;

    @BindView(R.id.et_Description_addConcept)
    EditText etDescription;

    @BindView(R.id.et_Price_Classified)
    EditText etPrice;

    @OnClick(R.id.tv_TC)
    public void openTerms() {
        startActivity(new Intent(this, ActivityWebView.class).putExtra("Title", "Groups Terms"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Post Classified Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Post Classified Screen Android", null);
        mFirebaseAnalytics.logEvent("Post_Classified_Screen_Android", params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__post_new_classified);
        ButterKnife.bind(this);

        intent = getIntent();
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        objectAddClassified.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectAddClassified) {

            progressBar.hideProgressBar();
            if (objectAddClassified.getAddClassifiedResponse().getSuccess()) {
                startActivity(new Intent(Activity_PostNewClassified.this, Activity_ThankYou.class).putExtra("Title", "Post new classified").putExtra("Heading", "Thank You").putExtra("Detail", " Your request has been received and it will be published soon.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", callback));
                finish();
            } else {
                Toast.makeText(this, objectAddClassified.getAddClassifiedResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
            objectAddClassified.deleteObservers();
        }
    }

    @OnClick(R.id.btn_Post_Classigied)
    public void submitClassified() {

        boolean check = true;
        etPrice.setError(null);
        etDescription.setError(null);
        etClassifiedName.setError(null);


        if (intent.getStringExtra("type").equals("1")) {
            callback = "ClassifiedSell";
        } else {
            callback = "ClassifiedRent";
        }

        if (etClassifiedName.getText().toString().trim().isEmpty()) {
            etClassifiedName.setError("Enter valid name");
            etClassifiedName.requestFocus();
            check = false;
        } else if (etDescription.getText().toString().trim().isEmpty()) {
            etDescription.setError("Enter valid description");
            etDescription.requestFocus();
            check = false;
        } else if (etPrice.getText().toString().trim().isEmpty()) {
            etPrice.setError("Enter valid Price");
            etPrice.requestFocus();
            check = false;
        } else if (picture.isEmpty()){
            Toast.makeText(this, "Please upload atleast one picture", Toast.LENGTH_SHORT).show();
        } else if (!cb_terms.isChecked()) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            check = false;
        } else if (check) {
            progressBar.showProgressBar(Activity_PostNewClassified.this);
            new Service().addClassified(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), etClassifiedName.getText().toString(), etPrice.getText().toString(), etDescription.getText().toString(), intent.getStringExtra("type"), picture, picture2, picture3);
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

    private void selectImage() {
        final CharSequence[] items = {"Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_PostNewClassified.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                Uri selectedImageUri = data.getData();
                String tempPath = getPath(selectedImageUri, Activity_PostNewClassified.this);
                Bitmap bm;
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 55, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();

                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                new Service().setGroupImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage, intent.getStringExtra("GroupId"));
                if (x == 1) {
                    ImageOne.setImageBitmap(bm);
                    picture = encodedImage;
                } else if (x == 2) {
                    ImageTwo.setImageBitmap(bm);
                    picture2 = encodedImage;
                } else if (x == 3) {
                    ImageThree.setImageBitmap(bm);
                    picture3 = encodedImage;
                }
            }
        }
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}