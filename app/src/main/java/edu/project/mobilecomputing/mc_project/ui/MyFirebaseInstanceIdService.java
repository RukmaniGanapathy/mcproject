package edu.project.mobilecomputing.mc_project.ui;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Referred from https://www.youtube.com/watch?v=wKwCgabRV2A
 *
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {



    private static final String REG_TOKEN = "REG_TOKEN";
    @Override
    public void onTokenRefresh() {

        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);

    }


}
