package com.psionicinteractive.directorycc;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PSIONIC on 11/7/2016.
 */
public class SearchActivity extends Activity {
    // List view
    private ListView lv;
    TextView m_search_title;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Typeface font_lato = Typeface.createFromAsset(getAssets(),  "fonts/lato.ttf");

        m_search_title= (TextView) findViewById(R.id.search_title);
        inputSearch= (EditText) findViewById(R.id.inputSearch);

        m_search_title.setTypeface(font_lato);
        inputSearch.setTypeface(font_lato);

    }


}
