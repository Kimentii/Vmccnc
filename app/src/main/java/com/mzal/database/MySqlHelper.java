package com.mzal.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.mzal.SecretData.DB_PASSWORD;
import static com.mzal.SecretData.DB_USERNAME;

public class MySqlHelper {
    private static final String TAG = MySqlHelper.class.getSimpleName();
    private static final String DB_HOST = "216.231.128.34";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "qjvmhoia_mzal";
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?autoReconnect=true&useSSL=false";

    private static final String SELECT_FROM = "SELECT * FROM ";
    private static final String REQUEST_END = ";";

    private Connection mConnection;

    public void openConnection() {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        try {
            mConnection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        Log.d(TAG, "getTableData: getting connection time: " + String.valueOf(endTime - startTime));
    }

    public void closeConnection() {
        if (mConnection != null) {
            try {
                mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray getTableData(String tableName) {
        JSONArray jsonArray = new JSONArray();
        long startTime;
        long endTime;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            startTime = System.currentTimeMillis();
            if (mConnection != null) {
                statement = mConnection.createStatement();
                resultSet = statement.executeQuery(SELECT_FROM + tableName + REQUEST_END);
                if (resultSet != null) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    // Сохранение данных в JSONArray
                    while (resultSet.next()) {
                        JSONObject rowObject = new JSONObject();
                        for (int i = 1; i <= columnCount; i++) {
                            // TODO fix this if
                            if (resultSet.getMetaData().getColumnName(i).equals("axisCount")) {
                                rowObject.put(resultSet.getMetaData().getColumnName(i), (resultSet.getString(i) != null) ? resultSet.getString(i) : "2");
                            } else {
                                rowObject.put(resultSet.getMetaData().getColumnName(i), (resultSet.getString(i) != null) ? resultSet.getString(i) : "");
                            }
                        }
                        jsonArray.put(rowObject);
                    }
                }
            }
            endTime = System.currentTimeMillis();
            Log.d(TAG, "getTableData: time: " + String.valueOf(endTime - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return jsonArray;
    }
}
