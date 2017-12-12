package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundTask_Searchme extends AsyncTask<String,Void,String> {
//    ProgressDialog dialog;
    Context ctx;
    ArrayList<Product> arrayList;
    ListView lv;
    String jsonArray_meta_url="";
    String StringToSearch;

    public BackgroundTask_Searchme(Context ctx,String StringToSearch, ListView lv)
    {
        this.ctx =ctx;
        this.StringToSearch=StringToSearch;
//        arrayList = new ArrayList<>();
        this.lv=lv;

    }

    @Override
    protected void onPreExecute() {
//            super.onPreExecute();
//        dialog = ProgressDialog.show(ctx,"please wait","Loading Members");
    }

    @Override
    protected String doInBackground(String... params) {
        String search_url="http://192.168.0.101:8000/search?data=";
        return readURL(search_url+""+StringToSearch);
    }


    @Override
    protected void onPostExecute(String content) {
//        dialog.dismiss();
        try {
            JSONObject jsonObject = new JSONObject(content);

            JSONArray jsonArray =  jsonObject.getJSONArray("data");
            JSONArray jsonArray_meta =  jsonObject.getJSONArray("meta");

            JSONObject productObject_meta = jsonArray_meta.getJSONObject(0);
            jsonArray_meta_url=  productObject_meta.getString("next_page_url");
            Toast.makeText(ctx, ""+jsonArray_meta_url, Toast.LENGTH_SHORT).show();

            arrayList=new ArrayList<>();
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
        CustomListAdapter adapter = new CustomListAdapter(ctx, R.layout.list_item, arrayList);
        lv.setAdapter(adapter);
    }


    static String readURL(String theUrl) {
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
