package com.marketplace;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import android.util.Log;

public class CurrencyExample
{
    public static void main(String[] args) 
    {
   
         Utils.getCurrencySymbol( Currency.getInstance(Locale.US).getCurrencyCode());
         Utils.getCurrencySymbol(Currency.getInstance(Locale.JAPAN).getCurrencyCode());
         Utils.getCurrencySymbol(Currency.getInstance(Locale.UK).getCurrencyCode());
         Utils.getCurrencySymbol("INR");
    }
}

class Utils{
      public static SortedMap<Currency, Locale> currencyLocaleMap;
      static {
          currencyLocaleMap = new TreeMap<Currency, Locale>(new Comparator<Currency>() {
            public int compare(Currency c1, Currency c2){
                return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
            }
        	/*  public int compare(Currency c1){
                  return c1.getCurrencyCode().compareTo(c1.getCurrencyCode());
              }

			@Override
			public int compare(Currency lhs, Currency rhs) {
				// TODO Auto-generated method stub
				return 0;
			}*/

        });
        for (Locale locale : Locale.getAvailableLocales()) {
             try {
                 Currency currency = Currency.getInstance(locale);
             currencyLocaleMap.put(currency, locale);
             }catch (Exception e){
         }
        }
    }

    public static String getCurrencySymbol(String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
       
        
   //     Log.i( "currency code",currencyCode+ ":-" + currency.getSymbol(currencyLocaleMap.get(currency)));
       
        return currency.getSymbol(currencyLocaleMap.get(currency));
    }
}