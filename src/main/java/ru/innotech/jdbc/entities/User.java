package ru.innotech.jdbc.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data @NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private Set<Product> productSet = new HashSet<>();

    public User(String userName) {
        this.userName = userName;
    }

    public void addProduct(Product product){
        productSet.add(product);
    }
    public void delProduct(Product product){
        productSet.remove(product);
    }
}
