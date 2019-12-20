package com.example.a511savetextfile_getexternalfilesdir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                TextView editTextQuantity = findViewById(R.id.editTextQuantity);
                TextView editTextPrice = findViewById(R.id.editTextPrice);

                if (editTextName.getText().toString().isEmpty() || editTextQuantity.getText().toString().isEmpty() || editTextPrice.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "! Не заполнены все поля товара !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("name",         editTextName.getText().toString());
                    intent.putExtra("price",        Integer.valueOf(editTextPrice.getText().toString()));
                    intent.putExtra("quantity",     Integer.valueOf(editTextQuantity.getText().toString()));
                    intent.putExtra("imgId",        R.mipmap.ic_launcher_round);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

    }



}
