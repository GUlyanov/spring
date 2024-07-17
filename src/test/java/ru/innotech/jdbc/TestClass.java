package ru.innotech.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innotech.jdbc.entities.Product;
import ru.innotech.jdbc.entities.User;
import ru.innotech.jdbc.servicies.ProductService;
import ru.innotech.jdbc.servicies.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestClass {
    @Autowired
    Connection connection;
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Test
    @DisplayName("1. Проверка создания соединения")
    public void testConnection() throws SQLException {
        assertNotNull(connection, "Соединение не создано");
        TestUtils.testConnection(connection);
    }

    @Test
    @DisplayName("2. Проверка сохранения пользователей в базу")
    public void TestIns() {
        assertNotNull(userService, "Сервис пользователей не создан");
        // Создание пользователей и вставка их в базу
        TestUtils.loadUsers(true, true, userService);
        // Извлечение всех пользователей из базы
        Set<User> userSet = userService.findAll();
        assertEquals(3, userSet.size(), "В базе не найдены пользователи");
        // Проверка реквизитов пользователя из базы
        assertTrue(userSet.stream().anyMatch(x->x.getUserName().equals("Иванов Г.В.")), "Нет пользователя с заданным именем");
        // Очистка таблицы пользователей
        userService.deleteAll();
        // Проверка отсутствия пользователей в базе
        userSet = userService.findAll();
        assertEquals(0, userSet.size(), "База не очищена от пользователей");
        // Проверка отсутствия продуктов в базе
        Set<Product> prodSet = productService.findAll();
        assertEquals(0, prodSet.size(), "База не очищена от продуктов");
    }

    @Test
    @DisplayName("3. Проверка изменения и удаления пользователя в базе")
    public void TestUpd() {
        assertNotNull(userService, "Источник данных не создан");
        // Создание пользователей и сохранение их в базу
        TestUtils.loadUsers(true, true, userService);

        // Найти пользователя по имени
        Set<User> userSet = userService.findByName("Сидоров П.Т.");
        assertEquals(1, userSet.size(), "В базе имеются дубли имен пользователей");
        User user = userSet.stream().findFirst().orElse(null);
        assertNotNull(user, "Пользователь в базе имеет пустое имя");
        // Проверим есть ли у пользователя договора
        assertEquals(4, user.getProductSet().size(), "У пользователя нет или не хватает договоров");
        // Модифицируем имя пользователя, идентифицируя его по Id
        Long idUser = user.getId();
        user.setUserName("Петров А.И.");
        userService.update(user);
        // Найдем пользователя в базе по Id
        User userNew = userService.findById(idUser).orElse(null);
        assertNotNull(userNew, "Не найден пользователь по Id = "+idUser);
        assertTrue(userNew.getUserName().equals("Петров А.И."), "Пользователь в базе не получил новое имя");
        // Удалим пользователя по Id
        userService.delete(idUser);
        // Проверим отстутствие пользователя в базе
        User userTst = userService.findById(idUser).orElse(null);
        assertNull(userTst, "Пользователь с Id = "+idUser+" не удален");

        // Очистка таблицы пользователей
        userService.deleteAll();
        // Проверка отсутствия пользователей в базе
        userSet = userService.findAll();
        assertEquals(0, userSet.size(), "База не очищена от пользователей");
        // Проверка отсутствия продуктов в базе
        Set<Product> prodSet = productService.findAll();
        assertEquals(0, prodSet.size(), "База не очищена от продуктов");
    }
}
