/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM;

import Annontation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fursov.ga
 */
public class Service1 {

    private BD_mng bd_mng;
    private Pattern pattern = Pattern.compile(":[^\\s]+\\s");

    public Service1(String[] config) throws Exception {
        this.bd_mng = new BD_mng(config);
    }

    public <T> List<T> select(List<Attr> add_param, Class<T> clazz, String sql) throws Exception {

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        sql = sql.replace(":table_name", table_name);

        HashMap<String, Attr> map_attr = new HashMap<String, Attr>();

        for (Attr Attr_ : add_param) {
            map_attr.put(":" + Attr_.name, Attr_);
        }

        Matcher matcher = pattern.matcher(sql);
        ArrayList<Attr> list_param = new ArrayList<>();

        while (matcher.find()) {
            String param = sql.substring(matcher.start(), matcher.end() - 1);
            sql = sql.replace(param, "?");
            list_param.add(map_attr.get(param));
            matcher = pattern.matcher(sql);
        }

        Constructor<?> foo = clazz.getConstructor(HashMap.class);
        List<T> all_ret = new ArrayList<>();

        for (ListIterator<HashMap<String, Object>> iter = bd_mng.select(list_param, sql).listIterator(); iter.hasNext();) {

            all_ret.add((T) foo.newInstance(iter.next()));

        }
        return all_ret;
    }

    
    
    public void insert(List<Attr> add_param, Class<?> clazz, String sql) throws Exception {

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        sql = sql.replace(":table_name", table_name);

        HashMap<String, Attr> map_attr = new HashMap<String, Attr>();

        for (Attr Attr_ : add_param) {
            map_attr.put(":" + Attr_.name, Attr_);
        }

        Matcher matcher = pattern.matcher(sql);
        ArrayList<Attr> list_param = new ArrayList<>();

        while (matcher.find()) {
            String param = sql.substring(matcher.start(), matcher.end() - 1);
            sql = sql.replace(param, "?");
            list_param.add(map_attr.get(param));
            matcher = pattern.matcher(sql);
        }

        bd_mng.select(list_param, sql);

    }

}
