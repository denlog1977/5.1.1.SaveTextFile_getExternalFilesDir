package com.example.a511savetextfile_getexternalfilesdir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products;

    private static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);


        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            products = loading();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_READ_STORAGE);
        }

        ListView listView = findViewById(R.id.listView);
        final BasketAdapter adapter = new BasketAdapter(this, products);
        listView.setAdapter(adapter);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddElementActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });





    }





    public ArrayList<Product> loading() {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(1,2, "Танчик", R.drawable.tank));
        products.add(new Product(10,20, "Автомобильчик", R.drawable.am200));
        products.add(new Product(15,30, "Мотик", R.drawable.moto200));
        products.add(new Product(1,25, "Самолётик", R.drawable.flight200));
        products.add(new Product(90,40, "Вертолётик", R.drawable.helicopter200));
        products.add(new Product(3500,1, "Чувиха", R.drawable.girl01));
        products.add(new Product(5000,1, "Другая Чувиха", R.drawable.blondinka200));
        products.add(new Product(1500,1, "Чувиха не дорого", R.drawable.a3));
        products.add(new Product(6750,1, "Дорогая чувиха", R.drawable.a4));
        products.add(new Product(19990,0, "Всем чувихам Чувиха", R.drawable.chuvixa200));
        Bundle arguments =  getIntent().getExtras();
        if(arguments != null){
            String name = arguments.getString("name");
            int quantity = arguments.getInt("quantity");
            int price = arguments.getInt("price");
            int imgId = arguments.getInt("imgId");
            Toast.makeText(this,  name + " " + quantity + " " + price, Toast.LENGTH_SHORT).show();
            products.add(new Product(price, quantity, name, imgId));
        }
        return products;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    loading();
                } else {
                    // permission denied
                }
                return;
        }
    }






    private void LoadImg()
    {

        ImageView view = findViewById(R.id.imageView);
        if (isExternalStorageWritable()) {

//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "1.jpg");

            File file = new File(getApplicationContext().getExternalFilesDir(null),"1.jpg");  //getExternalFilesDir  папка для доступа приложения

            Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
            view.setImageBitmap(b);
            Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, getApplicationContext().getExternalFilesDir(null).toString(), Toast.LENGTH_LONG).show();

        File logFile = new File(getApplicationContext().getExternalFilesDir(null),"log.txt");
        try {
            FileWriter logWriter = new FileWriter(logFile);
            logWriter.append("App loaded");
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
