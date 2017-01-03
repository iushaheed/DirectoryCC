package com.psionicinteractive.directorycc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
public class EcMembersActivity_withpager extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ListView lv;
    static ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ecmembers_withpager);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);



    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            ListView lv= (ListView) rootView.findViewById(R.id.listView);

            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

//            arrayList=loadList(getArguments().getInt(ARG_SECTION_NUMBER));
            int section=getArguments().getInt(ARG_SECTION_NUMBER);
//            arrayList=new ArrayList<>();
//            loadList(section);


            CustomListAdapterEC adapter = new CustomListAdapterEC(getContext(), R.layout.list_item_ec, loadList(getArguments().getInt(ARG_SECTION_NUMBER)));
//            Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
            lv.setAdapter(adapter);
//            arrayList.clear();




            return rootView;
        }

        private ArrayList loadList(int section) {
            ArrayList arrayList=new ArrayList<>();

            if(section==1){

//                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","one","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","one","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","one","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","one","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","one","email","mobile_number"));

            }else if(section==2)
            {
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","two","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","two","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","two","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","two","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","two","email","mobile_number"));

            }
            else if(section==3){
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","three","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","three","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","three","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","three","email","mobile_number"));
                arrayList.add(new Product("http://iamimam.com/directory/images/1.jpg","three","email","mobile_number"));

            }
            else{
                new ReadJSON().execute("http://iamimam.com/directory/contact.txt");

            }
            Toast.makeText(getContext(), ""+section, Toast.LENGTH_SHORT).show();
            return arrayList;
        }

        class ReadJSON extends AsyncTask<String, Integer, String> {

            int section;

            @Override
            protected void onPreExecute() {
//            super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                return readURL(params[0]);
            }

            @Override
            protected void onPostExecute(String content) {
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
                        Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
