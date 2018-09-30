package org.sczs.auction.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {

        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sm.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
