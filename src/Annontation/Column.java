/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Annontation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.FIELD)
public @interface Column {

    /**
     * Имя в БД.
     *
     */
    String name();

    /**
     * Тип данных в БД.
     * <br>string
     * <br>int
     * <br>bigint
     * <br>float
     * <br>double
     * <br>date
     * <br>bytes
     * <br>boolean
     *
     */
    String type();

    String format() default "";

    String def_value();

    String linked_class() default "";

    String linked_attr() default "";
}
