package com.wajahatkarim3.easymoneywidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Wajahat Karim on 1/15/2017.
 */

public class EasyMoneyEditText extends EditText {

    private String currencySymbol;
    private boolean showCurrency;
    private boolean showCommas;

    public EasyMoneyEditText(Context context) {
        super(context);
        initView(context, null);
    }

    public EasyMoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    @Override
    protected void onFinishInflate ()
    {
        super.onFinishInflate();
        updateValue(getText().toString());
    }

    private void initView(Context context, AttributeSet attrs)
    {
        // Setting Default Parameters
        currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
        showCurrency = true;

        // Check for the attributes
        if (attrs != null)
        {
            // Attribute initialization
            final TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.EasyMoneyWidgets, 0,0);
            try {
                String currnecy = attrArray.getString(R.styleable.EasyMoneyWidgets_currency_symbol);
                if (currnecy == null)
                    currnecy = Currency.getInstance(Locale.getDefault()).getSymbol();
                setCurrency(currnecy);

                showCurrency = attrArray.getBoolean(R.styleable.EasyMoneyWidgets_show_currency, true);
                showCommas = attrArray.getBoolean(R.styleable.EasyMoneyWidgets_show_commas, true);
            }
            finally {
                attrArray.recycle();
            }
        }

        // Add Text Watcher for Decimal formatting
        initTextWatchers();
    }

    private void initTextWatchers()
    {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                EasyMoneyEditText.this.removeTextChangedListener(this);
                String backupString = charSequence.toString();

                try {
                    String originalString = charSequence.toString();
                    String inputStrin = originalString;

                    Long longval;

                    originalString = getValueString();

                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
                    if (showCommas && !showCurrency)
                        formatter.applyPattern("#,###,###,###");
                    else if (showCommas && showCurrency)
                        formatter.applyPattern(currencySymbol + " #,###,###,###");
                    else if (!showCommas && showCurrency)
                        formatter.applyPattern(currencySymbol + " ");
                    else if (!showCommas && !showCurrency)
                    {
                        setText(originalString);
                        setSelection(getText().length());
                        EasyMoneyEditText.this.addTextChangedListener(this);
                        return;
                        //formatter.applyPattern("");
                    }

                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    setText(formattedString);
                    setSelection(getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    setText(backupString);
                    setSelection(getText().length());
                }

                EasyMoneyEditText.this.addTextChangedListener(this);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void updateValue(String text)
    {
        setText(text);
    }

    //Trims all the comma of the string and returns
    public String getValueString() {

        String string = getText().toString();

        if(string.contains(",")){
            string = string.replace(",","");
        }
        if (string.contains(" ")) {
            string = string.substring(string.indexOf(" ")+1, string.length());
        }
        return string;
    }

    public String getFormattedString()
    {
        return getText().toString();
    }

    public void setCurrency(String newSymbol)
    {
        currencySymbol = newSymbol;
        updateValue(getText().toString());
    }

    public void setCurrency(Locale locale)
    {
        setCurrency(Currency.getInstance(locale).getSymbol());
    }

    public void setCurrency(Currency currency)
    {
        setCurrency(currency.getSymbol());
    }

    public void setShowCurrency(boolean value)
    {
        showCurrency = value;
        updateValue(getText().toString());
    }

    public boolean isShowCurrency()
    {
        return showCurrency;
    }

    public void showCurrencySymbol()
    {
        setShowCurrency(true);
    }

    public void hideCurrencySymbol()
    {
        setShowCurrency(false);
    }

}
