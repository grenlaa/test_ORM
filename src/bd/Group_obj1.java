package bd;

import ORM.Class_base;
import Annontation.Column;
import Annontation.SQL;
import Annontation.Table;
import java.util.HashMap;
import java.util.Map;

@Table(name = "J24_FORMAT")
public class Group_obj1 extends Class_base {

//    public Group_obj(java.util.Map<String, Object> asd) {
//        super(new HashMap<String,Object>());
//    }
    public Group_obj1() throws Exception {

    }

    public Group_obj1(HashMap<String, Object> map) throws Exception {
        super(map);
    }

    
     @SQL(type = "select")
    public static String sql_all = "Select * From :table_name ";

    @SQL(type = "select")
    public static String sql_select_t1 = "Select FT_KEY, FT_ANC_KEY From :table_name where :FT_KEY < FT_KEY ";

    @SQL()
    public static String sql_select_all;

//    public Group_obj(String map) {
//        super(map);
//    }
    //annotation для БД
    @Column(name = "FT_KEY", type = "int", def_value = "")
    public Integer id;

    @Column(name = "FT_ANC_KEY", type = "int", def_value = "")
    public Integer FT_ANC_KEY;

    @Column(name = "FT_FILE_PAT", type = "string", def_value = "ttt")
    public String FT_FILE_PAT;

    @Column(name = "FT_NAME_REGISTR", type = "string", def_value = "")
    public String FT_NAME_REGISTR;

    @Column(name = "FT_PAT", type = "string", def_value = "")
    public String FT_PAT;

    @Column(name = "FT_FILL", type = "string", def_value = "")
    public String FT_FILL;
}
