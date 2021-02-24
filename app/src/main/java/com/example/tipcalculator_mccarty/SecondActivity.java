package com.example.tipcalculator_mccarty;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity{
    private int image;
    private TextView text;

    private ConstraintLayout setBackground;
    Intent i = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text = findViewById(R.id.textView);
        String viewText = getIntent().getExtras().getString("text");
        text.setText(viewText);


    //   setOnClickListener(new View.OnClickListener(){
    //        @Override
    //        public void onClick(View v) {
    //
    //            finish();
    //        }
    //    });
    }

    //@Override
    //public void onClick(View v) {
    //    if(getResources().getResourceEntryName(v.getId()) == "supermoon") {
    //        image = R.drawable.supermoon;
    //    }
    //    else {
    //        image = R.drawable.waterfall;
    //    }
    //    setBackground.setBackgroundResource(image);
    //    i.putExtra("image", image);
    //    setResult(RESULT_OK, i);
    //}

    @Override
    public void finish() {
        i.putExtra("image", image);
        setResult(1, i);
        super.finish();
    }
}