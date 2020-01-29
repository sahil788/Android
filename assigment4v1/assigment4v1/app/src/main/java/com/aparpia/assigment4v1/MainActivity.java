package com.aparpia.assigment4v1;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    ListView listOne;
    Button buttonOne, buttonTwo;
    LinearLayout layoutOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        final CustomListAdapter adapter = new CustomListAdapter(this, listItems0a, listItems1a, listItems2a, listItems3a, listItems4a);

        listOne = findViewById(R.id.list1);
        listOne.setAdapter(adapter);
        listOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SecondActivity.byte64 = listItems4a[position];
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("itemName0", listItems0a[position]);
                intent.putExtra("itemName1", listItems1a[position]);
                intent.putExtra("itemName2", listItems2a[position]);
                intent.putExtra("itemName3", listItems3a[position]);
                intent.putExtra("itemName4", listItems0a[position]);
                intent.putExtra("command", "ud");
                startActivity(intent);
            }
        });
        buttonOne = findViewById(R.id.buttonExit);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


        buttonTwo = findViewById(R.id.buttonAddTeam);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("itemName0","");
                intent.putExtra("itemName1","");
                intent.putExtra("itemName2","");
                intent.putExtra("itemName3","");
                intent.putExtra("itemName4","");
                intent.putExtra("command","add");
                startActivity(intent);
            }
        });

    }
}
class CustomListAdapter extends ArrayAdapter<String> {
    Activity context;
    String[] itemName0;
    String[] itemName1;
    String[] itemName2;
    String[] itemName3;
    String[] itemName4;

    public CustomListAdapter(Activity contextPar, String[] itemName0Par, String[] itemName1Par,
                             String[] itemName2Par, String[] itemName3Par, String[] itemName4Par){
        super(contextPar, R.layout.text_view1, itemName0Par);
        context = contextPar;
        itemName0 = itemName0Par;
        itemName1 = itemName1Par;
        itemName2 = itemName2Par;
        itemName3 = itemName3Par;
        itemName4 = itemName4Par;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.text_view1, null, true);
        TextView imageView = rowView.findViewById(R.id.text1);
        imageView.setText(itemName0[position]);
        TextView imageView2 = rowView.findViewById(R.id.text2);
        imageView2.setText(itemName1[position]);
        return rowView;
    }
}
