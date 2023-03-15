package com.minis.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 解析 xml 文件，获取 BeanDefinition
 *
 * @author luf
 * @date 2023/03/15 09:05
 **/
public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;

    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        //dom4j 这个外部 jar 包方便我们读取并解析 XML 文件内容，将 XML 的标签以及参数转换成 Java 的对象
        //创建一个SAXReader对象，用于读取xml文件，生成Document对象，Document对象代表整个xml文档，用于获取xml文档中的信息，如根节点，子节点等
        //SAXReader对象是非线程安全的，所以每次使用时都要创建一个新的对象，不能放在成员变量中，否则会出现线程安全问题，也不能放在方法中，否则每次调用都要创建一个新的对象，会影响性能
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        //将配置文件装载进来，生成一个迭代器，用于遍历配置文件中的每一个元素
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement = document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
