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

    private String _currencySymbol;
    private boolean _showCurrency;
    private boolean _showCommas;

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
        _currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
        _showCurrency = true;
        _showCommas = true;

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

                _showCurrency = attrArray.getBoolean(R.styleable.EasyMoneyWidgets_show_currency, true);
                _showCommas = attrArray.getBoolean(R.styleable.EasyMoneyWidgets_show_commas, true);
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
        /*
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
            if (_showCurrency)
                formatter.applyPattern(_currencySymbol + " #,###,###,###");
            else formatter.applyPattern("#,###,###,###");
            String formattedString = formatter.format(longval);

            //setting text after format to EditText
            setText(formattedString);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        */

        String backupString = valueStr;
        try {
            String originalString = valueStr;

            long longval;

            originalString = getValueString();
            longval = (Long.parseLong(originalString));
            String formattedString = getDecoratedStringFromNumber(longval);

            //setting text after format to EditText
            setText(formattedString);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            setText(backupString);

            String valStr = getValueString();

            if (valStr.equals(""))
            {
                long val = 0;
                setText(getDecoratedStringFromNumber(val));
            }
            else {
                // Some decimal number
                if (valStr.contains("."))
                {
                    if (valStr.indexOf(".") == valStr.length()-1)
                    {
                        // decimal has been currently put
                        String front = getDecoratedStringFromNumber(Long.parseLong(valStr.substring(0, valStr.length()-1)));
                        setText(front + ".");
                    }
                    else {
                        String[] nums = getValueString().split("\\.");
                        String front = getDecoratedStringFromNumber(Long.parseLong(nums[0]));
                        setText(front+"."+nums[1]);
                    }
                }
            }
        }
    }

    private String getDecoratedStringFromNumber(long number)
    {
        String numberPattern = "#,###,###,###";
        String decoStr = "";

        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        if (_showCommas && !_showCurrency)
            formatter.applyPattern(numberPattern);
        else if (_showCommas && _showCurrency)
            formatter.applyPattern(_currencySymbol + " " + numberPattern);
        else if (!_showCommas && _showCurrency)
            formatter.applyPattern(_currencySymbol + " ");
        else if (!_showCommas && !_showCurrency)
        {
            decoStr = number + "";
            return decoStr;
        }

        decoStr = formatter.format(number);

        return decoStr;
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
        _currencySymbol = newSymbol;
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
        _showCurrency = value;
        setValue(getText().toString());
    }

    public boolean isShowCurrency()
    {
        return _showCurrency;
    }

    public void showCurrencySymbol()
    {
        setShowCurrency(true);
    }

    public void hideCurrencySymbol()
    {
        setShowCurrency(false);
    }

    public void showCommas()
    {
        _showCommas = true;
        setValue(getText().toString());
    }

    public void hideCommas()
    {
        _showCommas = false;
        setValue(getText().toString());
    }
}
