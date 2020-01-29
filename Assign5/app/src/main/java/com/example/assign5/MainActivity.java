package com.example.assign5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView list;
    String stringURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-03-01&minmagnitude=6&limit=20&orderby=time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuakeAsyncTask task = new QuakeAsyncTask();
        task.execute(stringURL);
    }

    class QuakeAsyncTask extends AsyncTask<String, Void, List<String>>{
        @Override
        protected List<String> doInBackground(String... stringurl) {
            return Utils.fetchEarthquakeData(stringurl[0]);
        }

        public void onPostExecute(List<String> postExecuteResult){
            CustomListAdapter arrayAdapter = new CustomListAdapter (MainActivity.this, postExecuteResult);
            list = findViewById(R.id.list1);
            list.setAdapter(arrayAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent
                            ("android.intent.action.VIEW",  Uri.parse(((TextView) view.findViewById(R.id.date3)).getText().toString()));
                    startActivity(intent);
                }
            });
        }
    }

    class CustomListAdapter extends ArrayAdapter<String> {
        Activity context;
        List<String> itemname1;

        public CustomListAdapter(Activity activity, List<String> itemnameA){
            super(activity,R.layout.one_quake,itemnameA);
            this.context = activity;
            this.itemname1 = itemnameA;
        }

        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.one_quake,null,true);
            String earthInfo[] = itemname1.get(position).split("@@");
            TextView textInfo = rowView.findViewById(R.id.date);
            textInfo.setText(earthInfo[0]);
            if (position % 2 == 0) {
                textInfo.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            textInfo = (TextView) rowView.findViewById(R.id.date2);
            textInfo.setText(new Date(Long.parseLong(earthInfo[1])).toString());
            if (position % 2 == 0) {
                textInfo.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            textInfo = (TextView) rowView.findViewById(R.id.date3);
            textInfo.setText(earthInfo[2]);
            if (position % 2 == 0) {
                textInfo.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            return rowView;
        }
    }
}
