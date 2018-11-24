package com.spring.learn.ioc.v1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zfzj on 18/11/22.
 */
public class SimpleIOC {

    // bean 容器
    private static Map<String, Object> beanContainer = new HashMap<String, Object>();

    public SimpleIOC(String location) throws Exception {
        loadBeans(location);
    }

    public Object getBean(String name){
        Object bean = beanContainer.get(name);
        if(bean == null){
            throw new IllegalArgumentException("there is no bean with name "+name);
        }
        return bean;
    }

    private void loadBeans(String location) throws Exception{
        // 加载 xml 配置
        InputStream inStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inStream);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        // 遍历 bean 标签
        for(int i = 0;i<nodes.getLength();i++){
            Node node = nodes.item(i);
            if (node instanceof Element){
                Element element = (Element)node;
                String id = element.getAttribute("id");
                String className =  element.getAttribute("class");
                Class beanClass = null;
                try{
                    beanClass = Class.forName(className);
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                // 创建 bean
                Object bean = beanClass.newInstance();
                // 遍历 <property> 标签
                NodeList propertyNodes = element.getElementsByTagName("property");
                for(int j=0;j<propertyNodes.getLength();j++){
                    Node propertyNode = propertyNodes.item(j);
                    if(propertyNode instanceof Element){
                        Element propertyElement = (Element)propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        // 利用发射将 bean 相关字段访问权限设为可访问
                        Field field = bean.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        if(value!=null && value.length()>0){
                            field.set(bean,value);
                        }else{
                            String ref = propertyElement.getAttribute("ref");
                            if(ref==null || ref.length()==0){
                                throw new IllegalArgumentException("ref conf error");
                            }
                            field.set(bean,getBean(ref));
                        }
                    }
                }
                registerBean(id,bean);
            }
        }

    }

    // 注册 bean 到容器
    private void registerBean(String id, Object bean) {
        beanContainer.put(id, bean);
    }


}
