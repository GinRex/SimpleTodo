package com.dattgk.simpletodo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {

    TextView EditItem;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        EditItem = (TextView)findViewById(R.id.editText);

        Intent i = getIntent();
        EditItem.setText(i.getStringExtra("name"));

        Save = (Button)findViewById(R.id.btnSave);
    }

    public void SaveItem(View v) {
            Intent back = new Intent(EditItemActivity.this, MainActivity.class);
            back.putExtra("name", EditItem.getText().toString());
            setResult(RESULT_OK, back);
            finish();
    }


}
