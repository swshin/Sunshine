package com.example.android.sunshine.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
 * Created by swshin on 15. 8. 17.
 */
public class ForecastFragment extends Fragment {
    public ForecastFragment() {
        //
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.menu.forecastfragment) {
            new FetchWeatherTask().doInBackground();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] data = {
                "Sun-Sunny-88/63",
                "Mon-Rain-77/65",
                "Tue-Sunny-88/77",
                "Weds-Foggy-99/88",
                "Thus-Rain-88/66",
                "Fri-Sunny-77/66",
                "Sat-Sunny-88/77" };

        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(data));

        ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_item_forecast_textview);
        listView.setAdapter(forecastAdapter);

/*
            Button b = (Button) rootView.findViewById(
                    R.id.btn);
            LinearLayout container = (LinearLayout) rootView.findViewById(
                    R.id.container);
            TextView t = (TextView) rootView.findViewById(
                    R.id.txt);
*/
        return rootView;
    }

    public class FetchWeatherTask extends AsyncTask<Void, Void, Void>  {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String forecastJsonStr = null;

            String cityCd = "40943";
            String mode = "json";
            String units = "metric";
            String cnt = "7";

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityCd+"&mode="+mode+"&units="+units+"&cnt="+cnt);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream != null) {
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

            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "ERROR", e);
            } catch (IOException e){
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
            return null;
        }

    }
}
