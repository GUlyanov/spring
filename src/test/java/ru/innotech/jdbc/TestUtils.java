package ru.innotech.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.entities.ProductType;
import ru.innotech.jdbc.entities.User;
import ru.innotech.jdbc.servicies.UserService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static List<User> loadUsers(boolean bIns, boolean bClear, UserService userService){
        List<User> lstUsers = new ArrayList<>();
        User user = new User("Иванов Г.В.");
        user.addProduct(new Product("40702810312345678901", BigDecimal.valueOf(2160.30), ProductType.ACCOUNT));
        user.addProduct(new Product("40702840027467738901",BigDecimal.valueOf(53288.20), ProductType.ACCOUNT));
        user.addProduct(new Product("123456789012",BigDecimal.valueOf(50000.00), ProductType.CARD));
        lstUsers.add(user);
        user = new User("Сидоров П.Т.");
        user.addProduct(new Product("40702810923456278192",BigDecimal.valueOf(5672.11), ProductType.ACCOUNT));
        user.addProduct(new Product("40702810993333948192",BigDecimal.valueOf(11340.57), ProductType.ACCOUNT));
        user.addProduct(new Product("102938475612",BigDecimal.valueOf(10000.00), ProductType.CARD));
        user.addProduct(new Product("302963847563",BigDecimal.valueOf(40000.00), ProductType.CARD));
        lstUsers.add(user);
        user = new User("Теткин Ю.М.");
        user.addProduct(new Product("407028107486723232310",BigDecimal.valueOf(100000.00), ProductType.ACCOUNT));
        user.addProduct(new Product("67458793891",BigDecimal.valueOf(46723.54), ProductType.CARD));
        lstUsers.add(user);
        if (bClear)
            userService.deleteAll();
        if (bIns){
            // Сохранение пользователей в базу
            for (User userOne : lstUsers) {
                userService.insert(userOne);
            }
        }
        return lstUsers;
    }

    public static void testConnection(Connection connection) throws SQLException {
        try (var statement = connection.prepareStatement("SELECT 1 val");
             var resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                int val = resultSet.getInt("val");
                assertEquals(1, val);
            }
        } catch (Exception e) {
            System.out.println("Ошибка проверки соединения: " + e.getMessage());
        }
    }

}
