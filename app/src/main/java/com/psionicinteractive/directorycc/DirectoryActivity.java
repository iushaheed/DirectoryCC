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
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

//import static com.psionicinteractive.directorycc.R.id.checkboxId;

public class DirectoryActivity extends AppCompatActivity{


//    TextView m_name;
//    TextView m_email;
//    TextView m_phone;

    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog dialog;
    Context context;
//    TextView mMemberType;
//    TextView mMembershipTypeInToolbar;
    public Handler mHandler;
    public View ftView;
    public boolean isLoading=false;
    CustomListAdapter mAdapter;
    String jsonArray_meta_url="";
    EditText inputSearch;

    ActionBar actionbar;
    TextView textview;
    DrawerLayout.LayoutParams layoutparams;
    Typeface font_lato;

    LinearLayout linearLayout_filter;
    Button advance_search_button;

//    TextView dropText;

    Spinner spinner_name;
    Spinner spinner_year;
    String[] spinner_array_name;
    String[] spinner_array_year;

    String[] search_parameters;

    Button after_spinner_search_button;


//    Switch mSmsSwich;
//    CheckBox cb_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
//
//        getSupportActionBar().setTitle("CADET COLLEGE CLUB");
//        getSupportActionBar().setTitle("ALL MEMBERS");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(100,255,255,255)));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(25,94,159)));
        font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");
        ActionBarTitleGravity();

        spinner_name= (Spinner) findViewById(R.id.spinner_name);
        spinner_year= (Spinner) findViewById(R.id.spinner_year);
        after_spinner_search_button= (Button) findViewById(R.id.after_spinner_search_button);


        spinner_array_name=new String[]{"Choose College","A Cadet College","B Cadet College","C Cadet College","D Cadet College"};
        spinner_array_year=new String[]{"Choose Batch","Batch 2010-A","Batch 2010-B","Batch 2011-A","Batch 2011-B"};
        search_parameters=new String[2];

        ArrayAdapter<String> adapter_name = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_array_name);
        spinner_name.setAdapter(adapter_name);

        spinner_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search_parameters[0]=spinner_array_name[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_array_year);
        spinner_year.setAdapter(adapter_year);

        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search_parameters[1]=spinner_array_year[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        after_spinner_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String what_to_search="";
                for (String a:search_parameters) {
                    what_to_search=what_to_search+a+" ";
                }

                RelativeLayout.LayoutParams llp= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,0);
//                linearLayout_filter.setBottom(R.);
                llp.addRule(RelativeLayout.BELOW,R.id.inputSearch);
                linearLayout_filter.setLayoutParams(llp);

                Toast.makeText(context,"Searching "+ what_to_search, Toast.LENGTH_SHORT).show();
            }
        });







        arrayList = new ArrayList<>();
        context=this;
        inputSearch = (EditText) findViewById(R.id.inputSearch);

//        linearLayout_filter= (LinearLayout) findViewById(R.id.advance_filter_layout);

        LayoutInflater li= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView = li.inflate(R.layout.footer_view,null);
        mHandler = new MyHandler();


        advance_search_button= (Button) findViewById(R.id.advance_search_button);
//        dropText= (TextView) findViewById(R.id.droptest);

//        mMembershipTypeInToolbar= (TextView) findViewById(R.id.toolbar_title);
        //possible reason for list problem
//        cb_t= (CheckBox) findViewById(R.id.checkboxId);
        lv = (ListView) findViewById(R.id.listView);

        linearLayout_filter= (LinearLayout) findViewById(R.id.advance_filter_layout);
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

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                //Check when scroll to last item in listview, in this tut, init data in listview = 10 item
                if(view.getLastVisiblePosition() == totalItemCount-1 && lv.getCount() >=10 && isLoading == false) {
                    isLoading = true;
                    Toast.makeText(context, "Scrolling", Toast.LENGTH_SHORT).show();
                    Thread thread = new ThreadGetMoreData();
                    //Start thread
                    thread.start();
                }

            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                DirectoryActivity.this.mAdapter.getFilter().filter(s);
                Log.v("Typed",s+"");

                if(s==""){
//                    arrayList = new ArrayList<>();
//                    mMembershipTypeInToolbar.setText("Members");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new ReadJSON().execute("http://192.168.0.104:8000/api_getAllMembers");
//                        }
//                    });

                }else
                {
                    BackgroundTask_Searchme searchme=new BackgroundTask_Searchme(getApplicationContext(),s+"",lv);
                    searchme.execute();
                }



//                String searcj
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        new ReadJSON().execute("http://192.168.0.104:8000/search?data="+s);
////                new ReadJSON().execute("http://192.168.0.101:8000/api_getAllMembers");
//                    }
//                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        advance_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                linearLayout_filter.setLayoutParams(p);

//                FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                linearLayout_filter.setLayoutParams(new LinearLayout.LayoutParams(100,100));
//                dropText.setText("new text drop");
                RelativeLayout.LayoutParams llp= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                llp.addRule(RelativeLayout.BELOW,R.id.inputSearch);
                linearLayout_filter.setLayoutParams(llp);

                Toast.makeText(context, "c", Toast.LENGTH_SHORT).show();

            }
        });



//        mMembershipTypeInToolbar.setText("ALL MEMBERS");
//        mMemberType.setText("ALL MEMBERS");
//        getSupportActionBar().setTitle("ALL MEMBERS");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");

                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
            }
        });


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        if (drawer.isDrawerOpen(GravityCompat.START)) {
////            drawer.closeDrawer(GravityCompat.START);
////        } else {
////            super.onBackPressed();
////        }
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
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

//    public void open_filter_layout(View view) {
//
//        FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        p.gravity=Gravity.CENTER;
//        linearLayout_filter.setLayoutParams(p);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            arrayList = new ArrayList<>();
//            context=this;
//            mMembershipTypeInToolbar.setText("ALPHA MEMBERS");
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
//            mMembershipTypeInToolbar.setText("BETA MEMBERS");
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
//            mMembershipTypeInToolbar.setText("GAMMA MEMBERS");
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
//            mMembershipTypeInToolbar.setText("FREE MEMBERS");
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


                JSONArray jsonArray_meta =  jsonObject.getJSONArray("meta");
                JSONObject productObject_meta = jsonArray_meta.getJSONObject(0);
               jsonArray_meta_url=  productObject_meta.getString("next_page_url");
//                Toast.makeText(context, ""+jsonArray_meta_url, Toast.LENGTH_SHORT).show();
//
//                Log.v("nexturl",jsonArray_meta_url);
//                Log.v("Next URL",jsonArray_url);

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



    private ArrayList<Product> getMoreData() {

        ArrayList<Product>lst = new ArrayList<>();
//        //Sample code get new data :P

//        arrayList.add(new Product(
//                productObject.getString("image"),
//                productObject.getString("name"),
//                productObject.getString("email"),
//                productObject.getString("phone")
//        ));

        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "imam", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "ush", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "shaheed", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "shakib", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "hossen", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "rahul", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "chakrabarty", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "habib", "imam@imam", "123"));
        arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg", "wahid", "imam@imam", "123"));

        return lst;
    }

    public class ThreadGetMoreData extends Thread {
        @Override
        public void run() {
            //Add footer view after get data
            mHandler.sendEmptyMessage(0);
            //Search more data


            /////////eikhane thik kora lagbe
            ArrayList<Product> lstResult = getMoreData();
            //Delay time to show loading footer when debug, remove it when release
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Send the result to Handle
            Message msg = mHandler.obtainMessage(1, lstResult);
            mHandler.sendMessage(msg);


        }
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

//                    adapter.addListItemToAdapter((List<Product>)msg.obj);

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

    class ReadNewJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            dialog = ProgressDialog.show(context,"please wait","Loading Members");

        }

        @Override
        protected String doInBackground(String... params) {
            return readNewURL(params[0]);
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
                Toast.makeText(context, ""+jsonArray_meta_url, Toast.LENGTH_SHORT).show();

                ArrayList<Product> newArrayList=new ArrayList<>();
                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    newArrayList.add(new Product(
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


    private static String readNewURL(String theUrl) {
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
        textview.setText("ALL MEMBERS");
        textview.setTypeface(font_lato);
        textview.setTextColor(Color.WHITE);
        textview.setGravity(Gravity.CENTER);
        textview.setTextSize(20);
        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionbar.setCustomView(textview);

    }


}
