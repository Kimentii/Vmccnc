package com.kimentii.vmccnc;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlHelper {
    private static final String TAG = MySqlHelper.class.getSimpleName();
    private static final String DB_HOST = "216.231.128.34";
    private static final String DB_PORT = "3306";
    private static final String DB_USERNAME = "qjvmhoia_kiril_a";
    private static final String DB_NAME = "qjvmhoia_mzal";
    private static final String DB_PASSWORD = "wo?f!o&7WT~2";
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?autoReconnect=true&useSSL=false";

    public JSONArray executeQuery(String query) {
        JSONArray jsonArray = new JSONArray();
        long startTime;
        long endTime;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            startTime = System.currentTimeMillis();
            connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
            endTime = System.currentTimeMillis();
            Log.d(TAG, "executeQuery: getting connection time: " + String.valueOf(endTime - startTime));

            startTime = System.currentTimeMillis();
            if (connection != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet != null) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    // Сохранение данных в JSONArray
                    while (resultSet.next()) {
                        JSONObject rowObject = new JSONObject();
                        for (int i = 1; i <= columnCount; i++) {
                            rowObject.put(resultSet.getMetaData().getColumnName(i), (resultSet.getString(i) != null) ? resultSet.getString(i) : "");
                        }
                        jsonArray.put(rowObject);
                    }
                }
            }
            endTime = System.currentTimeMillis();
            Log.d(TAG, "executeQuery: query time: " + String.valueOf(endTime - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return jsonArray;
    }
}
