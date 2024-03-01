package com.shopnow.usermicroservice;

import java.util.List; // Import List
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.shopnow.usermicroservice.entities.Customer;
import com.shopnow.usermicroservice.repositories.CustomerRepository;
import com.shopnow.usermicroservice.external.dtos.Order;
import com.shopnow.usermicroservice.external.services.OrderServiceExternal;

@SpringBootTest
class UserserviceApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderServiceExternal orderServiceExternal;

    @Test
    void testCreateCustomer() {
        // Given
        Customer customer = Customer.builder()
                .fullname("zevils")
                .email("zevilsshrestha007@gmail.com")
                .password("21013002")
                .mobile("12344556")
                .address("sdsss")
                .isadmin(true)
                .build();

        // When
        Customer savedCustomer = customerRepository.save(customer);

        // Then
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getCid());
        assertEquals("zevils", savedCustomer.getFullname());
        assertEquals("zevilsshrestha007@gmail.com", savedCustomer.getEmail());
        assertEquals("21013002", savedCustomer.getPassword());
        assertEquals("12344556", savedCustomer.getMobile());
        assertEquals("sdsss", savedCustomer.getAddress());
        assertTrue(savedCustomer.isIsadmin());
    }

    @Test
    void testGetOrdersByCustomerId() {
        // Given
        int customerId = 1; // Replace with the actual customer ID
        
        // When
        List<Order> orders = orderServiceExternal.getAllOrdersByCustomerId(customerId);

        // Then
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        // Add more assertions as needed based on the behavior of the OrderServiceExternal
    }
}
