package com.app.injector;

import com.app.exseption.InjectorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class LabInjector {

    public <T> T inject(T object) throws InjectorException {

        try {
            Field[] arrayFields = object.getClass().getDeclaredFields();
            for (int i = 0; i < arrayFields.length; i++) {

                Field field = arrayFields[i];
                field.setAccessible(true);
                Class<?> typeField = field.getType();
                FileInputStream fis;
                Properties properties = new Properties();
                String property;
                try {
                    fis = new FileInputStream("src/main/resources/config.properties");
                    properties.load(fis);
                    fis.close();
                } catch (IOException e) {
                    throw new InjectorException(e);
                }
                if (field.isAnnotationPresent(ILabInjector.class)) {
                    property = properties.getProperty(typeField.getName());
                    field.set(object, Class.forName(property).newInstance());
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
           throw new InjectorException(e);
        }

        return object;
    }
}
