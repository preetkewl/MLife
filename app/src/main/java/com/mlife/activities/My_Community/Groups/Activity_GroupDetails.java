package com.mlife.activities.My_Community.Groups;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.fragments.Fragment_Members;
import com.mlife.utils.Constants;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectChangeGroupName;
import com.mlife.web.holder.Response.ObjectSetGroupImage;
import com.mlife.web.holder.Response.ObjectViewGroup;
import com.mlife.fragments.Fragment_About;
import com.mlife.fragments.Fragment_Posts;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_GroupDetails extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    Bitmap bitmap;
    private Uri picUri;
    String selectedImagePath;
    PageAdapter pagerAdapter;
    public static String groupId;
    public static String groupName;
    public static String groupType;
    protected static final int PIC_CROP = 3;
    DialogProgressBar progressBar = new DialogProgressBar();
    protected static final int CAMERA_CAPTURE = 0;
    protected static final int GALLERY_PICTURE = 1;
    MahindraClappPreference mahindraClappPreference;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @BindView(R.id.iv_EditBanner)
    ImageView iv_EditBanner;

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;

    @BindView(R.id.iv_GroupBanner)
    ImageView ivGroupBanner;


    @BindView(R.id.tv_GroupName)
    TextView tvGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__group_details);
        ButterKnife.bind(this);
        MultiDex.install(this);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        progressBar.showProgressBar(this);
        objectViewGroup.addObserver(this);
        objectChangeGroupName.addObserver(this);
        objectSetGroupImage.addObserver(this);

        intent = getIntent();
        groupId = intent.getStringExtra("GroupId");
        groupType = intent.getStringExtra("GroupType");
        new Service().viewGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("GroupId"));

        fragments.add(new Fragment_Posts());
        fragments.add(new Fragment_Members());
        fragments.add(new Fragment_About());

        pagerAdapter = new Activity_GroupDetails.PageAdapter(getSupportFragmentManager(), fragments);
        vp_Pager.setAdapter(pagerAdapter);
        vp_Pager.setCurrentItem(0);
        vp_Pager.setOffscreenPageLimit(3);
        tb_Layout.setupWithViewPager(vp_Pager);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectViewGroup) {

            progressBar.hideProgressBar();

            if (objectViewGroup.getViewGroupResponse().getSuccess()) {

                tvGroupName.setText(objectViewGroup.getViewGroupResponse().getData().getName());
                groupName = tvGroupName.getText().toString();
                Picasso.with(this).load(Constants.baseUrl + objectViewGroup.getViewGroupResponse().getData().getImagePath().toString()).placeholder(R.mipmap.placeholdertwo).into(ivGroupBanner);

                if (objectViewGroup.getViewGroupResponse().getData().getIsOwner()) {
                    iv_EditBanner.setVisibility(View.VISIBLE);
                }
            }
        } else if (o instanceof ObjectChangeGroupName) {
            progressBar.hideProgressBar();
                Toast.makeText(this, "Group Name Changed", Toast.LENGTH_SHORT).show();
        } else if (o instanceof ObjectSetGroupImage) {
            progressBar.hideProgressBar();
            if (objectSetGroupImage.getSetGroupImageResponse().getSuccess()) {
                Toast.makeText(this, "Group Banner Changed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, objectSetGroupImage.getSetGroupImageResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        selectedImagePath = null;

        if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE) {
            performCrop();
        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {

                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                selectedImagePath = c.getString(columnIndex);
                c.close();

                try {
                    bitmap = BitmapFactory.decodeFile(selectedImagePath); // load
                    bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

                ivGroupBanner.setImageBitmap(bitmap);
                bitmap = BitmapFactory.decodeFile(selectedImagePath, btmapOptions);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                progressBar.showProgressBar(Activity_GroupDetails.this);

                new Service().setGroupImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage, intent.getStringExtra("GroupId"));

            } else {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == PIC_CROP) {
            Bundle extras = data.getExtras();
            Bitmap thePic = null;
            if (extras != null) {
                thePic = (Bitmap) extras.get("data");
            }
            ivGroupBanner.setImageBitmap(thePic);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            thePic.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            progressBar.showProgressBar(Activity_GroupDetails.this);
            new Service().setGroupImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage, intent.getStringExtra("GroupId"));
        }
    }


    private void performCrop() {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public class PageAdapter extends FragmentPagerAdapter {

        String[] Tabtile = new String[]{"POSTS", "MEMBERS", "ABOUT"};
        List<Fragment> mFragment = new ArrayList<>();

        public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragment = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Tabtile[position];
        }
    }

    private void selectImage() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        try {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
                            File imageFile = new File(imageFilePath);
                            picUri = Uri.fromFile(imageFile); // convert path to Uri
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                            startActivityForResult(takePictureIntent, CAMERA_CAPTURE);
                        } catch (ActivityNotFoundException anfe) {
                            //display an error message
                            String errorMessage = "Whoops - your device doesn't support capturing images!";
                            Toast.makeText(Activity_GroupDetails.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        myAlertDialog.show();
    }

    @OnClick(R.id.iv_BackButton)
    public void backClick() {
        finish();
    }


    @OnClick(R.id.iv_EditBanner)
    public void changeBanner() {
        selectImage();
    }

}