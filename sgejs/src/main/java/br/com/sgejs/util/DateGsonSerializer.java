/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

/**
 *
 * @author Tiago
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class DateGsonSerializer implements JsonSerializer<Date> {

    @Override
    public JsonElement serialize(Date t, Type type, JsonSerializationContext jsc) {
        return jsc.serialize((new SimpleDateFormat("dd/MM/yyyy")).format(t));
    }
}
