package com.example.android.sunshine.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by swshin on 15. 8. 17.
 */
public class WeatherDataParser {

    /**
     * Given a string of the form returned by the api call:
     * http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
     * retrieve the maximum temperature for the day indicated by dayIndex
     * (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
            throws JSONException {

        JSONObject jObj = new JSONObject(weatherJsonStr);
        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONList = jArr.getJSONObject(dayIndex);
        JSONObject JSONTemp = JSONList.getJSONObject("temp");

        return JSONTemp.getDouble("max");
    }

    public static String getWeatherDataFromJson(String weatherJsonStr, int dayIndex)
            throws JSONException {

        String weatherStr = null;

        JSONObject jObj = new JSONObject(weatherJsonStr);
        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONList = jArr.getJSONObject(dayIndex);
        JSONObject JSONTemp = JSONList.getJSONObject("temp");
        JSONArray JSONWeather = JSONList.getJSONArray("weather");

        weatherStr = getReadableDateString(JSONList.getLong("dt")) + " - " + JSONWeather.getJSONObject(0).getString("main") + " - " + JSONTemp.getString("max") + "/" + JSONTemp.getString("min");

        return weatherStr;
    }
    private static String getReadableDateString(long time){
        SimpleDateFormat shortenedDataFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDataFormat.format(time);
    }

}
