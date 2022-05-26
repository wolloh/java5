import Annotation.AutoInjectable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector
{
    Properties properties;
    public Injector(String path){
        FileInputStream fileInputStream;
        properties = new Properties();
        try {
            fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + path+ " не обнаружено");
            e.printStackTrace();
        }
    };

    public <T> T inject(T obj)  {
        try {
            for (Field field : obj.getClass().getDeclaredFields())
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    field.setAccessible(true);
                    Class clazz = field.getType();
                    String field1 = properties.getProperty(clazz.getName());
                    Class claz = Class.forName(field1);
                    field.set(obj, claz.newInstance());
                }
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());
        } catch (InstantiationException e) {
            System.out.println(e.toString());
        }
        return obj;
    }
}
