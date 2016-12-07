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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import co.aenterhy.toggleswitch.ToggleSwitchButton;
import pl.droidsonroids.gif.GifTextView;

/**
 * Created by iShaheed on 8/28/2016.
 */
//public class BirthdaysActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
public class BirthdaysActivity extends AppCompatActivity{
    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;
    TextView dateTime;
    TextView m_wish_layout;
    GifTextView m_gif_img;
//    TextView mMembershipTypeInToolbar;

    ActionBar actionbar;
    TextView textview;
    DrawerLayout.LayoutParams layoutparams;
    Typeface font_lato;
    ToggleSwitchButton toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(25,94,159)));
        ActionBarTitleGravity();

        arrayList = new ArrayList<>();
        context=this;


        font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");
        toggle = (ToggleSwitchButton) findViewById(R.id.toggle);
        lv = (ListView) findViewById(R.id.listView);
        m_wish_layout= (TextView) findViewById(R.id.wish_layout);
        m_wish_layout.setTypeface(font_lato);
        m_gif_img= (GifTextView) findViewById(R.id.headerlayout);

        toggle.setOnTriggerListener(new ToggleSwitchButton.OnTriggerListener() {
            @Override
            public void toggledUp() {
                arrayList = new ArrayList<>();
                m_wish_layout.setText("BIRTHDAYS");
                m_gif_img.setBackgroundResource(R.drawable.birthdaycandle);
                Toast.makeText(BirthdaysActivity.this, "BIRTHDAYS", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ReadJSON().execute("http://iamimam.com/directory/member_a.txt");
                    }
                });
            }

            @Override
            public void toggledDown() {
                arrayList = new ArrayList<>();
                m_wish_layout.setText("ANNIVERSARIES");
                m_gif_img.setBackgroundResource(R.drawable.fireworksgif);
                Toast.makeText(BirthdaysActivity.this, "ANNIVERSARIES", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
                    }
                });
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
            }
        });

//        getSupportActionBar().setTitle("HAPPY BIRTHDAY !");


        //possible reason for list problem




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

    public void openGallery(View view) {
        Intent i = new Intent(BirthdaysActivity.this, GalleryActivity.class);
        startActivity(i);

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
            CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.list_item, arrayList);
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
        textview.setText("ANNIVERSARY");
        textview.setTypeface(font_lato);
        textview.setTextColor(Color.WHITE);
        textview.setGravity(Gravity.CENTER);
        textview.setTextSize(20);
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionbar.setCustomView(textview);

    }
}
