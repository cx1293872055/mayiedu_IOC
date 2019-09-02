package com.mayiedu.spring.ext;


import com.mayiedu.spring.exttannotation.ExtService;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExtClassPathXmlApplicationContext {

    private String packageName;

    private ConcurrentHashMap<String,Object> beans = null;

    public ExtClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        beans = new ConcurrentHashMap<>();
        initBean(packageName);
    }

    //初始化对象
    public void initBean(String packageName) throws Exception {
        //1,使用java的反射机制扫描包，获取当前包下所有的类
        //List<Class<?>> classes = ClassUtils.getClasses(packageName);

        //2,判断类上是否存在注入bean的注解
        // ConcurrentHashMap<String,Class<?>> classExisAnnotation = finClassExisAnnotation(classes);
//        if (classExisAnnotation == null || classExisAnnotation.isEmpty()) {
//            throw new  Exception("该包下没有任何类加上注解");
//        }
        //3,使用java的反射机制进行初始化
    }
//依赖注入原理
    public void attriAssign(Class<?> classInfo) {
        Field[] fields = classInfo.getDeclaredFields();
        for (Field field : fields) {
        }
    }
    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId参数为空");
        }
        Object classInfo = beans.get(beanId);
        if (classInfo == null) {
            throw new Exception("class not find");
        }
        return newInstance(beanId);
    }

    public Object newInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> classInfo = Class.forName(className);
        return classInfo.newInstance();

    }
    public ConcurrentHashMap<String,Object> finClassExisAnnotation(List<Class<?>> classes) {
        for (Class<?> classInfo : classes) {
            ExtService annotation = classInfo.getAnnotation(ExtService.class);
            if (annotation != null) {
                String className = classInfo.getSimpleName();
                //String beanid = toLow
                beans.put(className, classInfo);
            }
        }
        return beans;
    }
}