package ru.innotech.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.innotech.jdbc.config.AppConfig;
import ru.innotech.jdbc.entities.User;
import ru.innotech.jdbc.servicies.UserService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    @Test
    @DisplayName("1. Проверка создания контекста и источника данных")
    public void testDataSource() throws SQLException {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        assertNotNull(ctx, "Контекст приложения не создан");
        var dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource, "Источник данных не создан");
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    @DisplayName("2. Проверка сохранения пользователей в базу")
    public void TestIns() {
        var ctx = new AnnotationConfigApplicationContext(Application.class);
        UserService userService = ctx.getBean("userService", UserService.class);
        assertNotNull(userService, "Источник данных не создан");
        // Создание пользователей
        List<User> lstUser = loadUsers();
        // Сохранение пользователей в базу
        for (User user : lstUser) {
            userService.insert(user);
        }
        // Извлечение всех пользователей из базы
        Set<User> userSet = userService.findAll();
        assertEquals(2, userSet.size(), "В базе не найдены пользователи");
        // Проверка реквизитов пользователя из базы
        assertTrue(userSet.stream().anyMatch(x->x.getUserName().equals("Иванов Г.В.")), "Нет пользователя с заданным именем");
        // Очистка таблицы пользователей
        userService.deleteAll();
        // Проверка отсутствия пользователей в базе
        userSet = userService.findAll();
        assertEquals(0, userSet.size(), "База не очищена от пользователей");
        ctx.close();
    }

    @Test
    @DisplayName("3. Проверка изменения и удаления пользователя в базе")
    public void TestUpd() {
        var ctx = new AnnotationConfigApplicationContext(Application.class);
        UserService userService = ctx.getBean("userService", UserService.class);
        assertNotNull(userService, "Источник данных не создан");
        // Создание пользователей
        List<User> lstUser = loadUsers();
        // Сохранение пользователей в базу
        for (User user : lstUser) {
            userService.insert(user);
        }
        // Найти пользователя по имени
        Set<User> userSet = userService.findByName("Сидоров П.Т.");
        assertEquals(1, userSet.size(), "В базе имеются дубли имен пользователей");
        User user = userSet.stream().findFirst().orElse(null);
        assertNotNull(user, "Пользователь в базе имеет пустое имя");
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
    }


    public static List<User> loadUsers(){
        List<User> lstUsers = new ArrayList<>();
        lstUsers.add(new User("Иванов Г.В."));
        lstUsers.add(new User("Сидоров П.Т."));
        return lstUsers;
    }

    private void testDataSource(DataSource dataSource) throws SQLException{
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement("SELECT 1 val");
             var resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                int val = resultSet.getInt("val");
                assertEquals(1, val);
            }
        } catch (Exception e) {
            System.out.println("Ошибка проверки источника: " + e.getMessage());
        }
    }

}
