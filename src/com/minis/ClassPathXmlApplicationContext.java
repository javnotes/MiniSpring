package com.minis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析某个路径下的 XML 来构建应用上下文
 *
 * @author luf
 * @date 2023/03/14 08:58
 **/
public class ClassPathXmlApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();

    //构造器获取外部配置，解析出Bean的定义，形成内存映像
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    //通过传入的文件路径，也就是 XML 文件的全路径名，来获取 XML 内的信息
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            //对配置文件中的每一个<bean>，进行处理
            for (Element element : (List<Element>) rootElement.elements()) {
                //获取Bean的基本信息
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
                //将Bean的定义存放到beanDefinitions
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //利用反射创建Bean实例，并存储在singletons中
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //这是对外的一个方法，让外部程序从容器中获取Bean实例，会逐步演化成核心方法
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }
}