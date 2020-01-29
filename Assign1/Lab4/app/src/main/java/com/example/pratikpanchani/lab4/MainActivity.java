package com.example.pratikpanchani.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] arrayAlphabet1 = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] number = new String[99];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int i = 0;
        while (true) {
            String[] Array = this.number;
            if (i < Array.length) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append((i + 1) * 7);
                Array[i] = stringBuilder.toString();
                i++;
            } else {
                ((ListView) findViewById(R.id.list_view1)).setAdapter(new ArrayAdapter(this, R.layout.textviewitem, this.arrayAlphabet1));
                ((GridView) findViewById(R.id.grid_view)).setAdapter(new ArrayAdapter(this, R.layout.textviewitem2, this.number));
                return;
            }
        }
    }

}
