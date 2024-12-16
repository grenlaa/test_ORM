/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM.SQL_query;

import Annontation.Table;
import ORM.Attr;
import ORM.BD_mng;
import ORM.Class_base;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fursov.ga
 */
public class query_select {

    private static Pattern pattern = Pattern.compile(":[^\\s]+\\s");

    public static <T> List<T> select(Class<T> clazz, BD_mng bd_mng) throws Exception {
        String sql = "Select * from " + clazz.getDeclaredAnnotation(Table.class).name();
        return select_list(clazz, null, sql, bd_mng);
    }

    // патерн
    // query_select ^ from ^tablename where ^attr
    // пример
    // query_select * from tablename where key=:key and uuid=:uuid
    // переделать на
    // object = query_select * from tablename where key=:object.key
    // query_select(object)
    // object.getClass
    // object.getsql
    public static <T> List<T> select(Class<T> clazz, List<Attr> add_param, String sql, BD_mng bd_mng) throws Exception {

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        if (sql != null) {
            sql = sql.replace(":table_name", table_name);
        }

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

        return select_list(clazz, list_param, sql, bd_mng);

    }

    public static <T> List<T> select(Class<T> clazz, String sql, BD_mng bd_mng) throws Exception {
        return select_list(clazz, null, sql, bd_mng);
    }

//    public static <T> T select(Class_base clazz, BD_mng bd_mng) throws Exception {
//
//        String sql = "Select * from " + clazz.getClass().getDeclaredAnnotation(Table.class).name();
//
//        ListIterator<HashMap<String, Object>> iter = bd_mng.select(null, sql).listIterator();
//        if (iter.hasNext()) {
//            Constructor<?> clazzT = clazz.getClass().getConstructor(HashMap.class);
//            return (T) clazzT.newInstance(iter.next());
//        }
//
//        return null;
//    }
//
//    public static <T> T select(Class_base clazz, String sql, BD_mng bd_mng) throws Exception {
//
//        String table_name = clazz.getClass().getDeclaredAnnotation(Table.class).name();
//        List<Attr> add_param = clazz.toListAttr();
//
//        if (sql != null) {
//            sql = sql.replace(":table_name", table_name);
//        }
//
//        HashMap<String, Attr> map_attr = new HashMap<String, Attr>();
//
//        for (Attr Attr_ : add_param) {
//            map_attr.put(":" + Attr_.name, Attr_);
//        }
//
//        Matcher matcher = pattern.matcher(sql);
//        ArrayList<Attr> list_param = new ArrayList<>();
//
//        while (matcher.find()) {
//            String param = sql.substring(matcher.start(), matcher.end() - 1);
//            sql = sql.replace(param, "?");
//            list_param.add(map_attr.get(param));
//            matcher = pattern.matcher(sql);
//        }
//
//        ListIterator<HashMap<String, Object>> iter = bd_mng.select(list_param, sql).listIterator();
//
//        if (iter.hasNext()) {
//            Constructor<?> clazzT = clazz.getClass().getConstructor(HashMap.class);
//            return (T) clazzT.newInstance(iter.next());
//        }
//
//        return null;
//    }

    public static <T> List<T> select_list(Class<T> clazz, ArrayList<Attr> list_attr, String sql, BD_mng bd_mng) throws Exception {
        Constructor<?> clazzT = clazz.getConstructor(HashMap.class);

        List<T> all_ret = new ArrayList<>();

        if (list_attr == null) {
            for (ListIterator<HashMap<String, Object>> iter = bd_mng.select(null, sql).listIterator(); iter.hasNext();) {
                all_ret.add((T) clazzT.newInstance(iter.next()));
            }
        } else {
            for (ListIterator<HashMap<String, Object>> iter = bd_mng.select(list_attr, sql).listIterator(); iter.hasNext();) {
                all_ret.add((T) clazzT.newInstance(iter.next()));
            }
        }
        return all_ret;
    }

}
