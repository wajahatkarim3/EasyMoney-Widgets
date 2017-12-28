EasyMoney-Widgets
============
 [![Build Status](https://travis-ci.org/wajahatkarim3/EasyMoney-Widgets.svg?branch=master)](https://travis-ci.org/wajahatkarim3/EasyMoney-Widgets) [ ![Download](https://api.bintray.com/packages/wajahatkarim3/EasyMoney-Widgets/com.wajahatkarim3.EasyMoney-Widgets/images/download.svg) ](https://bintray.com/wajahatkarim3/EasyMoney-Widgets/com.wajahatkarim3.EasyMoney-Widgets/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyMoney--Widgets-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5123) [![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15) [![API](https://img.shields.io/badge/MaterialUp-EasyMoney--Widgets-yellowgreen.svg)](https://material.uplabs.com/posts/easymoney-widgets)
 
The widgets (EditText and TextView) for support of money requirements like currency, number formatting, comma formatting etc. 

![](https://github.com/wajahatkarim3/EasyMoney-Widgets/blob/master/Art/Demo.gif)

**NOTE:** This library support ALL and ANY kind of currency. The currencies in the demo are just for example. The library is NOT limited to those currencies.

Features
========
* EasyMoneyEditText and EasyMoneyTextView widgets
* Custom currency support added through string symbol, Locales, or Currency objects
* Commas formatting support added
* Show/hide currency symbol
* Show/hide commas in the widgets
* Get raw value in string or formatted value in string

Demo
====
Install [Demo](https://github.com/wajahatkarim3/EasyMoney-Widgets/releases/download/1.0.0/EasyMoney-Widgets-Demo_1.0.0.apk) app or APK from [Releases](https://github.com/wajahatkarim3/EasyMoney-Widgets/releases) on your device and play with the values of EasyMoneyEditText and EasyMoneyTextView!

Changelog
=========
Changes exist in the [releases](https://github.com/wajahatkarim3/EasyMoney-Widgets/releases) tab.

Installation
============
Add this in your app's build.gradle file:
```groovy
dependencies {
  compile 'com.wajahatkarim3.EasyMoney-Widgets:EasyMoney-Widgets:1.0.0'
}
```

Or add EasyMoney-Widgets as a new dependency inside your pom.xml

```xml
<dependency>
  <groupId>com.wajahatkarim3.EasyMoney-Widgets</groupId>
  <artifactId>EasyMoney-Widgets</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
Usage
=====
XML
---
```EasyMoneyEditText``` in xml layouts (output will be ```$ 123,456,234```)
```xml
 <com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText
        android:id="@+id/moneyEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="123456234"
        android:inputType="numberDecimal"
        app:show_currency="true"
        app:show_commas="true"
        app:currency_symbol="$"
        />
```
```EasyMoneyTextView``` in xml layouts (output will be ```$ 123,456,234```)
```xml
 <com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView
        android:id="@+id/moneyTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="123456234"
        app:show_currency="true"
        app:show_commas="true"
        app:currency_symbol="$"
        />
```
Customization in XML
---
All customizable attributes for EasyMoneyWidgets
<table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:currency_symbol="$"</td>
        <td>US Dollar $</td>
        <td>The currency symbol for the widgets</td>
	</tr>
    <tr>
        <td>app:show_currency="true"</td>
        <td>true</td>
        <td>Whether to show the currency symbol in the widgets</td>
    </tr>
     <tr>
            <td>app:show_commas="true"</td>
            <td>true</td>
            <td>Whether to show the commas between numbers in the widgets</td>
        </tr>
    </table>
    
In Code (Java)
----
```java
// Set the value for money widgets
moneyEditText.setText("23456.01");                          // $ 23,456.01
moneyTextView.setText("1234");                              // $ 1,234

// Get the value from money widgets
String valForm = moneyView.getFormattedString();            // $ 23,456.01
String valRaw = moneyView.getValueString();                 // 23456.01

// Set the currency of the money widgets
moneyView.setCurrency("€");                                 // € 23,456.01
moneyView.setCurrency(Locale.UK);                           // £ 23,456.01
moneyView.setCurrency(Currency.getInstance(Locale.US));     // $ 23,456.01

// Show/hide currency of the money widgets
moneyView.showCurrencySymbol();                             // $ 23,456.01
moneyView.hideCurrencySymbol();                             // 23,456.01
boolean curr = moneyView.isShowCurrency();                  // true if currency is shown

// Show/hide commas of the money widgets
moneyView.showCommas();                                     // $ 23,456.01
moneyView.hideCommas();                                     // $ 23456.01
```

Donations
=============

This project needs you! If you would like to support this project's further development, the creator of this project or the continuous maintenance of this project, feel free to donate. Your donation is highly appreciated (and I love food, coffee and beer). Thank you!

**PayPal**

* **[Donate $5](https://www.paypal.me/WajahatKarim/5)**: Thank's for creating this project, here's a tea (or some juice) for you!
* **[Donate $10](https://www.paypal.me/WajahatKarim/10)**: Wow, I am stunned. Let me take you to the movies!
* **[Donate $15](https://www.paypal.me/WajahatKarim/15)**: I really appreciate your work, let's grab some lunch!
* **[Donate $25](https://www.paypal.me/WajahatKarim/25)**: That's some awesome stuff you did right there, dinner is on me!
* **[Donate $50](https://www.paypal.me/WajahatKarim/50)**: I really really want to support this project, great job!
* **[Donate $100](https://www.paypal.me/WajahatKarim/100)**: You are the man! This project saved me hours (if not days) of struggle and hard work, simply awesome!
* **[Donate $2799](https://www.paypal.me/WajahatKarim/2799)**: Go buddy, buy Macbook Pro for yourself!

Of course, you can also choose what you want to donate, all donations are awesome!

Developed By
============
```
Wajahat Karim
```
- Website (http://wajahatkarim.com)
- Twitter (http://twitter.com/wajahatkarim)
- Medium (http://www.medium.com/@wajahatkarim3)
- LinkedIn (http://www.linkedin.com/in/wajahatkarim)

# How to Contribute
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

# License

    Copyright 2016 Wajahat Karim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
