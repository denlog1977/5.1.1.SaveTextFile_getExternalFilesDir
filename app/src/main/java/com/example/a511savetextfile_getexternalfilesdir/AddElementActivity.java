package com.example.a511savetextfile_getexternalfilesdir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);


        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView editTextName = findViewById(R.id.editTextName);
                TextView editTextDescriprtion = findViewById(R.id.editTextDescriprtion);
                TextView editTextKol = findViewById(R.id.editTextKol);
                TextView editTextPrice = findViewById(R.id.editTextPrice);

                if (editTextName.getText().length() > 0) {

     public Product(String editTextName.getText(), editTextDescriprtion.getText(), String(editTextKol.get)int quantity, int price, R.id.draw) {

                    } else {
                    Toast.makeText()
                    Toast.makeText(getApplicationContext(), "! Не заполнено наименование товара !", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
