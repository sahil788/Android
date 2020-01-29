package com.example.pratikpanchani.assign3;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    Button exitButton, addButton;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layoutID);
        DatabaseHandler databaseHandler1 = new DatabaseHandler(getApplicationContext());
        final List<String> listItems0 = databaseHandler1.getAllItems0();
        final String[] listItems0a = listItems0.toArray(new String[0]);
        final List<String> listItems1 = databaseHandler1.getAllItems1();
        final String[] listItems1a = listItems1.toArray(new String[0]);
        final List<String> listItems2 = databaseHandler1.getAllItems2();
        final String[] listItems2a = listItems2.toArray(new String[0]);
        final List<String> listItems3 = databaseHandler1.getAllItems3();
        final String[] listItems3a = listItems3.toArray(new String[0]);
        final List<String> listItems4 = databaseHandler1.getAllItems4();
        final String[] listItems4a = listItems4.toArray(new String[0]);

        final CustomListAdapter adapter =
                new CustomListAdapter(this, listItems0a, listItems1a, listItems2a, listItems3a, listItems4a);
        list = findViewById(R.id.list1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("itemname0",listItems0a[position]);
                intent.putExtra("itemname1",listItems1a[position]);
                intent.putExtra("itemname2",listItems2a[position]);
                intent.putExtra("itemname3",listItems3a[position]);
                intent.putExtra("itemname4",listItems4a[position]);
                intent.putExtra("command","ud");
                startActivity(intent);
            }
        });
                exitButton = findViewById(R.id.btn2);
                exitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });

                addButton = findViewById(R.id.btn1);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("itemname0","");
                        intent.putExtra("itemname1","");
                        intent.putExtra("itemname2","");
                        intent.putExtra("itemname3","");
                        intent.putExtra("itemname4","");
                        intent.putExtra("command","add");
                        startActivity(intent);
                    }
                });
    }

    class CustomListAdapter extends ArrayAdapter<String>{
        Activity context;
        String[] itemname0;
        String[] itemname1;
        String[] itemname2;
        String[] itemname3;
        String[] itemname4;

        public CustomListAdapter(Activity contextPar, String[] itemname0Par,String[] itemname1Par, String[] itemname2Par, String[] itemname3Par, String[] itemname4Par)
        {
            super(contextPar,R.layout.listitem,itemname0Par);
            context = contextPar;
            itemname0 = itemname0Par;
            itemname1 = itemname1Par;
            itemname2 = itemname2Par;
            itemname3 = itemname3Par;
            itemname4 = itemname4Par;
        }

        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem,null,true);
            TextView imageView = rowView.findViewById(R.id.text1);
            TextView imageView1 = rowView.findViewById(R.id.text2);
            imageView.setText(itemname0[position]);
            imageView1.setText(itemname1[position]);

            return rowView;
        }
    }
}
