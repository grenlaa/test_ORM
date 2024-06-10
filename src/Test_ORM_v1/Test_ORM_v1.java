/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test_ORM_v1;

import ORM.Attr;
import ORM.Class_base;
import bd.Group_obj;
import ORM.Service;
import ORM.Service1;
import bd.Group_obj1;
import configuration.config;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fursov.ga
 */
public class Test_ORM_v1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        HashMap<String, Object> FIELD = new HashMap<>();
//        FIELD.put("id", "asd13d");
//        FIELD.put("position", "20.23pp,12.312ps");
//        FIELD.put("name_position", "rassvet");
//        FIELD.put("time_open", "20t20m20s");
//        FIELD.put("count", 5);

        Group_obj1 group_obj1 = new Group_obj1();
        group_obj1.id = 1001;
        String[] Conect_config = config.get_config("4");
        Service Service = new Service(Conect_config);
        Service1 Service1 = new Service1(Conect_config);

      
        List<Attr> list_arr = (List<Attr>) Class_base.class.getDeclaredMethod("toListAttr", Class.class).invoke(group_obj1, group_obj1.getClass());
        
        List<Group_obj1> group_obj4 = Service1.select(list_arr , Group_obj1.class, group_obj1.sql_all);
//        Service.save(group_obj1);
//        Group_obj group_obj2 = Service.findById(Group_obj.class, "10");
        List<Group_obj1> group_obj3 = Service.findAll(Group_obj1.class);
        for (int i = 0; i < group_obj3.size(); i++) {
            group_obj3.get(i).id += 1000;

        }

//        Service.employ(list_arr,group_obj1.getClass(), group_obj1.sql_select_t1);
//        Service.save(group_obj);
//        Service.save(group_obj3);
        System.out.println("");
    }

}
