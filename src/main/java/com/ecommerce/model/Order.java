package com.ecommerce.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "e_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ElementCollection @OrderColumn
    private List<LineItem> orderSegments;
    private String address;
    private String state;
    private String city;
    private String zipcode;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LineItem> getOrderSegments() {
        return orderSegments;
    }

    public void setOrderSegments(List<LineItem> orderSegments) {
        this.orderSegments = orderSegments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getOrderTotal() {
        return this.getOrderSegments()
                .stream()
                .reduce(
                        0,
                        (partialCartQuantityResult, orderSegment) ->
                                Math.toIntExact(partialCartQuantityResult + (orderSegment.getQuantity() * orderSegment.getProduct().getPrice())),
                        Integer::sum
                );
    }
}
