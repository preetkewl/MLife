package com.mlife.web.api;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.google.gson.JsonObject;
import com.mlife.fragments.FirstFragment;
import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.web.model.AppVersion;
import com.mlife.web.model.ChangeBookingStatusResponse;
import com.mlife.web.model.ChangeGroupDetailsResponse;
import com.mlife.web.model.CreatePostResponse;
import com.mlife.web.model.DeleteProfileEducationDetailsResponse;
import com.mlife.web.model.Example;
import com.mlife.web.model.ForgotPasswordResponse;
import com.mlife.web.model.ForgotPasswordVerifyResponse;
import com.mlife.web.model.GetAvailableTimeSlotResponse;
import com.mlife.web.model.GetKeyResponse;
import com.mlife.web.model.GetProfileDetailsResponse;
import com.mlife.web.model.GetTimeSlotResponse;
import com.mlife.web.model.LikePostResponse;
import com.mlife.web.model.LoadProfileSettingsResponse;
import com.mlife.web.model.LoadVASSettingsResponse;
import com.mlife.web.model.LogoutResponse;
import com.mlife.web.model.NoticeListResponse;
import com.mlife.web.model.ProjectListResponse;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.model.AcceptEventResponse;
import com.mlife.web.model.AddCallBackResponse;
import com.mlife.web.model.AddClassifiedResponse;
import com.mlife.web.model.AddCommentsResponse;
import com.mlife.web.model.AddReportResponse;
import com.mlife.web.model.AddServiceRequestResponse;
import com.mlife.web.model.AddTicketResponse;
import com.mlife.web.model.AddUserVasResponse;
import com.mlife.web.model.AddedValueListResponse;
import com.mlife.web.model.AllPropertyResponse;
import com.mlife.web.model.AmentieeListResponse;
import com.mlife.web.model.BookClubhouseResponse;
import com.mlife.web.model.CancelSiteVisitResponse;
import com.mlife.web.model.ChangeGroupAboutResponse;
import com.mlife.web.model.ChangeGroupNameResponse;
import com.mlife.web.model.ClubHouseListResponse;
import com.mlife.web.model.CommitteeManagmentListResponse;
import com.mlife.web.model.ConstructionResponse;
import com.mlife.web.model.CreateGroupResponse;
import com.mlife.web.model.DeletePostResponse;
import com.mlife.web.model.EventListResponse;
import com.mlife.web.model.ExporeGroupsResponse;
import com.mlife.web.model.GetDownloadDocumentsResponse;
import com.mlife.web.model.GetPaymentDetailsResponse;
import com.mlife.web.model.GetPostPaymentDetailsResponse;
import com.mlife.web.model.GetPostsResponse;
import com.mlife.web.model.GetProductResponse;
import com.mlife.web.model.GetProjectDetailResponse;
import com.mlife.web.model.GetServiceRequestsResponse;
import com.mlife.web.model.GroupAboutbyGroupResponse;
import com.mlife.web.model.HomeBannerListResponse;
import com.mlife.web.model.JoinGroupResponse;
import com.mlife.web.model.JoinedGroupsResponse;
import com.mlife.web.model.LoadCommentsResponse;
import com.mlife.web.model.LoadDocumentsResponse;
import com.mlife.web.model.LoadGroupTypesResponse;
import com.mlife.web.model.LoadSiteVisitsResponse;
import com.mlife.web.model.LoginResponse;
import com.mlife.web.model.MPaymentResponse;
import com.mlife.web.model.MembersbyGroupResponse;
import com.mlife.web.model.MyGroupsResponse;
import com.mlife.web.model.NotificationListResponse;
import com.mlife.web.model.OfferListResponse;
import com.mlife.web.model.ProjectAmenitiesResponse;
import com.mlife.web.model.ProjectConstructionUpdateResponse;
import com.mlife.web.model.ProjectGalleryResponse;
import com.mlife.web.model.ProjectLocationResponse;
import com.mlife.web.model.ProjectOffersResponse;
import com.mlife.web.model.PropertyDetailResponse;
import com.mlife.web.model.PropertyResponse;
import com.mlife.web.model.RemoveOfferResponse;
import com.mlife.web.model.ResidentListResponse;
import com.mlife.web.model.SaveVASRequirementsResponse;
import com.mlife.web.model.SendNewEnquiryResponse;
import com.mlife.web.model.SetGroupImageResponse;
import com.mlife.web.model.SetProfileImageResponse;
import com.mlife.web.model.SetSiteVisitDateResponse;
import com.mlife.web.model.SubmitFeedbackResponse;
import com.mlife.web.model.TicketListResponse;
import com.mlife.web.model.TicketLogsResponse;
import com.mlife.web.model.UpdateGroupJoinRequestResponse;
import com.mlife.web.model.UpdatePostResponse;
import com.mlife.web.model.UpdateProfileDetailsResponse;
import com.mlife.web.model.UpdateProfileEducationResponse;
import com.mlife.web.model.UserData;
import com.mlife.web.model.ViewGroupResponse;
import com.mlife.web.model.ViewProfileDetailsResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mlife.web.holder.DataHolder.oObjectLoginOTPResponse;
import static com.mlife.web.holder.DataHolder.objectAcceptEvent;
import static com.mlife.web.holder.DataHolder.objectAddCallBack;
import static com.mlife.web.holder.DataHolder.objectAddClassified;
import static com.mlife.web.holder.DataHolder.objectAddComments;
import static com.mlife.web.holder.DataHolder.objectAddReport;
import static com.mlife.web.holder.DataHolder.objectAddServiceRequest;
import static com.mlife.web.holder.DataHolder.objectAddTicket;
import static com.mlife.web.holder.DataHolder.objectAddUserVas;
import static com.mlife.web.holder.DataHolder.objectAddedValueListInterior;
import static com.mlife.web.holder.DataHolder.objectAllProperty;
import static com.mlife.web.holder.DataHolder.objectAmentieeList;
import static com.mlife.web.holder.DataHolder.objectAppVersion;
import static com.mlife.web.holder.DataHolder.objectBookClubhouse;
import static com.mlife.web.holder.DataHolder.objectCancelSiteVisit;
import static com.mlife.web.holder.DataHolder.objectChangeBookingStatus;
import static com.mlife.web.holder.DataHolder.objectChangeGroupAbout;
import static com.mlife.web.holder.DataHolder.objectChangeGroupDetails;
import static com.mlife.web.holder.DataHolder.objectChangeGroupName;
import static com.mlife.web.holder.DataHolder.objectClubHouseList;
import static com.mlife.web.holder.DataHolder.objectCommitteeManagmentList;
import static com.mlife.web.holder.DataHolder.objectConstructionUpdatesResponse;
import static com.mlife.web.holder.DataHolder.objectCreateGroup;
import static com.mlife.web.holder.DataHolder.objectCreatePost;
import static com.mlife.web.holder.DataHolder.objectDeletePost;
import static com.mlife.web.holder.DataHolder.objectDeleteProfileEducationDetails;
import static com.mlife.web.holder.DataHolder.objectEventList;
import static com.mlife.web.holder.DataHolder.objectExploreGroups;
import static com.mlife.web.holder.DataHolder.objectForgotPassword;
import static com.mlife.web.holder.DataHolder.objectForgotPasswordVerify;
import static com.mlife.web.holder.DataHolder.objectGetAvailableTimeSlot;
import static com.mlife.web.holder.DataHolder.objectGetDownloadDocuments;
import static com.mlife.web.holder.DataHolder.objectGetKey;
import static com.mlife.web.holder.DataHolder.objectGetPaymentDetails;
import static com.mlife.web.holder.DataHolder.objectGetPostPaymentDetails;
import static com.mlife.web.holder.DataHolder.objectGetPosts;
import static com.mlife.web.holder.DataHolder.objectGetProduct;
import static com.mlife.web.holder.DataHolder.objectGetProfileDetails;
import static com.mlife.web.holder.DataHolder.objectGetProjectDetail;
import static com.mlife.web.holder.DataHolder.objectGetServiceRequests;
import static com.mlife.web.holder.DataHolder.objectGetTimeSlot;
import static com.mlife.web.holder.DataHolder.objectGroupAboutbyGroup;
import static com.mlife.web.holder.DataHolder.objectHomeBannerList;
import static com.mlife.web.holder.DataHolder.objectJoinGroup;
import static com.mlife.web.holder.DataHolder.objectJoinedGrops;
import static com.mlife.web.holder.DataHolder.objectKeyLocation;
import static com.mlife.web.holder.DataHolder.objectLikePost;
import static com.mlife.web.holder.DataHolder.objectLoadComments;
import static com.mlife.web.holder.DataHolder.objectLoadDocuments;
import static com.mlife.web.holder.DataHolder.objectLoadGroupTypes;
import static com.mlife.web.holder.DataHolder.objectLoadProfileSettings;
import static com.mlife.web.holder.DataHolder.objectLoadSiteVisits;
import static com.mlife.web.holder.DataHolder.objectLoadVASSettings;
import static com.mlife.web.holder.DataHolder.objectLoginResponse;
import static com.mlife.web.holder.DataHolder.objectLogout;
import static com.mlife.web.holder.DataHolder.objectMembersbyGroup;
import static com.mlife.web.holder.DataHolder.objectMyGroups;
import static com.mlife.web.holder.DataHolder.objectMyOfferList;
import static com.mlife.web.holder.DataHolder.objectNoticeList;
import static com.mlife.web.holder.DataHolder.objectNotificationList;
import static com.mlife.web.holder.DataHolder.objectOfferListAdmin;
import static com.mlife.web.holder.DataHolder.objectOfferListRent;
import static com.mlife.web.holder.DataHolder.objectOfferListSell;
import static com.mlife.web.holder.DataHolder.objectPaymentResponse;
import static com.mlife.web.holder.DataHolder.objectProjectAmenities;
import static com.mlife.web.holder.DataHolder.objectProjectConstructionUpdate;
import static com.mlife.web.holder.DataHolder.objectProjectGallery;
import static com.mlife.web.holder.DataHolder.objectProjectList;
import static com.mlife.web.holder.DataHolder.objectProjectLocation;
import static com.mlife.web.holder.DataHolder.objectProjectOffers;
import static com.mlife.web.holder.DataHolder.objectPropertyDetailsResponse;
import static com.mlife.web.holder.DataHolder.objectPropertyResponse;
import static com.mlife.web.holder.DataHolder.objectRemoveOffer;
import static com.mlife.web.holder.DataHolder.objectResidentList;
import static com.mlife.web.holder.DataHolder.objectSaveVASRequirements;
import static com.mlife.web.holder.DataHolder.objectSendNewEnquiry;
import static com.mlife.web.holder.DataHolder.objectSetGroupImage;
import static com.mlife.web.holder.DataHolder.objectSetProfileImage;
import static com.mlife.web.holder.DataHolder.objectSetSiteVisitDate;
import static com.mlife.web.holder.DataHolder.objectSubmitFeedback;
import static com.mlife.web.holder.DataHolder.objectTicketListEnquiries;
import static com.mlife.web.holder.DataHolder.objectTicketLogs;
import static com.mlife.web.holder.DataHolder.objectUpdateGroupJoinRequest;
import static com.mlife.web.holder.DataHolder.objectUpdatePost;
import static com.mlife.web.holder.DataHolder.objectUpdateProfileDetails;
import static com.mlife.web.holder.DataHolder.objectUpdateProfileEducation;
import static com.mlife.web.holder.DataHolder.objectViewGroup;
import static com.mlife.web.holder.DataHolder.objectViewProfileDetails;
import static com.mlife.web.holder.DataHolder.ooObjectLoginOTPResponse;

public class Service {

    static String cookie;

    Gson gson = new GsonBuilder().setLenient().create();

    CertificatePinner certificatePinner = new CertificatePinner.Builder().add(Constants.baseUrl, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=").build();

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().certificatePinner(certificatePinner)
            .connectTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.baseUrl)
            .client(okHttpClient).build();


    public void appVersion(String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AppVersion> call = serviceInterface.appversion(deviceType);
        call.enqueue(new Callback<AppVersion>() {
            @Override
            public void onResponse(Call<AppVersion> call, Response<AppVersion> response) {
                if (response.isSuccessful()) {
                    objectAppVersion.setAppVersion(response.body());
                } else {
                    AppVersion appVersion = new AppVersion();
                    appVersion.setVal("");
                    objectAppVersion.setAppVersion(appVersion);
                }
            }

            @Override
            public void onFailure(Call<AppVersion> call, Throwable t) {
                AppVersion appVersion = new AppVersion();
                appVersion.setVal("");
                objectAppVersion.setAppVersion(appVersion);
            }
        });
    }

    public void loginOTP(String email, String token, String type) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<Example> call = serviceInterface.loginWithOTP(email, token, type);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                cookie = response.headers().get("Set-Cookie");


                if (response.isSuccessful()) {
                    oObjectLoginOTPResponse.setLoginResponse(response.body());
                    ooObjectLoginOTPResponse.setLoginResponse(response.body());

                } else {
                    Example loginResponse = new Example();
                    loginResponse.setSuccess(false);
                    loginResponse.setMessage("Something went wrong, Try again later");
                    loginResponse.setAction("");
                    oObjectLoginOTPResponse.setLoginResponse(loginResponse);
                    ooObjectLoginOTPResponse.setLoginResponse(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
                Example loginResponse = new Example();
                loginResponse.setSuccess(false);
                loginResponse.setMessage("Something went wrong, Try again later");
                loginResponse.setAction("");
                oObjectLoginOTPResponse.setLoginResponse(loginResponse);
                ooObjectLoginOTPResponse.setLoginResponse(loginResponse);
            }
        });
    }

    public void verifyOTP(String otp, String token, String deviceToken, String deviceType, String email) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ResponseBody> call = serviceInterface.loginOTP(cookie, otp, token, deviceToken, deviceType, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    UserData userData = new UserData();
                    JSONObject jsonObject = new JSONObject(response.body().string());

                    LoginResponse loginResponse = new LoginResponse();
                    if (jsonObject.getBoolean("success")){
                        loginResponse.setSuccess(jsonObject.getBoolean("success"));

                        userData.setUserFullName(jsonObject.getJSONObject("data").getString("userFullName"));
                        userData.setUserEmail(jsonObject.getJSONObject("data").getString("userEmail"));
                        userData.setUserId(jsonObject.getJSONObject("data").getString("userId"));
                        userData.setToken(jsonObject.getJSONObject("data").getString("token"));
                        userData.setMobile(jsonObject.getJSONObject("data").getString("mobile"));
                        userData.setImagePath(jsonObject.getJSONObject("data").getString("imagePath"));

                        loginResponse.setData(userData);
                        loginResponse.setMessage(jsonObject.getString("message"));
                    } else {
                        loginResponse.setSuccess(jsonObject.getBoolean("success"));
                        loginResponse.setMessage(jsonObject.getString("message"));
                        loginResponse.setDataKey(jsonObject.getString("data"));
                        loginResponse.setAction(jsonObject.getString("action"));
                    }

                        objectLoginResponse.setLoginResponse(loginResponse);

                } catch (Exception e){
                    e.printStackTrace();
                }


//                if (response.isSuccessful()) {
//                    LoginResponse loginResponse = new LoginResponse();
//                    try {
//                        loginResponse.setSuccess(jsonObject.getBoolean("success"));
//                        loginResponse.setData((UserData) jsonObject.get("data"));
//                        loginResponse.setMessage(jsonObject.getString("message"));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
////                    loginResponse.setMessage(response.message());
////                    loginResponse.setAction("");
//                    objectLoginResponse.setLoginResponse(loginResponse);
//
//
////                    objectLoginResponse.setLoginResponse(response.body());
////                    cookie = response.headers().get("Set-Cookie");
//                    Log.e("Cookie", cookie);
//                } else {
//                    LoginResponse loginResponse = new LoginResponse();
////                    loginResponse.setSuccess(false);
////                    loginResponse.setMessage(response.message());
////                    loginResponse.setAction("");
//                    objectLoginResponse.setLoginResponse(loginResponse);
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(false);
                loginResponse.setMessage("Something went wrong, Try again Later!");
                loginResponse.setAction("");
                objectLoginResponse.setLoginResponse(loginResponse);
            }
        });
    }


    public void login(String login, String password, String deviceToken, String deviceType, String version, String response) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoginResponse> call = serviceInterface.login(cookie, login, password, deviceToken, deviceType, "v3", response);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                if (response.isSuccessful()) {
                    objectLoginResponse.setLoginResponse(response.body());
                } else {
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setSuccess(false);
                    loginResponse.setMessage("Something went wrong, Try again later");
                    loginResponse.setAction("");
                    objectLoginResponse.setLoginResponse(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(false);
                loginResponse.setMessage("Something went wrong, Try again later");
                loginResponse.setAction("");
                objectLoginResponse.setLoginResponse(loginResponse);
            }
        });
    }

    public void forgotPassword_verify(String emailId, String deviceToken, String version, String deviceType, String otp) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ForgotPasswordVerifyResponse> call = serviceInterface.forgotPassword_verify(emailId, deviceToken, "v3", deviceType, otp);
        call.enqueue(new Callback<ForgotPasswordVerifyResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordVerifyResponse> call, Response<ForgotPasswordVerifyResponse> response) {
                if (response.isSuccessful()) {
                    objectForgotPasswordVerify.setForgotPasswordVerifyResponse(response.body());
                } else {
                    ForgotPasswordVerifyResponse forgotPasswordVerifyResponse = new ForgotPasswordVerifyResponse();
                    forgotPasswordVerifyResponse.setSuccess(false);
                    forgotPasswordVerifyResponse.setMessage("Something went wrong,Try again later.");
                    objectForgotPasswordVerify.setForgotPasswordVerifyResponse(forgotPasswordVerifyResponse);
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordVerifyResponse> call, Throwable t) {
                ForgotPasswordVerifyResponse forgotPasswordVerifyResponse = new ForgotPasswordVerifyResponse();
                forgotPasswordVerifyResponse.setSuccess(false);
                forgotPasswordVerifyResponse.setMessage("Something went wrong,Try again later.");
                objectForgotPasswordVerify.setForgotPasswordVerifyResponse(forgotPasswordVerifyResponse);
            }
        });

    }

    public void getKeys(String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ResponseBody> call = serviceInterface.getKeys(deviceToken, deviceType);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    cookie = response.headers().get("Set-Cookie");
                    GetKeyResponse staff = null;
                    try {
                        staff = gson.fromJson(response.body().string(), GetKeyResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    objectGetKey.setGetKeyResponse(staff);
                } else {
                    GetKeyResponse getKeyResponse = new GetKeyResponse();
                    getKeyResponse.setSuccess(false);
                    getKeyResponse.setMessage("Something went wrong, Try again later");
                    objectGetKey.setGetKeyResponse(getKeyResponse);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                GetKeyResponse getKeyResponse = new GetKeyResponse();
                getKeyResponse.setSuccess(false);
                getKeyResponse.setMessage("Something went wrong, Try again later");
                objectGetKey.setGetKeyResponse(getKeyResponse);
            }
        });
    }

    public void logout(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LogoutResponse> call = serviceInterface.logout(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    objectLogout.setLogoutResponse(response.body());
                } else {
                    LogoutResponse logoutResponse = new LogoutResponse();
                    logoutResponse.setSuccess(false);
                    logoutResponse.setAction("");
                    logoutResponse.setMessage("Something went wrong, Try again later");
                    objectLogout.setLogoutResponse(logoutResponse);
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                LogoutResponse logoutResponse = new LogoutResponse();
                logoutResponse.setSuccess(false);
                logoutResponse.setAction("");
                logoutResponse.setMessage("Something went wrong, Try again later");
                objectLogout.setLogoutResponse(logoutResponse);
            }
        });


    }

    public void getPropertyList(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<PropertyResponse> call = serviceInterface.getProperty(userId, token, deviceToken, deviceType);

        call.enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {
                if (response.isSuccessful()) {
                    objectPropertyResponse.setPropertyResponse(response.body());
                } else {
                    PropertyResponse propertyResponse = new PropertyResponse();
                    propertyResponse.setSuccess(false);
                    propertyResponse.setAction("");
                    propertyResponse.setMessage("Something went wrong, Try again later");
                    objectPropertyResponse.setPropertyResponse(propertyResponse);
                }
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                PropertyResponse propertyResponse = new PropertyResponse();
                propertyResponse.setSuccess(false);
                propertyResponse.setAction("");
                propertyResponse.setMessage("Something went wrong, Try again later");
                objectPropertyResponse.setPropertyResponse(propertyResponse);
            }
        });
    }

    public void getPropertyDetail(String userId, String token, String deviceToken, String deviceType, String pId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<PropertyDetailResponse> call = serviceInterface.getPropertyDetais(userId, token, deviceToken, deviceType, pId);
        call.enqueue(new Callback<PropertyDetailResponse>() {
            @Override
            public void onResponse(Call<PropertyDetailResponse> call, Response<PropertyDetailResponse> response) {
                if (response.isSuccessful()) {
                    objectPropertyDetailsResponse.setPropertyDetailResponse(response.body());
                } else {
                    PropertyDetailResponse propertyDetailResponse = new PropertyDetailResponse();
                    propertyDetailResponse.setSuccess(false);
                    propertyDetailResponse.setMessage("Something went wrong, Try again later");
                    propertyDetailResponse.setAction("");
                    objectPropertyDetailsResponse.setPropertyDetailResponse(propertyDetailResponse);
                }
            }

            @Override
            public void onFailure(Call<PropertyDetailResponse> call, Throwable t) {
                PropertyDetailResponse propertyDetailResponse = new PropertyDetailResponse();
                propertyDetailResponse.setSuccess(false);
                propertyDetailResponse.setAction("");
                propertyDetailResponse.setMessage("Something went wrong, Try again later");
                objectPropertyDetailsResponse.setPropertyDetailResponse(propertyDetailResponse);
            }
        });
    }

    public void getConstructionUpdates(String userId, String token, String deviceToken, String deviceType, String pId, String method) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ConstructionResponse> call = serviceInterface.getConstructionUpdates(userId, token, deviceToken, deviceType, pId, method);
        call.enqueue(new Callback<ConstructionResponse>() {
            @Override
            public void onResponse(Call<ConstructionResponse> call, Response<ConstructionResponse> response) {
                if (response.isSuccessful()) {
                    objectConstructionUpdatesResponse.setConstructionResponse(response.body());
                } else {
                    ConstructionResponse constructionResponse = new ConstructionResponse();
                    constructionResponse.setSuccess(false);
                    constructionResponse.setMessage("Something went wrong, Try again later");
                    constructionResponse.setAction("");
                    objectConstructionUpdatesResponse.setConstructionResponse(constructionResponse);
                }
            }

            @Override
            public void onFailure(Call<ConstructionResponse> call, Throwable t) {
                ConstructionResponse constructionResponse = new ConstructionResponse();
                constructionResponse.setSuccess(false);
                constructionResponse.setMessage("Something went wrong, Try again later");
                constructionResponse.setAction("");
                objectConstructionUpdatesResponse.setConstructionResponse(constructionResponse);
            }
        });
    }

    public void getAllProperties(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AllPropertyResponse> call = serviceInterface.getAllProperties(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<AllPropertyResponse>() {
            @Override
            public void onResponse(Call<AllPropertyResponse> call, Response<AllPropertyResponse> response) {
                if (response.isSuccessful()) {
                    objectAllProperty.setAllPropertyResponse(response.body());
                } else {
                    AllPropertyResponse allPropertyResponse = new AllPropertyResponse();
                    allPropertyResponse.setSuccess(false);
                    allPropertyResponse.setMessage("Something went wrong, Try again later");
                    allPropertyResponse.setAction("");
                    objectAllProperty.setAllPropertyResponse(allPropertyResponse);
                }
            }

            @Override
            public void onFailure(Call<AllPropertyResponse> call, Throwable t) {
                AllPropertyResponse allPropertyResponse = new AllPropertyResponse();
                allPropertyResponse.setSuccess(false);
                allPropertyResponse.setAction("");
                allPropertyResponse.setMessage("Something went wrong, Try again later");
                objectAllProperty.setAllPropertyResponse(allPropertyResponse);
            }
        });
    }

    public void scheduleVisit(String userId, String token, String deviceToken, String deviceType, String date, String timeSlot,
                              String property, String projectId, String pId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SetSiteVisitDateResponse> call = serviceInterface.scheduleSiteVisit(userId, token,
                deviceToken, deviceType, date, timeSlot, property, projectId, pId);
        call.enqueue(new Callback<SetSiteVisitDateResponse>() {
            @Override
            public void onResponse(Call<SetSiteVisitDateResponse> call, Response<SetSiteVisitDateResponse> response) {
                if (response.isSuccessful()) {
                    objectSetSiteVisitDate.setSetSiteVisitDateResponse(response.body());
                } else {
                    SetSiteVisitDateResponse setSiteVisitDateResponse = new SetSiteVisitDateResponse();
                    setSiteVisitDateResponse.setSuccess(false);
                    setSiteVisitDateResponse.setMessage("Something went wrong, Try again later");
                    setSiteVisitDateResponse.setAction("");
                    objectSetSiteVisitDate.setSetSiteVisitDateResponse(setSiteVisitDateResponse);
                }
            }

            @Override
            public void onFailure(Call<SetSiteVisitDateResponse> call, Throwable t) {
                SetSiteVisitDateResponse setSiteVisitDateResponse = new SetSiteVisitDateResponse();
                setSiteVisitDateResponse.setSuccess(false);
                setSiteVisitDateResponse.setMessage("Something went wrong, Try again later");
                setSiteVisitDateResponse.setAction("");
                objectSetSiteVisitDate.setSetSiteVisitDateResponse(setSiteVisitDateResponse);
            }
        });
    }

    public void loadScheduleVisit(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadSiteVisitsResponse> call = serviceInterface.siteVisits(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<LoadSiteVisitsResponse>() {
            @Override
            public void onResponse(Call<LoadSiteVisitsResponse> call, Response<LoadSiteVisitsResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadSiteVisits.setLoadSiteVisitsResponse(response.body());
                } else {
                    LoadSiteVisitsResponse loadSiteVisitsResponse = new LoadSiteVisitsResponse();
                    loadSiteVisitsResponse.setSuccess(false);
                    loadSiteVisitsResponse.setMessage("Something went wrong, Try again later");
                    loadSiteVisitsResponse.setAction("");
                    objectLoadSiteVisits.setLoadSiteVisitsResponse(loadSiteVisitsResponse);
                }
            }

            @Override
            public void onFailure(Call<LoadSiteVisitsResponse> call, Throwable t) {
                LoadSiteVisitsResponse loadSiteVisitsResponse = new LoadSiteVisitsResponse();
                loadSiteVisitsResponse.setSuccess(false);
                loadSiteVisitsResponse.setMessage("Something went wrong, Try again later");
                loadSiteVisitsResponse.setAction("");
                objectLoadSiteVisits.setLoadSiteVisitsResponse(loadSiteVisitsResponse);
            }
        });
    }

    public void loadGroupsTypes(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadGroupTypesResponse> call = serviceInterface.groupsTypes(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<LoadGroupTypesResponse>() {
            @Override
            public void onResponse(Call<LoadGroupTypesResponse> call, Response<LoadGroupTypesResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadGroupTypes.setLoadGroupTypesResponse(response.body());
                } else {
                    LoadGroupTypesResponse loadGroupTypesResponse = new LoadGroupTypesResponse();
                    loadGroupTypesResponse.setSuccess(false);
                    loadGroupTypesResponse.setMessage("Something went wrong, Try again later");
                    loadGroupTypesResponse.setAction("");
                    objectLoadGroupTypes.setLoadGroupTypesResponse(loadGroupTypesResponse);
                }
            }

            @Override
            public void onFailure(Call<LoadGroupTypesResponse> call, Throwable t) {
                LoadGroupTypesResponse loadGroupTypesResponse = new LoadGroupTypesResponse();
                loadGroupTypesResponse.setSuccess(false);
                loadGroupTypesResponse.setMessage("Something went wrong, Try again later");
                loadGroupTypesResponse.setAction("");
                objectLoadGroupTypes.setLoadGroupTypesResponse(loadGroupTypesResponse);
            }
        });
    }

    public void createGroup(String userId, String token, String deviceToken, String deviceType, String groupName, String groupType,
                            String description, String projectId, String isPublic) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<CreateGroupResponse> call = serviceInterface.createGroup(userId, token, deviceToken,
                deviceType, groupName, groupType, description, projectId, isPublic);
        call.enqueue(new Callback<CreateGroupResponse>() {
            @Override
            public void onResponse(Call<CreateGroupResponse> call, Response<CreateGroupResponse> response) {
                if (response.isSuccessful()) {
                    objectCreateGroup.setCreateGroupResponse(response.body());
                } else {
                    CreateGroupResponse createGroupResponse = new CreateGroupResponse();
                    createGroupResponse.setSuccess(false);
                    createGroupResponse.setMessage("Try again later");
                    createGroupResponse.setAction("");
                    objectCreateGroup.setCreateGroupResponse(createGroupResponse);
                }
            }

            @Override
            public void onFailure(Call<CreateGroupResponse> call, Throwable t) {
                CreateGroupResponse createGroupResponse = new CreateGroupResponse();
                createGroupResponse.setSuccess(false);
                createGroupResponse.setMessage("Try again later");
                createGroupResponse.setAction("");
                objectCreateGroup.setCreateGroupResponse(createGroupResponse);
                t.printStackTrace();
            }
        });
    }

    public void exploreGroup(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ExporeGroupsResponse> call = serviceInterface.exploreGroups(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ExporeGroupsResponse>() {
            @Override
            public void onResponse(Call<ExporeGroupsResponse> call, Response<ExporeGroupsResponse> response) {
                if (response.isSuccessful()) {
                    objectExploreGroups.setExporeGroupsResponse(response.body());
                } else {
                    ExporeGroupsResponse exporeGroupsResponse = new ExporeGroupsResponse();
                    exporeGroupsResponse.setSuccess(false);
                    exporeGroupsResponse.setMessage("Something went wrong, Try again later");
                    exporeGroupsResponse.setAction("");
                    objectExploreGroups.setExporeGroupsResponse(exporeGroupsResponse);
                }
            }

            @Override
            public void onFailure(Call<ExporeGroupsResponse> call, Throwable t) {
                ExporeGroupsResponse exporeGroupsResponse = new ExporeGroupsResponse();
                exporeGroupsResponse.setSuccess(false);
                exporeGroupsResponse.setAction("");
                exporeGroupsResponse.setMessage("Something went wrong, Try again later");
                objectExploreGroups.setExporeGroupsResponse(exporeGroupsResponse);
            }
        });
    }

    public void joinedGroups(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<JoinedGroupsResponse> call = serviceInterface.joinedGroups(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<JoinedGroupsResponse>() {
            @Override
            public void onResponse(Call<JoinedGroupsResponse> call, Response<JoinedGroupsResponse> response) {
                if (response.isSuccessful()) {
                    objectJoinedGrops.setJoinedGroupsResponse(response.body());
                } else {
                    JoinedGroupsResponse joinedGroupsResponse = new JoinedGroupsResponse();
                    joinedGroupsResponse.setSuccess(false);
                    joinedGroupsResponse.setMessage("Something went wrong, Try again later");
                    joinedGroupsResponse.setAction("");
                    objectJoinedGrops.setJoinedGroupsResponse(joinedGroupsResponse);
                }
            }

            @Override
            public void onFailure(Call<JoinedGroupsResponse> call, Throwable t) {
                JoinedGroupsResponse joinedGroupsResponse = new JoinedGroupsResponse();
                joinedGroupsResponse.setSuccess(false);
                joinedGroupsResponse.setAction("");
                joinedGroupsResponse.setMessage("Something went wrong, Try again later");
                objectJoinedGrops.setJoinedGroupsResponse(joinedGroupsResponse);
            }
        });
    }

    public void myGroups(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<MyGroupsResponse> call = serviceInterface.myGroups(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<MyGroupsResponse>() {
            @Override
            public void onResponse(Call<MyGroupsResponse> call, Response<MyGroupsResponse> response) {
                if (response.isSuccessful()) {
                    objectMyGroups.setMyGroupsResponse(response.body());
                } else {
                    MyGroupsResponse myGroupsResponse = new MyGroupsResponse();
                    myGroupsResponse.setSuccess(false);
                    myGroupsResponse.setMessage("Something went wrong, Try again later");
                    myGroupsResponse.setAction("");
                    objectMyGroups.setMyGroupsResponse(myGroupsResponse);

                }
            }

            @Override
            public void onFailure(Call<MyGroupsResponse> call, Throwable t) {
                MyGroupsResponse myGroupsResponse = new MyGroupsResponse();
                myGroupsResponse.setSuccess(false);
                myGroupsResponse.setAction("");
                myGroupsResponse.setMessage("Something went wrong, Try again later");
                objectMyGroups.setMyGroupsResponse(myGroupsResponse);
            }
        });
    }

    public void cancleVisit(String userId, String token, String deviceToken, String deviceType, String siteVisitId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<CancelSiteVisitResponse> call = serviceInterface.cancelSiteVisit(userId, token, deviceToken, deviceType, siteVisitId);
        call.enqueue(new Callback<CancelSiteVisitResponse>() {
            @Override
            public void onResponse(Call<CancelSiteVisitResponse> call, Response<CancelSiteVisitResponse> response) {
                if (response.isSuccessful()) {
                    objectCancelSiteVisit.setCancelSiteVisitResponse(response.body());
                } else {
                    CancelSiteVisitResponse cancelSiteVisitResponse = new CancelSiteVisitResponse();
                    cancelSiteVisitResponse.setSuccess(false);
                    cancelSiteVisitResponse.setMessage("Something went wrong, Try again later");
                    cancelSiteVisitResponse.setAction("");
                    objectCancelSiteVisit.setCancelSiteVisitResponse(cancelSiteVisitResponse);
                }
            }

            @Override
            public void onFailure(Call<CancelSiteVisitResponse> call, Throwable t) {
                CancelSiteVisitResponse cancelSiteVisitResponse = new CancelSiteVisitResponse();
                cancelSiteVisitResponse.setSuccess(false);
                cancelSiteVisitResponse.setAction("");
                cancelSiteVisitResponse.setMessage("Something went wrong, Try again later");
                objectCancelSiteVisit.setCancelSiteVisitResponse(cancelSiteVisitResponse);
            }
        });
    }

    public void joinGroup(String userId, String token, String deviceToken, String deviceType, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<JoinGroupResponse> call = serviceInterface.joinGroup(userId, token, deviceToken, deviceType, groupId);
        call.enqueue(new Callback<JoinGroupResponse>() {
            @Override
            public void onResponse(Call<JoinGroupResponse> call, Response<JoinGroupResponse> response) {
                if (response.isSuccessful()) {
                    objectJoinGroup.setJoinGroupResponse(response.body());
                } else {
                    JoinGroupResponse joinGroupResponse = new JoinGroupResponse();
                    joinGroupResponse.setSuccess(false);
                    joinGroupResponse.setAction("");
                    joinGroupResponse.setMessage("Something went wrong, Try again later");
                    objectJoinGroup.setJoinGroupResponse(joinGroupResponse);

                }
            }

            @Override
            public void onFailure(Call<JoinGroupResponse> call, Throwable t) {
                JoinGroupResponse joinGroupResponse = new JoinGroupResponse();
                joinGroupResponse.setSuccess(false);
                joinGroupResponse.setAction("");
                joinGroupResponse.setMessage("Something went wrong, Try again later");
                objectJoinGroup.setJoinGroupResponse(joinGroupResponse);
            }
        });
    }

    public void viewGroup(String userId, String token, String deviceToken, String deviceType, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ViewGroupResponse> call = serviceInterface.viewGroup(userId, token, deviceToken, deviceType, groupId);
        call.enqueue(new Callback<ViewGroupResponse>() {
            @Override
            public void onResponse(Call<ViewGroupResponse> call, Response<ViewGroupResponse> response) {
                if (response.isSuccessful()) {
                    objectViewGroup.setViewGroupResponse(response.body());

                } else {
                    ViewGroupResponse viewGroupResponse = new ViewGroupResponse();
                    viewGroupResponse.setSuccess(false);
                    viewGroupResponse.setAction(" ");
                    viewGroupResponse.setMessage("Something went wrong, Try again later");
                    objectViewGroup.setViewGroupResponse(viewGroupResponse);
                }
            }

            @Override
            public void onFailure(Call<ViewGroupResponse> call, Throwable t) {
                ViewGroupResponse viewGroupResponse = new ViewGroupResponse();
                viewGroupResponse.setSuccess(false);
                viewGroupResponse.setAction(" ");
                viewGroupResponse.setMessage("Something went wrong, Try again later");
                objectViewGroup.setViewGroupResponse(viewGroupResponse);
            }
        });
    }

    public void noticeList(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<NoticeListResponse> call = serviceInterface.noticeList(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<NoticeListResponse>() {
            @Override
            public void onResponse(Call<NoticeListResponse> call, Response<NoticeListResponse> response) {
                if (response.isSuccessful()) {
                    objectNoticeList.setNoticeListResponse(response.body());
                } else {
                    NoticeListResponse noticeListResponse = new NoticeListResponse();
                    noticeListResponse.setSuccess(false);
                    noticeListResponse.setAction(" ");
                    noticeListResponse.setMessage("Something went wrong, Try again later");
                    objectNoticeList.setNoticeListResponse(noticeListResponse);
                }
            }

            @Override
            public void onFailure(Call<NoticeListResponse> call, Throwable t) {
                NoticeListResponse noticeListResponse = new NoticeListResponse();
                noticeListResponse.setSuccess(false);
                noticeListResponse.setAction(" ");
                noticeListResponse.setMessage("Something went wrong, Try again later");
                objectNoticeList.setNoticeListResponse(noticeListResponse);
            }
        });
    }

    public void createPost(String userId, String token, String deviceToken, String deviceType, String groupId, String details, String images) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<CreatePostResponse> call = serviceInterface.createPost(userId, token, deviceToken, deviceType, groupId, details, images);
        call.enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if (response.isSuccessful()) {
                    objectCreatePost.setCreatePostResponse(response.body());
                } else {
                    CreatePostResponse createPostResponse = new CreatePostResponse();
                    createPostResponse.setSuccess(false);
                    createPostResponse.setAction(" ");
                    createPostResponse.setMessage("Something went wrong, Please try again later.");
                    objectCreatePost.setCreatePostResponse(createPostResponse);
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                CreatePostResponse createPostResponse = new CreatePostResponse();
                createPostResponse.setSuccess(false);
                createPostResponse.setAction(" ");
                createPostResponse.setMessage("Something went wrong, Please try again later.");
                objectCreatePost.setCreatePostResponse(createPostResponse);
            }
        });
    }

    public void eventList(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<EventListResponse> call = serviceInterface.eventList(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
                if (response.isSuccessful()) {
                    objectEventList.setEventListResponse(response.body());
                } else {
                    EventListResponse eventListResponse = new EventListResponse();
                    eventListResponse.setSuccess(false);
                    eventListResponse.setAction(" ");
                    eventListResponse.setMessage("Something went wrong, Try again later");
                    objectEventList.setEventListResponse(eventListResponse);
                }
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                EventListResponse eventListResponse = new EventListResponse();
                eventListResponse.setSuccess(false);
                eventListResponse.setAction(" ");
                eventListResponse.setMessage("Something went wrong, Try again later");
                objectEventList.setEventListResponse(eventListResponse);
            }
        });
    }

    public void acceptEvent(String userId, String token, String deviceToken, String deviceType, String userEventId, String interestStatus) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AcceptEventResponse> call = serviceInterface.acceptEvent(userId, token, deviceToken, deviceType, userEventId, interestStatus);
        call.enqueue(new Callback<AcceptEventResponse>() {
            @Override
            public void onResponse(Call<AcceptEventResponse> call, Response<AcceptEventResponse> response) {
                if (response.isSuccessful()) {
                    objectAcceptEvent.setAcceptEventResponse(response.body());
                } else {
                    AcceptEventResponse acceptEventResponse = new AcceptEventResponse();
                    acceptEventResponse.setSuccess(false);
                    acceptEventResponse.setAction(" ");
                    acceptEventResponse.setMessage("Something went wrong, Try again later");
                    objectAcceptEvent.setAcceptEventResponse(acceptEventResponse);
                }
            }

            @Override
            public void onFailure(Call<AcceptEventResponse> call, Throwable t) {
                AcceptEventResponse acceptEventResponse = new AcceptEventResponse();
                acceptEventResponse.setSuccess(false);
                acceptEventResponse.setAction(" ");
                acceptEventResponse.setMessage("Something went wrong, Try again later");
                objectAcceptEvent.setAcceptEventResponse(acceptEventResponse);
            }
        });
    }

    public void getPosts(String userId, String token, String deviceToken, String deviceType, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetPostsResponse> call = serviceInterface.getPosts(userId, token, deviceToken, deviceType, groupId);
        call.enqueue(new Callback<GetPostsResponse>() {
            @Override
            public void onResponse(Call<GetPostsResponse> call, Response<GetPostsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetPosts.setGetPostsResponse(response.body());
                } else {
                    GetPostsResponse getPostsResponse = new GetPostsResponse();
                    getPostsResponse.setSuccess(false);
                    getPostsResponse.setAction(" ");
                    getPostsResponse.setMessage("Something went wrong, Try again later");
                    objectGetPosts.setGetPostsResponse(getPostsResponse);
                }
            }

            @Override
            public void onFailure(Call<GetPostsResponse> call, Throwable t) {
                GetPostsResponse getPostsResponse = new GetPostsResponse();
                getPostsResponse.setSuccess(false);
                getPostsResponse.setAction(" ");
                getPostsResponse.setMessage("Something went wrong, Try again later");
                objectGetPosts.setGetPostsResponse(getPostsResponse);
            }
        });
    }

    public void loadComments(String userId, String token, String deviceToken, String deviceType, String postId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadCommentsResponse> call = serviceInterface.loadComments(userId, token, deviceToken, deviceType, postId);
        call.enqueue(new Callback<LoadCommentsResponse>() {
            @Override
            public void onResponse(Call<LoadCommentsResponse> call, Response<LoadCommentsResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadComments.setLoadCommentsResponse(response.body());
                } else {
                    LoadCommentsResponse loadCommentsResponse = new LoadCommentsResponse();
                    loadCommentsResponse.setSuccess(false);
                    loadCommentsResponse.setAction(" ");
                    loadCommentsResponse.setMessage("Something went wrong, Try again later");
                    objectLoadComments.setLoadCommentsResponse(loadCommentsResponse);
                }
            }

            @Override
            public void onFailure(Call<LoadCommentsResponse> call, Throwable t) {
                LoadCommentsResponse loadCommentsResponse = new LoadCommentsResponse();
                loadCommentsResponse.setSuccess(false);
                loadCommentsResponse.setAction(" ");
                loadCommentsResponse.setMessage("Something went wrong, Try again later");
                objectLoadComments.setLoadCommentsResponse(loadCommentsResponse);
            }
        });
    }

    public void addComments(String userId, String token, String deviceToken, String deviceType, String postId, String comments) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddCommentsResponse> call = serviceInterface.addComments(userId, token, deviceToken, deviceType, postId, comments);
        call.enqueue(new Callback<AddCommentsResponse>() {
            @Override
            public void onResponse(Call<AddCommentsResponse> call, Response<AddCommentsResponse> response) {
                if (response.isSuccessful()) {
                    objectAddComments.setAddCommentsResponse(response.body());
                } else {
                    AddCommentsResponse addCommentsResponse = new AddCommentsResponse();
                    addCommentsResponse.setSuccess(false);
                    addCommentsResponse.setAction(" ");
                    addCommentsResponse.setMessage("Something went wrong, Try again later");
                    objectAddComments.setAddCommentsResponse(addCommentsResponse);
                }
            }

            @Override
            public void onFailure(Call<AddCommentsResponse> call, Throwable t) {
                AddCommentsResponse addCommentsResponse = new AddCommentsResponse();
                addCommentsResponse.setSuccess(false);
                addCommentsResponse.setAction(" ");
                addCommentsResponse.setMessage("Something went wrong, Try again later");
                objectAddComments.setAddCommentsResponse(addCommentsResponse);
            }
        });
    }

    public void likePost(String userId, String token, String deviceToken, String deviceType, String postId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LikePostResponse> call = serviceInterface.likePost(userId, token, deviceToken, deviceType, postId);
        call.enqueue(new Callback<LikePostResponse>() {
            @Override
            public void onResponse(Call<LikePostResponse> call, Response<LikePostResponse> response) {
                if (response.isSuccessful()) {
                    objectLikePost.setLikePostResponse(response.body());
                } else {
                    LikePostResponse likePostResponse = new LikePostResponse();
                    likePostResponse.setSuccess(false);
                    likePostResponse.setMessage("Something went wrong, Try again later");
                    likePostResponse.setAction(" ");
                    objectLikePost.setLikePostResponse(likePostResponse);
                }
            }

            @Override
            public void onFailure(Call<LikePostResponse> call, Throwable t) {
                LikePostResponse likePostResponse = new LikePostResponse();
                likePostResponse.setSuccess(false);
                likePostResponse.setMessage("Something went wrong, Try again later");
                likePostResponse.setAction(" ");
                objectLikePost.setLikePostResponse(likePostResponse);
            }
        });
    }

    public void clubhouseList(String userId, String token, String deviceToken, String deviceType, String projectId, String clubhouse_booked) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ClubHouseListResponse> call = serviceInterface.clubhouseList(userId, token, deviceToken, deviceType, projectId, clubhouse_booked);
        call.enqueue(new Callback<ClubHouseListResponse>() {
            @Override
            public void onResponse(Call<ClubHouseListResponse> call, Response<ClubHouseListResponse> response) {
                if (response.isSuccessful()) {
                    objectClubHouseList.setClubHouseListResponse(response.body());
                } else {
                    ClubHouseListResponse data = new ClubHouseListResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectClubHouseList.setClubHouseListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ClubHouseListResponse> call, Throwable t) {
                ClubHouseListResponse data = new ClubHouseListResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectClubHouseList.setClubHouseListResponse(data);
            }

        });
    }

    public void amentieeList(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AmentieeListResponse> call = serviceInterface.amentieeList(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<AmentieeListResponse>() {
            @Override
            public void onResponse(Call<AmentieeListResponse> call, Response<AmentieeListResponse> response) {
                if (response.isSuccessful()) {
                    objectAmentieeList.setAmentieeListResponse(response.body());
                } else {
                    AmentieeListResponse data = new AmentieeListResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAmentieeList.setAmentieeListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AmentieeListResponse> call, Throwable t) {
                AmentieeListResponse data = new AmentieeListResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAmentieeList.setAmentieeListResponse(data);
            }
        });

    }

    public void changeBookingStatus(String userId, String token, String deviceToken, String deviceType,
                                    String clubhouse_booking_id, String clubhouse_booked) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ChangeBookingStatusResponse> call = serviceInterface.changeBookingStatus(userId, token,
                deviceToken, deviceType, clubhouse_booking_id, clubhouse_booked);
        call.enqueue(new Callback<ChangeBookingStatusResponse>() {
            @Override
            public void onResponse(Call<ChangeBookingStatusResponse> call, Response<ChangeBookingStatusResponse> response) {
                if (response.isSuccessful()) {
                    objectChangeBookingStatus.setChangeBookingStatusResponse(response.body());
                } else {
                    ChangeBookingStatusResponse data = new ChangeBookingStatusResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectChangeBookingStatus.setChangeBookingStatusResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ChangeBookingStatusResponse> call, Throwable t) {
                ChangeBookingStatusResponse data = new ChangeBookingStatusResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectChangeBookingStatus.setChangeBookingStatusResponse(data);
            }
        });
    }

    public void bookClubhouse(String userId, String token, String deviceToken, String deviceType, String projectId,
                              String clubhouse_booking_amenty_id, String clubhouse_booking_date,
                              String clubhouse_booking_timeslot, String clubhouse_booking_noofpeople, String bId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<BookClubhouseResponse> call = serviceInterface.bookClubhouse(userId, token, deviceToken, deviceType,
                projectId, clubhouse_booking_amenty_id, clubhouse_booking_date,
                clubhouse_booking_timeslot, clubhouse_booking_noofpeople, bId);
        call.enqueue(new Callback<BookClubhouseResponse>() {
            @Override
            public void onResponse(Call<BookClubhouseResponse> call, Response<BookClubhouseResponse> response) {
                if (response.isSuccessful()) {
                    objectBookClubhouse.setBookClubhouseResponse(response.body());
                } else {
                    BookClubhouseResponse data = new BookClubhouseResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectBookClubhouse.setBookClubhouseResponse(data);
                }
            }

            @Override
            public void onFailure(Call<BookClubhouseResponse> call, Throwable t) {
                BookClubhouseResponse data = new BookClubhouseResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectBookClubhouse.setBookClubhouseResponse(data);
            }
        });
    }

    public void getPaymentDetails(String userId, String token, String deviceToken, String deviceType, String bId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetPaymentDetailsResponse> call = serviceInterface.getPaymentDetails(userId, token, deviceToken, deviceType, bId);
        call.enqueue(new Callback<GetPaymentDetailsResponse>() {
            @Override
            public void onResponse(Call<GetPaymentDetailsResponse> call, Response<GetPaymentDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetPaymentDetails.setGetPaymentDetailsResponse(response.body());
                } else {
                    GetPaymentDetailsResponse getPaymentDetailsResponse = new GetPaymentDetailsResponse();
                    getPaymentDetailsResponse.setSuccess(false);
                    getPaymentDetailsResponse.setAction(" ");
                    getPaymentDetailsResponse.setMessage("Something went wrong, Please try again later");
                    objectGetPaymentDetails.setGetPaymentDetailsResponse(getPaymentDetailsResponse);
                }
            }

            @Override
            public void onFailure(Call<GetPaymentDetailsResponse> call, Throwable t) {
                GetPaymentDetailsResponse getPaymentDetailsResponse = new GetPaymentDetailsResponse();
                getPaymentDetailsResponse.setSuccess(false);
                getPaymentDetailsResponse.setAction(" ");
                getPaymentDetailsResponse.setMessage("Something went wrong, Please try again later");
                objectGetPaymentDetails.setGetPaymentDetailsResponse(getPaymentDetailsResponse);
            }
        });
    }

    public void addedValueList(String userId, String token, String deviceToken, String deviceType, String projectId,
                               final String value_addedservice_type, final FirstFragment fragment) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddedValueListResponse> call = serviceInterface.addedValueList(userId, token, deviceToken, deviceType, projectId, value_addedservice_type);
        call.enqueue(new Callback<AddedValueListResponse>() {
            @Override
            public void onResponse(Call<AddedValueListResponse> call, Response<AddedValueListResponse> response) {
                if (response.isSuccessful()) {
                    objectAddedValueListInterior.setAddedValueListData(response.body(), fragment);
                } else {
                    AddedValueListResponse data = new AddedValueListResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAddedValueListInterior.setAddedValueListData(data, fragment);
                }
            }

            @Override
            public void onFailure(Call<AddedValueListResponse> call, Throwable t) {
                AddedValueListResponse data = new AddedValueListResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAddedValueListInterior.setAddedValueListData(data, fragment);
            }
        });
    }

    public void offerList(String userId, String token, String deviceToken, String deviceType, String projectId, final String offerType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<OfferListResponse> call = serviceInterface.offerList(userId, token, deviceToken, deviceType, projectId, offerType);
        call.enqueue(new Callback<OfferListResponse>() {
            @Override
            public void onResponse(Call<OfferListResponse> call, Response<OfferListResponse> response) {
                if (response.isSuccessful()) {
                    if (offerType == "0") {
                        objectOfferListAdmin.setOfferListResponse(response.body());
                    } else if (offerType == "1") {
                        objectOfferListSell.setOfferListResponse(response.body());
                    } else if (offerType == "2") {
                        objectOfferListRent.setOfferListResponse(response.body());
                    }
                } else {
                    OfferListResponse data = new OfferListResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectOfferListRent.setOfferListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<OfferListResponse> call, Throwable t) {
                OfferListResponse data = new OfferListResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectOfferListRent.setOfferListResponse(data);
            }
        });
    }

    public void removeOffer(String userId, String token, String deviceToken, String deviceType, String removeOffer) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<RemoveOfferResponse> call = serviceInterface.removeOffer(userId, token, deviceToken, deviceType, removeOffer);
        call.enqueue(new Callback<RemoveOfferResponse>() {
            @Override
            public void onResponse(Call<RemoveOfferResponse> call, Response<RemoveOfferResponse> response) {
                if (response.isSuccessful()) {
                    objectRemoveOffer.setRemoveOfferResponse(response.body());
                } else {
                    RemoveOfferResponse data = new RemoveOfferResponse();
                    data.setSuccess(true);
                    data.setMessage("Classified Removed");
                    data.setAction("");
                    objectRemoveOffer.setRemoveOfferResponse(data);
                }
            }

            @Override
            public void onFailure(Call<RemoveOfferResponse> call, Throwable t) {
                RemoveOfferResponse data = new RemoveOfferResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction("");
                objectRemoveOffer.setRemoveOfferResponse(data);

            }
        });


    }

    public void myOfferList(String userId, String token, String deviceToken, String deviceType, String projectId, final String offerType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<OfferListResponse> call = serviceInterface.myOfferList(userId, token, deviceToken, deviceType, projectId, offerType);
        call.enqueue(new Callback<OfferListResponse>() {
            @Override
            public void onResponse(Call<OfferListResponse> call, Response<OfferListResponse> response) {
                if (response.isSuccessful()) {
                    objectMyOfferList.setOfferListResponse(response.body());
                } else {
                    OfferListResponse data = new OfferListResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectMyOfferList.setOfferListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<OfferListResponse> call, Throwable t) {
                OfferListResponse data = new OfferListResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectMyOfferList.setOfferListResponse(data);
            }
        });
    }

    public void insertPaymentDetail(String userId, String token, String deviceToken, String deviceType, String projectId, String buildingId, String towerId, String paymentId, String bill_amount) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<MPaymentResponse> call = serviceInterface.insertPaymentDetail(userId, token, deviceToken, deviceType, projectId, buildingId, towerId, paymentId, bill_amount);
        call.enqueue(new Callback<MPaymentResponse>() {
            @Override
            public void onResponse(Call<MPaymentResponse> call, Response<MPaymentResponse> response) {
                if (response.isSuccessful()) {
                    objectPaymentResponse.setmPaymentResponse(response.body());
                } else {
                    MPaymentResponse data = new MPaymentResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectPaymentResponse.setmPaymentResponse(data);
                }
            }

            @Override
            public void onFailure(Call<MPaymentResponse> call, Throwable t) {
                MPaymentResponse data = new MPaymentResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectPaymentResponse.setmPaymentResponse(data);
            }
        });
    }

    public void addClassified(String userId, String token, String deviceToken, String deviceType, String projectId, String offer_title,
                              String offer_price, String offer_dec, String offer_type, String picture, String picture2, String picture3) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddClassifiedResponse> call = serviceInterface.addClassified(userId, token, deviceToken, deviceType, projectId,
                offer_title, offer_price, offer_dec, offer_type, picture, picture2, picture3);
        call.enqueue(new Callback<AddClassifiedResponse>() {
            @Override
            public void onResponse(Call<AddClassifiedResponse> call, Response<AddClassifiedResponse> response) {
                if (response.isSuccessful()) {
                    objectAddClassified.setAddClassifiedResponse(response.body());
                } else {
                    AddClassifiedResponse data = new AddClassifiedResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAddClassified.setAddClassifiedResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddClassifiedResponse> call, Throwable t) {
                AddClassifiedResponse data = new AddClassifiedResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAddClassified.setAddClassifiedResponse(data);
            }
        });
    }

    public void ticketList(String userId, String token, String deviceToken, String deviceType, String propertyId,
                           final String ticketType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<TicketListResponse> call = serviceInterface.ticketList(userId, token, deviceToken, deviceType, propertyId, ticketType, projectId);
        call.enqueue(new Callback<TicketListResponse>() {

                         @Override
                         public void onResponse(Call<TicketListResponse> call, Response<TicketListResponse> response) {
                             if (response.isSuccessful()) {
                                 objectTicketListEnquiries.setTicketListResponse(response.body());
                             } else {
                                 TicketListResponse data = new TicketListResponse();
                                 data.setSuccess(false);
                                 data.setAction("");
                                 data.setMessage("Something went wrong, Try again later");
                                 objectTicketListEnquiries.setTicketListResponse(data);
                             }
                         }

                         @Override
                         public void onFailure(Call<TicketListResponse> call, Throwable t) {
                             TicketListResponse data = new TicketListResponse();
                             data.setSuccess(false);
                             data.setAction("");
                             data.setMessage("Something went wrong, Try again later");
                             objectTicketListEnquiries.setTicketListResponse(data);
                         }
                     }
        );
    }

    public void addCallBack(String userId, String token, String deviceToken, String deviceType, String propertyId, String callback_name, String callback_email, String callback_phone, String callback_type, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddCallBackResponse> call = serviceInterface.addCallBack(userId, token, deviceToken, deviceType, propertyId, callback_name, callback_email, callback_phone, callback_type, projectId);
        call.enqueue(new Callback<AddCallBackResponse>() {
            @Override
            public void onResponse(Call<AddCallBackResponse> call, Response<AddCallBackResponse> response) {
                if (response.isSuccessful()) {
                    objectAddCallBack.setAddCallBackResponse(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<AddCallBackResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addCallBackReq(String userId, String token, String deviceToken, String deviceType, String bId, String callback_name, String callback_email, String callback_phone, String callback_type, String projectId, String propType, String description) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddCallBackResponse> call = serviceInterface.addCallBackReq(userId, token, deviceToken, deviceType, bId, callback_name, callback_email, callback_phone, callback_type, projectId, propType, description);
        call.enqueue(new Callback<AddCallBackResponse>() {
            @Override
            public void onResponse(Call<AddCallBackResponse> call, Response<AddCallBackResponse> response) {
                if (response.isSuccessful()) {
                    objectAddCallBack.setAddCallBackResponse(response.body());
                } else {
                    AddCallBackResponse data = new AddCallBackResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAddCallBack.setAddCallBackResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddCallBackResponse> call, Throwable t) {
                AddCallBackResponse data = new AddCallBackResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAddCallBack.setAddCallBackResponse(data);
            }
        });
    }

    public void addCallBackRefer(String userId, String token, String deviceToken, String deviceType, String propertyId, String callback_name, String callback_email, String callback_phone, String callback_type, String projectId, String callback_desc) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddCallBackResponse> call = serviceInterface.addCallBackRefer(userId, token, deviceToken, deviceType, propertyId, callback_name, callback_email, callback_phone, callback_type, projectId, callback_desc);
        call.enqueue(new Callback<AddCallBackResponse>() {
            @Override
            public void onResponse(Call<AddCallBackResponse> call, Response<AddCallBackResponse> response) {
                if (response.isSuccessful()) {
                    objectAddCallBack.setAddCallBackResponse(response.body());
                } else {
                    AddCallBackResponse data = new AddCallBackResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAddCallBack.setAddCallBackResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddCallBackResponse> call, Throwable t) {
                AddCallBackResponse data = new AddCallBackResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAddCallBack.setAddCallBackResponse(data);
            }
        });
    }

    public void addTicket(String userId, String token, String deviceToken, String deviceType, String propertyId, String ticket_subject, String ticket_priorty, String ticket_dec, String ticket_type, String ticket_attachment, String ticket_attachment_2, String ticket_attachment_3, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddTicketResponse> call = serviceInterface.addTicket(userId, token, deviceToken, deviceType, propertyId, ticket_subject, ticket_priorty, ticket_dec, ticket_type, ticket_attachment, ticket_attachment_2, ticket_attachment_3, projectId);
        call.enqueue(new Callback<AddTicketResponse>() {
            @Override
            public void onResponse(Call<AddTicketResponse> call, Response<AddTicketResponse> response) {
                if (response.isSuccessful()) {
                    objectAddTicket.setAddTicketResponse(response.body());
                } else {
                    AddTicketResponse data = new AddTicketResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectAddTicket.setAddTicketResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddTicketResponse> call, Throwable t) {
                AddTicketResponse data = new AddTicketResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectAddTicket.setAddTicketResponse(data);
            }
        });
    }

    public void projectList(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectListResponse> call = serviceInterface.projectList(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<ProjectListResponse>() {
            @Override
            public void onResponse(Call<ProjectListResponse> call, Response<ProjectListResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectList.setProjectListResponse(response.body());
                } else {
                    ProjectListResponse data = new ProjectListResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectProjectList.setProjectListResponse(data);

                }
            }

            @Override
            public void onFailure(Call<ProjectListResponse> call, Throwable t) {
                ProjectListResponse data = new ProjectListResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectProjectList.setProjectListResponse(data);
            }
        });
    }

    public void getProjectDetail(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetProjectDetailResponse> call = serviceInterface.getProjectDetail(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<GetProjectDetailResponse>() {
            @Override
            public void onResponse(Call<GetProjectDetailResponse> call, Response<GetProjectDetailResponse> response) {
                if (response.isSuccessful()) {
                    objectGetProjectDetail.setGetProjectDetailResponse(response.body());

                } else {
                    GetProjectDetailResponse data = new GetProjectDetailResponse();
                    data.setSuccess(false);
                    data.setAction(" ");
                    data.setMessage("Something went wrong, Try again later");
                    objectGetProjectDetail.setGetProjectDetailResponse(data);

                }
            }

            @Override
            public void onFailure(Call<GetProjectDetailResponse> call, Throwable t) {
                GetProjectDetailResponse data = new GetProjectDetailResponse();
                data.setSuccess(false);
                data.setAction(" ");
                data.setMessage("Something went wrong, Try again later");
                objectGetProjectDetail.setGetProjectDetailResponse(data);
            }
        });
    }

    public void projectAmenities(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectAmenitiesResponse> call = serviceInterface.projectAmenities(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ProjectAmenitiesResponse>() {
            @Override
            public void onResponse(Call<ProjectAmenitiesResponse> call, Response<ProjectAmenitiesResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectAmenities.setProjectAmenitiesResponse(response.body());
                } else {
                    ProjectAmenitiesResponse data = new ProjectAmenitiesResponse();
                    data.setSuccess(false);
                    data.setAction("");
                    data.setMessage("Something went wrong, Try again later");
                    objectProjectAmenities.setProjectAmenitiesResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ProjectAmenitiesResponse> call, Throwable t) {
                ProjectAmenitiesResponse data = new ProjectAmenitiesResponse();
                data.setSuccess(false);
                data.setAction("");
                data.setMessage("Something went wrong, Try again later");
                objectProjectAmenities.setProjectAmenitiesResponse(data);
            }
        });
    }

    public void projectGallery(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectGalleryResponse> call = serviceInterface.projectGallery(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ProjectGalleryResponse>() {
            @Override
            public void onResponse(Call<ProjectGalleryResponse> call, Response<ProjectGalleryResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectGallery.setProjectGalleryResponse(response.body());
                } else {
                    ProjectGalleryResponse data = new ProjectGalleryResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectProjectGallery.setProjectGalleryResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ProjectGalleryResponse> call, Throwable t) {
                ProjectGalleryResponse data = new ProjectGalleryResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectProjectGallery.setProjectGalleryResponse(data);
            }
        });
    }

    public void projectConstructionUpdate(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectConstructionUpdateResponse> call = serviceInterface.projectConstructionUpdate(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ProjectConstructionUpdateResponse>() {
            @Override
            public void onResponse(Call<ProjectConstructionUpdateResponse> call, Response<ProjectConstructionUpdateResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectConstructionUpdate.setProjectConstructionUpdateResponse(response.body());
                } else {
                    ProjectConstructionUpdateResponse data = new ProjectConstructionUpdateResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectProjectConstructionUpdate.setProjectConstructionUpdateResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ProjectConstructionUpdateResponse> call, Throwable t) {
                ProjectConstructionUpdateResponse data = new ProjectConstructionUpdateResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectProjectConstructionUpdate.setProjectConstructionUpdateResponse(data);
            }
        });
    }

    public void projectLocation(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectLocationResponse> call = serviceInterface.projectLocation(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ProjectLocationResponse>() {
            @Override
            public void onResponse(Call<ProjectLocationResponse> call, Response<ProjectLocationResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectLocation.setProjectLocationResponse(response.body());
                } else {
                    ProjectLocationResponse data = new ProjectLocationResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectProjectLocation.setProjectLocationResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ProjectLocationResponse> call, Throwable t) {
                ProjectLocationResponse data = new ProjectLocationResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectProjectLocation.setProjectLocationResponse(data);
            }
        });
    }

    public void projectOffers(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ProjectOffersResponse> call = serviceInterface.projectOffers(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<ProjectOffersResponse>() {
            @Override
            public void onResponse(Call<ProjectOffersResponse> call, Response<ProjectOffersResponse> response) {
                if (response.isSuccessful()) {
                    objectProjectOffers.setProjectOffersResponse(response.body());
                } else {
                    ProjectOffersResponse data = new ProjectOffersResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectProjectOffers.setProjectOffersResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ProjectOffersResponse> call, Throwable t) {
                ProjectOffersResponse data = new ProjectOffersResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectProjectOffers.setProjectOffersResponse(data);
            }
        });
    }

    public void ticketLogs(String userId, String token, String deviceToken, String deviceType, String ticketId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<TicketLogsResponse> call = serviceInterface.ticketLogs(userId, token, deviceToken, deviceType, ticketId);
        call.enqueue(new Callback<TicketLogsResponse>() {
            @Override
            public void onResponse(Call<TicketLogsResponse> call, Response<TicketLogsResponse> response) {
                if (response.isSuccessful()) {
                    objectTicketLogs.setTicketLogsResponse(response.body());
                } else {
                    TicketLogsResponse data = new TicketLogsResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectTicketLogs.setTicketLogsResponse(data);
                }
            }

            @Override
            public void onFailure(Call<TicketLogsResponse> call, Throwable t) {
                TicketLogsResponse data = new TicketLogsResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectTicketLogs.setTicketLogsResponse(data);
            }
        });
    }

    public void addTicketLog(String userId, String token, String deviceToken, String deviceType, String ticketId, String ticket_log_comment) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddTicketResponse> call = serviceInterface.addTicketLog(userId, token, deviceToken, deviceType, ticketId, ticket_log_comment);
        call.enqueue(new Callback<AddTicketResponse>() {
            @Override
            public void onResponse(Call<AddTicketResponse> call, Response<AddTicketResponse> response) {
                if (response.isSuccessful()) {
                    objectAddTicket.setAddTicketResponse(response.body());

                } else {
                    AddTicketResponse data = new AddTicketResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectAddTicket.setAddTicketResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddTicketResponse> call, Throwable t) {
                AddTicketResponse data = new AddTicketResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectAddTicket.setAddTicketResponse(data);
            }
        });

    }

    public void getPostPaymentDetails(String userId, String token, String deviceToken, String deviceType, String pId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetPostPaymentDetailsResponse> call = serviceInterface.getPostPaymentDetails(userId, token, deviceToken, deviceType, pId);
        call.enqueue(new Callback<GetPostPaymentDetailsResponse>() {
            @Override
            public void onResponse(Call<GetPostPaymentDetailsResponse> call, Response<GetPostPaymentDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetPostPaymentDetails.setGetPostPaymentDetailsResponse(response.body());
                } else {
                    GetPostPaymentDetailsResponse data = new GetPostPaymentDetailsResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGetPostPaymentDetails.setGetPostPaymentDetailsResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetPostPaymentDetailsResponse> call, Throwable t) {
                GetPostPaymentDetailsResponse data = new GetPostPaymentDetailsResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGetPostPaymentDetails.setGetPostPaymentDetailsResponse(data);
            }
        });
    }

    public void changeGroupName(String userId, String token, String deviceToken, String deviceType, String group_id, String group_name, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ChangeGroupNameResponse> call = serviceInterface.changeGroupName(userId, token, deviceToken, deviceType, group_id, group_name, projectId);
        call.enqueue(new Callback<ChangeGroupNameResponse>() {
            @Override
            public void onResponse(Call<ChangeGroupNameResponse> call, Response<ChangeGroupNameResponse> response) {
                if (response.isSuccessful()) {
                    objectChangeGroupName.setChangeGroupNameResponse(new ChangeGroupNameResponse());
                } else {
                    ChangeGroupNameResponse changeGroupNameResponse = new ChangeGroupNameResponse();
                    changeGroupNameResponse.setSuccess(false);
                    changeGroupNameResponse.setMessage("Something went wrong, Please try again later.");
                    changeGroupNameResponse.setAction(" ");
                }
            }

            @Override
            public void onFailure(Call<ChangeGroupNameResponse> call, Throwable t) {
                ChangeGroupNameResponse changeGroupNameResponse = new ChangeGroupNameResponse();
                changeGroupNameResponse.setSuccess(false);
                changeGroupNameResponse.setMessage("Something went wrong, Please try again later.");
                changeGroupNameResponse.setAction(" ");
                t.printStackTrace();
            }
        });

    }

    public void setGroupImage(String userId, String token, String deviceToken, String deviceType, String group_header_image, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SetGroupImageResponse> call = serviceInterface.setGroupImage(userId, token, deviceToken, deviceType, group_header_image, groupId);
        call.enqueue(new Callback<SetGroupImageResponse>() {
            @Override
            public void onResponse(Call<SetGroupImageResponse> call, Response<SetGroupImageResponse> response) {
                if (response.isSuccessful()) {
                    objectSetGroupImage.setSetGroupImageResponse(response.body());
                } else {
                    SetGroupImageResponse setGroupImageResponse = new SetGroupImageResponse();
                    setGroupImageResponse.setSuccess(false);
                    setGroupImageResponse.setMessage("Something went wrong, Please try again later.");
                    objectSetGroupImage.setSetGroupImageResponse(setGroupImageResponse);
                }
            }

            @Override
            public void onFailure(Call<SetGroupImageResponse> call, Throwable t) {
                SetGroupImageResponse setGroupImageResponse = new SetGroupImageResponse();
                setGroupImageResponse.setSuccess(false);
                setGroupImageResponse.setMessage("Something went wrong, Please try again later.");
                objectSetGroupImage.setSetGroupImageResponse(setGroupImageResponse);
                t.printStackTrace();
            }
        });

    }

    public void membersbyGroup(String userId, String token, String deviceToken, String deviceType, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<MembersbyGroupResponse> call = serviceInterface.membersbyGroup(userId, token, deviceToken, deviceType, groupId);
        call.enqueue(new Callback<MembersbyGroupResponse>() {
            @Override
            public void onResponse(Call<MembersbyGroupResponse> call, Response<MembersbyGroupResponse> response) {
                if (response.isSuccessful()) {
                    objectMembersbyGroup.setMembersbyGroupResponse(response.body());

                } else {
                    MembersbyGroupResponse data = new MembersbyGroupResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectMembersbyGroup.setMembersbyGroupResponse(data);

                }
            }

            @Override
            public void onFailure(Call<MembersbyGroupResponse> call, Throwable t) {
                MembersbyGroupResponse data = new MembersbyGroupResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectMembersbyGroup.setMembersbyGroupResponse(data);
                t.printStackTrace();
            }
        });

    }

    public void groupAboutbyGroup(String userId, String token, String deviceToken, String deviceType, String groupId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GroupAboutbyGroupResponse> call = serviceInterface.groupAboutbyGroup(userId, token, deviceToken, deviceType, groupId);
        call.enqueue(new Callback<GroupAboutbyGroupResponse>() {
            @Override
            public void onResponse(Call<GroupAboutbyGroupResponse> call, Response<GroupAboutbyGroupResponse> response) {
                if (response.isSuccessful()) {
                    objectGroupAboutbyGroup.setGroupAboutbyGroupResponse(response.body());

                } else {
                    GroupAboutbyGroupResponse data = new GroupAboutbyGroupResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGroupAboutbyGroup.setGroupAboutbyGroupResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GroupAboutbyGroupResponse> call, Throwable t) {
                GroupAboutbyGroupResponse data = new GroupAboutbyGroupResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGroupAboutbyGroup.setGroupAboutbyGroupResponse(data);
            }
        });
    }

    public void changeGroupAbout(String userId, String token, String deviceToken, String deviceType, String groupId, String group_about) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ChangeGroupAboutResponse> call = serviceInterface.changeGroupAbout(userId, token, deviceToken, deviceType, groupId, group_about);
        call.enqueue(new Callback<ChangeGroupAboutResponse>() {
            @Override
            public void onResponse(Call<ChangeGroupAboutResponse> call, Response<ChangeGroupAboutResponse> response) {
                if (response.isSuccessful()) {
                    objectChangeGroupAbout.setChangeGroupAboutResponse(response.body());
                } else {
                    ChangeGroupAboutResponse data = new ChangeGroupAboutResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectChangeGroupAbout.setChangeGroupAboutResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ChangeGroupAboutResponse> call, Throwable t) {
                ChangeGroupAboutResponse data = new ChangeGroupAboutResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectChangeGroupAbout.setChangeGroupAboutResponse(data);
            }
        });
    }

    public void getServiceRequests(String userId, String token, String deviceToken, String deviceType, String bId, String type) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetServiceRequestsResponse> call = serviceInterface.getServiceRequests(userId, token, deviceToken, deviceType, bId, type);
        call.enqueue(new Callback<GetServiceRequestsResponse>() {
            @Override
            public void onResponse(Call<GetServiceRequestsResponse> call, Response<GetServiceRequestsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetServiceRequests.setGetServiceRequestsResponse(response.body());
                } else {
                    GetServiceRequestsResponse data = new GetServiceRequestsResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGetServiceRequests.setGetServiceRequestsResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetServiceRequestsResponse> call, Throwable t) {
                GetServiceRequestsResponse data = new GetServiceRequestsResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGetServiceRequests.setGetServiceRequestsResponse(data);
            }
        });
    }

    public void residentList(String userId, String token, String deviceToken, String deviceType, String projectId, String towerId, String residentName) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ResidentListResponse> call = serviceInterface.residentList(userId, token, deviceToken, deviceType, projectId, towerId, residentName);
        call.enqueue(new Callback<ResidentListResponse>() {
            @Override
            public void onResponse(Call<ResidentListResponse> call, Response<ResidentListResponse> response) {
                if (response.isSuccessful()) {
                    objectResidentList.setResidentListResponse(response.body());
                } else {
                    ResidentListResponse data = new ResidentListResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectResidentList.setResidentListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ResidentListResponse> call, Throwable t) {
                ResidentListResponse data = new ResidentListResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectResidentList.setResidentListResponse(data);
                t.printStackTrace();
            }
        });
    }

    public void committeeManagmentList(String userId, String token, String deviceToken, String deviceType, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<CommitteeManagmentListResponse> call = serviceInterface.committeeManagmentList(userId, token, deviceToken, deviceType, projectId);
        call.enqueue(new Callback<CommitteeManagmentListResponse>() {
                         @Override
                         public void onResponse(Call<CommitteeManagmentListResponse> call, Response<CommitteeManagmentListResponse> response) {
                             if (response.isSuccessful()) {
                                 objectCommitteeManagmentList.setCommitteeManagmentListResponse(response.body());
                             } else {
                                 CommitteeManagmentListResponse data = new CommitteeManagmentListResponse();
                                 data.setSuccess(false);
                                 data.setMessage("Something went wrong, Try again later");
                                 data.setAction(" ");
                                 objectCommitteeManagmentList.setCommitteeManagmentListResponse(data);
                             }
                         }

                         @Override
                         public void onFailure(Call<CommitteeManagmentListResponse> call, Throwable t) {
                             CommitteeManagmentListResponse data = new CommitteeManagmentListResponse();
                             data.setSuccess(false);
                             data.setMessage("Something went wrong, Try again later");
                             data.setAction(" ");
                             objectCommitteeManagmentList.setCommitteeManagmentListResponse(data);
                             t.printStackTrace();
                         }
                     }
        );

    }

    public void notificationList(String userId, String token, String deviceToken, String deviceType, String notification_type) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<NotificationListResponse> call = serviceInterface.notificationList(userId, token, deviceToken, deviceType, notification_type);
        call.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                if (response.isSuccessful()) {
                    objectNotificationList.setNotificationListResponse(response.body());
                } else {
                    NotificationListResponse data = new NotificationListResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectNotificationList.setNotificationListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                NotificationListResponse data = new NotificationListResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectNotificationList.setNotificationListResponse(data);
                t.printStackTrace();
            }
        });
    }

    public void deletePost(String userId, String token, String deviceToken, String deviceType, String postId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<DeletePostResponse> call = serviceInterface.deletePost(userId, token, deviceToken, deviceType, postId);
        call.enqueue(new Callback<DeletePostResponse>() {
            @Override
            public void onResponse(Call<DeletePostResponse> call, Response<DeletePostResponse> response) {
                if (response.isSuccessful()) {
                    objectDeletePost.setDeletePostResponse(response.body());
                } else {
                    DeletePostResponse data = new DeletePostResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectDeletePost.setDeletePostResponse(data);
                }
            }

            @Override
            public void onFailure(Call<DeletePostResponse> call, Throwable t) {
                DeletePostResponse data = new DeletePostResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectDeletePost.setDeletePostResponse(data);
                t.printStackTrace();
            }
        });
    }

    public void addReport(String userId, String token, String deviceToken, String deviceType, String postId, String comment) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddReportResponse> call = serviceInterface.addReport(userId, token, deviceToken, deviceType, postId, comment);
        call.enqueue(new Callback<AddReportResponse>() {
            @Override
            public void onResponse(Call<AddReportResponse> call, Response<AddReportResponse> response) {
                if (response.isSuccessful()) {
                    objectAddReport.setAddReportResponse(response.body());
                } else {
                    AddReportResponse data = new AddReportResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectAddReport.setAddReportResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddReportResponse> call, Throwable t) {
                AddReportResponse data = new AddReportResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectAddReport.setAddReportResponse(data);
                t.printStackTrace();
            }
        });
    }

    public void addServiceRequest(String userId, String token, String deviceToken, String deviceType, String bId, String comment, String type, String projectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddServiceRequestResponse> call = serviceInterface.addServiceRequest(userId, token, deviceToken, deviceType, bId, comment, type, projectId);
        call.enqueue(new Callback<AddServiceRequestResponse>() {
            @Override
            public void onResponse(Call<AddServiceRequestResponse> call, Response<AddServiceRequestResponse> response) {
                if (response.isSuccessful()) {
                    objectAddServiceRequest.setAddServiceRequestResponse(response.body());

                } else {
                    AddServiceRequestResponse data = new AddServiceRequestResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction("  ");
                    objectAddServiceRequest.setAddServiceRequestResponse(data);
                }
            }

            @Override
            public void onFailure(Call<AddServiceRequestResponse> call, Throwable t) {
                AddServiceRequestResponse data = new AddServiceRequestResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction("  ");
                objectAddServiceRequest.setAddServiceRequestResponse(data);
                t.printStackTrace();
            }
        });

    }

    public void submitFeedback(String userId, String token, String deviceToken, String deviceType, String feedback, String rating, String notificationType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SubmitFeedbackResponse> call = serviceInterface.submitFeedback(userId, token, deviceToken, deviceType, feedback, rating, notificationType);
        call.enqueue(new Callback<SubmitFeedbackResponse>() {
            @Override
            public void onResponse(Call<SubmitFeedbackResponse> call, Response<SubmitFeedbackResponse> response) {
                if (response.isSuccessful()) {
                    objectSubmitFeedback.setSubmitFeedbackResponse(response.body());
                } else {
                    SubmitFeedbackResponse data = new SubmitFeedbackResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectSubmitFeedback.setSubmitFeedbackResponse(data);
                }
            }

            @Override
            public void onFailure(Call<SubmitFeedbackResponse> call, Throwable t) {
                SubmitFeedbackResponse data = new SubmitFeedbackResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectSubmitFeedback.setSubmitFeedbackResponse(data);
            }
        });


    }

    public void loadDocuments(String userId, String token, String deviceToken, String deviceType, String dType, String documentId, final Context context) {
        final DialogProgressBar progressBar = new DialogProgressBar();
        progressBar.showProgressBar((Activity) context);

        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadDocumentsResponse> call = serviceInterface.loadDocuments(userId, token, deviceToken, deviceType, dType, documentId);
        call.enqueue(new Callback<LoadDocumentsResponse>() {
            @Override
            public void onResponse(Call<LoadDocumentsResponse> call, Response<LoadDocumentsResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadDocuments.setLoadDocumentsResponse(response.body());
                    progressBar.hideProgressBar();
                } else {
                    progressBar.hideProgressBar();
                    Toast.makeText(context, "No Data Available.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoadDocumentsResponse> call, Throwable t) {
                progressBar.hideProgressBar();
                Toast.makeText(context, "No Data Available.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDownloadDocuments(String userId, String token, String deviceToken, String deviceType, String bId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetDownloadDocumentsResponse> call = serviceInterface.getDownloadDocuments(userId, token, deviceToken, deviceType, bId);

        call.enqueue(new Callback<GetDownloadDocumentsResponse>() {
            @Override
            public void onResponse(Call<GetDownloadDocumentsResponse> call, Response<GetDownloadDocumentsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetDownloadDocuments.setGetDownloadDocumentsResponse(response.body());
                } else {
                    GetDownloadDocumentsResponse data = new GetDownloadDocumentsResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGetDownloadDocuments.setGetDownloadDocumentsResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetDownloadDocumentsResponse> call, Throwable t) {
                GetDownloadDocumentsResponse data = new GetDownloadDocumentsResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGetDownloadDocuments.setGetDownloadDocumentsResponse(data);
            }

        });
    }

    public void homebannerList(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<HomeBannerListResponse> call = serviceInterface.homebannerList(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<HomeBannerListResponse>() {
            @Override
            public void onResponse(Call<HomeBannerListResponse> call, Response<HomeBannerListResponse> response) {
                if (response.isSuccessful()) {
                    objectHomeBannerList.setHomeBannerListResponse(response.body());
                } else {
                    HomeBannerListResponse data = new HomeBannerListResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectHomeBannerList.setHomeBannerListResponse(data);
                }
            }

            @Override
            public void onFailure(Call<HomeBannerListResponse> call, Throwable t) {
                HomeBannerListResponse data = new HomeBannerListResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectHomeBannerList.setHomeBannerListResponse(data);
            }
        });

    }

    public void updatePost(String userId, String token, String deviceToken, String deviceType, String postId, String details, String attachments) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<UpdatePostResponse> call = serviceInterface.updatePost(userId, token, deviceToken, deviceType, postId, details, attachments);
        call.enqueue(new Callback<UpdatePostResponse>() {
            @Override
            public void onResponse(Call<UpdatePostResponse> call, Response<UpdatePostResponse> response) {
                if (response.isSuccessful()) {
                    objectUpdatePost.setUpdatePostResponse(response.body());
                } else {
                    UpdatePostResponse data = new UpdatePostResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectUpdatePost.setUpdatePostResponse(data);
                }
            }

            @Override
            public void onFailure(Call<UpdatePostResponse> call, Throwable t) {
                UpdatePostResponse data = new UpdatePostResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectUpdatePost.setUpdatePostResponse(data);
            }
        });
    }

    public void getKeyLocationService(double lat, double lng) {

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://maps.googleapis.com/maps/api/").build();
        ;

        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);

        Call<ResponseBody> call = serviceInterface.getkeyLocations(lat + "," + lng, "1000", "AIzaSyCOuwQpSwnMAGbZFCrFDH8gpu9PnSJLk0M");


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String s = null;
                try {
                    s = response.body().string();
                    objectKeyLocation.setKeyLocation(new JSONObject(s));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getProduct(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetProductResponse> call = serviceInterface.getProduct(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                if (response.isSuccessful()) {
                    objectGetProduct.setGetProductResponse(response.body());
                } else {
                    GetProductResponse data = new GetProductResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGetProduct.setGetProductResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                GetProductResponse data = new GetProductResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGetProduct.setGetProductResponse(data);
                t.printStackTrace();
            }
        });
    }

    public void addUserVas(String userId, String token, String deviceToken, String deviceType, String vasId, final FirstFragment fragment) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<AddUserVasResponse> call = serviceInterface.addUserVas(userId, token, deviceToken, deviceType, vasId);
        call.enqueue(new Callback<AddUserVasResponse>() {
            @Override
            public void onResponse(Call<AddUserVasResponse> call, Response<AddUserVasResponse> response) {
                if (response.isSuccessful()) {
                    objectAddUserVas.setAddUserVasResponse(response.body(), fragment);
                } else {
                    AddUserVasResponse data = new AddUserVasResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectAddUserVas.setAddUserVasResponse(data, fragment);
                }
            }

            @Override
            public void onFailure(Call<AddUserVasResponse> call, Throwable t) {
                AddUserVasResponse data = new AddUserVasResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectAddUserVas.setAddUserVasResponse(data, fragment);
            }
        });
    }

    public void getTimeSlot(String userId, String token, String deviceToken, String deviceType, String projectId, String date) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetTimeSlotResponse> call = serviceInterface.getTimeSlot(userId, token, deviceToken, deviceType, projectId, date);
        call.enqueue(new Callback<GetTimeSlotResponse>() {
            @Override
            public void onResponse(Call<GetTimeSlotResponse> call, Response<GetTimeSlotResponse> response) {
                if (response.isSuccessful()) {
                    objectGetTimeSlot.setGetTimeSlotResponse(response.body());
                } else {
                    GetTimeSlotResponse data = new GetTimeSlotResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction(" ");
                    objectGetTimeSlot.setGetTimeSlotResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetTimeSlotResponse> call, Throwable t) {
                GetTimeSlotResponse data = new GetTimeSlotResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                data.setAction(" ");
                objectGetTimeSlot.setGetTimeSlotResponse(data);
            }
        });
    }

    public void getAvailableTimeSlot(String userId, String token, String deviceToken, String deviceType, String amenityId, String date) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetAvailableTimeSlotResponse> call = serviceInterface.getAvailableTimeSlot(userId, token, deviceToken, deviceType, amenityId, date);
        call.enqueue(new Callback<GetAvailableTimeSlotResponse>() {
            @Override
            public void onResponse(Call<GetAvailableTimeSlotResponse> call, Response<GetAvailableTimeSlotResponse> response) {
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        objectGetAvailableTimeSlot.setGetAvailableTimeSlotResponse(response.body());
                    } else {
                        GetAvailableTimeSlotResponse data = new GetAvailableTimeSlotResponse();
                        data.setSuccess(false);
                        data.setMessage("Something went wrong, Try again later");
                        data.setAction("");
                        objectGetAvailableTimeSlot.setGetAvailableTimeSlotResponse(data);
                    }
                } else {
                    GetAvailableTimeSlotResponse data = new GetAvailableTimeSlotResponse();
                    data.setSuccess(false);
                    data.setMessage("Something went wrong, Try again later");
                    data.setAction("");
                    objectGetAvailableTimeSlot.setGetAvailableTimeSlotResponse(data);
                }
            }

            @Override
            public void onFailure(Call<GetAvailableTimeSlotResponse> call, Throwable t) {
                GetAvailableTimeSlotResponse data = new GetAvailableTimeSlotResponse();
                data.setSuccess(false);
                data.setMessage("Something went wrong, Try again later");
                objectGetAvailableTimeSlot.setGetAvailableTimeSlotResponse(data);
                t.printStackTrace();
            }
        });

    }

    public void setProfileImage(String userId, String token, String deviceToken, String deviceType, String image) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SetProfileImageResponse> call = serviceInterface.setProfileImage(userId, token, deviceToken, deviceType, image);
        call.enqueue(new Callback<SetProfileImageResponse>() {
            @Override
            public void onResponse(Call<SetProfileImageResponse> call, Response<SetProfileImageResponse> response) {
                if (response.isSuccessful()) {
                    objectSetProfileImage.setSetProfileImageResponse(response.body());
                } else {
                    SetProfileImageResponse setProfileImageResponse = new SetProfileImageResponse();
                    setProfileImageResponse.setSuccess(false);
                    setProfileImageResponse.setMessage("Something went wrong, Try again later");
                    setProfileImageResponse.setAction(" ");
                    objectSetProfileImage.setSetProfileImageResponse(setProfileImageResponse);
                }
            }

            @Override
            public void onFailure(Call<SetProfileImageResponse> call, Throwable t) {
                SetProfileImageResponse setProfileImageResponse = new SetProfileImageResponse();
                setProfileImageResponse.setSuccess(false);
                setProfileImageResponse.setMessage("Something went wrong, Try again later");
                setProfileImageResponse.setAction(" ");
                objectSetProfileImage.setSetProfileImageResponse(setProfileImageResponse);
            }
        });

    }

    public void getProfileDetails(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<GetProfileDetailsResponse> call = serviceInterface.getProfileDetails(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<GetProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<GetProfileDetailsResponse> call, Response<GetProfileDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectGetProfileDetails.setGetProfileDetailsResponse(response.body());
                } else {
                    GetProfileDetailsResponse getProfileDetailsResponse = new GetProfileDetailsResponse();
                    getProfileDetailsResponse.setSuccess(false);
                    getProfileDetailsResponse.setMessage("Something went wrong, Try again later");
                    getProfileDetailsResponse.setAction(" ");
                    objectGetProfileDetails.setGetProfileDetailsResponse(getProfileDetailsResponse);
                }
            }

            @Override
            public void onFailure(Call<GetProfileDetailsResponse> call, Throwable t) {
                GetProfileDetailsResponse getProfileDetailsResponse = new GetProfileDetailsResponse();
                getProfileDetailsResponse.setSuccess(false);
                getProfileDetailsResponse.setMessage("Something went wrong, Try again later");
                getProfileDetailsResponse.setAction(" ");
                objectGetProfileDetails.setGetProfileDetailsResponse(getProfileDetailsResponse);
            }
        });
    }

    public void updateProfileDetails(String userId, String token, String deviceToken, String deviceType, String isPublic, String companyName, String designation, String homeTown, String livesIn, String dateOfBirth, String marriageAnniversary, String interest, String companyType, String companyLocation) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<UpdateProfileDetailsResponse> call = serviceInterface.updateProfileDetails(userId, token, deviceToken, deviceType, isPublic, companyName,
                designation, homeTown, livesIn, dateOfBirth, marriageAnniversary, interest, companyType, companyLocation);
        call.enqueue(new Callback<UpdateProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileDetailsResponse> call, Response<UpdateProfileDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectUpdateProfileDetails.setUpdateProfileDetailsResponse(response.body());
                } else {
                    UpdateProfileDetailsResponse updateProfileDetailsResponse = new UpdateProfileDetailsResponse();
                    updateProfileDetailsResponse.setSuccess(false);
                    updateProfileDetailsResponse.setMessage("Something went wrong, Try again later.");
                    objectUpdateProfileDetails.setUpdateProfileDetailsResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileDetailsResponse> call, Throwable t) {
                UpdateProfileDetailsResponse updateProfileDetailsResponse = new UpdateProfileDetailsResponse();
                updateProfileDetailsResponse.setSuccess(false);
                updateProfileDetailsResponse.setMessage("Something went wrong, Try again later.");
                objectUpdateProfileDetails.setUpdateProfileDetailsResponse(updateProfileDetailsResponse);
            }
        });
    }

    public void updateProfileEducationDetails(String userId, String token, String deviceToken, String deviceType, String university, String degree, String yearFrom, String yearTo, String educationId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<UpdateProfileEducationResponse> call = serviceInterface.updateProfileEducationDetails(userId, token, deviceToken, deviceType, university, degree, yearFrom, yearTo, educationId);
        call.enqueue(new Callback<UpdateProfileEducationResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileEducationResponse> call, Response<UpdateProfileEducationResponse> response) {
                if (response.isSuccessful()) {
                    objectUpdateProfileEducation.setUpdateProfileEducationResponse(response.body());
                } else {
                    UpdateProfileEducationResponse updateProfileEducationResponse = new UpdateProfileEducationResponse();
                    updateProfileEducationResponse.setSuccess(false);
                    updateProfileEducationResponse.setMessage("Something went wrong, Try again later.");
                    updateProfileEducationResponse.setAction("");
                    objectUpdateProfileEducation.setUpdateProfileEducationResponse(updateProfileEducationResponse);
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileEducationResponse> call, Throwable t) {
                UpdateProfileEducationResponse updateProfileEducationResponse = new UpdateProfileEducationResponse();
                updateProfileEducationResponse.setSuccess(false);
                updateProfileEducationResponse.setMessage("Something went wrong, Try again later.");
                updateProfileEducationResponse.setAction("");
                objectUpdateProfileEducation.setUpdateProfileEducationResponse(updateProfileEducationResponse);
            }
        });
    }

    public void sendNewEnquiry(String userId, String token, String deviceToken, String deviceType, String fName, String lName, String email, String mobile, String projectId, String description) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SendNewEnquiryResponse> call = serviceInterface.sendNewEnquiry(userId, token, deviceToken, deviceType, fName, lName, email, mobile, projectId, description);
        call.enqueue(new Callback<SendNewEnquiryResponse>() {
            @Override
            public void onResponse(Call<SendNewEnquiryResponse> call, Response<SendNewEnquiryResponse> response) {
                if (response.isSuccessful()) {
                    objectSendNewEnquiry.setSendNewEnquiryResponse(response.body());
                } else {
                    SendNewEnquiryResponse sendNewEnquiryResponse = new SendNewEnquiryResponse();
                    sendNewEnquiryResponse.setSuccess(false);
                    sendNewEnquiryResponse.setMessage("Something went wrong, Try again later");
                    sendNewEnquiryResponse.setAction(" ");
                    objectSendNewEnquiry.setSendNewEnquiryResponse(sendNewEnquiryResponse);
                }
            }

            @Override
            public void onFailure(Call<SendNewEnquiryResponse> call, Throwable t) {
                SendNewEnquiryResponse sendNewEnquiryResponse = new SendNewEnquiryResponse();
                sendNewEnquiryResponse.setSuccess(false);
                sendNewEnquiryResponse.setMessage("Something went wrong, Try again later");
                sendNewEnquiryResponse.setAction(" ");
                objectSendNewEnquiry.setSendNewEnquiryResponse(sendNewEnquiryResponse);
                t.printStackTrace();
            }
        });
    }

    public void deleteProfileEducationDetails(String userId, String token, String deviceToken, String deviceType, String educationId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<DeleteProfileEducationDetailsResponse> call = serviceInterface.deleteProfileEducationDetails(userId, token, deviceToken, deviceType, educationId);
        call.enqueue(new Callback<DeleteProfileEducationDetailsResponse>() {
            @Override
            public void onResponse(Call<DeleteProfileEducationDetailsResponse> call, Response<DeleteProfileEducationDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectDeleteProfileEducationDetails.setDeleteProfileEducationDetailsResponse(response.body());
                } else {
                    DeleteProfileEducationDetailsResponse deleteProfileEducationDetailsResponse = new DeleteProfileEducationDetailsResponse();
                    deleteProfileEducationDetailsResponse.setSuccess(false);
                    deleteProfileEducationDetailsResponse.setMessage("Something went wrong, Please try again later");
                    deleteProfileEducationDetailsResponse.setAction(" ");
                    objectDeleteProfileEducationDetails.setDeleteProfileEducationDetailsResponse(deleteProfileEducationDetailsResponse);
                }
            }

            @Override
            public void onFailure(Call<DeleteProfileEducationDetailsResponse> call, Throwable t) {
                DeleteProfileEducationDetailsResponse deleteProfileEducationDetailsResponse = new DeleteProfileEducationDetailsResponse();
                deleteProfileEducationDetailsResponse.setSuccess(false);
                deleteProfileEducationDetailsResponse.setMessage("Something went wrong, Please try again later");
                deleteProfileEducationDetailsResponse.setAction(" ");
                objectDeleteProfileEducationDetails.setDeleteProfileEducationDetailsResponse(deleteProfileEducationDetailsResponse);
            }
        });
    }

    public void viewProfileDetails(String userId, String token, String deviceToken, String deviceType, String profileId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ViewProfileDetailsResponse> call = serviceInterface.viewProfileDetails(userId, token, deviceToken, deviceType, profileId);
        call.enqueue(new Callback<ViewProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<ViewProfileDetailsResponse> call, Response<ViewProfileDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectViewProfileDetails.setViewProfileDetailsResponse(response.body());
                } else {
                    ViewProfileDetailsResponse viewProfileDetailsResponse = new ViewProfileDetailsResponse();
                    viewProfileDetailsResponse.setSuccess(false);
                    viewProfileDetailsResponse.setMessage(String.valueOf(R.string.error_Message));
                    viewProfileDetailsResponse.setAction(" ");
                    objectViewProfileDetails.setViewProfileDetailsResponse(viewProfileDetailsResponse);

                }
            }

            @Override
            public void onFailure(Call<ViewProfileDetailsResponse> call, Throwable t) {
                ViewProfileDetailsResponse viewProfileDetailsResponse = new ViewProfileDetailsResponse();
                viewProfileDetailsResponse.setSuccess(false);
                viewProfileDetailsResponse.setMessage(String.valueOf(R.string.error_Message));
                viewProfileDetailsResponse.setAction(" ");
                objectViewProfileDetails.setViewProfileDetailsResponse(viewProfileDetailsResponse);
            }
        });
    }

    public void loadVASSettings(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadVASSettingsResponse> call = serviceInterface.loadVASSettings(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<LoadVASSettingsResponse>() {
            @Override
            public void onResponse(Call<LoadVASSettingsResponse> call, Response<LoadVASSettingsResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadVASSettings.setLoadVASSettingsResponse(response.body());
                } else {
                    LoadVASSettingsResponse loadVASSettingsResponse = new LoadVASSettingsResponse();
                    loadVASSettingsResponse.setSuccess(false);
                    loadVASSettingsResponse.setMessage("Something went wrong, Try again later");
                    loadVASSettingsResponse.setAction(" ");
                    objectLoadVASSettings.setLoadVASSettingsResponse(loadVASSettingsResponse);
                }
            }

            @Override
            public void onFailure(Call<LoadVASSettingsResponse> call, Throwable t) {
                LoadVASSettingsResponse loadVASSettingsResponse = new LoadVASSettingsResponse();
                loadVASSettingsResponse.setSuccess(false);
                loadVASSettingsResponse.setMessage("Something went wrong, Try again later");
                loadVASSettingsResponse.setAction(" ");
                objectLoadVASSettings.setLoadVASSettingsResponse(loadVASSettingsResponse);

            }
        });
    }

    public void saveVASRequirements(String userId, String token, String deviceToken, String deviceType, String selectedItems, String description, String budgetRange, String ProjectId) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<SaveVASRequirementsResponse> call = serviceInterface.saveVASRequirements(userId, token, deviceToken, deviceType, selectedItems, description, budgetRange, ProjectId);
        call.enqueue(new Callback<SaveVASRequirementsResponse>() {
            @Override
            public void onResponse(Call<SaveVASRequirementsResponse> call, Response<SaveVASRequirementsResponse> response) {
                if (response.isSuccessful()) {
                    objectSaveVASRequirements.setSaveVASRequirementsResponse(response.body());

                } else {
                    SaveVASRequirementsResponse saveVASRequirementsResponse = new SaveVASRequirementsResponse();
                    saveVASRequirementsResponse.setSuccess(false);
                    saveVASRequirementsResponse.setMessage("Something went wrong, Try again later.");
                    saveVASRequirementsResponse.setAction("  ");
                    objectSaveVASRequirements.setSaveVASRequirementsResponse(saveVASRequirementsResponse);
                }
            }

            @Override
            public void onFailure(Call<SaveVASRequirementsResponse> call, Throwable t) {
                SaveVASRequirementsResponse saveVASRequirementsResponse = new SaveVASRequirementsResponse();
                saveVASRequirementsResponse.setSuccess(false);
                saveVASRequirementsResponse.setMessage("Something went wrong, Try again later.");
                saveVASRequirementsResponse.setAction("  ");
                objectSaveVASRequirements.setSaveVASRequirementsResponse(saveVASRequirementsResponse);
            }
        });
    }

    public void loadProfileSettings(String userId, String token, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<LoadProfileSettingsResponse> call = serviceInterface.loadProfileSettings(userId, token, deviceToken, deviceType);
        call.enqueue(new Callback<LoadProfileSettingsResponse>() {
            @Override
            public void onResponse(Call<LoadProfileSettingsResponse> call, Response<LoadProfileSettingsResponse> response) {
                if (response.isSuccessful()) {
                    objectLoadProfileSettings.setLoadProfileSettingsResponse(response.body());

                } else {
                    LoadProfileSettingsResponse loadProfileSettingsResponse = new LoadProfileSettingsResponse();
                    loadProfileSettingsResponse.setSuccess(false);
                    loadProfileSettingsResponse.setMessage("Something went wrong, Try again later");
                    loadProfileSettingsResponse.setAction(" ");
                    objectLoadProfileSettings.setLoadProfileSettingsResponse(loadProfileSettingsResponse);
                }
            }

            @Override
            public void onFailure(Call<LoadProfileSettingsResponse> call, Throwable t) {
                LoadProfileSettingsResponse loadProfileSettingsResponse = new LoadProfileSettingsResponse();
                loadProfileSettingsResponse.setSuccess(false);
                loadProfileSettingsResponse.setMessage("Something went wrong, Try again later");
                loadProfileSettingsResponse.setAction(" ");
                objectLoadProfileSettings.setLoadProfileSettingsResponse(loadProfileSettingsResponse);
            }
        });
    }

    public void updateGroupJoinRequest(String userId, String token, String deviceToken, String deviceType, String groupId, String requestId, String status) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<UpdateGroupJoinRequestResponse> call = serviceInterface.updateGroupJoinRequest(userId, token, deviceToken, deviceType, groupId, requestId, status);
        call.enqueue(new Callback<UpdateGroupJoinRequestResponse>() {
            @Override
            public void onResponse(Call<UpdateGroupJoinRequestResponse> call, Response<UpdateGroupJoinRequestResponse> response) {
                if (response.isSuccessful()) {
                    objectUpdateGroupJoinRequest.setUpdateGroupJoinRequestResponse(response.body());
                } else {
                    UpdateGroupJoinRequestResponse updateGroupJoinRequestResponse = new UpdateGroupJoinRequestResponse();
                    updateGroupJoinRequestResponse.setSuccess(false);
                    updateGroupJoinRequestResponse.setMessage("Something went wrong, Try again later");
                    updateGroupJoinRequestResponse.setAction(" ");
                    objectUpdateGroupJoinRequest.setUpdateGroupJoinRequestResponse(updateGroupJoinRequestResponse);
                }
            }

            @Override
            public void onFailure(Call<UpdateGroupJoinRequestResponse> call, Throwable t) {
                UpdateGroupJoinRequestResponse updateGroupJoinRequestResponse = new UpdateGroupJoinRequestResponse();
                updateGroupJoinRequestResponse.setSuccess(false);
                updateGroupJoinRequestResponse.setMessage("Something went wrong, Try again later");
                updateGroupJoinRequestResponse.setAction(" ");
                objectUpdateGroupJoinRequest.setUpdateGroupJoinRequestResponse(updateGroupJoinRequestResponse);
            }
        });
    }

    public void changeGroupDetails(String userId, String token, String deviceToken, String deviceType, String projectId, String group_id, String group_name, String group_about, String isPublic) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ChangeGroupDetailsResponse> call = serviceInterface.changeGroupDetails(userId, token, deviceToken, deviceType, projectId, group_id, group_name, group_about, isPublic);
        call.enqueue(new Callback<ChangeGroupDetailsResponse>() {
            @Override
            public void onResponse(Call<ChangeGroupDetailsResponse> call, Response<ChangeGroupDetailsResponse> response) {
                if (response.isSuccessful()) {
                    objectChangeGroupDetails.setChangeGroupDetailsResponse(response.body());
                } else {
                    ChangeGroupDetailsResponse changeGroupDetailsResponse = new ChangeGroupDetailsResponse();
                    changeGroupDetailsResponse.setSuccess(true);
                    changeGroupDetailsResponse.setMessage("Something went wrong, Try again later");
                    changeGroupDetailsResponse.setAction("");
                    objectChangeGroupDetails.setChangeGroupDetailsResponse(changeGroupDetailsResponse);
                }
            }

            @Override
            public void onFailure(Call<ChangeGroupDetailsResponse> call, Throwable t) {
                ChangeGroupDetailsResponse changeGroupDetailsResponse = new ChangeGroupDetailsResponse();
                changeGroupDetailsResponse.setSuccess(true);
                changeGroupDetailsResponse.setMessage("Something went wrong, Try again later");
                changeGroupDetailsResponse.setAction("");
                objectChangeGroupDetails.setChangeGroupDetailsResponse(changeGroupDetailsResponse);
            }
        });
    }

    public void forgotPassword(String emailId, String deviceToken, String deviceType) {
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        Call<ForgotPasswordResponse> call = serviceInterface.forgotPassword(emailId, deviceToken, deviceType, "v3");
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.isSuccessful()) {
                    objectForgotPassword.setForgotPasswordResponse(response.body());
                } else {
                    ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
                    forgotPasswordResponse.setSuccess(false);
                    forgotPasswordResponse.setMessage("Something went wrong, Try again later");
                    forgotPasswordResponse.setAction("");
                    objectForgotPassword.setForgotPasswordResponse(forgotPasswordResponse);
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
                forgotPasswordResponse.setSuccess(false);
                forgotPasswordResponse.setAction("");
                forgotPasswordResponse.setMessage("Something went wrong, Try again later");
                objectForgotPassword.setForgotPasswordResponse(forgotPasswordResponse);
            }
        });
    }
}