package com.example.alexk.studentprofilecreator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AvatarSelector extends AppCompatActivity  {

    //This method converts an ImageView into a byte array
    public static byte[] createByteArrayPNG(ImageView view) {
        BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selector);

        final ImageButton imageButton1 = findViewById(R.id.imageButton1);
        final ImageButton imageButton2 = findViewById(R.id.imageButton2);
        final ImageButton imageButton3 = findViewById(R.id.imageButton3);
        final ImageButton imageButton4 = findViewById(R.id.imageButton4);
        final ImageButton imageButton5 = findViewById(R.id.imageButton5);
        final ImageButton imageButton6 = findViewById(R.id.imageButton6);
        final Intent intent = new Intent();

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton1));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton2));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton3));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton4));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton5));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(MainActivity.VALUE_KEY, createByteArrayPNG(imageButton6));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}

