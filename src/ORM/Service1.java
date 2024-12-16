/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM;

import Annontation.*;
import ORM.SQL_query.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Service1 implements Service_API {

    private BD_mng bd_mng;
    private Pattern pattern = Pattern.compile(":[^\\s]+\\s");

    public Service1(String[] config) throws Exception {
        this.bd_mng = new BD_mng(config);
    }

    public <T> List<T> select(Class<T> clazz) throws Exception {
        return (List<T>) query_select.select(clazz, bd_mng);
    }

    public <T> List<T> select(Class<T> clazz, String sql) throws Exception {
        return (List<T>) query_select.select(clazz, sql, bd_mng);
    }

    public <T> List<T> select(Class<T> clazz, List<Attr> add_param, String sql) throws Exception {
        return (List<T>) query_select.select(clazz, add_param, sql, bd_mng);
    }

    public <T> List<T> select(Class_base clazz) throws Exception {
        return (List<T>) query_select.select(clazz.getClass(), bd_mng);
    }

    public <T> List<T> select(Class_base clazz, String sql) throws Exception {
        return (List<T>) query_select.select(clazz.getClass(), clazz.toListAttr(), sql, bd_mng);
    }

    public <T> List<T> select(Class_base clazz, List<Attr> add_param, String sql) throws Exception {
        add_param.addAll(clazz.toListAttr());
        return (List<T>) query_select.select(clazz.getClass(), add_param, sql, bd_mng);
    }

    public void insert(Class_base clazz) throws Exception {
        query_insert.insert(new ArrayList<Class_base>(Arrays.asList(clazz)), bd_mng);
    }
//
//    public void insert(Class_base clazz, String sql) throws Exception {
//    }
//
//    public void insert(Class_base clazz, List<Attr> add_param, String sql) throws Exception {
//    }

    public void insert(List<?> clazz) throws Exception {
        query_insert.insert(clazz, bd_mng);
    }

    public void insert(List<Class_base> clazz, String sql) throws Exception {
    }

    public void insert(List<Class_base> clazz, List<Attr> add_param, String sql) throws Exception {
    }

}
