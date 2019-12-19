package com.example.a511savetextfile_getexternalfilesdir;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasketAdapter extends BaseAdapter {

    ArrayList<Product> goods;
    LayoutInflater layoutInflater;
    Context ctx;

    BasketAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        goods = products;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        return goods.get(position);
    }

    public Product getProduct(int position) {
        return goods.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.basket_item, parent, false);
        }

        final Product product = getProduct(position);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(product.image);

        ((TextView) (view.findViewById(R.id.name))).setText(Integer.toString(position+1) + ". " + product.name);
        ((TextView) (view.findViewById(R.id.price))).setText(Integer.toString(product.getPrice()) + ",00 руб.");
        ((TextView) (view.findViewById(R.id.quantity))).setText("Количество: " + Integer.toString(product.getQuantity()) + " шт.");

        CheckBox chekBox = view.findViewById(R.id.selected);
        chekBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(buttonView.getContext(), "OnCheckedChangeListener строка № " + Integer.toString(position + 1) + " isChecked=" + Boolean.toString(isChecked), Toast.LENGTH_SHORT).show();
                goods.get((Integer) buttonView.getTag()).setChecked(isChecked);
            }
        });
        chekBox.setTag(position);
        chekBox.setChecked(product.getChecked());

        Button buttonRemove = (Button) (view.findViewById(R.id.buttonRemove));
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.remove(position);
                notifyDataSetChanged();
//                Toast.makeText(v.getContext(), "Удаление позиции № " + Integer.toString(position + 1) + "\n\nОсталось строк: " + Integer.toString(goods.size()), Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(ctx, " onLongClick Позиция № " + Integer.toString(position + 1) + "\n\nНаименование товара:"  + product.getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, " imageView onClick Позиция № " + Integer.toString(position), Toast.LENGTH_SHORT).show();

                Toast toast3 = Toast.makeText(ctx, product.getName() + ". Цена " + product.getPrice() + ",00 руб.", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                ImageView imageToast = new ImageView(ctx);
                imageToast.setImageResource(product.getImage());
                LinearLayout toastContainer = (LinearLayout) toast3.getView();
//                LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                toastContainer.setLayoutParams(linLayoutParam);
                toastContainer.addView(imageToast, 0);
                toast3.show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, " view onClick Позиция № " + Integer.toString(position + 1), Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }


}