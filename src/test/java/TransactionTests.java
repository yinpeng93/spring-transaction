import com.yp.transaction.BookShopDao;
import com.yp.transaction.BookShopService;
import com.yp.transaction.Cashier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yp
 * @description
 * @date 2017/10/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:transaction.xml"})
public class TransactionTests {
    @Autowired
    BookShopDao bookShopDao;

    @Autowired
    BookShopService bookShopService;

    @Autowired
    Cashier cashier;
    /**
     * 测试查询书的价格
     */
    @Test
    public void findBookPriceByIsbn(){
        String isbn = "978-124-24";
        double price = bookShopDao.findBookPriceByIsbn(isbn);
        Assert.assertEquals(20,price,0);
    }

    /**
     * 测试更新书库库存
     */
    @Test
    public void updateBookStock(){
        bookShopDao.updateBookStock("978-124-24");
    }

    /**
     * 测试更新用户余额
     */
    @Test
    public void updateUserAccount(){
        bookShopDao.updateUserAccount("zs",10);
    }

    /**
     * 测试购买图书
     */
    @Test
    public void purchase(){
        bookShopService.purchase("zs","978-124-24");
    }

    /**
     * 测试事务传播
     */
    @Test
    public void checkout(){
        List<String> isbns = new ArrayList<>();
        isbns.add("978-124-24");
        cashier.checkout("zs",isbns);
    }
}
