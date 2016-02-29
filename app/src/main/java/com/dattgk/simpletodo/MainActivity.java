package com.dattgk.simpletodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<TodoList> items;
    ListAdapter itemsAdapter;
    ListView lvItems;
    public int epos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);

        readItems();

        itemsAdapter = new ListAdapter(
                MainActivity.this,
                R.layout.activity_line_todo_list,
                items
        );

        lvItems.setAdapter(itemsAdapter);
        returnResultAdd();

        setupListViewListener();
    }

    private final int REQUEST_CODE = 20;

    public void returnResultAdd() {
        Intent i = getIntent();

        TodoList item = new TodoList(i.getStringExtra("name"),i.getStringExtra("prio"), i.getStringExtra("date"));
        itemsAdapter.add(item);
        itemsAdapter.notifyDataSetChanged();
        writeItems();
    }


    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                Toast.makeText(getApplicationContext(), "Item is deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("nameEdit", items.get(pos).name);
                i.putExtra("prioEdit", items.get(pos).priority);
                i.putExtra("dateEdit", items.get(pos).date);
                epos = pos;
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            items.get(epos).name = data.getExtras().getString("nameEdit");
            items.get(epos).date = data.getExtras().getString("dateEdit");
            items.get(epos).priority = data.getExtras().getString("prioEdit");
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    public void onAddItem(View v) {

        Intent i = new Intent(MainActivity.this, AddItem.class);
        startActivity(i);
    }

    public void writeItems() {
        SharedPreferences appSharePrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = appSharePrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items);

        editor.putString("TodoList", json);
        editor.commit();
    }



    public void readItems() {
        try {
            SharedPreferences appSharePrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

            Gson gson = new Gson();
            String json = appSharePrefs.getString("TodoList", "");

            Type type = new TypeToken<ArrayList<TodoList>>() {
            }.getType();

            items = gson.fromJson(json, type);
            if (items == null) items = new ArrayList<TodoList>();
            //itemsAdapter.notifyDataSetChanged();
        } catch (NullPointerException e) {

            items = new ArrayList<TodoList>();
        }
    }


}
