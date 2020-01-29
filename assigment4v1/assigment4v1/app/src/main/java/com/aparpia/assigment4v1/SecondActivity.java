package com.aparpia.assigment4v1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    TextView textView, textViewSpinner1;
    EditText editText0, editText1, editText3, textID;
    Button buttonOne, buttonTwo, uploadImageButton1, buttonAdd, buttonThree;
    ImageView imageView;
    boolean spinnerSet = true;
    Spinner spinner1;
    String imgDecodableString, string2;
    public static String byte64;
    byte[] imageArray = null;
    LinearLayout topLayout, bottomLayout, top2Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText0 = findViewById(R.id.editText1);
        editText1 = findViewById(R.id.editText2);
        textID = findViewById(R.id.textID);
        spinner1 = findViewById(R.id.spinner1);
        textViewSpinner1 = findViewById(R.id.spinnerTextView1);
        spinnerSet = false;
        List<String> listItems1 = Arrays.asList(" ", "Baseball", "Basketball", "Football", "Hockey");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listItems1);
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string2 = parent.getItemAtPosition(position).toString();
                if (spinnerSet) {
                    textViewSpinner1.setText(string2);
                }
                spinnerSet = true;
                if (string2.length() > 2) {
                    spinner1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editText3 = findViewById(R.id.editText4);
        imageView = findViewById(R.id.imageView1);
        uploadImageButton1 = findViewById(R.id.uploadImage);
        uploadImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent
                        (Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });


        buttonAdd = findViewById(R.id.submit);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler2 = new DatabaseHandler(getApplicationContext());
                String item0 = editText0.getText().toString();
                String item1 = editText1.getText().toString();
                String item2 = string2;
                String item3 = editText3.getText().toString();
                if (editText0.length() == 0 || editText1.length() == 0) {
                    Toast.makeText(getApplicationContext(), "City and Name are required", Toast.LENGTH_SHORT).show();
                }
                Bitmap bitmap1 = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream1);
                imageArray = byteArrayOutputStream1.toByteArray();
                String base64String = Base64.encodeToString(imageArray, Base64.DEFAULT);

                if (item0.length() != 0) {
                    databaseHandler2.insertItem(item0, item1, item2, item3, base64String);
                    editText0.setText("");
                    editText1.setText("");
                    spinner1.setVisibility(View.VISIBLE);
                    spinner1.setSelection(0);
                    textViewSpinner1.setText("");
                    editText3.setText("");
                    Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap3.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
                    imageArray = byteArrayOutputStream.toByteArray();
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(SecondActivity.this.imageArray, 0, SecondActivity.this.imageArray.length));
                }
            }
        });

        buttonOne = findViewById(R.id.update);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler3 = new DatabaseHandler(getApplicationContext());
                String itemID = textID.getText().toString();
                String item0 = editText0.getText().toString();
                String item1 = editText1.getText().toString();
                String item2 = string2;
                String item3 = editText3.getText().toString();

                Bitmap bitmap2 = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream1);
                imageArray = byteArrayOutputStream1.toByteArray();
                String base64String = Base64.encodeToString(imageArray, Base64.DEFAULT);
                if (itemID.length() > 0) {
                    databaseHandler3.deleteItem(itemID);
                    databaseHandler3.insertItem(item0, item1, item2, item3, base64String);
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        buttonTwo = findViewById(R.id.delete);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandlerDelete = new DatabaseHandler(getApplicationContext());
                dbHandlerDelete.deleteItem(editText0.getText().toString());
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonThree = findViewById(R.id.exit);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String stringData = intent.getStringExtra("itemName0");
        textView = findViewById(R.id.editText1);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemName1");
        textView = findViewById(R.id.editText2);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemName2");
        textViewSpinner1.setText(stringData);
        stringData = intent.getStringExtra("itemName3");
        textView = findViewById(R.id.editText4);
        textView.setText(stringData);
        stringData = intent.getStringExtra("itemName4");
        textView = findViewById(R.id.textID);
        textView.setText(stringData);
        stringData = intent.getStringExtra("command");
        topLayout = findViewById(R.id.top);
        bottomLayout = findViewById(R.id.bottom);
        top2Layout = findViewById(R.id.top2);
        if (stringData.equals("add")) {
            buttonTwo.setVisibility(View.GONE);
            buttonOne.setVisibility(View.GONE);
            buttonAdd.setVisibility(View.VISIBLE);
            uploadImageButton1.setBackgroundColor(Color.parseColor("#FFFF00"));
            top2Layout.setBackgroundColor(Color.parseColor("#FFFF00"));
            topLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
            bottomLayout.setBackgroundColor(Color.parseColor("#0000ff"));
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
            imageArray = byteArrayOutputStream.toByteArray();
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length));
        }
        if (stringData.equals("ud")) {
            buttonTwo.setVisibility(View.VISIBLE);
            buttonOne.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.GONE);
            uploadImageButton1.setBackgroundColor(Color.parseColor("#ff0000"));
            top2Layout.setBackgroundColor(Color.parseColor("#ff0000"));
            topLayout.setBackgroundColor(Color.parseColor("#ff0000"));
            bottomLayout.setBackgroundColor(Color.parseColor("#0000ff"));
            byte[] decodedString = Base64.decode(byte64, Base64.DEFAULT);
            Bitmap bitmap64 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(bitmap64);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null,
                    null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            ImageView imgView = (ImageView) findViewById(R.id.imageView1);
            imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}

