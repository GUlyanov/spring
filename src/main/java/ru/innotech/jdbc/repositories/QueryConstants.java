package ru.innotech.jdbc.repositories;

public enum QueryConstants {
    ;
    public static final String SIMPLE_INSERT = "insert into USERS (username) values (?)";
    public static final String UPDATE_USER = "update USERS set username = ? where id = ?";
    public static final String SIMPLE_DELETE = "delete from USERS where id = ?";
    public static final String DELETE_ALL = "delete from USERS";
    public static final String ALL_SELECT = "select * from USERS";
    public static final String FIND_BY_NAME = "select * from USERS where username = ?";
    public static final String FIND_NAME = "select userName from USERS where id = ?";

}
