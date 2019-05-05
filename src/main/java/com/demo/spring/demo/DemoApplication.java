package com.demo.spring.demo;

import com.demo.spring.demo.beans.Address;
import com.demo.spring.demo.beans.Cart;
import com.demo.spring.demo.beans.Entry;
import com.demo.spring.demo.service.AddressService;
import com.demo.spring.demo.service.CartService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.Arrays;

@ComponentScan("com.demo.spring.demo.*")
@SpringBootApplication
@EntityScan("com.demo.spring.demo.*")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = { "com.demo.spring.demo.*" })
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CartService cartService , AddressService addressService) {
        Cart cart1 = new Cart();
        cart1.setCode("cart-1234");
        cart1.setTotal(new BigDecimal(50));
        cart1.setSubtotal(new BigDecimal(48));
        cart1.setTotalTax(new BigDecimal(8));


        Address address1 = new Address();
        address1.setCountry("Germany");
        address1.setName("Iron Man");
        address1.setPostalCode("11111");
        address1.setStreetName("Avenger street");
        address1.setStreetNumber("11");


        cart1.setBillingAddress(address1);
        cart1.setShippingAddress(address1);

        Entry entry1 = new Entry();
        entry1.setCode("12345_1_1");
        entry1.setQuantity(10L);
        entry1.setTotal(BigDecimal.valueOf(25));

        Entry entry2 = new Entry();
        entry2.setCode("12345_1_2");
        entry2.setQuantity(1L);
        entry2.setTotal(BigDecimal.valueOf(23));




        Cart cart2 = new Cart();
        cart2.setCode("cart-1234");
        cart2.setTotal(new BigDecimal(25));
        cart2.setSubtotal(new BigDecimal(25));
        cart2.setTotalTax(new BigDecimal(5));


        Address address2 = new Address();
        address2.setCountry("Germany");
        address2.setName("Super Man");
        address2.setPostalCode("11111");
        address2.setStreetName("DC street");
        address2.setStreetNumber("1111");


        cart2.setBillingAddress(address2);
        cart2.setShippingAddress(address2);

        Entry entry3 = new Entry();
        entry3.setCode("99999_1_1");
        entry3.setQuantity(10L);
        entry3.setTotal(BigDecimal.valueOf(5));

        Entry entry4 = new Entry();
        entry4.setCode("88888_1_2");
        entry4.setQuantity(1L);
        entry4.setTotal(BigDecimal.valueOf(20));




        addressService.saveAddress(address1);
        addressService.saveAddress(address2);

        cartService.saveCartEntry(entry1);
        cartService.saveCartEntry(entry2);
        cartService.saveCartEntry(entry3);
        cartService.saveCartEntry(entry4);


        cart1.setEntriesList(Arrays.asList(entry1,entry2));
        cart2.setEntriesList(Arrays.asList(entry3,entry4));

        cartService.saveCart(cart1);
        cartService.saveCart(cart2);


        entry1.setCart(cart1);
        entry2.setCart(cart1);
        entry3.setCart(cart2);
        entry4.setCart(cart2);


        cartService.saveCartEntry(entry1);
        cartService.saveCartEntry(entry2);
        cartService.saveCartEntry(entry3);
        cartService.saveCartEntry(entry4);

        return args ->  cartService.getCarts().forEach(System.out::println);
    }

}
