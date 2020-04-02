package com.mlife.activities.My_Community.Groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.ActivityWebView;
import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectCreateGroup;
import com.mlife.web.holder.Response.ObjectLoadGroupTypes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_CreateGroup extends AppCompatActivity implements Observer, DataHolder {

    String callback = "";
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> cat = new ArrayList<>();
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    ArrayList<GetterSetter> groupsList = new ArrayList<>();

    @BindView(R.id.et_GroupName)
    EditText et_groupName;

    @BindView(R.id.et_GroupDescription)
    EditText et_groupDescription;

    @BindView(R.id.cb_terms)
    CheckBox cb_terms;

    @BindView(R.id.sp_GroupStatus)
    Spinner sp_GroupStatus;

    @BindView(R.id.sp_GroupType)
    Spinner sp_groupTypes;

    @OnClick(R.id.tv_TC)
    public void openTerms() {
        startActivity(new Intent(this, ActivityWebView.class).putExtra("Title", "Classified Terms"));
    }

    @OnClick(R.id.btn_CreatGroup)
    public void onClick() {
        Boolean check = true;

        et_groupName.setError(null);
        et_groupDescription.setError(null);

        if (et_groupName.getText().toString().equals("")) {
            et_groupName.setError("Cant be empty");
            et_groupName.requestFocus();
            check = false;
        } else if (et_groupDescription.getText().toString().equals("")) {
            et_groupDescription.setError("Cant be empty");
            et_groupDescription.requestFocus();
            check = false;
        } else if (!cb_terms.isChecked()) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            check = false;
        } else if (check) {
            progressBar.showProgressBar(Activity_CreateGroup.this);
            String groupId = "/";
//                    getType(sp_groupTypes.getSelectedItem().toString());
            String index = String.valueOf(sp_GroupStatus.getSelectedItemPosition());
            new Service().createGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", et_groupName.getText().toString(), groupId, et_groupDescription.getText().toString(), mahindraClappPreference.getData("Project"), index);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__create_group);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        objectLoadGroupTypes.addObserver(this);
        objectCreateGroup.addObserver(this);
        new Service().loadGroupsTypes(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
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

    @Override
    public void update(Observable observable, Object arg) {

        if (observable instanceof ObjectLoadGroupTypes) {

            GetterSetter getterSetter;
            if (objectLoadGroupTypes.getLoadGroupTypesResponse().getSuccess()) {
                for (int i = 0; i < objectLoadGroupTypes.getLoadGroupTypesResponse().getData().size(); i++) {
                    getterSetter = new GetterSetter(objectLoadGroupTypes.getLoadGroupTypesResponse().getData().get(i).getName().toString(), objectLoadGroupTypes.getLoadGroupTypesResponse().getData().get(i).getId().toString());
                    groupsList.add(getterSetter);
                    list.add(objectLoadGroupTypes.getLoadGroupTypesResponse().getData().get(i).getName().toString());
                }
            }
            ArrayAdapter<String> categoriesAdapter;

            categoriesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
            sp_groupTypes.setAdapter(categoriesAdapter);
            cat.add("Private");
            cat.add("Public");
            categoriesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cat);
            sp_GroupStatus.setAdapter(categoriesAdapter);

        } else if (observable instanceof ObjectCreateGroup) {
            progressBar.hideProgressBar();
            if (objectCreateGroup.getCreateGroupResponse().getSuccess()) {
                startActivity(new Intent(this, Activity_ThankYou.class).putExtra("Title", "Post new classified").putExtra("Heading", "Thank You").putExtra("Detail", "We have recived your request, It will come live shortly.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", callback));
                finish();
            } else {
                Toast.makeText(this, objectCreateGroup.getCreateGroupResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        observable.deleteObservers();
    }

    public class GetterSetter {

        String Name, Id;

        public GetterSetter(String name, String id) {
            Name = name;
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }

    public String getType(String groupName) {

        String check = "";
        for (int i = 0; i < groupsList.size(); i++) {
            if (groupsList.get(i).getName().equals(groupName)) {
                check = groupsList.get(i).getId().toString();
            }
        }
        return check;
    }
}