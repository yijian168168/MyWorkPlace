package com.sax;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/1/13 0013.
 */
public class ParseXmlUtils {

    public static <T> T parseXml(String xmlMsg, T xmlVo) throws Exception {

        //1.创建解析工厂
        SAXParserFactory sf = SAXParserFactory.newInstance();
        //2.创建解析器
        SAXParser sp = sf.newSAXParser();
        //3.得到读写器
        XMLReader xr = sp.getXMLReader();
        //4.设置内容处理器
        BeanListHandler<T> bh = new BeanListHandler(xmlVo);
        xr.setContentHandler(bh);
        //5.读取xml文档
        xr.parse(new InputSource(new ByteArrayInputStream(xmlMsg.getBytes("utf-8"))));
        xmlVo = bh.getXmlVo();
        return xmlVo;
    }
}


/**
 * 解析xml报文
 *
 * Created by Administrator on 2015/12/17 0017.
 */
class BeanListHandler<T> extends DefaultHandler {

    /**
     * 应答信息
     * */
    private T xmlVo;

    /**
     * 当前标签
     * */
    private String currentTag;

    public BeanListHandler(T xmlVo) {
        this.xmlVo = xmlVo;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //报文头赋值
        String methodName = new StringBuilder("set").append(StringUtils.capitalize(currentTag)).toString();
        try {
            Method method = xmlVo.getClass().getMethod(methodName,String.class);
            method.invoke(xmlVo, new String(ch, start, length));
        } catch (Exception e) {
            //没有找到方法,在下一个类中继续寻找
        }
    }

    public T getXmlVo(){
        return xmlVo;
    }

}
