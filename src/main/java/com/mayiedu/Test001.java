package com.mayiedu;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Test001 {

    public static void main(String[] args) throws DocumentException {
        new Test001().tesy001();
    }
    public void add() {
        System.out.println("获取到了对象");
    }
    public void tesy001() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAsStream("student.xml"));
        Element rootElement = document.getRootElement();
        getNotes(rootElement);
    }

    public void getNotes(Element rootElement) {
        System.out.println("获取节点名称" + rootElement.getName());
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName() + "----" + attribute.getText());
        }
        String textTrim = rootElement.getTextTrim();
        if (StringUtils.isEmpty(textTrim)) {
            System.out.println("textTrim=" + textTrim);
        }
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNotes(next);
        }
    }
    public InputStream getResourceAsStream(String xmlPath) {
       return this.getClass().getClassLoader().getResourceAsStream(xmlPath);
    }
}
