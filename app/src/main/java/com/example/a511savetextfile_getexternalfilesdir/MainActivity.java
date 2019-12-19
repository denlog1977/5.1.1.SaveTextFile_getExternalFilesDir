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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products;

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    public static final int REQUEST_CODE_PERMISSION_WRITE_STORAGE = 11;

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
                saveProducts(fileContent(products));
            }
        });

        Button buttonRead = findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadItems();
            }
        });

    }





    public ArrayList<Product> loading() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Танчик", 2,1, R.drawable.tank));
        products.add(new Product("Автомобильчик", 10,20, R.drawable.am200));
        products.add(new Product("Мотик", 15,30, R.drawable.moto200));
        products.add(new Product("Самолётик", 1,25, R.drawable.flight200));
        products.add(new Product("Вертолётик", 90,40, R.drawable.helicopter200));
        products.add(new Product("Чувиха", 3500,1, R.drawable.girl01));
        products.add(new Product("Другая Чувиха", 5000,1, R.drawable.blondinka200));
        products.add(new Product("Чувиха не дорого", 1500,1, R.drawable.a3));
        products.add(new Product("Дорогая чувиха", 6750,1, R.drawable.a4));
        products.add(new Product("Всем чувихам Чувиха",19990,0,  R.drawable.chuvixa200));
        Bundle arguments =  getIntent().getExtras();
        if(arguments != null){
            String name = arguments.getString("name");
            int quantity = arguments.getInt("quantity");
            int price = arguments.getInt("price");
            int imgId = arguments.getInt("imgId");
//            Toast.makeText(this,  name + " " + price + " " + quantity, Toast.LENGTH_SHORT).show();
            products.add(new Product(name, price, quantity, imgId));
        }
        return products;
    }


    private ArrayList<Product> loadItems() {
        Log.i("BufferedReader", "loadItems");

        ArrayList<Product> products = new ArrayList<>();
        try {
            File productsFile = new File(getApplicationContext().getExternalFilesDir(null),"products.txt");
            FileReader fileReader = new FileReader(productsFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineOfFile = bufferedReader.readLine();
            while (lineOfFile!= null) {
                String[] p = lineOfFile.split(";");
                Log.i("BufferedReader", p[0] + " " + p[1] + " " + p[2] + " " + p[3]);
                Toast.makeText(this, p[0] + " " + p[1] + " " + p[2] + " " + p[3], Toast.LENGTH_LONG).show();
                lineOfFile = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("BufferedReader", e.toString());
            Toast.makeText(this, "e.toString() = " + e.toString(), Toast.LENGTH_LONG).show();
        }
        return products;
    }



    private void saveProducts(String fileContent) {
        if (isExternalStorageWritable()) {
            Toast.makeText(this, "getExternalFilesDir = " + getApplicationContext().getExternalFilesDir(null).toString(), Toast.LENGTH_LONG).show();
            Log.i("BufferedReader", "getExternalFilesDir = " + getApplicationContext().getExternalFilesDir(null).toString());

            File productsFile = new File(getApplicationContext().getExternalFilesDir(null),"products.txt");
            Toast.makeText(this, "AbsolutePath        = " + productsFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Log.i("BufferedReader", "AbsolutePath        = " + productsFile.getAbsolutePath());
            try {
                FileWriter logWriter = new FileWriter(productsFile);
                logWriter.append(fileContent);
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("BufferedReader", e.toString());
            }
        } else {
            Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
        }
    }


    private String fileContent(ArrayList<Product> products) {
        String fileContent = "";
        for (Product item: products) {
            fileContent = fileContent + item.getName()  + ";" + item.getPrice()  + ";" + item.getQuantity() + ";" + item.getImage() + ";" + item.getChecked()  + "\n";
        }
        return fileContent;
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
