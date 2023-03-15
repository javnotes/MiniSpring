package com.minis.beans;

import com.minis.BeanDefinition;
import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * 将解析好的 xml 转换成 BeanDefinition
 * @author luf
 * @date 2023/03/15 10:12
 **/
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 把解析的 XML 内容转换成 BeanDefinition，并加载到 BeanFactory 中
     * @param resource
     */
    public void loadBeanDefinitions(Resource resource) {
        // 1. 读取 xml 文件
        // 2. 解析 xml 文件
        // 3. 将解析好的 xml 转换成 BeanDefinition
        // 4. 将 BeanDefinition 注册到 BeanFactory
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition bd = new BeanDefinition(beanID, beanClassName);
            this.beanFactory.registerBeanDefinition(bd);
        }
    }
}
