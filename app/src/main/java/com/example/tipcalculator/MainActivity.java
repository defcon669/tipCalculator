package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText numberID;
    private TextView percentID;
    private SeekBar seekBarID;
    private TextView tipPercentID;
    private float percentTip;
    private TextView totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberID = (EditText) findViewById(R.id.numberID);
        percentID = (TextView) findViewById(R.id.percentID);
        seekBarID = (SeekBar) findViewById(R.id.seekBarID);
        Button resultID = (Button) findViewById(R.id.resultID);
        tipPercentID = (TextView) findViewById(R.id.tipPercentID);
        totalBill = (TextView) findViewById(R.id.billTotalID);


        resultID.setOnClickListener(this);
        seekBarID.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercentID.setText(String.format("%s%%", String.valueOf(seekBarID.getProgress())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                percentTip = seekBarID.getProgress();
            }
        });
    }

    @Override
    public void onClick(View view) {
        calculate();
    }

    public void calculate() {
        short result;

        if (!numberID.getText().toString().equals("")) {
            float enteredAmount = Float.parseFloat(numberID.getText().toString());
            result = (short) ((enteredAmount * percentTip) / 100);
            percentID.setText(String.format("Your tip amount will be $%s", String.valueOf(result)));
            totalBill.setText(String.format("Your total bill amount plus tip will be: $%s", String.valueOf(enteredAmount + result)));
        } else {
            Toast.makeText(MainActivity.this, "Please enter bill amount $%s", Toast.LENGTH_LONG).show();
        }

    }
}