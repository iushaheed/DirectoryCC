package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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
public class EcMembersActivity extends AppCompatActivity{
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

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setTitle("HAPPY BIRTHDAY !");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(90,68,161,100)));

        arrayList = new ArrayList<>();
        context=this;

//        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

//        dateTime= (TextView) findViewById(R.id.date_time);

        //setting date
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());

        // textView is the TextView view that should display it
//        dateTime.setText("IT'S "+currentDateTimeString+ " !");


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

        //
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        //
    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

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
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            arrayList = new ArrayList<>();
//            context=this;
////            mMembershipTypeInToolbar.setText("ALPHA MEMBERS");
////            mMemberType.setText("ALPHA MEMBERS");
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJSON().execute("http://192.168.0.101:8000/apps api_getMembers_of_a_type/Alpha");
//                }
//            });
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//            arrayList = new ArrayList<>();
//            context=this;
////            mMembershipTypeInToolbar.setText("BETA MEMBERS");
////            mMemberType.setText("BETA MEMBERS");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJSON().execute("http://192.168.0.101:8000/apps api_getMembers_of_a_type/Beta");
//                }
//            });
//
//
//        } else if (id == R.id.nav_slideshow) {
//            arrayList = new ArrayList<>();
//            context=this;
////            mMembershipTypeInToolbar.setText("GAMMA MEMBERS");
////            mMemberType.setText("GAMMA MEMBERS");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJSON().execute("http://192.168.0.101:8000/apps api_getMembers_of_a_type/Gamma");
//                }
//            });
//
//        } else if (id == R.id.nav_manage) {
//            arrayList = new ArrayList<>();
//            context=this;
////            mMembershipTypeInToolbar.setText("FREE MEMBERS");
////            mMemberType.setText("FREE MEMBERS");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJSON().execute("http://192.168.0.101:8000/apps api_getMembers_of_a_type/Beta");
//                }
//            });
//
//        } else if (id == R.id.nav_share) {
//            Toast.makeText(context, "Loading website", Toast.LENGTH_SHORT).show();
//
//        } else if (id == R.id.nav_send) {
//            Toast.makeText(context, "Loading facebook", Toast.LENGTH_SHORT).show();
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

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
