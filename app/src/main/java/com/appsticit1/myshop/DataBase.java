package com.appsticit1.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "myshopdb";
    private static final String TABLE_NAME = "tbl_myshop";
    private static final String tbl_id = "ID";
    private static final String productName = "product_name";
    private static final String buyPrice = "buy_price";
    private static final String sellPrice = "sell_price";
    private static final String dateTime ="mydate";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s = "Create table "+TABLE_NAME+" ( "+tbl_id+" INTEGER PRIMARY KEY AUTOINCREMENT , "+productName+" varcher(200), "+buyPrice+" varcher(200),"+sellPrice+" varcher(200),"+dateTime+" varcher(200))";
        db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public   void addingDataToTable(ModelClass model)
    {

        SQLiteDatabase sqd = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(productName, model.getProduct_Name());
        cv.put(buyPrice, model.getBuy_Price());
        cv.put(sellPrice,model.getSell_Price());
        cv.put(dateTime,model.getmydataTime());


        //Log.d("test",model.getMenu_title() + " " + model.getMenuID() +" " + model.getMenu_price());


        sqd.insert(TABLE_NAME, null ,cv);
        sqd.close();

    }


    public String[] viewData (String dayDate){

        SQLiteDatabase sq =this.getReadableDatabase();
        String q="SELECT "+productName+","+buyPrice+","+sellPrice+" From "+TABLE_NAME+" WHERE mydate = "+dayDate;
        Cursor c= sq.rawQuery(q,null);
        String[] recvied_data = new String[c.getCount()];
        c.moveToFirst();

        if(c.moveToFirst()){
            int count = 0;
            do{
                recvied_data[count] =
                        " "+c.getString(c.getColumnIndex(productName))+"         "
                                +" "+c.getString(c.getColumnIndex(buyPrice))+"        "
                                +" "+c.getString(c.getColumnIndex(sellPrice));

                count=count+1;
            }while (c.moveToNext());
        }
        return recvied_data ;
    }

    public String[] dataHistoryView (){

        SQLiteDatabase sq =this.getReadableDatabase();
        String ql="SELECT "+dateTime+" From "+TABLE_NAME+" ORDER BY "+dateTime+" DESC" ;
        Cursor cl= sq.rawQuery(ql,null);
        String[] recvied_data2 = new String[cl.getCount()];
        cl.moveToFirst();

        if(cl.moveToFirst()){
            int count = 0;
            do{
                recvied_data2[count] =
                        ""+cl.getString(cl.getColumnIndex(dateTime));
                count=count+1;
            }while (cl.moveToNext());
        }
        return recvied_data2 ;
    }


    public ArrayList<ModelClass> arrayList(String mdate){

        ArrayList<ModelClass> arrayView = new ArrayList<ModelClass>();
        String s1,s2,s3, s4;
        SQLiteDatabase sq =this.getReadableDatabase();
        String q="SELECT * FROM "+TABLE_NAME+" WHERE mydate = "+mdate;
       // String q="SELECT * FROM "+TABLE_NAME;
        Cursor c= sq.rawQuery(q,null);
        while (c.moveToNext()){
                                s1= c.getString(c.getColumnIndex(productName));
                                s2=c.getString(c.getColumnIndex(buyPrice));
                                s3=c.getString(c.getColumnIndex(sellPrice));
                                s4=c.getString(c.getColumnIndex(dateTime));


        arrayView.add(new ModelClass(s1,s2,s3,s4));
        }
        return arrayView;
    }



}
