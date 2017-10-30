package com.example.java_oglen.a1_sqliteusing;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int idSnf;
EditText adi,soyadi,mail,tel;
    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // yaz();
        //dataOku();

        adi=(EditText)findViewById(R.id.editAdi);
        soyadi=(EditText)findViewById(R.id.editSoyadi);
        mail=(EditText)findViewById(R.id.editMail);
        tel=(EditText)findViewById(R.id.editTel);
        lv=(ListView)findViewById(R.id.lv);
    }

    public void yaz(Kullanicilar kl){
        SQLiteDatabase yaz=new DB(this).getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put(""+Kisiler.adi,kl.getAdi());
        data.put(""+Kisiler.soyadi,kl.getSoyadi());
        data.put(""+Kisiler.mail,kl.getMail());
        data.put(""+Kisiler.telefon,kl.getTel());
         long sonuc=yaz.insert("kisiler",null,data);
        if(sonuc>0){
            Toast.makeText(this,"Yazma İşlemi Başarılı",Toast.LENGTH_SHORT).show();
            dataOku();
        }
        yaz.close();
    }

    //DATA OKUMA
    public void dataOku(){
        Toast.makeText(this, "fghfghkhjk", Toast.LENGTH_SHORT).show();
        SQLiteDatabase oku=new DB(this).getReadableDatabase();
        Cursor cr=oku.query("kisiler",null,null,null,null,null,null);
        final ArrayList<String> txt =new ArrayList<String>();
        final ArrayList<Integer> idtxt = new ArrayList<Integer>();
        int i=1;
        while (cr.moveToNext()){
            String adi=cr.getString(1);
            String soyadi=cr.getString(2);
            long id = cr.getLong(cr.getColumnIndex("kid"));
            Log.d("Adı",adi);
            Log.d("Soyadi",soyadi);
            txt.add(adi+" "+soyadi);
            idtxt.add((int)id);
            i++;
        }
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1,txt);
         lv.setAdapter(veriAdaptoru);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> arg0,
                                   View arg1, int arg2, long arg3) {
               idSnf=idtxt.get(arg2);
               Toast.makeText(getApplicationContext(), txt.get(arg2) , Toast.LENGTH_LONG).show();

           }
       });
    }

    //buton tıkla
    public void bntKayitYap(View v){

        String a= adi.getText().toString();
        String s= soyadi.getText().toString();
        String m= mail.getText().toString();
        String t= tel.getText().toString();


        //doğrulama işlemi
        if(a.equals("")){
            Toast.makeText(this,"Lütfen Adınızı Giriniz!",Toast.LENGTH_SHORT).show();
            adi.requestFocus();//fokuslama işlemi yap
            adi.setBackgroundColor(0xFF00FF00);
        }
        else if(s.equals("")){
            Toast.makeText(this,"Lütfen Soyadınızı Giriniz!",Toast.LENGTH_SHORT).show();
            soyadi.requestFocus();
        }else
        {
            //artık datalar gösterilebilir
            Kullanicilar kl= new Kullanicilar();
            kl.setAdi(a);
            kl.setSoyadi(s);
            kl.setMail(m);
            kl.setTel(t);
            yaz(kl);
        }
    }



}
