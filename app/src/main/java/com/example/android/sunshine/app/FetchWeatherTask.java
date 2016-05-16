package com.example.android.sunshine.app;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

    @Override
    protected String[] doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String forecastJsonStr = null;

        String appid = "92adf4c3de3ad11b719f0a4d8aa47688";
        String format = "json";
        String units = "metric";
        int numDays = 7;
        String[] data = new String[numDays];

        try {
            // test url : http://api.openweathermap.org/data/2.5/forecast/daily?q=40943&mode=json&units=metric&cnt=7
            final String FORECAST_BASE_URL =
                    "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "appid";

            Uri buildUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                    .appendQueryParameter(APPID_PARAM, appid)
                    .build();
            URL url = new URL(buildUri.toString());

            Log.v(LOG_TAG, "Built URI " + buildUri.toString());

//            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityCd+"&mode="+mode+"&units="+units+"&cnt="+cnt+"&appid="+appid);

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
            Log.v(LOG_TAG, "Forecast JSON String: " + forecastJsonStr);

            for (int i=0;i<numDays;i++) {
                data[i] = WeatherDataParser.getWeatherStr(forecastJsonStr, i);
            }

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "MalformedURLException", e);
        } catch (IOException e){
            Log.e(LOG_TAG, "IOException", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSONException", e);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception", e);
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

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String[] data) {
/*
        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(data));
        ForecastFragment f = new ForecastFragment();
        ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(
                f.getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast);
*/

//        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        ListView listView = (ListView) rootView.findViewById(R.id.list_item_forecast_textview);
//        listView.setAdapter(forecastAdapter);

        super.onPostExecute(data);
    }
}
