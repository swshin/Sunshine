package com.example.android.sunshine.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static String getWeatherStr(String weatherJsonStr, int dayIndex)
            throws JSONException {

        String weatherStr = null;

        JSONObject jObj = new JSONObject(weatherJsonStr);
        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject JSONList = jArr.getJSONObject(dayIndex);
        JSONObject JSONTemp = JSONList.getJSONObject("temp");
        JSONObject JSONWeather = JSONList.getJSONObject("weather");

        weatherStr = JSONWeather.getString("main") + " - " + JSONTemp.getString("max") + "/" + JSONTemp.getString("min");

        return weatherStr;
    }

}
