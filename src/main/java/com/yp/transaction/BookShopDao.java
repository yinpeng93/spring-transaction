package com.yp.transaction;

public interface BookShopDao {
    /**
     * 根据书号获取书的单价
     * @param isbn
     * @return
     */
    public double findBookPriceByIsbn(String isbn);

    /**
     * 更新书的库存，使书号对应的库存-1
     * @param isbn
     */
    public void updateBookStock(String isbn);

    /**
     * 更新用户的账户余额：account的balance-price
     * @param username
     * @param price
     */
    public void updateUserAccount(String username, double price);
}
