package com.example.estudiantes.photo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtCode;
    Button btnShow;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCode = findViewById(R.id.txvCode);
        btnShow = findViewById(R.id.btnLoad);
        imgPhoto = findViewById(R.id.imgLoad);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoto();
            }
        });
    }


    public void showPhoto(){
        String code = txtCode.getText().toString().trim();
        String strUrl = "http://acad.ucaldas.edu.co/fotos/";
        URL url = null;
        Runnable thread = null;

        if (!code.isEmpty()){
            strUrl += code + ".jpg";

            try {
                url = new URL(strUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            if (url != null){

                thread = new Runnable() {
                    @Override
                    public void run() {
                        //
                    }
                };

            }
        }
    }
}
