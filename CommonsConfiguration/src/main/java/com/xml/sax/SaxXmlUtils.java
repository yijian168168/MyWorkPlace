package com.xml.sax;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class SaxXmlUtils {

    public static void main(String[] args) throws Exception{
        //1.创建解析工厂
        SAXParserFactory sf = SAXParserFactory.newInstance();
        //2.创建解析器
        SAXParser sp = sf.newSAXParser();
        //3.得到读写器
        XMLReader xr = sp.getXMLReader();
        //4.设置内容处理器
        BeanListHandler bh = new BeanListHandler();
        xr.setContentHandler(bh);
        //5.读取xml文档
        String xmlMsg = "<?xml version=\"1.0\" encoding=\"gbk\"?><书架><书><书名>JAVA就业培训教材</书名><作者>张孝祥</作者><价格>39.00</价格></书><书><书名>SaxJDK7.0全解</书名><作者>哈哈</作者><价格>90.00</价格></书></书架>";
//        xr.parse(new InputSource(new ByteInputStream(xmlMsg.getBytes(),0)));
        InputStream is = new ByteArrayInputStream(xmlMsg.getBytes());
        xr.parse(new InputSource(is));
        List<Book> list = bh.getList();
        System.out.println(list);
    }
}
