package org.example.framework;


import jakarta.servlet.http.HttpServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DependencyInjector {

    private static final Logger logger = LoggerFactory.getLogger(DependencyInjector.class);

    private final Map<Class<?>, Object> instances = new HashMap<>();
    private final Map<Class<?>, String> controllerPaths = new HashMap<>();

    public void initialize(String basePackage) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = basePackage.replace('.', '/');
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Invalid base package: " + basePackage);
        }
        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + directory);
        }
        scanDirectory(directory, basePackage);

        // Inject dependencies
        for (Object instance : instances.values()) {
            injectDependencies(instance);
        }
    }

    private void scanDirectory(File directory, String packageName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(CustomControllerAnnotation.class) ||
                        clazz.isAnnotationPresent(CustomServiceAnnotation.class) ||
                        clazz.isAnnotationPresent(CustomRepositoryAnnotation.class)) {
                    Object instance = createInstance(clazz);
                    instances.put(clazz, instance);
                    if (clazz.isAnnotationPresent(CustomControllerAnnotation.class)) {
                        CustomControllerAnnotation annotation = clazz.getAnnotation(CustomControllerAnnotation.class);
                        controllerPaths.put(clazz, annotation.path());
                    }
                    logger.debug("Instantiated class: " + className);
                }
            }
        }
    }

    private Object createInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            return clazz.newInstance();
        }

        Constructor<?> constructor = constructors[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length == 0) {
            return clazz.newInstance();
        }

        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = instances.get(parameterTypes[i]);
        }

        try {
            return constructor.newInstance(parameters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of class: " + clazz.getName(), e);
        }
    }

    private void injectDependencies(Object instance) throws IllegalAccessException {
        for (Field field : instance.getClass().getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            Object dependency = instances.get(fieldType);
            if (dependency != null) {
                field.setAccessible(true);
                field.set(instance, dependency);
                logger.debug("Injected dependency: " + dependency.getClass().getName() + " into " + instance.getClass().getName());
            }
        }

        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (method.getName().startsWith("set")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Object dependency = instances.get(parameterTypes[0]);
                    if (dependency != null) {
                        try {
                            method.setAccessible(true);
                            method.invoke(instance, dependency);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        logger.debug("Injected dependency via setter: " + dependency.getClass().getName() + " into " + instance.getClass().getName());
                    }
                }
            }
        }
    }

    public <T> T getInstance(Class<T> clazz) {
        T instance = (T) instances.get(clazz);
        if (instance == null) {
            logger.error("Instance not found for class: " + clazz.getName());
        }
        return instance;
    }

    public ServletHolder getServletHolder(Class<? extends HttpServlet> clazz) {
        HttpServlet servletInstance = getInstance(clazz);
        if (servletInstance == null) {
            throw new IllegalStateException("Servlet instance not found for class: " + clazz.getName());
        }
        return new ServletHolder(servletInstance);
    }

    public Map<Class<?>, String> getControllerPaths() {
        return controllerPaths;
    }
}