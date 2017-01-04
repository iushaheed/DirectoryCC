package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by iShaheed on 8/28/2016.
 */
//public class BirthdaysActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
public class EcMembersActivity extends AppCompatActivity {

    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;

    String jsonArray_meta_url="";


    ActionBar actionbar;
    TextView textview;
    TextView m_ec_text_year;
    DrawerLayout.LayoutParams layoutparams;
    Typeface font_lato;

    Button m_arrow_button_left;
    Button m_arrow_button_right;
    ImageView m_banner_of_ec_board_by_year;

    String[] ec_year_array;
    int cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ecmembers);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(25,94,159)));
        font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");
        ActionBarTitleGravity();

        arrayList = new ArrayList<>();
        context=this;
        lv = (ListView) findViewById(R.id.listView);
        m_arrow_button_left= (Button) findViewById(R.id.arrow_button_left);
        m_arrow_button_right= (Button) findViewById(R.id.arrow_button_right);
        m_ec_text_year= (TextView) findViewById(R.id.ec_text_year);
        m_banner_of_ec_board_by_year= (ImageView) findViewById(R.id.banner_of_ec_board_by_year);



        ec_year_array=new String[]{"2016","2015","2014","2013","2012","2011","2010"};
        final String banner_of_ec_board_by_year_string[]=new String[]{"sixteen","fifteen","fourteen","thirteen","twelve","eleven","ten",};

        cursor=0;
        m_ec_text_year.setText(ec_year_array[cursor]);

        m_arrow_button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cursor<=0)
                {
                    Toast.makeText(context, "Does not exist", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cursor--;
                    arrayList = new ArrayList<>();
//                    Drawable temp= Drawable.createFromPath("R.drawable."+banner_of_ec_board_by_year_string[cursor]);

                    int id = getResources().getIdentifier(banner_of_ec_board_by_year_string[cursor], "drawable", getPackageName());
                    m_banner_of_ec_board_by_year.setImageResource(id);

                Toast.makeText(context, "R.drawable."+banner_of_ec_board_by_year_string[cursor], Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ReadJSON().execute("http://iamimam.com/directory/contact.txt");

//                new EcMembersActivity.ReadJSON().execute("http://iamimam.com/directory/contact.txt");
                    }
                });
                    m_ec_text_year.setText(ec_year_array[cursor]);
                }

            }
        });

        m_arrow_button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor>=ec_year_array.length-1)
                {
                    Toast.makeText(context, "Does not exist", Toast.LENGTH_SHORT).show();
                }
                else {


                    cursor++;
                    int id = getResources().getIdentifier(banner_of_ec_board_by_year_string[cursor], "drawable", EcMembersActivity.this.getPackageName());
                    m_banner_of_ec_board_by_year.setImageResource(id);

                    Toast.makeText(context, "R.drawable."+banner_of_ec_board_by_year_string[cursor], Toast.LENGTH_SHORT).show();
                    arrayList = new ArrayList<>();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ReadJSON().execute("http://iamimam.com/directory/member_a.txt");

//                new EcMembersActivity.ReadJSON().execute("http://iamimam.com/directory/contact.txt");
                        }
                    });
                    m_ec_text_year.setText(ec_year_array[cursor]);
                }
            }
        });




        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");

//                new EcMembersActivity.ReadJSON().execute("http://iamimam.com/directory/contact.txt");
            }
        });



    }



    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            dialog = ProgressDialog.show(context,"please wait","processing");

        }

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            dialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("data");


                JSONArray jsonArray_meta =  jsonObject.getJSONArray("meta");
                JSONObject productObject_meta = jsonArray_meta.getJSONObject(0);
                jsonArray_meta_url=  productObject_meta.getString("next_page_url");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product(
                            productObject.getString("user_image"),
                            productObject.getString("name"),
                            productObject.getString("email"),
                            productObject.getString("mobile_number")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapterEC adapter = new CustomListAdapterEC(getApplicationContext(), R.layout.list_item_ec, arrayList);
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    private void ActionBarTitleGravity() {
        // TODO Auto-generated method stub

        actionbar = getSupportActionBar();
        textview = new TextView(getApplicationContext());
        layoutparams = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        textview.setLayoutParams(layoutparams);
        textview.setText("EC MEMBERS");
        textview.setTypeface(font_lato);
        textview.setTextColor(Color.WHITE);
        textview.setGravity(Gravity.CENTER);
        textview.setTextSize(20);
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionbar.setCustomView(textview);

    }


}
