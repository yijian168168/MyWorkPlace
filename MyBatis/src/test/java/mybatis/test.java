package mybatis;

import com.models.Address;
import com.models.User;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/12 0012.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/springConfig.xml")
public class test {

    @Autowired
    private UserService userService;

    /**
     * 执行保存操作
     */
    @Test
    public void testSave() {

        User user = new User();
        user.setBirthday(new Date());
        user.setName("xiaoqin");
        user.setSalary(4000.00);

        userService.save(user);

    }

    /**
     * 执行删除操作
     */
    @Test
    public void testDelete() {
        int id = 4;
        userService.delete(id);
    }

    /**
     * 执行更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(6);
        user.setBirthday(new Date());
        user.setName("xiaoqin");
        user.setSalary(4500.00);

        userService.update(user);
    }

    /**
     * 执行查找操作
     */
    @Test
    public void testFindByID() {
        int id = 2;
        User user = userService.findByID(id);
        System.out.println(user);
    }

    /**
     * 执行查找全部操作
     */
    @Test
    public void testFindAll() {

        List<User> users = userService.findAll();
        System.out.println(users);
    }

    /**
     * 执行添加用户操作
     * */
    @Test
    public void addAddress(){
        User user = new User();
        user.setBirthday(new Date());
        user.setName("xiaoqin");
        user.setSalary(4000.00);
        Address address = new Address("00001","长湴");
        userService.addAddress(user, address);
    }

    @Test
    public void findByAddress(){
        Address address = new Address("00001","长湴");
        List<User> users = userService.findByAddress(address);
        System.out.println(users);
    }
}
