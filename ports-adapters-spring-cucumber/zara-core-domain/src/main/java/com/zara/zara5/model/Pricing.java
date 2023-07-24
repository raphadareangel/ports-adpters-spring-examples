package com.zara.zara5.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    
    @OneToOne
    @JoinColumn(name= "product_id")
    private Product product;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceList;
    
    private Integer priority;
    private Float price;
    private String currency;


    public Pricing() {
    }

    public Pricing(LocalDateTime startDate, LocalDateTime endDate, String priceList,
                   Integer priority, Float price, String currency) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }


}

