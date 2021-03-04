package com.example.tipcalculator_mccarty;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private float tabDoub;
    private float peopleInt;
    private float tipAmount;
    private float totalDoub;
    private TextView tab;
    private TextView people;
    private TextView tipNum;
    private TextView total;

    private ConstraintLayout setBackground;
    Intent i = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tab = (TextView) findViewById(R.id.tabAmount);
        people = (TextView) findViewById(R.id.totalPeopleTab);
        tipNum = (TextView) findViewById(R.id.tipAmountCalced);
        total = (TextView) findViewById(R.id.totalPrice);
        try {
            tabDoub = Float.valueOf(getIntent().getExtras().getString("totalPrice"));
            peopleInt = Float.valueOf(getIntent().getExtras().getString("numPeople"));
            tipAmount = Float.valueOf(getIntent().getExtras().getString("tipAmountt"));
        }
        catch (NumberFormatException | NullPointerException e) {
            Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_LONG).show();
        }
        if(getIntent().getExtras().getBoolean("specialTipBool")) {
            totalDoub = (((tabDoub + tipAmount) / peopleInt));
        }
        else {
            totalDoub = (((tabDoub + (tabDoub * tipAmount)) / peopleInt));
        }
        tab.setText(String.valueOf(tabDoub));
        people.setText(String.valueOf(peopleInt));
        tipNum.setText(String.valueOf(tipAmount * tabDoub));
        total.setText(String.valueOf(totalDoub));
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putFloat("tabDoub", tabDoub);
        outState.putFloat("peopleInt", peopleInt);
        outState.putFloat("tipAmount", tipAmount);
        outState.putFloat("totalDoub", totalDoub);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tab.setText(String.valueOf(savedInstanceState.getFloat("tabdoub")));
        people.setText(String.valueOf(savedInstanceState.getFloat("peopleInt")));
        tipNum.setText(String.valueOf(savedInstanceState.getFloat("tipAmount")));
        total.setText(String.valueOf(savedInstanceState.getFloat("totalDoub")));
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
        setResult(1, i);
        super.finish();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}