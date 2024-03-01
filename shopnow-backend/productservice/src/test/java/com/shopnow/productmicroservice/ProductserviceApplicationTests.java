package com.shopnow.productmicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.shopnow.productmicroservice.entities.Product;
import com.shopnow.productmicroservice.entities.Category;
import com.shopnow.productmicroservice.repositories.ProductRepository;
import com.shopnow.productmicroservice.repositories.CategoryRepository;

@SpringBootTest
class ProductserviceApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void testCreateProduct() {
        // Given
        Category category = Category.builder()
                .categoryname("Electronics")
                .categorydescription("Category for electronic products")
                .build();

        Category savedCategory = categoryRepository.save(category);

        Product product = Product.builder()
                .name("Sample Product")
                .description("Description of sample product")
                .price(100)
                .category(savedCategory)
                .build();

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getPid());
        assertEquals("Sample Product", savedProduct.getName());
        assertEquals("Description of sample product", savedProduct.getDescription());
        assertEquals(100, savedProduct.getPrice());
        assertEquals(savedCategory, savedProduct.getCategory());

        // Delete the created product
        productRepository.delete(savedProduct);

        // Verify that the product has been deleted
        assertFalse(productRepository.existsById(savedProduct.getPid()));
    }

    @Test
    void testCreateCategory() {
        // Given
        Category category = Category.builder()
                .categoryname("human")
                .categorydescription("Category human products")
                .build();

        // When
        Category savedCategory = categoryRepository.save(category);

        // Then
        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getCategoryid());
        assertEquals("human", savedCategory.getCategoryname());
        assertEquals("Category human products", savedCategory.getCategorydescription());

        // Delete the created category
        categoryRepository.delete(savedCategory);

        // Verify that the category has been deleted
        assertFalse(categoryRepository.existsById(savedCategory.getCategoryid()));
    }
}
