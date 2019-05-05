package com.demo.spring.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    String code;
    BigDecimal total;
    BigDecimal totalTax;
    BigDecimal subtotal;
    BigDecimal discounts;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_billing_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Address billingAddress;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_shipping_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Address shippingAddress;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    @JsonManagedReference
    List<Entry> entriesList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscounts() {
        return discounts;
    }

    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Entry> getEntriesList() {
        return entriesList;
    }

    public void setEntriesList(List<Entry> entriesList) {
        this.entriesList = entriesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
