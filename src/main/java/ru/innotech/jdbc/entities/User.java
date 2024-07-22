package ru.innotech.jdbc.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private Set<Product> productSet = new HashSet<>();

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(String userName) {
        this(null, userName);
    }

    public void addProduct(Product product){
        productSet.add(product);
    }
}
