package com.appsticit1.myshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryData extends AppCompatActivity {

    ListView historyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_data);

        DataBase dt = new DataBase(HistoryData.this);
        historyList = findViewById(R.id.dateList);

        String s="";
        final String[] dataList = dt.dataHistoryView();

        for( int i = 0; i<dataList.length; i++)
        {
            s=s+dataList[i]+"\n";

        }
         String[] listRealdate = new String[dataList.length];

        for( int i = 0; i<dataList.length; i++)
        {
            if(dataList[i].length() == 7) {

               listRealdate[i] = dataList[i].substring(0, 1) + "-" + dataList[i].substring(1, 3) + "-" + dataList[i].substring(3, 7);
                //.gsubstring(0,2)+"-"+dateToast.substring(2,4)+"-"+dateToast.substring(4,8);
            }else {
                listRealdate[i] = dataList[i].substring(0, 2) + "-" + dataList[i].substring(2, 4) + "-" + dataList[i].substring(4, 8);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listRealdate);
        historyList.setAdapter(adapter);


        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             //   Toast.makeText(HistoryData.this, dataList[position] ,Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),MakePdf.class);
                i.putExtra("date",dataList[position]);
                startActivity(i);

            }
        });
    }

}
