/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM;

import Annontation.Column;
import Annontation.Command;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author fursov.ga
 */
public abstract class Class_base {

    public Class_base() throws Exception {
    }

    public Class_base(HashMap<String, Object> map) throws Exception {
        for (Field field : this.getClass().getDeclaredFields()) {
            //Смотрим, есть ли у параметра нужная нам Аннотация @Command
            if (field.isAnnotationPresent(Column.class)) {
                //Берем объект нашей Аннотации
                Column cmd = field.getAnnotation(Column.class);

                field.setAccessible(true);

                //Если элемент пустой, заполняем дефолтным значением
                if (map.get(cmd.name()) == null) {
                    if (cmd.def_value().equals("")) {
                        map.put(cmd.name(), null);
                    } else {
                        map.put(cmd.name(), cmd.def_value());
                    }
                }

                System.out.println(cmd.name() + "|" + map.get(cmd.name()) + "|" + cmd.type());
                //cmd.type() заменить на field.getType()
                if (map.get(cmd.name()) != null) {
                    if (cmd.type() == "") {
                        field.set(this, (Object) map.get(cmd.name()));
                    }
                    if (cmd.type().equals("string")) {

                        field.set(this, String.valueOf(map.get(cmd.name())));
                    }
                    if (cmd.type().equals("int")) {
                        field.set(this, Integer.parseInt(map.get(cmd.name()).toString()));

                    }
                    if (cmd.type().equals("float")) {
                        field.set(this, Float.parseFloat(map.get(cmd.name()).toString().replace(",", ".")));
                    }
                    if (cmd.type().equals("date")) {
                        SimpleDateFormat formatForDateNow = new SimpleDateFormat(cmd.format());
                        field.set(this, formatForDateNow.parse(map.get(cmd.name()).toString()).getTime());
                    }
                    if (cmd.type().equals("byte")) {
                        field.set(this, (byte[]) map.get(cmd.name()));
                    }
                } else {
                    field.set(this, null);
                }
            }
        }
    }

    /**
     * This method fromulgates the wibble-wrangler. It should not be called
     * without first saturating all glashnashers.
     */
    public List<Attr> toListAttr(Class<?> clazz) throws Exception {
        List<Attr> map = new ArrayList();
        for (Field field : this.getClass().getDeclaredFields()) {
            //Смотрим, есть ли у параметра нужная нам Аннотация @Command
            if (field.isAnnotationPresent(Column.class)) {
                Column cmd = field.getAnnotation(Column.class);
                field.setAccessible(true);
                map.add(new Attr(cmd.name(), cmd.type(), cmd.format(), field.get(this)));
//                System.out.println(cmd.name() + "|" + field.get(this));
            }
        }
        return map;
    }

//    @Command(name = "", args = "")
//    public List<HashMap<String, Object>> toList(Class<?> clazz) throws Exception {
//        ArrayList<HashMap<String, Object>> list = new ArrayList();
//        Method m = Class_base.class.getDeclaredMethod("toHashMap", Class.class);
//
//        for (ListIterator<Object> iter = (ListIterator<Object>) this; iter.hasNext();) {
//            this.toList(clazz);
//        }
//
//        return null;
//    }
}
