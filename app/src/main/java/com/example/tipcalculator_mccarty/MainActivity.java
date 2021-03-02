package com.example.tipcalculator_mccarty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.EditText;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private double inputAmount;
    private double tipMultiplier;
    private EditText people;
    private EditText tabPrice;
    private EditText tipAmount;
    private Button button;
    private RadioButton otherTip;
    private int REQ_CODE = 1;
    private int RES_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        otherTip = (RadioButton) findViewById(R.id.customTip);
        people = (EditText) findViewById(R.id.people);
        tipAmount = (EditText) findViewById(R.id.customTipText);
        tipAmount.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.calculate);
        tabPrice = (EditText) findViewById(R.id.inputAmount);
        people.setOnKeyListener(mKeyListener);
        tipAmount.setOnKeyListener(mKeyListener);
        tabPrice.setOnKeyListener(mKeyListener);
        Intent i = new Intent(this, SecondActivity.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputAmount:
        }
        startSecondActivity();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.tenPercent:
                if (checked)
                    tipMultiplier = .1;
                    break;
            case R.id.fiftPercent:
                if (checked)
                    tipMultiplier = .15;
                    break;
            case R.id.twentPercent:
                if (checked)
                    tipMultiplier = .2;
                    break;
            case R.id.customTip:
                if (checked)
                    tipAmount.setVisibility(View.VISIBLE);
                    break;
        }
    }
    public void startSecondActivity() {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("text", tabPrice.getText().toString());
        startActivityForResult(i, REQ_CODE);
    }

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.inputAmount:
                    Toast.makeText(MainActivity.this, tabPrice.getText().toString().charAt(0), Toast.LENGTH_SHORT).show();
                    //if(tabPrice.getText().toString().charAt(0) == '.') {
                        showErrorAlert("Price is  too low", findViewById(R.id.inputAmount).getId());
                        //tabPrice.setText(null);
                    //}
                    break;
                case R.id.people:
                    Toast.makeText(MainActivity.this, people.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.customTipText:
                    Toast.makeText(MainActivity.this, tipAmount.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }

    };
    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ConstraintLayout layout = findViewById(R.id.mainLayout);
        if (requestCode == REQ_CODE && resultCode == RES_CODE) {
            int image = data.getExtras().getInt("image");
            Toast.makeText(getApplicationContext(), image, Toast.LENGTH_SHORT).show();
            layout.setBackground(getDrawable(image));
        }
    }
}