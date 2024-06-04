/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ORM;

/**
 *
 * @author fursov.ga
 */
public class Attr {

    public Attr(String name, String type, String format, Object val) {
        this.name = name;
        this.type = type;
        this.val = val;
        this.format = format;
    }

    public String name;
    public String type;
    public String format;
    public Object val;
}
