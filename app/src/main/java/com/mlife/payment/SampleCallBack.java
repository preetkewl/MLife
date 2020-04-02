package com.mlife.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.billdesk.sdk.LibraryPaymentStatusProtocol;
import com.mlife.activities.Extras.Activity_ThankYou;

public class SampleCallBack implements LibraryPaymentStatusProtocol, Parcelable {
    String TAG = "Callback ::: > ";

    public SampleCallBack() {
        Log.v(TAG, "CallBack()....");
    }

    public SampleCallBack(Parcel in) {
        Log.v(TAG, "CallBack(Parcel in)....");
    }

    @Override
    public void paymentStatus(String status, Activity context) {
        Log.v(TAG,
                "paymentStatus(String status, Activity context)....::::status:::::"
                        + status);
        /*
        * MAHLIFE|AR1507723194683|JHDF5727077586|172847380150|00000002.00|HDF|NA|01|INR|DIRECT|NA|NA|0.00|11-10-2017 17:30:06|0300|NA|NA|NA|NA|NA|NA|NA|NA|NA|NA|2152178F28D972AB982A51C03B46AD06503FC715E19796E9C4E4ECC0D752867E
		*
		* */


        String[] strvalue = status.toString().split("\\|");

        String transaction = strvalue[14].replace("|", "");

        Intent mIntent = new Intent(context, Activity_ThankYou.class);

        if (transaction.equals("0300")) {
            mIntent.putExtra("Title", "Successful Transaction ");
            mIntent.putExtra("Heading", "Thank You. Your transaction is successfully Completed.");
            mIntent.putExtra("Detail", "Your Transaction Id:" + strvalue[2].replace("|", ""));
            context.startActivity(mIntent);
        }else if(transaction.equals("0002")){
            mIntent.putExtra("Title", "Pending Transaction");
            mIntent.putExtra("Heading", "Thank You. Your transaction is pending from bank side.");
            mIntent.putExtra("Detail", "Your Transaction Id:" + strvalue[2].replace("|", ""));
            context.startActivity(mIntent);
        }else{


        }
        context.finish();
    }

    @Override
    public void tryAgain() {

    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void cancelTransaction() {

    }

    @Override
    public int describeContents() {
        Log.v(TAG, "describeContents()....");
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.v(TAG, "writeToParcel(Parcel dest, int flags)....");
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {
        String TAG = "Callback --- Parcelable.Creator ::: > ";

        @Override
        public SampleCallBack createFromParcel(Parcel in) {
            Log.v(TAG, "CallBackActivity createFromParcel(Parcel in)....");
            return new SampleCallBack(in);
        }

        @Override
        public Object[] newArray(int size) {
            Log.v(TAG, "Object[] newArray(int size)....");
            return new SampleCallBack[size];
        }
    };
}
