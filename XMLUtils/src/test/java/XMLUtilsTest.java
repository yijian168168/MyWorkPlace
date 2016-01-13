import com.sax.ParseXmlUtils;
import com.velocity.Book;
import com.velocity.VelocityUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/1/13 0013.
 */
public class XMLUtilsTest {

    @Test
    public void testVelocity(){
        Book book = new Book();
        book.setBookName("三国演义");
        book.setBookPrice("30");
        String xml = VelocityUtil.getXML("vm/book.vm",book);
        System.out.println("获取的xml报文：");
        System.out.println(xml);
    }

    @Test
    public void testSax() throws Exception {
        Book book = new Book();
        book.setBookName("三国演义");
        book.setBookPrice("30");
        String xml = VelocityUtil.getXML("vm/book.vm",book);
        System.out.println("获取的xml报文：");
        System.out.println(xml);

        Book book1 = ParseXmlUtils.parseXml(xml, new Book());
        System.out.println(book1);
    }
}
