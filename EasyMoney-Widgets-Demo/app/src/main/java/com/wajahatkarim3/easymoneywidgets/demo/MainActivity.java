package com.wajahatkarim3.easymoneywidgets.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    EasyMoneyEditText moneyEditText;
    EasyMoneyTextView moneyTextView;
    Spinner spinnerCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyEditText = (EasyMoneyEditText) findViewById(R.id.moneyEditText);
        moneyTextView = (EasyMoneyTextView) findViewById(R.id.moneyTextView);
        spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);

        CheckBox checkCommas = (CheckBox) findViewById(R.id.checkCommas);
        checkCommas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                {
                    moneyEditText.showCommas();
                    moneyTextView.showCommas();
                }
                else{
                    moneyEditText.hideCommas();
                    moneyTextView.hideCommas();
                }

                Log.w(TAG, "onCreate: Value: " + moneyTextView.getValueString() );
                Log.w(TAG, "onCreate: Formatted Value: " + moneyTextView.getFormattedString() );

                Log.e(TAG, "onCreate: Value: " + moneyEditText.getValueString() );
                Log.e(TAG, "onCreate: Formatted Value: " + moneyEditText.getFormattedString() );
            }
        });

        CheckBox checkCurrency = (CheckBox) findViewById(R.id.checkCurrency);
        checkCurrency.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    moneyEditText.showCurrencySymbol();
                    moneyTextView.showCurrencySymbol();
                }
                else {
                    moneyEditText.hideCurrencySymbol();
                    moneyTextView.hideCurrencySymbol();
                }

                Log.w(TAG, "onCreate: Value: " + moneyTextView.getValueString() );
                Log.w(TAG, "onCreate: Formatted Value: " + moneyTextView.getFormattedString() );

                Log.e(TAG, "onCreate: Value: " + moneyEditText.getValueString() );
                Log.e(TAG, "onCreate: Formatted Value: " + moneyEditText.getFormattedString() );
            }
        });

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String itemName = spinnerCurrency.getSelectedItem().toString();
                String symbol = itemName.substring(itemName.indexOf("(")+1, itemName.indexOf(")"));
                moneyEditText.setCurrency(symbol);
                moneyTextView.setCurrency(symbol);

                Log.w(TAG, "onCreate: Value: " + moneyTextView.getValueString() );
                Log.w(TAG, "onCreate: Formatted Value: " + moneyTextView.getFormattedString() );

                Log.e(TAG, "onCreate: Value: " + moneyEditText.getValueString() );
                Log.e(TAG, "onCreate: Formatted Value: " + moneyEditText.getFormattedString() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        moneyTextView.setText("123456");

        Log.w(TAG, "onCreate: Value: " + moneyTextView.getValueString() );
        Log.w(TAG, "onCreate: Formatted Value: " + moneyTextView.getFormattedString() );

        Log.e(TAG, "onCreate: Value: " + moneyEditText.getValueString() );
        Log.e(TAG, "onCreate: Formatted Value: " + moneyEditText.getFormattedString() );
    }
}
