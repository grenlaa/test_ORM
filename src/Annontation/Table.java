/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Annontation;

//Указывает, что наша Аннотация может быть использована
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Во время выполнения через Reflection (нам как раз это нужно).
@Retention(RetentionPolicy.RUNTIME)

//Указывает, что целью нашей Аннотации является метод
//Не класс, не переменная, не поле, а именно метод.
@Target(ElementType.TYPE)
public @interface Table {

    String name();
}
