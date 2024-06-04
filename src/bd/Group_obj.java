package bd;

import ORM.Class_base;
import Annontation.Column;
import Annontation.Table;
import java.util.HashMap;
import java.util.Map;

@Table(name = "J24_FORMAT")
public class Group_obj extends Class_base {

//    public Group_obj(java.util.Map<String, Object> asd) {
//        super(new HashMap<String,Object>());
//    }
//    public Group_obj() {
//    }
    public Group_obj(HashMap<String, Object> map) throws Exception {
        super(map);
    }

//    public Group_obj(String map) {
//        super(map);
//    }
    //annotation для БД
    @Column(name = "FT_KEY", type = "string", def_value = "")
    String id;

    @Column(name = "position", type = "string", def_value = "")
    String position;

    @Column(name = "name_position", type = "string", def_value = "ttt")
    String name_position;

    @Column(name = "time_open", type = "string", def_value = "")
    String time_open;

    @Column(name = "count", type = "int", def_value = "")
    Integer count;
}
