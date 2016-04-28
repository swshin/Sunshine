package com.example.android.sunshine.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            String[] data = new FetchWeatherTask().doInBackground();

            List<String> weekForecast = new ArrayList<String>(
                    Arrays.asList(data));

            ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item_forecast,
                    R.id.list_item_forecast_textview,
                    weekForecast);
            Context context = Context

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.list_item_forecast_textview);
            listView.setAdapter(forecastAdapter);

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
}
