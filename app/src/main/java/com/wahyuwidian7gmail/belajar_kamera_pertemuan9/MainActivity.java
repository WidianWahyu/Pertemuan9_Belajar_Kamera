package com.wahyuwidian7gmail.belajar_kamera_pertemuan9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button takePictureButton;
    private ImageView imageView;
    Uri file;
    //pada script diatas untuk membuat sebuah class method uri file. digunakan juga untuk dapat mengambil gambar ketika kamera digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePictureButton = (Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.imageView2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
    //pada script diatas digunakan untuk membuat class button2 untuk nanti dapat menampilkan gambar
    //digunakan juga untuk menampilkan hasil gambar yang sudah difoto kedalam aplikasi

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }
    //pada script diatas digunakan untuk meminta hasil yang dapat ditampilkan ketika aplikasi sudah dapat berjalan

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 0);
    }
    //pada script diatas digunakan untuk mengambil gambar dan untuk dapat menyimpan gambar kedalam media/storage
    //digunakan juga untuk mendeklarasikan kelas file.


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }
    }
    //pada script diatas digunakan untuk meminta hasil activity yang sudah dilakukan ketika aplikasi sudah dapat berjalan
    //seperti melihat hasil gambar yang sudah dapat difoto

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        //pada script diatas digunakan untuk mengambil hasil keluaran dari gambar yang sudah difoto
        //kemudian menyimpannya didalam media file

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
        //pada script diatas digunakan untuk membuat format buat tanggal dan waktu ketika hasil foto sudah dapat diambil
    }

}