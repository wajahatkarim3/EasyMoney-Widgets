package com.wajahatkarim3.easymoneywidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Wajahat on 1/19/2017.
 */

public class EasyMoneyTextView extends TextView {

    private String currencySymbol;
    private boolean showCurrency;

    public EasyMoneyTextView(Context context) {
        super(context);
        initView(context, null);
    }

    public EasyMoneyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
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
            }
            finally {
                attrArray.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate ()
    {
        super.onFinishInflate();
        setValue(getText().toString());
    }

    public void setValue(String valueStr)
    {
        try {
            String originalString = valueStr.toString();
            String inputStrin = originalString;

            Long longval;
            if (originalString.contains(",")) {
                originalString = originalString.replaceAll(",", "");
            }

            originalString = getValueString();

            longval = Long.parseLong(originalString);

            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
            if (showCurrency)
                formatter.applyPattern(currencySymbol + " #,###,###,###");
            else formatter.applyPattern("#,###,###,###");
            String formattedString = formatter.format(longval);

            //setting text after format to EditText
            setText(formattedString);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
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
        setValue(getText().toString());
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
        setValue(getText().toString());
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
