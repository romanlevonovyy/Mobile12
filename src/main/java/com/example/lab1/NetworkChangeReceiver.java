package com.example.lab1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private View view;

    public NetworkChangeReceiver(View view) {
        super();
        this.view = view;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork == null) {
            Snackbar.make(view, R.string.internet_no_connection, Snackbar.LENGTH_LONG).show();
        }
    }
}