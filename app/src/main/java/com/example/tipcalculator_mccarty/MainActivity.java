package com.example.tipcalculator_mccarty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.example.tipcalculator_mccarty.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    private ConstraintLayout setBackground;
    private int REQ_CODE = 1;
    private int RES_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.set_background);
        editText = findViewById(R.id.editTextTextPersonName);
        Intent i = new Intent(this, SecondActivity.class);

    }

    @Override
    public void onClick(View v) {
        startSecondActivity();
    }
    public void startSecondActivity() {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("text", editText.getText().toString());
        startActivityForResult(i, REQ_CODE);
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