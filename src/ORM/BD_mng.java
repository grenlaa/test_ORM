/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/*
что надо переделать



что сделать

 */
public class BD_mng {

    private Connection connection;
    private String[] config;

    public BD_mng(String[] config) throws Exception {
        this.config = config;
        connect();
    }

    public void connect() throws Exception {
        if (config[0].equals("oracle")) {
            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();
        }
        connection = DriverManager.getConnection(config[1], config[2], config[3]);
    }

    public void save_many(ArrayList<List<Attr>> list_row, String table) throws Exception {
        List<Attr> list_attr = list_row.get(0);

        String sql = "INSERT INTO " + table + "(";

        String sql_q = "VALUES (";

        for (int i = 0; i < list_attr.size() - 1; i++) {
            sql += list_attr.get(i).name + ",";
            sql_q += "?,";
        }
        sql += list_attr.get(list_attr.size() - 1).name + ") " + sql_q + "?)";

        for (int i = 0; i < list_row.size() - 1; i++) {
            save(list_row.get(i), sql);
        }
    }

    public void save_one(List<Attr> list_attr, String table) throws Exception {
        String sql = "INSERT INTO " + table + " (";
        String sql_q = "VALUES (";

        for (int i = 0; i < list_attr.size() - 1; i++) {
            sql += list_attr.get(i).name + ",";
            sql_q += "?,";
        }
        sql += list_attr.get(list_attr.size() - 1).name + ") " + sql_q + "?)";

        save(list_attr, sql);
    }

    private void save(List<Attr> list_attr, String sql) throws Exception {

        // 2 создание sql запроса на добавление используя - metadata
        PreparedStatement pst = connection.prepareStatement(sql);
        // 2

        for (int i = 0; i < list_attr.size(); i++) {

            if (list_attr.get(i) != null) {
                Attr attr_ = list_attr.get(i);
                if (attr_.type == "string") {
                    if (attr_.val != null) {
                        pst.setString(i + 1, attr_.val.toString());
                    } else {
                        pst.setNull(i + 1, java.sql.Types.VARCHAR);
                    }
                }
                if (attr_.type == "int") {
                    if (attr_.val != null) {
                        pst.setInt(i + 1, Integer.parseInt(attr_.val.toString()));
                    } else {
                        pst.setNull(i + 1, java.sql.Types.INTEGER);
                    }
                }
                if (attr_.type == "bigint") {

                }
                if (attr_.type == "float") {
                    if (attr_.val != null) {
                        pst.setFloat(i + 1, Float.parseFloat(attr_.val.toString().replace(",", ".")));
                    } else {
                        pst.setNull(i + 1, java.sql.Types.FLOAT);
                    }
                }
                if (attr_.type == "date") {
                    if (attr_.val != null) {
                        try {
                            java.sql.Timestamp P_date;
                            if (attr_.format == "") {
                                P_date = new java.sql.Timestamp(((Date) attr_.val).getTime());
                            } else {
                                SimpleDateFormat formatForDateNow = new SimpleDateFormat(attr_.format);
                                P_date = new java.sql.Timestamp(formatForDateNow.parse(attr_.val.toString().toString()).getTime());
                            }
                            pst.setTimestamp(i + 1, P_date);
                        } catch (Exception ex) {
                            pst.setNull(i + 1, java.sql.Types.TIMESTAMP);
                        }
//                        java.util.Date date = new SimpleDateFormat(date_format).parse(sql_param.get(mtd.get(i).name).toString()).getTime();
//                        pst.setDate(i + 1, new java.sql.Date(new SimpleDateFormat(date_format).parse().getTime()));
                    } else {
                        pst.setNull(i + 1, java.sql.Types.TIMESTAMP);
                    }
                }

                if (attr_.type == "bytes") {
                    if (attr_.val != null) {
                        pst.setBytes(i + 1, (byte[]) attr_.val);
                    } else {
                        pst.setNull(i + 1, java.sql.Types.BLOB);
                    }
                }

                if (attr_.type == "boolean") {
                    if (attr_.val != null) {
                        pst.setBoolean(i + 1, (Boolean) attr_.val);
                    } else {
                        pst.setNull(i + 1, java.sql.Types.BOOLEAN);
                    }
                }
            }

        }

        pst.executeUpdate();
        pst.close();
    }

    public HashMap<String, Object> find_by_id(String table_name, String id_name, String id) throws Exception {

        String sql = "Select * from " + table_name + " where " + id_name + "=" + id;
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);
        ResultSetMetaData md = result.getMetaData();

        HashMap<String, Object> mtdas = new HashMap<String, Object>();

        while (result.next()) {
            for (int i = 0; i < md.getColumnCount(); i++) {
                mtdas.put(md.getColumnName(i + 1), result.getObject(i + 1));
            }
        }
        return mtdas;

    }

    public List<HashMap<String, Object>> find_all(String table_name, String id_name, int start, int count) throws Exception {

        String sql = "Select * from " + table_name;

        if (start != 0 && count != 0) {
            sql += " where " + id_name + ">" + start + " and " + id_name + "<" + (start + count);
        }
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);
        ResultSetMetaData md = result.getMetaData();

        HashMap<String, Object> mtdas;
        List<HashMap<String, Object>> mtdas2 = new ArrayList<>();

        while (result.next()) {
            mtdas = new HashMap<String, Object>();
            for (int i = 0; i < md.getColumnCount(); i++) {
                mtdas.put(md.getColumnName(i + 1), result.getObject(i + 1));
            }
            mtdas2.add(mtdas);
        }
        return mtdas2;

    }

    /* Укажите имя таблицы, атрибуты 
    <
    1. делаем пустой запрос к таблице
    2. сравниваем со списком атрибутов
    3. создаем запрос с учетом совпадений
    4. ? отправляем ответ какие параметры загружены, какие пропущены
     
     */
    public void save_with_field_verf(List<Attr> list_attr, String table_name) {
    }

    public void insert() {
    }

    public void update() {
    }

    public void delete() {
    }

    public void create() {
    }

    public void alter() {
    }

    public void drop() {
    }

}
