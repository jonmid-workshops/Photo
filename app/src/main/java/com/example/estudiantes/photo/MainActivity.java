package com.example.estudiantes.photo;

import android.graphics.Bitmap;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService queue = Executors.newSingleThreadExecutor();

    private TextInputEditText txtCode;
    private Button btnShow;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCode = findViewById(R.id.txvCode);
        btnShow = findViewById(R.id.btnLoad);
        imgPhoto = findViewById(R.id.imgLoad);

        txtCode.setText("27017220144");


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
        String strUrlFormat = "http://acad.ucaldas.edu.co/fotos/1701520879.jpg";

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

                final URL urlTemp = url;
                thread = new Runnable() {
                    @Override
                    public void run() {
                        CAFData data = CAFData.dataWithContentsOfURL(urlTemp);
                        Bitmap bitmap = null;
                        if (data != null){
                            bitmap = data.toImage();
                            if (bitmap != null){
                                final Bitmap bitmapTmp = bitmap;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgPhoto.setImageBitmap(bitmapTmp);
                                    }
                                });
                            }
                        }
                    }
                };

                queue.execute(thread);
            }
        }
    }
}
