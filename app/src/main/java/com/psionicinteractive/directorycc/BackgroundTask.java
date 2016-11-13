package com.psionicinteractive.directorycc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
//    ProgressDialog dialog;
    Context ctx;
    public BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
//        alertDialog = new AlertDialog.Builder(ctx).create();
//        alertDialog.setTitle("Login Information....");
//        dialog = ProgressDialog.show(ctx,"please wait","processing");
    }
    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://www.iamimam.com/club/register.php";
//        String login_url = "http://192.168.0.100:8000/authmob";
        String login_url = "http://cable.psionichub.com/authmob";
        String method = params[0];
        ///////////////register
        if (method.equals("register")) {
            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ///////////////login
        else if(method.equals("login"))
        {
            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("fcm_token_send")){
            String fcm_reg_id_url="http://192.168.0.101:8000/fireBaseSaveREGID";
//            Toast.makeText(ctx, "sending", Toast.LENGTH_SHORT).show();
            Log.e("jaitese","ghorardimjay");
            Log.v("sending","sending dcm regID");
            String reg_id = params[1];
            String  token = params[2];
            try {
                URL url = new URL(fcm_reg_id_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("regID", "UTF-8") + "=" + URLEncoder.encode(reg_id, "UTF-8") + "&" +
                        URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response += line;
                }
                Log.v("Response from server for FCM",response);

                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "fcm regid saved";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
//        dialog.dismiss();
        try{
        if(result.contains("token"))
        {
//            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Toast.makeText(ctx, "Welcome to Dhaka Club", Toast.LENGTH_LONG).show();
            JSONObject tokenholder = new JSONObject(result);
            String token = (String) tokenholder.get("token");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("token",token);
            editor.apply();

            Intent i = new Intent(ctx, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ctx.startActivity(i);


        }
        else if(result.equals(""))
        {
            Toast.makeText(ctx, "Error login", Toast.LENGTH_LONG).show();
        }

        }catch(Exception e){
            Toast.makeText(ctx, "Login failed. please try again. "+result , Toast.LENGTH_LONG).show();

        }
    }
}
