package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by iShaheed on 8/28/2016.
 */
//public class BirthdaysActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
public class EcMembersActivity_beforepager extends AppCompatActivity{
    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;
    TextView dateTime;
    TextView m_ec_textview;
//    TextView mMembershipTypeInToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecmembers);

        getSupportActionBar().hide();

        arrayList = new ArrayList<>();
        context=this;

        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());

        //possible reason for list problem
        lv = (ListView) findViewById(R.id.listView);

        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");
        m_ec_textview= (TextView) findViewById(R.id.ec_textview);
        m_ec_textview.setTypeface(font_lato);

//        mMembershipTypeInToolbar= (TextView) findViewById(R.id.toolbar_title);

//        mMembershipTypeInToolbar.setText("BIRTHDAY");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sendsms) {

            ArrayList<Product> productsTemp=CustomListAdapter.products;
            String shob="";
            for(int c=0;c<productsTemp.size();c++){
                Product productTemp= productsTemp.get(c);
                if (productTemp.getIsTrue()==true){
                    shob=shob+productTemp.getPhoneNumber()+";";
                }
            }
            if(shob==""){
                Toast.makeText(context, "No member selected", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, shob, Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("smsto:"+shob);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "");
                startActivity(i);
            }


            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
