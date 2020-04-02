package com.mlife.web.api;

import com.mlife.web.model.AcceptEventResponse;
import com.mlife.web.model.AddCallBackResponse;
import com.mlife.web.model.AddClassifiedResponse;
import com.mlife.web.model.AddCommentsResponse;
import com.mlife.web.model.AddReportResponse;
import com.mlife.web.model.AddServiceRequestResponse;
import com.mlife.web.model.AddUserVasResponse;
import com.mlife.web.model.AddedValueListResponse;
import com.mlife.web.model.AllPropertyResponse;
import com.mlife.web.model.AmentieeListResponse;
import com.mlife.web.model.AppVersion;
import com.mlife.web.model.BookClubhouseResponse;
import com.mlife.web.model.CancelSiteVisitResponse;
import com.mlife.web.model.ChangeBookingStatusResponse;
import com.mlife.web.model.ChangeGroupDetailsResponse;
import com.mlife.web.model.ChangeGroupNameResponse;
import com.mlife.web.model.ClubHouseListResponse;
import com.mlife.web.model.CommitteeManagmentListResponse;
import com.mlife.web.model.ConstructionResponse;
import com.mlife.web.model.CreateGroupResponse;
import com.mlife.web.model.CreatePostResponse;
import com.mlife.web.model.DeletePostResponse;
import com.mlife.web.model.DeleteProfileEducationDetailsResponse;
import com.mlife.web.model.Example;
import com.mlife.web.model.ExporeGroupsResponse;
import com.mlife.web.model.ForgotPasswordResponse;
import com.mlife.web.model.ForgotPasswordVerifyResponse;
import com.mlife.web.model.GetAvailableTimeSlotResponse;
import com.mlife.web.model.GetDownloadDocumentsResponse;
import com.mlife.web.model.GetPaymentDetailsResponse;
import com.mlife.web.model.GetPostPaymentDetailsResponse;
import com.mlife.web.model.GetPostsResponse;
import com.mlife.web.model.GetProfileDetailsResponse;
import com.mlife.web.model.GetProjectDetailResponse;
import com.mlife.web.model.GetServiceRequestsResponse;
import com.mlife.web.model.GetTimeSlotResponse;
import com.mlife.web.model.GroupAboutbyGroupResponse;
import com.mlife.web.model.HomeBannerListResponse;
import com.mlife.web.model.JoinGroupResponse;
import com.mlife.web.model.JoinedGroupsResponse;
import com.mlife.web.model.LikePostResponse;
import com.mlife.web.model.LoadCommentsResponse;
import com.mlife.web.model.LoadGroupTypesResponse;
import com.mlife.web.model.LoadProfileSettingsResponse;
import com.mlife.web.model.LoadVASSettingsResponse;
import com.mlife.web.model.LoginResponse;
import com.mlife.web.model.LogoutResponse;
import com.mlife.web.model.MPaymentResponse;
import com.mlife.web.model.MembersbyGroupResponse;
import com.mlife.web.model.MyGroupsResponse;
import com.mlife.web.model.NoticeListResponse;
import com.mlife.web.model.NotificationListResponse;
import com.mlife.web.model.OfferListResponse;
import com.mlife.web.model.ProjectConstructionUpdateResponse;
import com.mlife.web.model.ProjectGalleryResponse;
import com.mlife.web.model.ProjectListResponse;
import com.mlife.web.model.ProjectLocationResponse;
import com.mlife.web.model.PropertyDetailResponse;
import com.mlife.web.model.PropertyResponse;
import com.mlife.web.model.RemoveGroupMemberResponse;
import com.mlife.web.model.RemoveOfferResponse;
import com.mlife.web.model.SaveVASRequirementsResponse;
import com.mlife.web.model.SendNewEnquiryResponse;
import com.mlife.web.model.SetGroupImageResponse;
import com.mlife.web.model.SetProfileImageResponse;
import com.mlife.web.model.SetSiteVisitDateResponse;
import com.mlife.web.model.SubmitFeedbackResponse;
import com.mlife.web.model.TicketLogsResponse;
import com.mlife.web.model.UpdateGroupJoinRequestResponse;
import com.mlife.web.model.UpdatePostResponse;
import com.mlife.web.model.UpdateProfileDetailsResponse;
import com.mlife.web.model.UpdateProfileEducationResponse;
import com.mlife.web.model.ViewGroupResponse;
import com.mlife.web.model.AddTicketResponse;
import com.mlife.web.model.ChangeGroupAboutResponse;
import com.mlife.web.model.EventListResponse;
import com.mlife.web.model.GetProductResponse;
import com.mlife.web.model.LoadDocumentsResponse;
import com.mlife.web.model.LoadSiteVisitsResponse;
import com.mlife.web.model.ProjectAmenitiesResponse;
import com.mlife.web.model.ProjectOffersResponse;
import com.mlife.web.model.ResidentListResponse;
import com.mlife.web.model.TicketListResponse;
import com.mlife.web.model.ViewProfileDetailsResponse;

import java.net.HttpCookie;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceInterface {

    @POST("site/appversion")
    @FormUrlEncoded
    Call<AppVersion> appversion(@Field("device") String deviceType);

    @POST("auth/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Header("Cookie") String userCookie,
                              @Field("login") String login,
                              @Field("password") String password,
                              @Field("deviceToken") String deviceToken,
                              @Field("deviceType") String deviceType,
                              @Field("version") String version,
                              @Field("g-recaptcha-response") String response);

    @POST("auth/validateUserInfo")
    @FormUrlEncoded
    Call<Example> loginWithOTP(
            @Field("email") String email,
            @Field("deviceToken") String deviceToken,
            @Field("deviceType") String deviceType);

    @POST("auth/validateToken")
    @FormUrlEncoded
    Call<ResponseBody> loginOTP(@Header("Cookie") String userCookie,
            @Field("otp") String otp,
            @Field("token") String token,
            @Field("deviceToken") String deviceToken,
            @Field("deviceType") String deviceType,
            @Field("email") String email);

    @POST("auth/getKeys")
    @FormUrlEncoded
    Call<ResponseBody> getKeys(@Field("deviceToken") String deviceToken,
                               @Field("deviceType") String deviceType);

    @POST("auth/forgotPassword_verify")
    @FormUrlEncoded
    Call<ForgotPasswordVerifyResponse> forgotPassword_verify(@Field("emailId") String emailId,
                                                             @Field("deviceToken") String deviceToken,
                                                             @Field("version") String version,
                                                             @Field("deviceType") String deviceType,
                                                             @Field("otp") String otp);

    @POST("auth/logout")
    @FormUrlEncoded
    Call<LogoutResponse> logout(@Field("userId") String userId,
                                @Field("token") String token,
                                @Field("deviceToken") String deviceToken,
                                @Field("deviceType") String deviceType);

    @POST("site/data/getProperties")
    @FormUrlEncoded
    Call<PropertyResponse> getProperty(
                                       @Field("userId") String userId,
                                       @Field("token") String token,
                                       @Field("deviceToken") String deviceToken,
                                       @Field("deviceType") String deviceType);

    @POST("site/data/getPropertyDetails")
    @FormUrlEncoded
    Call<PropertyDetailResponse> getPropertyDetais(@Field("userId") String userId,
                                                   @Field("token") String token,
                                                   @Field("deviceToken") String deviceToken,
                                                   @Field("deviceType") String deviceType,
                                                   @Field("pId") String pId);

    @POST("site/data/getConstructionUpdates")
    @FormUrlEncoded
    Call<ConstructionResponse> getConstructionUpdates(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType,
                                                      @Field("pId") String pId,
                                                      @Field("method") String method);

    @POST("site/data/getProjectNames")
    @FormUrlEncoded
    Call<AllPropertyResponse> getAllProperties(@Field("userId") String userId,
                                               @Field("token") String token,
                                               @Field("deviceToken") String deviceToken,
                                               @Field("deviceType") String deviceType);

    @POST("site/api/setSiteVisitDate")
    @FormUrlEncoded
    Call<SetSiteVisitDateResponse> scheduleSiteVisit(@Field("userId") String userId,
                                                     @Field("token") String token,
                                                     @Field("deviceToken") String deviceToken,
                                                     @Field("deviceType") String deviceType,
                                                     @Field("date") String date,
                                                     @Field("timeSlot") String timeSlot,
                                                     @Field("property") String property,
                                                     @Field("projectId") String projectId,
                                                     @Field("pId") String pId);

    @POST("site/api/loadSiteVisits")
    @FormUrlEncoded
    Call<LoadSiteVisitsResponse> siteVisits(@Field("userId") String userId,
                                            @Field("token") String token,
                                            @Field("deviceToken") String deviceToken,
                                            @Field("deviceType") String deviceType);

    @POST("site/api/loadGroupTypes")
    @FormUrlEncoded
    Call<LoadGroupTypesResponse> groupsTypes(@Field("userId") String userId,
                                             @Field("token") String token,
                                             @Field("deviceToken") String deviceToken,
                                             @Field("deviceType") String deviceType);

    @POST("site/api/createGroup")
    @FormUrlEncoded
    Call<CreateGroupResponse> createGroup(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("groupName") String groupName,
                                          @Field("groupType") String groupType,
                                          @Field("description") String description,
                                          @Field("projectId") String projectId,
                                          @Field("isPublic") String isPublic );

    @POST("site/api/exploreGroups")
    @FormUrlEncoded
    Call<ExporeGroupsResponse> exploreGroups(@Field("userId") String userId,
                                             @Field("token") String token,
                                             @Field("deviceToken") String deviceToken,
                                             @Field("deviceType") String deviceType,
                                             @Field("projectId") String projectId);

    @POST("site/api/joinedGroups")
    @FormUrlEncoded
    Call<JoinedGroupsResponse> joinedGroups(@Field("userId") String userId,
                                            @Field("token") String token,
                                            @Field("deviceToken") String deviceToken,
                                            @Field("deviceType") String deviceType);

    @POST("site/api/myGroups")
    @FormUrlEncoded
    Call<MyGroupsResponse> myGroups(@Field("userId") String userId,
                                    @Field("token") String token,
                                    @Field("deviceToken") String deviceToken,
                                    @Field("deviceType") String deviceType);

    @POST("site/api/cancelSiteVisit")
    @FormUrlEncoded
    Call<CancelSiteVisitResponse> cancelSiteVisit(@Field("userId") String userId,
                                                  @Field("token") String token,
                                                  @Field("deviceToken") String deviceToken,
                                                  @Field("deviceType") String deviceType,
                                                  @Field("siteVisitId") String siteVisitId);

    @POST("site/api/joinGroup")
    @FormUrlEncoded
    Call<JoinGroupResponse> joinGroup(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("groupId") String groupId);

    @POST("site/api/viewGroup")
    @FormUrlEncoded
    Call<ViewGroupResponse> viewGroup(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("groupId") String groupId);

    @POST("site/Managenotices/noticeList")
    @FormUrlEncoded
    Call<NoticeListResponse> noticeList(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("projectId") String projectId);

    @POST("site/manageposts/createPost")
    @FormUrlEncoded
    Call<CreatePostResponse> createPost(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("groupId") String groupId,
                                        @Field("details") String details,
                                        @Field("post_attachments") String images);

    @POST("site/Manageevents/eventList")
    @FormUrlEncoded
    Call<EventListResponse> eventList(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("projectId") String projectId);

    @POST("site/Manageevents/acceptEvent")
    @FormUrlEncoded
    Call<AcceptEventResponse> acceptEvent(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("userEventId") String userEventId,
                                          @Field("interestStatus") String interestStatus);

    @POST("site/manageposts/getPosts")
    @FormUrlEncoded
    Call<GetPostsResponse> getPosts(@Field("userId") String userId,
                                    @Field("token") String token,
                                    @Field("deviceToken") String deviceToken,
                                    @Field("deviceType") String deviceType,
                                    @Field("groupId") String groupId);

    @POST("site/manageposts/loadComments")
    @FormUrlEncoded
    Call<LoadCommentsResponse> loadComments(@Field("userId") String userId,
                                            @Field("token") String token,
                                            @Field("deviceToken") String deviceToken,
                                            @Field("deviceType") String deviceType,
                                            @Field("postId") String postId);

    @POST("site/manageposts/addComments")
    @FormUrlEncoded
    Call<AddCommentsResponse> addComments(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("postId") String postId,
                                          @Field("comments") String comments);

    @POST("site/manageposts/likePost")
    @FormUrlEncoded
    Call<LikePostResponse> likePost(@Field("userId") String userId,
                                    @Field("token") String token,
                                    @Field("deviceToken") String deviceToken,
                                    @Field("deviceType") String deviceType,
                                    @Field("postId") String postId);

    @POST("site/clubhouse/clubhouseList")
    @FormUrlEncoded
    Call<ClubHouseListResponse> clubhouseList(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("projectId") String projectId,
                                              @Field("clubhouse_booked") String clubhouse_booked);

    @POST("site/clubhouse/amentieeList")
    @FormUrlEncoded
    Call<AmentieeListResponse> amentieeList(@Field("userId") String userId,
                                            @Field("token") String token,
                                            @Field("deviceToken") String deviceToken,
                                            @Field("deviceType") String deviceType,
                                            @Field("projectId") String projectId);

    @POST("site/clubhouse/changeBookingStatus")
    @FormUrlEncoded
    Call<ChangeBookingStatusResponse> changeBookingStatus(@Field("userId") String userId,
                                                          @Field("token") String token,
                                                          @Field("deviceToken") String deviceToken,
                                                          @Field("deviceType") String deviceType,
                                                          @Field("clubhouse_booking_id") String clubhouse_booking_id,
                                                          @Field("clubhouse_booked") String clubhouse_booked);

    @POST("site/clubhouse/bookClubhouse")
    @FormUrlEncoded
    Call<BookClubhouseResponse> bookClubhouse(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("projectId") String projectId,
                                              @Field("clubhouse_booking_amenty_id") String clubhouse_booking_amenty_id,
                                              @Field("clubhouse_booking_date") String clubhouse_booking_date,
                                              @Field("clubhouse_booking_timeslot") String clubhouse_booking_timeslot,
                                              @Field("clubhouse_booking_noofpeople") String clubhouse_booking_noofpeople,
                                              @Field("bId") String bId);

    @POST("site/data/getPaymentDetails")
    @FormUrlEncoded
    Call<GetPaymentDetailsResponse> getPaymentDetails(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType,
                                                      @Field("bId") String projectId);


    @POST("site/manageaddedvalue/addedvalueList")
    @FormUrlEncoded
    Call<AddedValueListResponse> addedValueList(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("projectId") String projectId,
                                                @Field("value_addedservice_type") String value_addedservice_type);

    @POST("site/managepayments/addPayment")
    @FormUrlEncoded
    Call<MPaymentResponse> insertPaymentDetail(@Field("userId") String userId,
                                               @Field("token") String token,
                                               @Field("deviceToken") String deviceToken,
                                               @Field("deviceType") String deviceType,
                                               @Field("projectId") String projectId,
                                               @Field("buildingId") String buildingId,
                                               @Field("towerId") String towerId,
                                               @Field("paymentId") String paymentId,
                                               @Field("bill_amount") String bill_amount);


    @POST("site/manageoffers/myOfferList")
    @FormUrlEncoded
    Call<OfferListResponse> myOfferList(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("projectId") String projectId,
                                      @Field("offerType") String offerType);

    @POST("site/manageoffers/offerList")
    @FormUrlEncoded
    Call<OfferListResponse> offerList(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("projectId") String projectId,
                                      @Field("offerType") String offerType);

    @POST("site/manageoffers/removeOffer")
    @FormUrlEncoded
    Call<RemoveOfferResponse> removeOffer(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("offerId") String offerId);

    @POST("site/manageoffers/addClassified")
    @FormUrlEncoded
    Call<AddClassifiedResponse> addClassified(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("projectId") String projectId,
                                              @Field("offer_title") String offer_title,
                                              @Field("offer_price") String offer_price,
                                              @Field("offer_dec") String offer_dec,
                                              @Field("offer_type") String offer_type,
                                              @Field("offer_attachment") String offer_attachment,
                                              @Field("offer_attachment_2") String offer_attachment_2,
                                              @Field("offer_attachment_3") String offer_attachment_3);

    @POST("site/managetickets/ticketList")
    @FormUrlEncoded
    Call<TicketListResponse> ticketList(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("propertyId") String propertyId,
                                        @Field("ticketType") String ticketType,
                                        @Field("projectId") String projectId);

    @POST("site/managetickets/addCallBack")
    @FormUrlEncoded
    Call<AddCallBackResponse> addCallBack(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("propertyId") String propertyId,
                                          @Field("callback_name") String callback_name,
                                          @Field("callback_email") String callback_email,
                                          @Field("callback_phone") String callback_phone,
                                          @Field("callback_type") String callback_type,
                                          @Field("projectId") String projectId);

    @POST("site/data/addCallBack")
    @FormUrlEncoded
    Call<AddCallBackResponse> addCallBackReq(@Field("userId") String userId,
                                             @Field("token") String token,
                                             @Field("deviceToken") String deviceToken,
                                             @Field("deviceType") String deviceType,
                                             @Field("bId") String bId,
                                             @Field("callback_name") String callback_name,
                                             @Field("callback_email") String callback_email,
                                             @Field("callback_phone") String callback_phone,
                                             @Field("callback_type") String callback_type,
                                             @Field("projectId") String projectId,
                                             @Field("propType") String propType,
                                             @Field("description") String description);

    @POST("site/managetickets/addCallBack")
    @FormUrlEncoded
    Call<AddCallBackResponse> addCallBackRefer(@Field("userId") String userId,
                                               @Field("token") String token,
                                               @Field("deviceToken") String deviceToken,
                                               @Field("deviceType") String deviceType,
                                               @Field("propertyId") String propertyId,
                                               @Field("callback_name") String callback_name,
                                               @Field("callback_email") String callback_email,
                                               @Field("callback_phone") String callback_phone,
                                               @Field("callback_type") String callback_type,
                                               @Field("projectId") String projectId,
                                               @Field("callback_desc") String callback_desc);

    @POST("site/managetickets/addTicket")
    @FormUrlEncoded
    Call<AddTicketResponse> addTicket(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("propertyId") String propertyId,
                                      @Field("ticket_subject") String ticket_subject,
                                      @Field("ticket_priorty") String ticket_priorty,
                                      @Field("ticket_dec") String ticket_dec,
                                      @Field("ticket_type") String ticket_type,
                                      @Field("ticket_attachment") String ticket_attachment,
                                      @Field("ticket_attachment_2") String ticket_attachment_2,
                                      @Field("ticket_attachment_3") String ticket_attachment_3,
                                      @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectList")
    @FormUrlEncoded
    Call<ProjectListResponse> projectList(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType);

    @POST("site/Managemldlprojects/getProjectDetail")
    @FormUrlEncoded
    Call<GetProjectDetailResponse> getProjectDetail(@Field("userId") String userId,
                                                    @Field("token") String token,
                                                    @Field("deviceToken") String deviceToken,
                                                    @Field("deviceType") String deviceType,
                                                    @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectAmenities")
    @FormUrlEncoded
    Call<ProjectAmenitiesResponse> projectAmenities(@Field("userId") String userId,
                                                    @Field("token") String token,
                                                    @Field("deviceToken") String deviceToken,
                                                    @Field("deviceType") String deviceType,
                                                    @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectGallery")
    @FormUrlEncoded
    Call<ProjectGalleryResponse> projectGallery(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectConstructionUpdate")
    @FormUrlEncoded
    Call<ProjectConstructionUpdateResponse> projectConstructionUpdate(@Field("userId") String userId,
                                                                      @Field("token") String token,
                                                                      @Field("deviceToken") String deviceToken,
                                                                      @Field("deviceType") String deviceType,
                                                                      @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectLocation")
    @FormUrlEncoded
    Call<ProjectLocationResponse> projectLocation(@Field("userId") String userId,
                                                  @Field("token") String token,
                                                  @Field("deviceToken") String deviceToken,
                                                  @Field("deviceType") String deviceType,
                                                  @Field("projectId") String projectId);

    @POST("site/Managemldlprojects/projectOffers")
    @FormUrlEncoded
    Call<ProjectOffersResponse> projectOffers(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("projectId") String projectId);

    @POST("site/managetickets/ticketLogs")
    @FormUrlEncoded
    Call<TicketLogsResponse> ticketLogs(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("ticketId") String ticketId);

    @POST("site/managetickets/addTicketLog")
    @FormUrlEncoded
    Call<AddTicketResponse> addTicketLog(@Field("userId") String userId,
                                         @Field("token") String token,
                                         @Field("deviceToken") String deviceToken,
                                         @Field("deviceType") String deviceType,
                                         @Field("ticketId") String ticketId,
                                         @Field("ticket_log_comment") String ticket_log_comment);

    @POST("site/api/getPostPaymentDetails")
    @FormUrlEncoded
    Call<GetPostPaymentDetailsResponse> getPostPaymentDetails(@Field("userId") String userId,
                                                              @Field("token") String token,
                                                              @Field("deviceToken") String deviceToken,
                                                              @Field("deviceType") String deviceType,
                                                              @Field("pId") String pId);

    @POST("site/api/changeGroupName")
    @FormUrlEncoded
    Call<ChangeGroupNameResponse> changeGroupName(@Field("userId") String userId,
                                                  @Field("token") String token,
                                                  @Field("deviceToken") String deviceToken,
                                                  @Field("deviceType") String deviceType,
                                                  @Field("group_id") String group_id,
                                                  @Field("group_name") String group_name,
                                                  @Field("projectId") String projectId);

    @POST("site/api/setGroupImage")
    @FormUrlEncoded
    Call<SetGroupImageResponse> setGroupImage(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("group_header_image") String group_header_image,
                                              @Field("groupId") String groupId);

    @POST("site/api/membersbyGroup")
    @FormUrlEncoded
    Call<MembersbyGroupResponse> membersbyGroup(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("groupId") String groupId);

    @POST("site/api/groupAboutbyGroup")
    @FormUrlEncoded
    Call<GroupAboutbyGroupResponse> groupAboutbyGroup(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType,
                                                      @Field("groupId") String groupId);

    @POST("site/api/changeGroupAbout")
    @FormUrlEncoded
    Call<ChangeGroupAboutResponse> changeGroupAbout(@Field("userId") String userId,
                                                    @Field("token") String token,
                                                    @Field("deviceToken") String deviceToken,
                                                    @Field("deviceType") String deviceType,
                                                    @Field("group_id") String groupId,
                                                    @Field("group_about") String group_about);

    @POST("site/data/getServiceRequests")
    @FormUrlEncoded
    Call<GetServiceRequestsResponse> getServiceRequests(@Field("userId") String userId,
                                                        @Field("token") String token,
                                                        @Field("deviceToken") String deviceToken,
                                                        @Field("deviceType") String deviceType,
                                                        @Field("bId") String bId,
                                                        @Field("type") String type);

    @POST("site/data/residentList")
    @FormUrlEncoded
    Call<ResidentListResponse> residentList(@Field("userId") String userId,
                                            @Field("token") String token,
                                            @Field("deviceToken") String deviceToken,
                                            @Field("deviceType") String deviceType,
                                            @Field("projectId") String projectId,
                                            @Field("towerId") String towerId,
                                            @Field("residentName") String residentName);

    @POST("site/manageresidents/committeeManagmentList")
    @FormUrlEncoded
    Call<CommitteeManagmentListResponse> committeeManagmentList(@Field("userId") String userId,
                                                                @Field("token") String token,
                                                                @Field("deviceToken") String deviceToken,
                                                                @Field("deviceType") String deviceType,
                                                                @Field("projectId") String projectId);

    @POST("site/Managenotifications/notificationList")
    @FormUrlEncoded
    Call<NotificationListResponse> notificationList(@Field("userId") String userId,
                                                    @Field("token") String token,
                                                    @Field("deviceToken") String deviceToken,
                                                    @Field("deviceType") String deviceType,
                                                    @Field("notification_type") String notification_type);

    @POST("site/manageposts/deletePost")
    @FormUrlEncoded
    Call<DeletePostResponse> deletePost(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("postId") String postId);

    @POST("site/manageposts/addReport")
    @FormUrlEncoded
    Call<AddReportResponse> addReport(@Field("userId") String userId,
                                      @Field("token") String token,
                                      @Field("deviceToken") String deviceToken,
                                      @Field("deviceType") String deviceType,
                                      @Field("postId") String postId,
                                      @Field("comment") String comment);

    @POST("site/data/addServiceRequest")
    @FormUrlEncoded
    Call<AddServiceRequestResponse> addServiceRequest(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType,
                                                      @Field("bId") String bId,
                                                      @Field("comments") String comments,
                                                      @Field("type") String type,
                                                      @Field("projectId") String projectId);

    @POST("site/api/submitFeedback")
    @FormUrlEncoded
    Call<SubmitFeedbackResponse> submitFeedback(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("feedback") String feedback,
                                                @Field("rating") String rating,
                                                @Field("notificationType") String notificationType);

    @POST("site/data/loadDocuments")
    @FormUrlEncoded
    Call<LoadDocumentsResponse> loadDocuments(@Field("userId") String userId,
                                              @Field("token") String token,
                                              @Field("deviceToken") String deviceToken,
                                              @Field("deviceType") String deviceType,
                                              @Field("dType") String dType,
                                              @Field("documentId") String documentId);

    @POST("site/data/getDownloadDocuments")
    @FormUrlEncoded
    Call<GetDownloadDocumentsResponse> getDownloadDocuments(@Field("userId") String userId,
                                                            @Field("token") String token,
                                                            @Field("deviceToken") String deviceToken,
                                                            @Field("deviceType") String deviceType,
                                                            @Field("bId") String bId);

    @POST("site/managemldlprojects/homebannerList")
    @FormUrlEncoded
    Call<HomeBannerListResponse> homebannerList(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType);

    @GET("place/nearbysearch/json")
    Call<ResponseBody> getkeyLocations(@Query("location") String latlng,
                                       @Query("radius") String radius,
                                       @Query("key") String key);

    @POST("site/manageposts/updatePost")
    @FormUrlEncoded
    Call<UpdatePostResponse> updatePost(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("postId") String postId,
                                        @Field("details") String details,
                                        @Field("post_attachments") String post_attachments);

    @POST("site/manageaddedvalue/getProduct")
    @FormUrlEncoded
    Call<GetProductResponse> getProduct(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType);

    @POST("site/manageaddedvalue/addUserVas")
    @FormUrlEncoded
    Call<AddUserVasResponse> addUserVas(@Field("userId") String userId,
                                        @Field("token") String token,
                                        @Field("deviceToken") String deviceToken,
                                        @Field("deviceType") String deviceType,
                                        @Field("vasId") String vasId);

    @POST("site/data/getTimeSlot")
    @FormUrlEncoded
    Call<GetTimeSlotResponse> getTimeSlot(@Field("userId") String userId,
                                          @Field("token") String token,
                                          @Field("deviceToken") String deviceToken,
                                          @Field("deviceType") String deviceType,
                                          @Field("projectId") String projectId,
                                          @Field("date") String date);

    @POST("site/Clubhouse/getAvailableTimeSlot")
    @FormUrlEncoded
    Call<GetAvailableTimeSlotResponse> getAvailableTimeSlot(@Field("userId") String userId,
                                                            @Field("token") String token,
                                                            @Field("deviceToken") String deviceToken,
                                                            @Field("deviceType") String deviceType,
                                                            @Field("amenityId") String amenityId,
                                                            @Field("date") String date);

    @POST("site/api/setProfileImage")
    @FormUrlEncoded
    Call<SetProfileImageResponse> setProfileImage(@Field("userId") String userId,
                                                  @Field("token") String token,
                                                  @Field("deviceToken") String deviceToken,
                                                  @Field("deviceType") String deviceType,
                                                  @Field("profile_image") String profile_image);

    @POST("site/data/getProfileDetails")
    @FormUrlEncoded
    Call<GetProfileDetailsResponse> getProfileDetails(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType);

    @POST("site/api/updateProfileDetails")
    @FormUrlEncoded
    Call<UpdateProfileDetailsResponse> updateProfileDetails(@Field("userId") String userId,
                                                            @Field("token") String token,
                                                            @Field("deviceToken") String deviceToken,
                                                            @Field("deviceType") String deviceType,
                                                            @Field("isPublic") String isPublic,
                                                            @Field("companyName") String companyName,
                                                            @Field("designation") String designation,
                                                            @Field("homeTown") String homeTown,
                                                            @Field("livesIn") String livesIn,
                                                            @Field("dateOfBirth") String dateOfBirth,
                                                            @Field("marriageAnniversary") String marriageAnniversary,
                                                            @Field("interest") String interest,
                                                            @Field("companyType") String companyType,
                                                            @Field("companyLocation") String companyLocation);

    @POST("site/api/updateProfileEducationDetails")
    @FormUrlEncoded
    Call<UpdateProfileEducationResponse> updateProfileEducationDetails(@Field("userId") String userId,
                                                                       @Field("token") String token,
                                                                       @Field("deviceToken") String deviceToken,
                                                                       @Field("deviceType") String deviceType,
                                                                       @Field("university") String university,
                                                                       @Field("degree") String degree,
                                                                       @Field("yearFrom") String yearFrom,
                                                                       @Field("yearTo") String yearTo,
                                                                       @Field("educationId") String educationId);

    @POST("site/api/sendNewEnquiry")
    @FormUrlEncoded
    Call<SendNewEnquiryResponse> sendNewEnquiry(@Field("userId") String userId,
                                                @Field("token") String token,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("fName") String fName,
                                                @Field("lName") String lName,
                                                @Field("email") String email,
                                                @Field("mobile") String mobile,
                                                @Field("projectId") String projectId,
                                                @Field("description") String description);

    @POST("site/api/deleteProfileEducationDetails")
    @FormUrlEncoded
    Call<DeleteProfileEducationDetailsResponse> deleteProfileEducationDetails(@Field("userId") String userId,
                                                                              @Field("token") String token,
                                                                              @Field("deviceToken") String deviceToken,
                                                                              @Field("deviceType") String deviceType,
                                                                              @Field("educationId") String educationId);

    @POST("site/data/viewProfileDetails")
    @FormUrlEncoded
    Call<ViewProfileDetailsResponse> viewProfileDetails(@Field("userId") String userId,
                                                        @Field("token") String token,
                                                        @Field("deviceToken") String deviceToken,
                                                        @Field("deviceType") String deviceType,
                                                        @Field("profileId") String profileId);

    @POST("site/data/loadVASSettings")
    @FormUrlEncoded
    Call<LoadVASSettingsResponse> loadVASSettings(@Field("userId") String userId,
                                                  @Field("token") String token,
                                                  @Field("deviceToken") String deviceToken,
                                                  @Field("deviceType") String deviceType);

    @POST("site/api/saveVASRequirements")
    @FormUrlEncoded
    Call<SaveVASRequirementsResponse> saveVASRequirements(@Field("userId") String userId,
                                                          @Field("token") String token,
                                                          @Field("deviceToken") String deviceToken,
                                                          @Field("deviceType") String deviceType,
                                                          @Field("selectedItems") String selectedItems,
                                                          @Field("description") String description,
                                                          @Field("budgetRange") String budgetRange,
                                                          @Field("projectId") String ProjectId);

    @POST("site/data/loadProfileSettings")
    @FormUrlEncoded
    Call<LoadProfileSettingsResponse> loadProfileSettings(@Field("userId") String userId,
                                                          @Field("token") String token,
                                                          @Field("deviceToken") String deviceToken,
                                                          @Field("deviceType") String deviceType);

    @POST("site/api/updateGroupJoinRequest")
    @FormUrlEncoded
    Call<UpdateGroupJoinRequestResponse> updateGroupJoinRequest(@Field("userId") String userId,
                                                                @Field("token") String token,
                                                                @Field("deviceToken") String deviceToken,
                                                                @Field("deviceType") String deviceType,
                                                                @Field("groupId") String groupId,
                                                                @Field("requestId") String requestId,
                                                                @Field("status") String status);

    @POST("site/api/removeGroupMember")
    @FormUrlEncoded
    Call<RemoveGroupMemberResponse> removeGroupMember(@Field("userId") String userId,
                                                      @Field("token") String token,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("deviceType") String deviceType,
                                                      @Field("requestId") String requestId,
                                                      @Field("status") String status);

    @POST("site/api/changeGroupDetails")
    @FormUrlEncoded
    Call<ChangeGroupDetailsResponse> changeGroupDetails(@Field("userId") String userId,
                                                        @Field("token") String token,
                                                        @Field("deviceToken") String deviceToken,
                                                        @Field("deviceType") String deviceType,
                                                        @Field("projectId") String projectId,
                                                        @Field("group_id") String group_id,
                                                        @Field("group_name") String group_name,
                                                        @Field("group_about") String group_about,
                                                        @Field("isPublic") String isPublic);

    @POST("auth/forgotPassword")
    @FormUrlEncoded
    Call<ForgotPasswordResponse> forgotPassword(@Field("emailId") String emailId,
                                                @Field("deviceToken") String deviceToken,
                                                @Field("deviceType") String deviceType,
                                                @Field("version") String version);

}