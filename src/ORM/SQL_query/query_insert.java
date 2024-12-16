/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM.SQL_query;

import Annontation.Table;
import ORM.Attr;
import ORM.BD_mng;
import ORM.Class_base;
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
public class query_insert {

    private static Pattern pattern = Pattern.compile(":[^\\s]+\\s");

    public static void insert(List<?> clazz, BD_mng bd_mng) throws Exception {
        String sql = "";

        for (int i = 0; i < clazz.size(); i++) {
            Class_base clazz_b = (Class_base) clazz.get(i);
            bd_mng.insert(create_insert(clazz_b.toListAttr(), clazz.get(i).getClass().getDeclaredAnnotation(Table.class).name()), clazz_b.toListAttr());
//            System.out.print();

        }
    }

    public static void insert(List<Class_base> clazz, String sql, BD_mng bd_mng) throws Exception {

    }

    public static void insert(List<Attr> add_param, Class<?> clazz, String sql) throws Exception {

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

    }

    private static String create_insert(ArrayList<Attr> list_attr, String tbl_name) {
        String sql = "";
        String param_sql = "";
        for (int i = 0; i < list_attr.size(); i++) {
            if (sql.length() > 0) {
                sql += ",";
                param_sql += ",";
            }
            sql += "?";
            param_sql += list_attr.get(i).name;
        }
        return "INSERT INTO " + tbl_name + " (" + param_sql + ") VALUES (" + sql + ")";
    }

}
