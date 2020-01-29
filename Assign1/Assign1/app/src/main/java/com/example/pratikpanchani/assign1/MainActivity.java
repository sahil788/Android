package com.example.pratikpanchani.assign1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup1, radioGroup2;
    Button button1, button2, button3;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);


        radioGroup1.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.radioButton1)
                            Toast.makeText(getApplicationContext(), "Alice", Toast.LENGTH_SHORT).show();
                        if(checkedId == R.id.radioButton2)
                            Toast.makeText(getApplicationContext(), "Bob", Toast.LENGTH_SHORT).show();
                        if(checkedId == R.id.radioButton3)
                            Toast.makeText(getApplicationContext(), "Carol", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        button1 = findViewById(R.id.button1);
        textView1 = findViewById(R.id.textView1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int changedRadio = radioGroup1.getCheckedRadioButtonId();
                if (changedRadio == R.id.radioButton1)
                    textView1.setText("Hi Alice");
                if (changedRadio == R.id.radioButton2)
                    textView1.setText("Hi Bob");
                if(changedRadio == R.id.radioButton3)
                    textView1.setText("Hi Carol");
            }
        });

        radioGroup2.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.radioButton4)
                            Toast.makeText(getApplicationContext(), "Dave", Toast.LENGTH_SHORT).show();
                        if(checkedId == R.id.radioButton5)
                            Toast.makeText(getApplicationContext(), "Eve", Toast.LENGTH_SHORT).show();
                        if(checkedId == R.id.radioButton6)
                            Toast.makeText(getApplicationContext(), "Fred", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        button2 = findViewById(R.id.button2);
        textView2 = findViewById(R.id.textView2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int changedRadio = radioGroup2.getCheckedRadioButtonId();
                if (changedRadio == R.id.radioButton4)
                    textView2.setText("Hello Dave");
                if (changedRadio == R.id.radioButton5)
                    textView2.setText("Hello Eve");
                if(changedRadio == R.id.radioButton6)
                    textView2.setText("Hello Fred");
            }
        });

        final CheckBox checkred = findViewById(R.id.checkRed);
        checkred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked())Toast.makeText(getApplicationContext(),"Red",Toast.LENGTH_SHORT).show();
            }
        });
        final CheckBox checkyellow = findViewById(R.id.checkYellow);
        checkyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked())Toast.makeText(getApplicationContext(),"Yellow",Toast.LENGTH_SHORT).show();
            }
        });
        final CheckBox checkgreen = findViewById(R.id.checkGreen);
        checkgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked())Toast.makeText(getApplicationContext(),"Green",Toast.LENGTH_SHORT).show();
            }
        });

        Button button =findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer colours = new StringBuffer();
                if(checkred.isChecked())colours.append(" Red");
                if(checkyellow.isChecked())colours.append(" Yellow");
                if(checkgreen.isChecked())colours.append(" Green");
                Toast.makeText(getApplicationContext(),colours,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
