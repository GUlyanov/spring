package ru.innotech.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.innotech.dtos.dto.ProductDto;
import ru.innotech.dtos.dto.ProductsDto;
import ru.innotech.products.entities.Product;
import ru.innotech.products.entities.ProductType;
import ru.innotech.products.entities.User;
import ru.innotech.products.exceptions.ProductByIdNotFoundException;
import ru.innotech.products.services.ProductService;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UnitProdControllerTests.class)
@ComponentScan
public class UnitProdControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService prodServ;

    public ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("1.Получение продукта по Id")
    public void TestProductById() throws Exception {
        // Создаем продукт
        Long prodId = 11L;
        Product product = new Product(prodId, "40702840027467738901", BigDecimal.valueOf(53288.20), ProductType.ACCOUNT);
        ProductDto productDto = product.toProductDto();
        // Подготавливаем ожидаемый ответ
        var expected = mapper.writeValueAsString(productDto);
        // Мокируем вызов сервиса продуктов
        when(prodServ.getProductById(prodId)).thenReturn(product);
        // Выполняем запрос к сервису
        mockMvc.perform(get("/product/" + prodId))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("2.Получение списка продуктов по ид клиента")
    public void TestProductsByUserId() throws Exception {
        // создать пользователя с продуктами
        Long userId = 6L;
        User user = new User(userId, "Теткин Ю.М.");
        ProductsDto productsDto = new ProductsDto();
        Product product = new Product(17L, "407028107486723232310", BigDecimal.valueOf(100000.00), ProductType.ACCOUNT);
        productsDto.addProductDto(product.toProductDto());
        user.addProduct(product);
        product = new Product(18L, "67458793891", BigDecimal.valueOf(46723.54), ProductType.CARD);
        productsDto.addProductDto(product.toProductDto());
        user.addProduct(product);
        // Поготавливаем ожидаемый ответ
        var expected = mapper.writeValueAsString(productsDto);
        // Мокируем вызов сервиса продуктов
        when(prodServ.getProductsByUserId(userId)).thenReturn(user.getProductSet());
        // Выполняем запрос к сервису
        mockMvc.perform(get("/products/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("3.Получение сообщения об отсутствии продукта ")
    public void TestProductByIdNotFoundEx() throws Exception {
        Long prodId = 3L;
        // Подготавливаем ожидаемый ответ
        String expected = "Продукт с ид =  <" + prodId + "> не найден";
        // Мокируем вызов сервиса продуктов
        when(prodServ.getProductById(prodId)).thenThrow(new ProductByIdNotFoundException(null, prodId));
        // Выполняем запрос к сервису
        mockMvc.perform(get("/product/" + prodId))
                .andExpect(status().is(404))
                .andExpect(content().string(expected));
    }

}
