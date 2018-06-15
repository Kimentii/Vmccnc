package com.kimentii.vmccnc;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.kimentii.vmccnc.dto.AutomaticLine;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class NetworkIntentService extends IntentService {
    private static final String TAG = NetworkIntentService.class.getSimpleName();

    private static final String ACTION_GET_AUTOMATIC_LINES = "com.kimentii.vmccnc.action.GET_AUTOMATIC_LINES";
    private static final String ACTION_GET_LATHES = "com.kimentii.vmccnc.action.GET_LATHE";
    private static final String ACTION_GET_LIVETOOLS = "com.kimentii.vmccnc.action.GET_LIVETOOL";
    private static final String ACTION_GET_TUBES = "com.kimentii.vmccnc.action.GET_TUBES";

    public NetworkIntentService() {
        super("NetworkIntentService");
    }

    public static void startActionGetAutomaticLines(Context context) {
        Intent intent = new Intent(context, NetworkIntentService.class);
        intent.setAction(ACTION_GET_AUTOMATIC_LINES);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_AUTOMATIC_LINES.equals(action)) {
                handleActionGetAutomaticLines();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void handleActionGetAutomaticLines() {
        MySqlHelper mySqlHelper = new MySqlHelper();
        long startTime = System.currentTimeMillis();
        JSONArray jsonArray = mySqlHelper.executeQuery("SELECT * FROM automated_line");
        long endTime = System.currentTimeMillis();
        Log.d(TAG, "handleActionGetAutomaticLines: request time: " + String.valueOf(endTime - startTime));
        //Log.d(TAG, "Data:\n" + jsonArray.toString());
        ArrayList<Serializable> automaticLines = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                //Log.d(TAG, jsonArray.getJSONObject(i).toString());
                automaticLines.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), AutomaticLine.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < automaticLines.size(); i++) {
            Log.d(TAG, "handleActionGetAutomaticLines: " + ((AutomaticLine) automaticLines.get(i)).getId());
        }
        MainActivity.sendDataViaBroadcastReceiver(NetworkIntentService.this,
                MainActivity.DATA_TYPE_AUTOMATIC_LINE, automaticLines);
    }

    private void handleActionGetLathes() {

    }

    private void handleActionGetLivetools() {

    }

    private void handleActionGetTubes() {

    }
}
