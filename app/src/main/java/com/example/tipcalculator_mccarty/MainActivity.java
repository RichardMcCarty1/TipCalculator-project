package com.example.tipcalculator_mccarty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private double inputAmount;
    private double tipMultiplier;
    private EditText people;
    private RadioGroup tipGroup;
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
        tipGroup = (RadioGroup) findViewById(R.id.radioGroup);
        otherTip = (RadioButton) findViewById(R.id.customTip);
        people = (EditText) findViewById(R.id.people);
        tipAmount = (EditText) findViewById(R.id.customTipText);
        tipAmount.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.calculate);
        tabPrice = (EditText) findViewById(R.id.inputAmount);
        tabPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if((tabPrice.getText() != null) && s.toString().trim().length()>0) {
                    Toast.makeText(getApplicationContext(), tabPrice.getText(), Toast.LENGTH_SHORT).show();
                    if (tabPrice.getText().toString().charAt(0) == '.') {
                        showErrorAlert("Price is  too low", findViewById(R.id.inputAmount).getId());
                        tabPrice.setText(null);
                    }
                }
            }
        });
        people.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if((people.getText() != null)  && s.toString().trim().length()>0) {
                    if(Float.valueOf(people.getText().toString()) < 1) {
                        showErrorAlert("Number of people is too low", findViewById(R.id.inputAmount).getId());
                        people.setText(null);
                    }
                }
            }
        });
        tipAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if((tipAmount.getText() != null) && s.toString().trim().length()>0) {
                    if(Float.valueOf(tipAmount.getText().toString()) < 1) {{
                        showErrorAlert("Tip is too low", findViewById(R.id.inputAmount).getId());
                        tipAmount.setText(null);
                    }}
                }
            }
        });
        Intent i = new Intent(this, SecondActivity.class);

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if(tabPrice.getText() != null) {
            outState.putString("tabPrice", tabPrice.getText().toString());
        }
        else {
            outState.putString("tabPrice", "");
        }
        if(people.getText() != null) {
            outState.putString("people", people.getText().toString());
        }
        else {
            outState.putString("people", "");
        }
        if(tipAmount.getText() != null) {
            outState.putString("tipAmount", tipAmount.getText().toString());
        }
        else {
            outState.putString("tipAmount", "");
        }
        if(tipGroup.getCheckedRadioButtonId() != -1) {
            outState.putInt("tipGroup", tipGroup.getCheckedRadioButtonId());
            if(tipGroup.getCheckedRadioButtonId() != R.id.customTip) {
                outState.putDouble("tipMulti", tipMultiplier);
            }
        }
        else {
            outState.putString("tipGroup", "");
        }
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tabPrice.setText(savedInstanceState.getString("tabPrice"));
        people.setText(savedInstanceState.getString("people"));
        tipAmount.setText(savedInstanceState.getString("tipAmount"));
        tipGroup.check(savedInstanceState.getInt("tipGroup"));
        tipMultiplier = savedInstanceState.getDouble("tipMulti", 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculate  && (people.getText() != null) && (tabPrice != null)) {
            if(!otherTip.isChecked() || (otherTip.isChecked() && tipAmount != null)) {
                startSecondActivity();
            }
        }
        else if( v.getId() == R.id.reset){
            people.setText(null);
            tabPrice.setText(null);
            tipAmount.setText(null);
            tipGroup.clearCheck();
            tipAmount.setVisibility(View.INVISIBLE);
        }
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
        i.putExtra("totalPrice", tabPrice.getText().toString());
        i.putExtra("numPeople", people.getText().toString());
        if(otherTip.isChecked()) {
            i.putExtra("specialTipBool", true);
            i.putExtra("tipAmountt", tipAmount.getText().toString());
        }
        else {
            i.putExtra("specialTipBool", false);
            i.putExtra("tipAmountt", String.valueOf(tipMultiplier));
        }
        startActivityForResult(i, REQ_CODE);
    }

    //private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
    //    @Override
    //    public boolean onKey(View v, int keyCode, KeyEvent event) {
    //        switch (v.getId()) {
    //            case R.id.inputAmount:
    //                if(tabPrice.getText() != null) {
    //                    Toast.makeText(getApplicationContext(), tabPrice.getText(), Toast.LENGTH_SHORT).show();
    //                    if (tabPrice.getText().toString().charAt(0) == '.') {
    //                        showErrorAlert("Price is  too low", findViewById(R.id.inputAmount).getId());
    //                        tabPrice.setText(null);
    //                    }
    //                    return true;
    //                }
    //                break;
    //            case R.id.people:
    //                if(people.getText() != null) {
    //                    if(Float.valueOf(people.getText().toString()) < 1) {
    //                        showErrorAlert("Number of people is too low", findViewById(R.id.inputAmount).getId());
    //                        people.setText(null);
    //                    }
    //                    return true;
    //                }
    //                break;
    //            case R.id.customTipText:
    //                if(tipAmount.getText() != null) {
    //                if(Float.valueOf(tipAmount.getText().toString()) < 1) {{
    //                    showErrorAlert("Tip is too low", findViewById(R.id.inputAmount).getId());
    //                    tipAmount.setText(null);
    //                }}
    //                return true;
    //                }
    //                break;
    //        }
    //        return false;
    //    }
    //};
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