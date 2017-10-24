package com.yp.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yp
 * @description
 * @date 2017/10/24
 */
@Service("cashier")
public class CashierImpl implements Cashier {
    @Autowired
    BookShopService bookShopService;

    @Transactional
    @Override
    public void checkout(String username, List<String> isbns) {
        for (String isbn :
                isbns) {
            bookShopService.purchase(username, isbn);
        }
    }
}
