package com.mlife.activities.My_Community.Groups;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mlife.adapter.PrePostImageAdapter;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectCreatePost;
import com.mlife.web.holder.Response.ObjectUpdatePost;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Post extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String imageEncoded;
    String getImageEncoded = "";
    int PICK_IMAGE_MULTIPLE = 1;
    List<String> imagesEncodedList;
    PrePostImageAdapter prePostImageAdapter;
    private static final int SELECT_FILE = 1;
    StringBuilder images = new StringBuilder();
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.tv_UserName)
    TextView tv_UserName;

    @BindView(R.id.et_PostContent)
    EditText etPostContent;

    @BindView(R.id.rv_PrePostImage)
    RecyclerView recyclerView;

    @BindView(R.id.iv_UserImage)
    CircularImageView iv_UserImage;


    @Override
    protected void onResume() {
        super.onResume();


        if (!mahindraClappPreference.getProfileImage().isEmpty()) {
            Picasso.with(this).load(mahindraClappPreference.getProfileImage()).into(iv_UserImage);
        }

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Group Post Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Group Post Screen Android", null);
        mFirebaseAnalytics.logEvent("Group_Post_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__post);
        ButterKnife.bind(this);

        objectCreatePost.addObserver(this);
        objectUpdatePost.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        tv_UserName.setText(mahindraClappPreference.getData("Name"));
        intent = getIntent();
        try {
            if (intent.getStringExtra("callBack").equals("2")) {
                etPostContent.setText(intent.getStringExtra("PostDetails"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectCreatePost) {
            if (objectCreatePost.getCreatePostResponse().getSuccess()) {
                progressBar.hideProgressBar();
                Toast.makeText(this, objectCreatePost.getCreatePostResponse().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(007, intent);
                finish();
            } else {
                progressBar.hideProgressBar();
                Toast.makeText(this, objectCreatePost.getCreatePostResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (o instanceof ObjectUpdatePost) {
            if (objectUpdatePost.getUpdatePostResponse().getSuccess()) {
                progressBar.hideProgressBar();
                Intent intent = new Intent();
                setResult(007, intent);
                finish();
            } else {
                progressBar.hideProgressBar();
                Toast.makeText(this, objectCreatePost.getCreatePostResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    Uri mImageUri = data.getData();
                    mArrayUri.add(mImageUri);
                    Cursor cursor = getContentResolver().query(mImageUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();
                    progressBar.showProgressBar(Activity_Post.this);
                    new ImportImages().execute((mArrayUri.toArray(new Uri[mArrayUri.size()])));
                    prePostImageAdapter = new PrePostImageAdapter(mArrayUri, Activity_Post.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_Post.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(prePostImageAdapter);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                        }

                        progressBar.showProgressBar(Activity_Post.this);
                        new ImportImages().execute((mArrayUri.toArray(new Uri[mArrayUri.size()])));

                        prePostImageAdapter = new PrePostImageAdapter(mArrayUri, Activity_Post.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_Post.this, LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(prePostImageAdapter);
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.iv_UploadImages)
    public void uploadImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @OnClick(R.id.tv_Post)
    public void onCreatePost() {
        etPostContent.setError(null);
        if (etPostContent.getText().toString().trim().length() == 0) {
            etPostContent.setError("Please enter some text");
            return;
        } else if (intent.getStringExtra("callBack").equals("2")) {
            progressBar.showProgressBar(Activity_Post.this);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Service().updatePost(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("PostId"), etPostContent.getText().toString(), images.toString());
                }
            }
            ).run();

        } else {
            progressBar.showProgressBar(Activity_Post.this);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String imagesData = method(images.toString());
                    new Service().createPost(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId, etPostContent.getText().toString(), imagesData);
                }
            }
            ).run();
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    public class ImportImages extends AsyncTask<Uri, Void, Void> {

        @Override
        protected Void doInBackground(Uri... uris) {

            for (int i = 0; i < uris.length; i++) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Activity_Post.this.getContentResolver(), uris[i]);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
                    byte[] b = baos.toByteArray();
                    String encodedString = Base64.encodeToString(b, Base64.DEFAULT);
                    images.append(encodedString + ",");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.hideProgressBar();
                }
            });

            return null;
        }
    }

    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}