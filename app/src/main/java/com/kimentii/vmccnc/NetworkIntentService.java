package com.kimentii.vmccnc;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kimentii.vmccnc.activities.MainActivity;
import com.kimentii.vmccnc.dto.AutomaticLine;
import com.kimentii.vmccnc.dto.Lathe;
import com.kimentii.vmccnc.dto.Livetool;
import com.kimentii.vmccnc.dto.LivetoolPhoto;
import com.kimentii.vmccnc.dto.Tube;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class NetworkIntentService extends IntentService {
    private static final String TAG = NetworkIntentService.class.getSimpleName();

    private static final String ACTION_UPDATE_DATA = "com.kimentii.vmccnc.action.UPDATE_DATA";

    public NetworkIntentService() {
        super("NetworkIntentService");
    }

    public static void startActionUpdateData(Context context) {
        Intent intent = new Intent(context, NetworkIntentService.class);
        intent.setAction(ACTION_UPDATE_DATA);
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
            if (ACTION_UPDATE_DATA.equals(action)) {
                handleActionUpdateData();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void handleActionUpdateData() {
        MySqlHelper mySqlHelper = new MySqlHelper();
        mySqlHelper.openConnection();

        long startTime = System.currentTimeMillis();
        JSONArray automaticLineJsonArray = mySqlHelper.getTableData(AutomaticLine.DATABASE_TABLE_NAME);
        JSONArray latheJsonArray = mySqlHelper.getTableData(Lathe.DATABASE_TABLE_NAME);
        JSONArray livetoolJsonArray = mySqlHelper.getTableData(Livetool.DATABASE_TABLE_NAME);
        JSONArray tubeJsonArray = mySqlHelper.getTableData(Tube.DATABASE_TABLE_NAME);
        JSONArray livetoolPhotosJsonArray = mySqlHelper.getTableData(LivetoolPhoto.DATABASE_TABLE_NAME);
        long endTime = System.currentTimeMillis();
        Log.d(TAG, "handleActionUpdateData: request time: " + String.valueOf(endTime - startTime));

        mySqlHelper.closeConnection();

        ArrayList<AutomaticLine> automaticLines = getArrayFromJsonArray(automaticLineJsonArray, AutomaticLine.class);
        ArrayList<Lathe> lathes = getArrayFromJsonArray(latheJsonArray, Lathe.class);
        ArrayList<Livetool> livetools = getArrayFromJsonArray(livetoolJsonArray, Livetool.class);
        ArrayList<Tube> tubes = getArrayFromJsonArray(tubeJsonArray, Tube.class);
        ArrayList<LivetoolPhoto> livetoolPhotos = getArrayFromJsonArray(livetoolPhotosJsonArray, LivetoolPhoto.class);
        for (LivetoolPhoto livetoolPhoto : livetoolPhotos) {
            for (Livetool livetool : livetools) {
                if (livetool.getId() == livetoolPhoto.getLivetool_id()) {
                    livetool.addPhotoName(livetoolPhoto.getPhoto_name());
                    break;
                }
            }
        }

        ItemStorage.setAutomaticLines(automaticLines);
        ItemStorage.setLathes(lathes);
        ItemStorage.setLivetools(livetools);
        ItemStorage.setTubes(tubes);

        MainActivity.notifyDataStorageChanged(NetworkIntentService.this);
    }

    private <T> ArrayList<T> getArrayFromJsonArray(JSONArray jsonArray, Class<T> tClass) {
        ArrayList<T> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                arrayList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), tClass));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JsonSyntaxException e) {
                Log.d(TAG, "getArrayFromJsonArray: JsonSyntaxException!!!!!");
            }
        }
        return arrayList;
    }
}
