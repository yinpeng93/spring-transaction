package com.yp.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author yp
 * @description
 * @date 2017/10/24
 */
@Repository("bookShopDao")
public class BookShopImpl implements BookShopDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public double findBookPriceByIsbn(String isbn) {
        return jdbcTemplate.queryForObject("select price from tb_book where isbn = ?",double.class,isbn);
    }

    public void updateBookStock(String isbn) {
        //检查书的库存是否足够，若不够，则抛出异常
        String sql2 = "SELECT stock FROM tb_bookstock WHERE isbn = ?";
        int stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
        if (stock == 0) {
            throw new BookStockException("库存不足！");
        }
        String sql = "UPDATE tb_bookstock SET stock = stock - 1 WHERE isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    public void updateUserAccount(String username, double price) {
        //检查余额是否不足，若不足，则抛出异常
        String sql2 = "SELECT balance FROM tb_account WHERE username = ?";
        int balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);
        if (balance < price) {
            throw new UserAccountException("余额不足！");
        }
        String sql = "UPDATE tb_account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql, price, username);
    }
}
