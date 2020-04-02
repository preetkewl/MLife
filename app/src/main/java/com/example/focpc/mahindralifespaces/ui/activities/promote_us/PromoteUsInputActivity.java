package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.mlife.R;
import com.example.focpc.mahindralifespaces.ui.activities.promote_us_contact.PromoteContactActivity;
import com.example.focpc.mahindralifespaces.utils.MlsBaseActivty;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.List;
import java.util.Locale;


public class PromoteUsInputActivity extends MlsBaseActivty implements View.OnClickListener, PromoteUsView {
    private EditText instNameET, contNameET, contMobNumET, commentsET, designationET, cityET;
    private String instType = "";
    private PromoteUsPresenter promoteUsPresenter;
    private Toolbar promoteUsTitleCard;
    private Geocoder mGeocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_input);
        FrameLayout attachWrapper = findViewById(R.id.attachWrapper);
        FrameLayout instiWrapper = findViewById(R.id.instiWrapper);
        Button submitBtn = findViewById(R.id.submitBtn);
        instNameET = findViewById(R.id.instNameET);
        contNameET = findViewById(R.id.contNameET);
        contMobNumET = findViewById(R.id.contMobNumET);
        commentsET = findViewById(R.id.commentsET);
        designationET = findViewById(R.id.designationET);
        cityET = findViewById(R.id.cityET);

        mGeocoder = new Geocoder(this, Locale.getDefault());


        if (getIntent() != null && getIntent().hasExtra(MlsConstants.INSTITUTION_TYPE))
            instType = getIntent().getStringExtra(MlsConstants.INSTITUTION_TYPE);

        attachWrapper.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        instiWrapper.setOnClickListener(this);

        promoteUsPresenter = new PromoteUsPresenterImple(this);
        initToolbar();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attachWrapper:
                Intent intent = new Intent(this, PromoteContactActivity.class);
                startActivityForResult(intent, MlsConstants.CONTACT_PICK_REQUEST);
                break;
            case R.id.instiWrapper:
                if (isNetworkAvailable()) {
                    findPlace(instNameET);
                } else {
                    MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
                }
                break;

            case R.id.submitBtn:
                String instName = instNameET.getText().toString().trim();
                String contName = contNameET.getText().toString().trim();
                String contNum = contMobNumET.getText().toString().trim();
                String design = designationET.getText().toString().trim();
                String comment = commentsET.getText().toString().trim();
                String city = cityET.getText().toString().trim();
                if (TextUtils.isEmpty(instName)) {
                    setEtError(instNameET, "Please enter institution name");
                } else if (TextUtils.isEmpty(city)) {
                    setEtError(cityET, "Please enter your city");
                } else if (TextUtils.isEmpty(contName)) {
                    setEtError(contNameET, "Please enter person's name");
                } else if (TextUtils.isEmpty(contNum)) {
                    setEtError(contMobNumET, "Please enter mobile number");
                } else if (contNum.length() != 10) {
                    setEtError(contMobNumET, "Please enter 10 digit mobile number");
                } else if (TextUtils.isEmpty(design)) {
                    setEtError(designationET, "Please enter designation");
                } else if (TextUtils.isEmpty(comment)) {
                    setEtError(commentsET, "Please enter comment");
                } else {
                    if (isNetworkAvailable()) {
                        promoteUsPresenter.promoteContact(String.valueOf(MlsUtils.getUserId(this)), design, comment, contNum,
                                contName, "", instType, instName, city);
                    } else {
                        MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
                    }
                }
                break;
            case R.id.attachIV:
                break;

        }
    }

    private void setEtError(EditText editText, String error) {
        if (editText != instNameET) instNameET.setError(null);
        if (editText != contNameET) contNameET.setError(null);
        if (editText != contMobNumET) contMobNumET.setError(null);
        if (editText != designationET) designationET.setError(null);
        if (editText != commentsET) commentsET.setError(null);
        if (editText != cityET) cityET.setError(null);
        editText.setError(error);
        editText.requestFocus();

    }


    @Override
    public void onContactPromoted() {
        MlsUtils.showToast(this, "Selected contact added to promote list", true);
        this.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_n_translate);

    }

    @Override
    public void onPromoteFailed(String message) {
        MlsUtils.showToast(this, message, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && data.hasExtra(MlsConstants.PICKED_NAME) &&
                data.hasExtra(MlsConstants.PICKED_NUMBER)) {
            contNameET.setText(data.getStringExtra(MlsConstants.PICKED_NAME));
            contMobNumET.setText(data.getStringExtra(MlsConstants.PICKED_NUMBER));

        } else if (data != null && requestCode == MlsConstants.PLACES_PICK_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                instNameET.setText(place.getName());
                String city = getCityNameByCoordinates(place.getLatLng().latitude, place.getLatLng().longitude);
                if (city != null) {
                    cityET.setText(city);
                    cityET.requestFocus();
                    cityET.setSelection(city.length());
                } else {
                    instNameET.requestFocus();
                    instNameET.setSelection(place.getName().length());
                }
            }
//            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_n_translate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_n_translate);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initToolbar() {
        promoteUsTitleCard = findViewById(R.id.promoteUsTitleCard);
        setSupportActionBar(promoteUsTitleCard);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        TextView dialogTitleTV = findViewById(R.id.dialogTitleTV);
        dialogTitleTV.setText(instType);
    }

    public void findPlace(View view) {
        try {
            AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(Place.TYPE_COUNTRY )
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                    .setCountry("IN")
                    .build();
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(autocompleteFilter)
                            .build(this);
            startActivityForResult(intent, MlsConstants.PLACES_PICK_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            MlsUtils.showToast(this, e.getMessage(), true);
        } catch (GooglePlayServicesNotAvailableException e) {
            MlsUtils.showToast(this, "Sorry, Google play services not available", true);
        }
    }

    private String getCityNameByCoordinates(double lat, double lon) {
        try {
            List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
            if (addresses != null && addresses.size() > 0) {
                return addresses.get(0).getLocality();
            }
        } catch (Exception e) {

        }
        return null;
    }

}
