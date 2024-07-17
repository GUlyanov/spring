package ru.innotech.jdbc.repositories;

public enum QueryConstants {
    ;
    public static final String INSERT_USER = "insert into USERS (username) values (?)";
    public static final String UPDATE_USER = "update USERS set username = ? where id = ?";
    public static final String DELETE_USER = "delete from USERS where id = ?";
    public static final String DELETE_ALL_USERS = "delete from USERS";
    public static final String SELECT_ALL_USERS = "select * from USERS";
    public static final String FIND_USER_BY_NAME = "select * from USERS where username = ?";
    public static final String FIND_USER_BY_ID = "select * from USERS where id = ?";
    public static final String FIND_PRODUCTS_BY_USER_ID = "select * from PRODUCTS where userId = ?";
    public static final String FIND_PRODUCT_BY_ID = "select id from PRODUCTS where id = ?";
    public static final String INSERT_PRODUCT = "insert into PRODUCTS (accnumber, accrest, prodtype, userid) values (?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT = "update PRODUCTS set accnumber = ?, accrest = ?, prodtype = ? where id = ?";
    public static final String DELETE_PRODUCT = "delete from PRODUCTS where id = ?";
    public static final String DELETE_ALL_PRODUCTS = "delete from PRODUCTS";
    public static final String SELECT_ALL_PRODUCTS = "select * from PRODUCTS";
}
