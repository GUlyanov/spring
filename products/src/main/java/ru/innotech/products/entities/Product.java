package ru.innotech.products.entities;

import jakarta.persistence.*;
import lombok.Data;
import ru.innotech.dtos.dto.ProductDto;
import ru.innotech.dtos.dto.ProductsDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "accnumber")
    private String accNumber;

    @Column(name = "accrest")
    private BigDecimal accRest;

    @Enumerated(EnumType.STRING)
    @Column(name = "prodtype")
    private ProductType prodType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    private User user;

    public Product() {
    }

    public Product(Long id, String accNumber, BigDecimal accRest, ProductType prodType, User user) {
        this.id = id;
        this.accNumber = accNumber;
        this.accRest = accRest;
        this.prodType = prodType;
        this.user = user;
    }

    public Product(String accNumber, BigDecimal accRest, ProductType prodType, User user) {
        this(null, accNumber, accRest, prodType, user);
    }

    public Product(Long id, String accNumber, BigDecimal accRest, ProductType prodType) {
        this(id, accNumber, accRest, prodType, null);
    }

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(this.getId());
        productDto.setAccNumber(this.getAccNumber());
        productDto.setAccRest(this.getAccRest());
        productDto.setProdTypeName(this.getProdType().toString());
        return productDto;
    }

    public static ProductsDto createProductsDto(Set<Product> productSet) {
        Set<ProductDto> productDtoSet = new HashSet<>();
        for (Product product : productSet) {
            productDtoSet.add(product.toProductDto());
        }
        return new ProductsDto(productDtoSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getAccNumber(), product.getAccNumber()) && getProdType() == product.getProdType() && Objects.equals(getUser(), product.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccNumber(), getProdType(), getUser());
    }
}
