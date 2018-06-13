package com.kimentii.vmccnc;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkIntentService extends IntentService {
    private static final String TAG = NetworkIntentService.class.getSimpleName();

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GET_AUTOMATIC_LINES = "com.kimentii.vmccnc.action.GET_AUTOMATIC_LINES";

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

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetAutomaticLines() {
        MySqlHelper mySqlHelper = new MySqlHelper();
        JSONArray jsonArray = mySqlHelper.executeQuery("SELECT * FROM automated_line");
        //Log.d(TAG, "Data:\n" + jsonArray.toString());
        ArrayList<AutomaticLine> automaticLines = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                //Log.d(TAG, jsonArray.getJSONObject(i).toString());
                automaticLines.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), AutomaticLine.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "handleActionGetAutomaticLines: created machines list");
        for (int i = 0; i < automaticLines.size(); i++) {
            Log.d(TAG, "id: " + automaticLines.get(i).getId() + " type: " + automaticLines.get(i).getType_ru());
        }
    }
}
