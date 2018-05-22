package com.example.aili.e_care;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connector {

    Context ct;
    public Connector(Context c)
    {

        ct=c;
    }
    public boolean isConnected()
    {
        ConnectivityManager connectivity=(ConnectivityManager)ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity!=null)
        {
            NetworkInfo networkInfo=connectivity.getActiveNetworkInfo();
            if(networkInfo!=null)
            {
                if(networkInfo.getState()== NetworkInfo.State.CONNECTED)
                {
                    return  true;
                }
            }

        }
        return  false;
    }

}
