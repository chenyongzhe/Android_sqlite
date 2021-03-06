package com.example.a12763.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ThridActivity extends AppCompatActivity {
      public static final int TAKE_PHOTO=1;
      private ImageView picture;
      private Uri imaguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        Button takephoto =(Button)findViewById(R.id.take_phone);
        picture=(ImageView)findViewById(R.id.picture);
        takephoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                File outputImage =new File(getExternalCacheDir(),"output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch(IOException e){
                    e.printStackTrace();

                }
                if(Build.VERSION.SDK_INT>=24){
                   imaguri= FileProvider.getUriForFile(ThridActivity.this,"com.example.a12763.myapplication.fileprovider",outputImage);



                }else{

                    imaguri=Uri.fromFile(outputImage);
                }
                //启动相机
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imaguri);
              startActivityForResult(intent,TAKE_PHOTO);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imaguri));
                        picture.setImageBitmap(bitmap);

                    }catch(FileNotFoundException e){

                        e.printStackTrace();
                    }

                }
                break;
             default:
                 break;
        }
    }
}
