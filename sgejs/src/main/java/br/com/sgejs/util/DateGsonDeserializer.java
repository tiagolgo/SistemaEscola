/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

/**
 *
 * @author Tiagolgo
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class DateGsonDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        System.out.println("****** DESERIALIZAR *******");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(je.getAsString());
        } catch (ParseException ex) {
            Logger.getLogger(DateGsonDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
