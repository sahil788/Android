package com.example.pratikpanchani.assign3;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    EditText editText0, editText1, editText2, editText3, editText4;
    Button exit2Button, addrow2Button, deleteButton, updateButton;
    TextView textView;
    LinearLayout topLayout, bottomLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText0 = findViewById(R.id.e_city);
        editText1 = findViewById(R.id.e_name);
        editText2 = findViewById(R.id.e_sport);
        editText3 = findViewById(R.id.e_mvp);
        editText4 = findViewById(R.id.e_stadium);

        addrow2Button = findViewById(R.id.submit);
        addrow2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler2 = new DatabaseHandler(getApplicationContext());
                String item0 = editText0.getText().toString();
                String item1 = editText1.getText().toString();
                String item2 = editText2.getText().toString();
                String item3 = editText3.getText().toString();
                String item4 = editText4.getText().toString();
                if(item0.length() == 0||item1.length()==0)
                {
                    Toast.makeText(SecondActivity.this.getApplicationContext(), "City and Name are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                    databaseHandler2.insertItem(item0,item1,item2,item3,item4);
                    editText0.setText("");
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");

            }
        });

        updateButton = findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        exit2Button = findViewById(R.id.exit);
        exit2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String stringData = intent.getStringExtra("itemname0");
        textView = findViewById(R.id.e_city);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemname1");
        textView = findViewById(R.id.e_name);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemname2");
        textView = findViewById(R.id.e_sport);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemname3");
        textView = findViewById(R.id.e_mvp);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemname4");
        textView = findViewById(R.id.e_stadium);
        textView.setText(stringData);

        stringData = intent.getStringExtra("command");
        topLayout = findViewById(R.id.top);
        bottomLayout = findViewById(R.id.bottom);

        if(stringData.equals("add")){
            deleteButton.setVisibility(View.GONE);
            updateButton.setVisibility(View.GONE);
            addrow2Button.setVisibility(View.VISIBLE);
            topLayout.setBackgroundColor(Color.parseColor("#ffffbb33"));
            bottomLayout.setBackgroundColor(Color.parseColor("#ffaa66cc"));
        }
        if(stringData.equals("ud")){
            deleteButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            addrow2Button.setVisibility(View.GONE);
            topLayout.setBackgroundColor(Color.parseColor("#ffff4444"));
            bottomLayout.setBackgroundColor(Color.parseColor("#ffaa66cc"));
        }

    }
}
