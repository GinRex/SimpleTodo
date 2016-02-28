package com.dattgk.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {

    TextView name;
    TextView date;
    public RadioGroup check;
    Button add;
    public String priocheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name = (TextView)findViewById(R.id.etNameAdd);

        date = (TextView)findViewById(R.id.etDate);

        check = (RadioGroup)findViewById(R.id.rbtnpriorityAdd);

        add = (Button)findViewById(R.id.btnAdd);

        check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)findViewById(checkedId);
                priocheck = rb.getText().toString();
            }
        });

    }

    public void AddItem(View v) {
        Intent i = new Intent(AddItem.this, MainActivity.class);

        i.putExtra("name", name.getText().toString());
        i.putExtra("date", date.getText().toString());
        i.putExtra("prio", priocheck);

        startActivity(i);
    }
}
