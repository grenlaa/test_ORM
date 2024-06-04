/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test_ORM_v1;

import bd.Group_obj;
import ORM.Service;
import configuration.config;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        HashMap<String, Object> FIELD = new HashMap<>();
        FIELD.put("id", "asd13d");
        FIELD.put("position", "20.23pp,12.312ps");
//        FIELD.put("name_position", "rassvet");
        FIELD.put("time_open", "20t20m20s");
        FIELD.put("count", 5);

        Group_obj group_obj = new Group_obj(FIELD);
//        System.out.println("");
        String[] Conect_config = config.get_config("4");
        Service Service = new Service(Conect_config);
//        Group_obj group_obj2 = Service.findById(Group_obj.class, "10");
        List<Group_obj> group_obj3 = Service.findAll(Group_obj.class);

        Service.save(group_obj);
        Service.save(group_obj3);
        System.out.println("");
    }

}
