package com.example.android.sunshine.app;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by swshin on 15. 8. 30.
 */
public class FetchWeatherTask extends AsyncTask<Void, Void, String[]> {

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

    @Override
    protected String[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String forecastJsonStr = null;

        String cityCd = "94043";
        String mode = "json";
        String units = "metric";
        int cnt = 7;
        String[] data = new String[cnt];

        try {
            // test url : http://api.openweathermap.org/data/2.5/forecast/daily?q=40943&mode=json&units=metric&cnt=7
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityCd+"&mode="+mode+"&units="+units+"&cnt="+cnt);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            forecastJsonStr = buffer.toString();
            for (int i=0;i<cnt;i++) {
                data[i] = WeatherDataParser.getWeatherStr(forecastJsonStr, i);
            }

//            List<String> weekForecast = new ArrayList<String>(
//                    Arrays.asList(data));
//
//            ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(
//                    f.getActivity(),
//                    R.layout.list_item_forecast,
//                    R.id.list_item_forecast_textview,
//                    weekForecast);
//
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//
//            ListView listView = (ListView) rootView.findViewById(R.id.list_item_forecast_textview);
//            listView.setAdapter(forecastAdapter);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "ERROR", e);
        } catch (IOException e){
            Log.e(LOG_TAG, "ERROR", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "ERROR", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "ERROR", e);
                }
            }
        }

        return data;
    }

}
