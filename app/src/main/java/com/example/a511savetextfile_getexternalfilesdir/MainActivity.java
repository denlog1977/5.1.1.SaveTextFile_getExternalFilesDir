package com.example.a511savetextfile_getexternalfilesdir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        final ArrayList<Product> products = new ArrayList<Product>();
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



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                Toast.makeText(getApplicationContext(), " listView setOnItemClickListener Позиция № " + Integer.toString(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
//                Toast.makeText(getApplicationContext(), " LongClick Позиция № " + Integer.toString(position) + "\n\nНаименование товара:"  + products.get(position).getName(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//        });


    }


}
