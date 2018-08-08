package com.appsticit1.myshop;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class shop extends AppCompatActivity {

    EditText ed_ProductName, ed_buyingPrice, ed_sellingPrice;
    TextView  TodateText;
    Button dayButton, submitButton;
    DataBase myData;
    String date1 = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        myData = new DataBase(shop.this);


        ed_ProductName = findViewById(R.id.ed_productName);
        ed_buyingPrice = findViewById(R.id.ed_BuyingPrice);
        ed_sellingPrice = findViewById(R.id.ed_SellingPrice);
        TodateText = findViewById(R.id.todateText);
        submitButton = findViewById(R.id.Submitbutton);
        dayButton = findViewById(R.id.dayPdfButton);


        String product_name = ed_ProductName.getText().toString();
        String buying_price = ed_buyingPrice.getText().toString();
        String  selling_price = ed_sellingPrice.getText().toString();
        final String  date_text = TodateText.getText().toString();

        date1 = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
        final String date = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());

        TodateText.setText(""+date1);


       submitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(ed_ProductName.getText().toString().length() >0 && ed_buyingPrice.getText().toString().length() > 0
                       && ed_sellingPrice.getText().toString().length() > 0) {


                   ModelClass model1 = new ModelClass(ed_ProductName.getText().toString(),
                           ed_buyingPrice.getText().toString(), ed_sellingPrice.getText().toString(),
                           date);

                   myData.addingDataToTable(model1);


                   Toast.makeText(getApplicationContext(), "WoW!!Seccessfuly done!!", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(getApplicationContext(), "Please fill up all gap", Toast.LENGTH_SHORT).show();
               }

               ed_ProductName.setText("");
               ed_buyingPrice.setText("");
               ed_sellingPrice.setText("");
           }
       });


        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),DataView.class);
                in.putExtra("date",date);
                startActivity(in);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Previous_Histry:
                startActivity(new Intent(getApplicationContext(), HistoryData.class));
                Toast.makeText(shop.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.et_help:
                startActivity(new Intent(getApplicationContext(), Help.class));
                Toast.makeText(shop.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_about_us:
                startActivity(new Intent(getApplicationContext(), AboutUS.class));
                Toast.makeText(shop.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Intent intent =new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody = "https://play.google.com/store/apps/details?id=com.appsticit1.myshop";
                String sharesub= "Your Subject hare";
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                startActivity(Intent.createChooser(intent,"Share using"));


                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
