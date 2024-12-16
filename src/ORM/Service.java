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
public class Service {

    private BD_mng_old_v1 bd_mng;
    private Pattern pattern = Pattern.compile(":[^\\s]+\\s");

    public Service(String[] config) throws Exception {
        this.bd_mng = new BD_mng_old_v1(config);
    }

    public <T> List<T> employ(List<?> objs, String str) {

        return null;
    }

//    public <T> List<T> employ(List<Attr> add_param , Object obj, String sql) throws Exception {
    public <T> List<T> employ(List<Attr> add_param, Class<T> clazz, String sql) throws Exception {

//        List<Attr> list_attr = (List<Attr>) Class_base.class.getDeclaredMethod("toListAttr", Class.class).invoke(obj, obj.getClass());
        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        sql = sql.replace(":table_name", table_name);

        HashMap<String, Attr> map_attr = new HashMap<String, Attr>();

//        for (Attr Attr_ : list_attr) {
//            map_attr.put(":" + Attr_.name, Attr_);
//        }
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

        return null;
    }

//    public <T> void save(List<T> objs) throws Exception {
    public void save(List<?> objs) throws Exception {
        if (objs.get(0) != null) {

            Object obj = objs.get(0);

//        Field field = obj.getClass().getDeclaredField("id");
//        String id_name = field.getAnnotation(Column.class).name();
            String table_name = obj.getClass().getDeclaredAnnotation(Table.class).name();

            ArrayList<List<Attr>> list_row = new ArrayList();

            Method m = Class_base.class.getDeclaredMethod("toListAttr", Class.class);

            for (int i = 0; i < objs.size(); i++) {
                list_row.add((List<Attr>) m.invoke(objs.get(i), objs.get(i).getClass()));
            }
            bd_mng.save_many(list_row, table_name);
        }

    }

    public void save(Object obj) throws Exception {
//        Field field = obj.getClass().getDeclaredField("id");
//        String id_name = field.getAnnotation(Column.class).name();
        String table_name = obj.getClass().getDeclaredAnnotation(Table.class).name();

        List<Attr> list_arr = (List<Attr>) Class_base.class.getDeclaredMethod("toListAttr", Class.class).invoke(obj, obj.getClass());

//        List<attr> map = (List<attr>) m2.invoke(dsa, dsa.getClass());
//        for (Method m : Class_base.class.getDeclaredMethods()) {
//            Type[] sadxc = m.getGenericParameterTypes();
//            m.invoke(dsa, dsa.getClass());
//            System.out.println(sadxc);
//        }
        bd_mng.save_one(list_arr, table_name);
//        System.out.println(id_name + "|" + table_name);
    }

    public void update() {

    }

    public void delete() {
    }

    public <T> List<T> findAll(Class<T> clazz) throws Exception {

        Field field = clazz.getDeclaredField("id");
        String id_name = field.getAnnotation(Column.class).name();

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        List<T> all_ret = new ArrayList<>();

        Constructor<?> foo = clazz.getConstructor(HashMap.class);

        for (ListIterator<HashMap<String, Object>> iter = bd_mng.find_all(table_name, id_name, 0, 0).listIterator(); iter.hasNext();) {

            all_ret.add((T) foo.newInstance(iter.next()));

        }

        return all_ret;
    }

    public <T> List<T> findAll(Class<T> clazz, int start, int count) throws Exception {

        Field field = clazz.getDeclaredField("id");
        String id_name = field.getAnnotation(Column.class).name();

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        List<T> all_ret = new ArrayList<>();
        Constructor<?> foo = clazz.getConstructor(HashMap.class);

        for (ListIterator<HashMap<String, Object>> iter = bd_mng.find_all(table_name, id_name, start, count).listIterator(); iter.hasNext();) {

            all_ret.add((T) foo.newInstance(iter.next()));

        }

        return all_ret;
    }

    public <T> T findById(Class<T> clazz, String id) throws Exception {

        Field field = clazz.getDeclaredField("id");

        String id_name = field.getAnnotation(Column.class).name();

        String table_name = clazz.getDeclaredAnnotation(Table.class).name();

        Constructor<?> foo = clazz.getConstructor(HashMap.class);

        Object ret = foo.newInstance(bd_mng.find_by_id(table_name, id_name, id));

        return (T) ret;
    }
}
