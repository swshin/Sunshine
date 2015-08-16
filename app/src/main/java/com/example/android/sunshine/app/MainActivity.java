package com.example.android.sunshine.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import android.support.v7.app.ActionBarActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
            //
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

//            View rootView = inflater.inflate(R.layout.fragment_main, viewGroup);

            String[] data = {
                    "Today-Sunny-88/63",
                    "Tomorrow-Rain-77/65",
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

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String forecastJsonStr = null;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=40943&mode=json&units=metric&cnt=7");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();


            } catch (Exception e) {
                //
            }

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

    }
}
