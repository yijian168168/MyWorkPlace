package com.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
class BeanListHandler extends DefaultHandler {
    private Book book;
    private List <Book> list = new ArrayList<Book>();
    private String currentTag;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentTag = qName;
        if("书".equals(currentTag)){
            book = new Book();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currentTag = null;
        if("书".equals(qName)){
            list.add(book);
            book = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if("书名".equals(currentTag)){
            String name = new String(ch , start , length);
            book.setName(name);
        }
        if("作者".equals(currentTag)){
            String author = new String(ch , start , length);
            book.setAuthor(author);
        }
        if("价格".equals(currentTag)){
            String price = new String(ch , start , length);
            book.setPrice(price);
        }
    }

    public List<Book> getList(){
        return list;
    }

}
