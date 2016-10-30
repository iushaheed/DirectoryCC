package com.psionicinteractive.directorycc;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapter extends ArrayAdapter<Product> {

    static ArrayList<Product> products;
    Context context;
    int resource;
//    final boolean[] checkArray;



    public CustomListAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
//        checkArray=new boolean[products.size()];
    }

    public void addListItemToAdapter(List<Product> list){

        products.addAll(list);
        this.notifyDataSetChanged();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null, true);

        }
        final Product product = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context).load(product.getImage()).into(imageView);

        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        txtName.setText(product.getName());

        TextView txtEmail = (TextView) convertView.findViewById(R.id.email);
        txtEmail.setText(product.getEmail());

        CheckBox cb= (CheckBox) convertView.findViewById(R.id.checkboxId);

        cb.setChecked(product.getIsTrue());

//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                product.setIsTrue(isChecked);
//                Toast.makeText(getContext(), ""+isChecked, Toast.LENGTH_SHORT).show();
//            }
//        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getIsTrue())
                product.setIsTrue(!product.getIsTrue());
                else
                    product.setIsTrue(!product.getIsTrue());
                Toast.makeText(getContext(), ""+product.getIsTrue(), Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,Product.getName(), Toast.LENGTH_LONG).show();

                //calling an activity from a non activity class
                Intent intent = new Intent(context,PutGetExtra.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //passing name id image phone string in the PUtGetExtra.java class for member_profile display

                intent.putExtra("name", product.getName());
                intent.putExtra("id", product.getEmail());
                intent.putExtra("image",product.getImage());
                intent.putExtra("phone",product.getPhoneNumber());

                context.startActivity(intent);

            }
        });
        //MY ENDS

        return convertView;
    }

}
