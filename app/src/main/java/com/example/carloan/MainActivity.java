package com.example.carloan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity {

    private SeekBar MonthBar;
    private EditText CostIn;
    private EditText DPayIn;
    private EditText APRIn;
    private RadioButton LeaseButton;
    private RadioButton LoanButton;
    private TextView MonthlyOut;
    private TextView LLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MonthBar = findViewById(R.id.MonthBar);
        CostIn = findViewById(R.id.CostIn);
        DPayIn = findViewById(R.id.DPayIn);
        APRIn = findViewById(R.id.APRIn);
        LeaseButton = findViewById(R.id.LeaseButton);
        LoanButton = findViewById(R.id.LoanButton);
        MonthlyOut = findViewById(R.id.MonthlyOut);
        LLView = findViewById(R.id.LLView);
        MonthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LLView.setText("Length of Loan (months) " + MonthBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void convert(View view){
        double MPay;
        double MInterest;
        double lAmt;
        double DPMT;
        double carTotal;
        String parseString = "null";
        int tMonths;
        if(LoanButton.isChecked()){
            parseString = APRIn.getText().toString();
            MInterest = Double.parseDouble(parseString)/12;
            parseString = CostIn.getText().toString();
            carTotal = Double.parseDouble(parseString);
            parseString = DPayIn.getText().toString();
            DPMT = Double.parseDouble(parseString);
            lAmt = carTotal - DPMT;
            tMonths = MonthBar.getProgress();
            MPay = (MInterest * lAmt) / (1 - Math.pow((1 + MInterest), -tMonths));
            MonthlyOut.setText("$" + String.format("%.2f", MPay));
        }
        else if(LeaseButton.isChecked()){
            MonthBar.setProgress(36);
            parseString = APRIn.getText().toString();
            MInterest = Double.parseDouble(parseString)/12;
            parseString = CostIn.getText().toString();
            carTotal = Double.parseDouble(parseString)/3;
            parseString = DPayIn.getText().toString();
            DPMT = Double.parseDouble(parseString);
            lAmt = carTotal - DPMT;
            tMonths = 36;
            MPay = (MInterest*lAmt)/(1-Math.pow((1+MInterest),-tMonths));
            MonthlyOut.setText("$" + String.format("%.2f", MPay));

        }
    }
}
