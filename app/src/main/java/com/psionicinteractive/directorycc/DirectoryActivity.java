package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
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

import static com.psionicinteractive.directorycc.R.id.checkboxId;

public class DirectoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;
//    TextView mMemberType;
    TextView mMembershipTypeInToolbar;
    public Handler mHandler;
    public View ftView;
    public boolean isLoading=false;
    CustomListAdapter adapter;
//    Switch mSmsSwich;
//    CheckBox cb_t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        arrayList = new ArrayList<>();
        context=this;

//        LayoutInflater li= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ftView = li.inflate(R.layout.footer_view,null);
//        mHandler = new MyHandler();

        //possible reason for list problem
//        cb_t= (CheckBox) findViewById(R.id.checkboxId);
        lv = (ListView) findViewById(R.id.listView);
//        mMemberType= (TextView) findViewById(R.id.membership_name);
//        mSmsSwich= (Switch) findViewById(R.id.sms_switch);
//        mSmsSwich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(mSmsSwich.isChecked()){
//    //            mSmsSwich.getVisibility();
//                    cb_t.getVisibility();
//                    Toast.makeText(context, ""+isChecked, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        mMembershipTypeInToolbar= (TextView) findViewById(R.id.toolbar_title);

        mMembershipTypeInToolbar.setText("ALL MEMBERS");
//        mMemberType.setText("ALL MEMBERS");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            arrayList = new ArrayList<>();
            context=this;
            mMembershipTypeInToolbar.setText("ALPHA MEMBERS");
//            mMemberType.setText("ALPHA MEMBERS");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://iamimam.com/directory/member_a.txt");
                }
            });
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            arrayList = new ArrayList<>();
            context=this;
            mMembershipTypeInToolbar.setText("BETA MEMBERS");
//            mMemberType.setText("BETA MEMBERS");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://iamimam.com/directory/member_b.txt");
                }
            });


        } else if (id == R.id.nav_slideshow) {
            arrayList = new ArrayList<>();
            context=this;
            mMembershipTypeInToolbar.setText("GAMMA MEMBERS");
//            mMemberType.setText("GAMMA MEMBERS");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://iamimam.com/directory/member_c.txt");
                }
            });

        } else if (id == R.id.nav_manage) {
            arrayList = new ArrayList<>();
            context=this;
            mMembershipTypeInToolbar.setText("FREE MEMBERS");
//            mMemberType.setText("FREE MEMBERS");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://iamimam.com/directory/member_d.txt");
                }
            });

        } else if (id == R.id.nav_share) {
            Toast.makeText(context, "Loading website", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(context, "Loading facebook", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                JSONArray jsonArray =  jsonObject.getJSONArray("contacts");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product(
                            productObject.getString("image"),
                            productObject.getString("name"),
                            productObject.getString("email"),
                            productObject.getString("phone")
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

    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    //loading view
                    lv.addFooterView(ftView);
                    break;
                case 1:
                    //update data adaper and ui
                    adapter.addListItemToAdapter((ArrayList<Product>)msg.obj);

                    //remove loading view
                    lv.removeFooterView(ftView);
                    isLoading=false;
                    break;
                default:
                    break;


            }
        }
    }

//    https://www.youtube.com/watch?v=XwIKb_f0Y_w

}
