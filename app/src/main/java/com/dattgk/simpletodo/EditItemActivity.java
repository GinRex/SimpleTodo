package com.dattgk.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {

    TextView EditItem;
    TextView date;
    public RadioGroup check;
    Button Save;
    public String priocheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        EditItem = (TextView)findViewById(R.id.etNameEdit);

        date = (TextView)findViewById(R.id.etDateEdit);

        check = (RadioGroup)findViewById(R.id.rbtnpriorityEdit);



        Intent i = getIntent();
        EditItem.setText(i.getStringExtra("nameEdit"));
        date.setText(i.getStringExtra("dateEdit"));
        priocheck = i.getStringExtra("prioEdit");

        Save = (Button)findViewById(R.id.btnSave);


        check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)findViewById(checkedId);
                priocheck = rb.getText().toString();
            }
        });
    }

    public void SaveItem(View v) {
            Intent back = new Intent(EditItemActivity.this, MainActivity.class);
            back.putExtra("nameEdit", EditItem.getText().toString());
            back.putExtra("dateEdit", date.getText().toString());
            back.putExtra("prioEdit", priocheck);

            setResult(RESULT_OK, back);
            finish();
    }


}
