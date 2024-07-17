package ru.innotech.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.innotech.jdbc.entities.User;
import ru.innotech.jdbc.servicies.ProductService;

import java.util.List;

@WebMvcTest(UnitProdControllerTests.class)
@ComponentScan
public class UnitProdControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService prodServ;

    public ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void LoadData(){
        //TestUtils.loadUsers(true, true, );
    }

   @Test
   @DisplayName("1.Проверка получения продукта по Id")
   public void TestProductById(){

   }
}
