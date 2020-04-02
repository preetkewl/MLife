package com.mlife.activities.Edit_Profile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetProfileDetails;
import com.mlife.web.holder.Response.ObjectLoadProfileSettings;
import com.mlife.web.holder.Response.ObjectSetProfileImage;
import com.mlife.web.holder.Response.ObjectUpdateProfileDetails;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.focpc.mahindralifespaces.utils.MlsApp.getContext;

public class ActivityEditProfile extends AppCompatActivity implements Observer, DataHolder {

    Bitmap bitmap;
    String profile = "0";
    String selectedImagePath;
    protected static final int PIC_CROP = 3;
    DialogProgressBar progressBar = new DialogProgressBar();
    List<String> userIntrest = new ArrayList<>();
    protected static final int CAMERA_CAPTURE = 0;
    List<String> userOccupation = new ArrayList<>();
    protected static final int GALLERY_PICTURE = 1;
    MahindraClappPreference mahindraClappPreference;
    int check = 0;
    final Calendar myCalendar = Calendar.getInstance();

    @BindView(R.id.sw_Profile)
    Switch sw_Profile;

    @BindView(R.id.iv_EditProfilePicture)
    ImageView iv_EditProfilePicture;

    @BindView(R.id.iv_UserImage)
    CircularImageView iv_UserImage;

    @BindView(R.id.tv_UserName)
    TextView tv_UserName;

    @BindView(R.id.et_UserMobileNumber)
    EditText et_UserMobileNumber;

    @BindView(R.id.et_UserUnit)
    EditText et_UserUnit;

    @BindView(R.id.et_UserEmail)
    EditText et_UserEmail;

    @BindView(R.id.et_UserCompany)
    EditText et_UserCompany;

    @BindView(R.id.et_UserDesignation)
    EditText et_UserDesignation;

    @BindView(R.id.et_Education_One)
    EditText et_Education_One;

    @BindView(R.id.et_Education_Two)
    EditText et_Education_Two;

    @BindView(R.id.et_Education_Three)
    EditText et_Education_Three;

    @BindView(R.id.tv_Education_Add_New)
    TextView tv_Education_Add_New;

    @BindView(R.id.sp_UserIntrest)
    Spinner sp_UserIntrest;

    @BindView(R.id.sp_UserOccupation)
    Spinner sp_UserOccupation;

    @BindView(R.id.et_UserDob)
    EditText et_UserDob;

    @BindView(R.id.et_UserMarriageAniversary)
    EditText et_UserMarriageAniversary;

    @BindView(R.id.et_UserLocation)
    EditText et_CompanyLocation;

    @BindView(R.id.et_UserHomeTown)
    EditText et_UserHomeTown;

    @BindView(R.id.et_UserLivesIn)
    EditText et_UserLivesIn;

    @Override
    protected void onStart() {
        super.onStart();
        new Service().getProfileDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit_profile_screen);
        MultiDex.install(this);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        updateView();
        objectSetProfileImage.addObserver(this);
        objectGetProfileDetails.addObserver(this);
        objectUpdateProfileDetails.addObserver(this);
        objectLoadProfileSettings.addObserver(this);
        new Service().loadProfileSettings(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
        progressBar.showProgressBar(this);
    }

    @OnClick(R.id.iv_UserImage)
    public void changeUserProfilePicture() {
        if (checkCameraPermission() && checkWriteExternalPermission()){
            startDialog();
        } else {
            Toast.makeText(this, "Please allow all the permission to application", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.iv_EditProfilePicture)
    public void changeProfilePicture() {
        if (checkCameraPermission() && checkWriteExternalPermission()){
            startDialog();
        } else {
            Toast.makeText(this, "Please allow all the permission to application", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_Education_Add_New)
    public void addNewEducationField() {
        startActivity(new Intent(this, ActivityAddEducation.class).putExtra("Check", "0"));
    }

    private void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(ActivityEditProfile.this);
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

                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_CAPTURE);
                        } catch (ActivityNotFoundException anfe) {
                            //display an error message
                            String errorMessage = "Whoops - your device doesn't support capturing images!";
                            Toast.makeText(ActivityEditProfile.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        myAlertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        selectedImagePath = null;

        if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            iv_UserImage.setImageBitmap(photo);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            progressBar.showProgressBar(ActivityEditProfile.this);
            new Service().setProfileImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage);

//            performCrop();
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

                iv_UserImage.setImageBitmap(bitmap);

                bitmap = BitmapFactory.decodeFile(selectedImagePath, btmapOptions);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.NO_WRAP);

                Log.e("Image",encodedImage);

                progressBar.showProgressBar(ActivityEditProfile.this);
                new Service().setProfileImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage);

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
            iv_UserImage.setImageBitmap(thePic);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            thePic.compress(Bitmap.CompressFormat.JPEG, 40, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            progressBar.showProgressBar(ActivityEditProfile.this);
            new Service().setProfileImage(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", encodedImage);


        }
    }

    @OnClick(R.id.btn_UpdateProfile)
    public void updateProfile() {

        if (sw_Profile.isChecked()) {
            profile = "1";
        } else {
            profile = "0";
        }
        progressBar.showProgressBar(ActivityEditProfile.this);
        new Service().updateProfileDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", profile, et_UserCompany.getText().toString(), et_UserDesignation.getText().toString(), et_UserHomeTown.getText().toString().trim(), et_UserLivesIn.getText().toString().trim(), et_UserDob.getText().toString(), et_UserMarriageAniversary.getText().toString().trim(), sp_UserIntrest.getSelectedItem().toString().trim(), sp_UserOccupation.getSelectedItem().toString().trim(), et_CompanyLocation.getText().toString());
    }

    @OnClick(R.id.et_Education_One)
    public void et_Education_One_Edit() {
        if (et_Education_One.getText().toString().equals("")) {
            startActivity(new Intent(this, ActivityAddEducation.class).putExtra("Check", "0"));
        } else {
            startActivity(new Intent(this, ActivityAddEducation.class)
                    .putExtra("Check", "1")
                    .putExtra("University", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity())
                    .putExtra("Degree", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree())
                    .putExtra("YearFrom", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearFrom())
                    .putExtra("YearTo", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearTo())
                    .putExtra("Id", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getId()));
        }
    }

    @OnClick(R.id.et_Education_Two)
    public void et_Education_Two_Edit() {
        startActivity(new Intent(this, ActivityAddEducation.class)
                .putExtra("Check", "1")
                .putExtra("University", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getUniversity())
                .putExtra("Degree", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getDegree())
                .putExtra("YearFrom", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearFrom())
                .putExtra("YearTo", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearTo())
                .putExtra("Id", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getId()));
    }

    @OnClick(R.id.et_Education_Three)
    public void et_Education_Three_Edit() {
        startActivity(new Intent(this, ActivityAddEducation.class)
                .putExtra("Check", "1")
                .putExtra("University", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getUniversity())
                .putExtra("Degree", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getDegree())
                .putExtra("YearFrom", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getYearFrom())
                .putExtra("YearTo", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getYearTo())
                .putExtra("Id", objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getId()));
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
        finish();
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
        finish();
    }

    @OnClick(R.id.et_UserDob)
    public void updateDob() {
        check = 1;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, 1960, Calendar.MONTH, Calendar.DAY_OF_MONTH);  //date is dateSetListener as per your code in question
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.et_UserMarriageAniversary)
    public void updateAniversary() {
        check = 2;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, 1990, Calendar.MONTH, Calendar.DAY_OF_MONTH);  //date is dateSetListener as per your code in question
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


        //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if(check == 1){et_UserDob.setText(sdf.format(myCalendar.getTime()));}
        else if (check == 2){et_UserMarriageAniversary.setText(sdf.format(myCalendar.getTime()));}
    }

    private boolean checkWriteExternalPermission()
    {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkCameraPermission()
    {
        String permission = Manifest.permission.CAMERA;
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private void updatePref() {
        mahindraClappPreference.setProfileImage(Constants.baseUrl + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getImagePath());
        mahindraClappPreference.setpName(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getName());
        mahindraClappPreference.setpMobile(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getMobile());
        mahindraClappPreference.setpEmail(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEmail());
        mahindraClappPreference.setpHomeTown(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getHomeTown());
        mahindraClappPreference.setpLivesIn(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getLivesIn());
        mahindraClappPreference.setpDOB(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getDateOfBirth());
        mahindraClappPreference.setpAniversary(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getMarriageAnniversary());
        mahindraClappPreference.setpCompany(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getCompanyName());
        mahindraClappPreference.setpRole(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getDesignation());
        mahindraClappPreference.setpCompanyLocation(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getCompanyLocation());
        mahindraClappPreference.setpCompanyType(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getCompanyType());
        mahindraClappPreference.setpIntrest(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getInterest());
    }

    private void updateView() {
        if (!mahindraClappPreference.getProfileImage().isEmpty()) {
            Picasso.with(this).load(mahindraClappPreference.getProfileImage()).placeholder(R.mipmap.placeholdertwo).into(iv_UserImage);
        }
        tv_UserName.setText(mahindraClappPreference.getpName());
        et_UserMobileNumber.setText(mahindraClappPreference.getpMobile());
        et_UserUnit.setText(mahindraClappPreference.getData("ProjectName"));
        et_UserEmail.setText(mahindraClappPreference.getpEmail());
        et_UserHomeTown.setText(mahindraClappPreference.getpHomeTown());
        et_UserLivesIn.setText(mahindraClappPreference.getpLivesIn());
        et_UserDob.setText(mahindraClappPreference.getpDOB());
        et_UserMarriageAniversary.setText(mahindraClappPreference.getpAniversary());
        et_UserCompany.setText(mahindraClappPreference.getpCompany());
        et_UserDesignation.setText(mahindraClappPreference.getpRole());
        et_CompanyLocation.setText(mahindraClappPreference.getpCompanyLocation());
        sp_UserIntrest.setSelection(getPosition(mahindraClappPreference.getpIntrest(), (ArrayList<String>) userIntrest));
        sp_UserOccupation.setSelection(getPosition(mahindraClappPreference.getpCompanyType(), (ArrayList<String>) userOccupation));
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectSetProfileImage) {
            progressBar.hideProgressBar();
            if (objectSetProfileImage.getSetProfileImageResponse().getSuccess()) {
                Toast.makeText(this, objectSetProfileImage.getSetProfileImageResponse().getMessage(), Toast.LENGTH_SHORT).show();
                mahindraClappPreference.setProfileImage(Constants.baseUrl + objectSetProfileImage.getSetProfileImageResponse().getData().getPath());
                Picasso.with(this).load(mahindraClappPreference.getProfileImage()).placeholder(R.mipmap.placeholdertwo).into(iv_UserImage);
            } else {
                Toast.makeText(this, "Something went wrong, Try again later", Toast.LENGTH_SHORT).show();
            }
            objectSetProfileImage.deleteObservers();
        } else if (o instanceof ObjectGetProfileDetails) {
            if (objectGetProfileDetails.getGetProfileDetailsResponse().getSuccess()) {

                updatePref();
                updateView();

                if (objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().size() == 1) {
                    tv_Education_Add_New.setVisibility(View.VISIBLE);
                    et_Education_Two.setVisibility(View.GONE);
                    et_Education_Three.setVisibility(View.GONE);
                    et_Education_Two.setText("");
                    et_Education_Three.setText("");
                    et_Education_One.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearTo());
                } else if (objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().size() == 2) {
                    tv_Education_Add_New.setVisibility(View.VISIBLE);
                    et_Education_Two.setVisibility(View.VISIBLE);
                    et_Education_Three.setVisibility(View.GONE);
                    et_Education_Three.setText("");
                    et_Education_One.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearTo());
                    et_Education_Two.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearTo());
                } else if (objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().size() == 3) {
                    tv_Education_Add_New.setVisibility(View.GONE);
                    et_Education_Two.setVisibility(View.VISIBLE);
                    et_Education_Three.setVisibility(View.VISIBLE);
                    et_Education_One.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(0).getYearTo());
                    et_Education_Two.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(1).getYearTo());
                    et_Education_Three.setText(objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getUniversity() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getDegree() + ", " + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getYearFrom() + "-" + objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getEducation().get(2).getYearTo());
                }

                if (objectGetProfileDetails.getGetProfileDetailsResponse().getData().get(0).getIsPublic().equals("1")) {
                    sw_Profile.setChecked(true);
                    profile = "1";
                } else {
                    sw_Profile.setChecked(false);
                    profile = "0";
                }

                progressBar.hideProgressBar();
            } else {
                progressBar.hideProgressBar();
                Toast.makeText(this, objectGetProfileDetails.getGetProfileDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (o instanceof ObjectUpdateProfileDetails) {
            progressBar.hideProgressBar();
            Toast.makeText(this, objectUpdateProfileDetails.getUpdateProfileDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
        } else if (o instanceof ObjectLoadProfileSettings) {
            try {
                for (int i = 0; i < objectLoadProfileSettings.getLoadProfileSettingsResponse().getData().getInterests().size(); i++) {
                    userIntrest.add(objectLoadProfileSettings.getLoadProfileSettingsResponse().getData().getInterests().get(i).getValue());
                }
                for (int i = 0; i < objectLoadProfileSettings.getLoadProfileSettingsResponse().getData().getOcupation().size(); i++) {
                    userOccupation.add(objectLoadProfileSettings.getLoadProfileSettingsResponse().getData().getOcupation().get(i).getValue());
                }
                ArrayAdapter<String> categoriesAdapter;
                categoriesAdapter = new ArrayAdapter<String>(ActivityEditProfile.this, R.layout.new_spinner_item, userIntrest);
                sp_UserIntrest.setAdapter(categoriesAdapter);
                categoriesAdapter = new ArrayAdapter<String>(ActivityEditProfile.this, R.layout.new_spinner_item, userOccupation);
                sp_UserOccupation.setAdapter(categoriesAdapter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private int getPosition(String item, ArrayList<String> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            if (item.equals(dataList.get(i))) {
                return i;
            }
        }
        return 0;
    }

}