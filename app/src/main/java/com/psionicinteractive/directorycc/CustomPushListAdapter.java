package com.psionicinteractive.directorycc;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.psionicinteractive.directorycc.model.Push;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomPushListAdapter extends ArrayAdapter<Push> {

    ArrayList<Push> products;
    Context context;
    int resource;
    TextView m_titlet;
    TextView m_message;

    public CustomPushListAdapter(Context context, int resource, ArrayList<Push> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }

    public void addListItemToAdapter(List<Push> list){

        products.addAll(list);
        this.notifyDataSetChanged();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_push, null, true);

        }
        final Push product = getItem(position);
        Typeface lato_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato.ttf");



        TextView txtName = (TextView) convertView.findViewById(R.id.titlet);
        txtName.setText(product.getTitle());
        txtName.setTypeface(lato_font);

        TextView txtMSG = (TextView) convertView.findViewById(R.id.messaget);
        txtMSG.setText(product.getMessage());
        txtMSG.setTypeface(lato_font);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SinglePushActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",product.getTitle());
                intent.putExtra("message",product.getMessage());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}
