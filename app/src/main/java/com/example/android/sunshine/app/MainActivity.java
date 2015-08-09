package com.example.android.sunshine.app;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
            //
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, viewGroup);

            ArrayList<String> todayList = new ArrayList<>();
            todayList.add("Today-Sunny-88/63");
            todayList.add("Tomorrow-Rain-77/65");
            todayList.add("Tue-Sunny-88/77");
            todayList.add("Wen-Foggy-99/88");
            todayList.add("Thu-Rain-88/66");
            todayList.add("Fri-Sunny-77/66");
            todayList.add("Sat-Sunny-88/77");

            return rootView;
        }

    }
}
