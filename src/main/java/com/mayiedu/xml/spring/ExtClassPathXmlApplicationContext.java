package com.mayiedu.xml.spring;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class ExtClassPathXmlApplicationContext {

    private String xmlPath;

    public ExtClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("branid不能为空");
        }
        //1，解析xml文件
        List<Element> readerXML = ReaderXML();
        if (readerXML == null||readerXML.isEmpty()) {
            throw new Exception("配置文件中没有配置bran信息");
        }
        //2，使用方法参数beanid查找配置文件中bean节点的id信息是否一致
        String className = findByElementClass(readerXML, beanId);
        if (StringUtils.isEmpty(className)) {
            throw new Exception("该bean对象没有配置class地址");
        }
        //3，使用方法反射机制初始化
        return newInstance(className);
    }

    public String findByElementClass(List<Element> readerXML, String beanId) {
        for (Element element : readerXML) {
            //获取属性信息
            String xmlBeanId = element.attributeValue("id");
            if (StringUtils.isEmpty(xmlBeanId)) {
                continue;
            }
            //
            if (xmlBeanId.equals(beanId)) {
                String xmlClass = element.attributeValue("class");
                return xmlClass;
            }
        }
        return null;
    }
    /**
     * 解析XML
     */
    public List<Element> ReaderXML() throws DocumentException {

        //1,解析xml文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAsStream(xmlPath));
        //2,读取根节点
        Element rootElement = document.getRootElement();
        //3,获取根节点的所有子节点
        List<Element> elements = rootElement.elements();
        return elements;
        //4,
    }

    public Object newInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> classInfo = Class.forName(className);
        return classInfo.newInstance();

    }
    //获取当前上下文路径
    public InputStream getResourceAsStream(String xmlPath) {
        return this.getClass().getClassLoader().getResourceAsStream(xmlPath);
    }
}
