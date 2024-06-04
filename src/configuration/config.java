/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fursov.ga
 */
public class config {
    
    private static String database;
    private static String dbhost;
    private static String dbname;
    private static String dbpass;
    private static double uploadpath;
    
    public static String[] get_config(String n) {
        try {
            Properties props = new Properties();
            System.out.println("configuration/config" + n + ".ini");
            props.load(new FileInputStream(new File("configuration/config" + n + ".ini")));
            
            database = props.getProperty("database");
            dbhost = props.getProperty("dbhost");
            dbname = props.getProperty("dbname");
            dbpass = props.getProperty("dbpass");
            
            String[] Conect_config = new String[]{database, dbhost, dbname, dbpass};
            return Conect_config;
        } catch (Exception ex) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public config() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("configuration/config.ini")));
        
        database = props.getProperty("database");
        dbhost = props.getProperty("dbhost");
        dbname = props.getProperty("dbname");
        dbpass = props.getProperty("dbpassword");

//        SOME_INT_VALUE = Integer.valueOf(props.getProperty("SOME_INT_VALUE", "1"));
//        SOME_STRING_VALUE = props.getProperty("SOME_STRING_VALUE");
//        SOME_DOUBLE_VALUE = Double.valueOf(props.getProperty("SOME_DOUBLE_VALUE", "1.0"));
        // Предположим, что в настройках находится список целых через точку с запятой
//        String[] parts = props.getProperty("SOME_INT_ARRAY").split(";");
//        SOME_INT_ARRAY = new int[parts.length];
//        for (int i = 0; i < parts.length; ++i) {
//            SOME_INT_ARRAY[i] = Integer.valueOf(parts[i]);
//        }
    }
    
}
