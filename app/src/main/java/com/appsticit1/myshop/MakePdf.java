package com.appsticit1.myshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MakePdf extends AppCompatActivity {

    Button myday;
    String folder_main = "pdfMomo";
    String dateToast= "";
    int toatl = 0;
    int profit =0;
    int sell = 0, buy= 0;
    DataBase df1 = new DataBase(MakePdf.this);
    String realDate= " ";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_pdf);


        MobileAds.initialize(this,"ca-app-pub-3220851972439401~8319086729");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3220851972439401/2792687058");
        AddLoad();

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        }
        else {

            AddLoad();
        }

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                AddLoad();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
           }
        });




        myday = findViewById(R.id.myPdf);

        dateToast = getIntent().getStringExtra("date");

        if(dateToast.length()==7){
            realDate = dateToast.substring(0,1)+"-"+dateToast.substring(1,3)+"-"+dateToast.substring(3,7);
        }else{
            realDate = dateToast.substring(0,2)+"-"+dateToast.substring(2,4)+"-"+dateToast.substring(4,8);
        }

        Toast.makeText(MakePdf.this, realDate, Toast.LENGTH_SHORT).show();

        if(ContextCompat.checkSelfPermission(MakePdf.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MakePdf.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE );
        }

        myday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    makepdf();
                } catch (DocumentException | IOException e) {
                    e.printStackTrace();
                }
                File pdfFile = new File(Environment.getExternalStorageDirectory(), File.separator + folder_main
                        + File.separator +getIntent().getStringExtra("date") + ".pdf");//File path

                if (pdfFile.exists()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(File.separator + folder_main + File.separator + "rrr44" + ".pdf"));
                    intent.setDataAndType(Uri.parse("file:///" + pdfFile), "application/pdf");
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "This file not exsits ! ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




    private void makepdf() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }
        PdfWriter.getInstance(document, new FileOutputStream(Environment.getExternalStorageDirectory()
                + File.separator + folder_main + File.separator + getIntent().getStringExtra("date") + ".pdf"));
        document.open();


        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);


        Paragraph bottomLine = new Paragraph("Appstic It", font2);
        bottomLine.setAlignment(Element.ALIGN_CENTER);


        Paragraph bottomLine1 = new Paragraph("Head Office: Appstic It,Level- 2, Rangmohal Towre, Sylhet", font);
        bottomLine1.setAlignment(Element.ALIGN_CENTER);

        Paragraph bottomLine2 = new Paragraph("01680680000, 01626926214  Web : www.appsticit.com", font);
        bottomLine2.setAlignment(Element.ALIGN_CENTER);


        Paragraph bottomLine3 = new Paragraph("Contact us for Pro-Version", font);
        bottomLine3.setAlignment(Element.ALIGN_CENTER);

        Paragraph money_receipt = new Paragraph(""+realDate, font3);
        money_receipt.setAlignment(Element.ALIGN_CENTER);



        PdfPTable t = new PdfPTable(4);
        t.setSpacingBefore(25);
        t.setSpacingAfter(15);
        t.setWidthPercentage(100f);
        PdfPCell c1 = new PdfPCell(new Phrase("Product Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        c1 = new PdfPCell(new Phrase("Buying Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        c1 = new PdfPCell(new Phrase("Selling Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        c1 = new PdfPCell(new Phrase("Profit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);


        ArrayList<ModelClass> dataList1 = df1.arrayList(getIntent().getStringExtra("date")) ;



        for( int i = 0; i<dataList1.size(); i++)
        {

            c1= new PdfPCell(new Phrase(dataList1.get(i).product_Name));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(1);
            t.addCell(c1);

            buy += Integer.parseInt(dataList1.get(i).buy_Price);
            c1= new PdfPCell(new Phrase(dataList1.get(i).buy_Price));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(1);
            t.addCell(c1);

            sell += Integer.parseInt(dataList1.get(i).sell_Price);
            c1= new PdfPCell(new Phrase(dataList1.get(i).sell_Price));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(1);
            t.addCell(c1);


            toatl=Integer.parseInt(dataList1.get(i).sell_Price)-Integer.parseInt(dataList1.get(i).buy_Price);
            c1= new PdfPCell(new Phrase(toatl+""));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(1);
            t.addCell(c1);

            profit += toatl;

        }

        c1= new PdfPCell(new Phrase("Total ",font1 ) );
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);

        t.addCell(c1);

        c1= new PdfPCell(new Phrase(buy+"",font1));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        c1= new PdfPCell(new Phrase(sell+"",font1));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        c1= new PdfPCell(new Phrase(profit+"",font1));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(1);
        t.addCell(c1);

        t.setHorizontalAlignment(Element.ALIGN_BOTTOM);
        document.bottom(document.bottomMargin());
        document.add(bottomLine);
        document.add(bottomLine1);
        document.add(bottomLine2);
        document.add(bottomLine3);
        document.add(money_receipt);
        document.add(t);
        document.close();

    }

    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;

    }

    public class RotateEvent extends PdfPageEventHelper {
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, PdfPage.SEASCAPE);
        }
    }
    public void AddLoad(){

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


}
