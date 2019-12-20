package com.example.a511savetextfile_getexternalfilesdir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products;

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    public static final int REQUEST_CODE_PERMISSION_WRITE_STORAGE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();

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
//                MainActivity.this.finish();
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProducts();
            }
        });

        Button buttonRead = findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveProducts();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String name = data.getStringExtra("name");
        if(!name.isEmpty()){
            int quantity = data.getIntExtra("quantity",0);
            int price = data.getIntExtra("price", 0);
            int imgId = data.getIntExtra("imgId", 0);
            Toast.makeText(this,  "Добавлен товар " + name + " по цене " + price + " в количестве" + quantity, Toast.LENGTH_SHORT).show();
            products.add(new Product(name, price, quantity, imgId));
        }

    }


    public ArrayList<Product> loading() {
        products = readProducts();
        if (products.size() > 0) {
            Toast.makeText(this,  "Загружено из файла " + products.size() + " товаров", Toast.LENGTH_SHORT).show();
        } else {
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
            Toast.makeText(this,  "Первоначальное заполнение списка товаров", Toast.LENGTH_LONG).show();
        }
        return products;
    }


    private ArrayList<Product> readProducts() {
        products.clear();
        Log.i("BufferedReader", "products.size() = " + products.size());

        try {

            File productsFile = new File(getApplicationContext().getExternalFilesDir(null),"products.txt");
            FileReader fileReader = new FileReader(productsFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineOfFile = bufferedReader.readLine();
            while (lineOfFile!= null) {
                String[] p = lineOfFile.split(";");
                Log.i("BufferedReader", p[0] + " " + p[1] + " " + p[2] + " id img = " + p[3]);
                products.add(new Product(p[0], Integer.valueOf(p[1]), Integer.valueOf(p[2]), Integer.valueOf(p[3])));
                lineOfFile = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();

//            Bundle arguments =  getIntent().getExtras();
//            if(arguments != null){
//                String name = arguments.getString("name");
//                int quantity = arguments.getInt("quantity");
//                int price = arguments.getInt("price");
//                int imgId = arguments.getInt("imgId");
//                if (arguments.getString("name") != null) {
//                    Toast.makeText(this,  "Добавлен товар " + name + " по цене " + price + " в количестве" + quantity, Toast.LENGTH_SHORT).show();
//                    products.add(new Product(name, price, quantity, imgId));
//                    saveProducts();
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("BufferedReader", e.toString());
        }
        return products;
    }





    private void saveProducts() {
        String fileContent = fileContent(products);
        if (isExternalStorageWritable()) {
//            Toast.makeText(this, "getExternalFilesDir = " + getApplicationContext().getExternalFilesDir(null).toString(), Toast.LENGTH_SHORT).show();
//            Log.i("BufferedReader", "getExternalFilesDir = " + getApplicationContext().getExternalFilesDir(null).toString());
            File productsFile = new File(getApplicationContext().getExternalFilesDir(null),"products.txt");
//            Toast.makeText(this, "AbsolutePath        = " + productsFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
//            Log.i("BufferedReader", "AbsolutePath        = " + productsFile.getAbsolutePath());
            try {
                FileWriter logWriter = new FileWriter(productsFile);
                logWriter.append(fileContent);
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
//                Log.i("BufferedReader", e.toString());
            }
            Toast.makeText(this,  "Сохранено в файл " + products.size() + " товаров", Toast.LENGTH_SHORT).show();
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

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }








}
