package com.ecommerce.bean;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.helpers.SessionContext;
import com.ecommerce.model.Cart;
import com.ecommerce.model.LineItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ManagedBean(name = "orderBean", eager = true)
@ApplicationScoped
public class OrderBean {
    private String address;
    private String state;
    private String city;
    private String zipcode;

    public String createOrder() {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cartItems = cartDAO.getCartItems(userId);
        String address = this.address;
        String city = this.city;
        String state = this.state;
        String zipcode = this.zipcode;
        List<LineItem> orderSegments = cartItems.stream().map(cartItem -> new LineItem(cartItem.getProduct(), cartItem.getQuantity())).collect(Collectors.toList());

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userId);

        Order order = new Order();
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZipcode(zipcode);
        order.setOrderSegments(orderSegments);
        order.setUser(user);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createOrder(order);

        cartDAO.deleteUserCart(userId);

        return "user-orders";
    }

    public List<Order> getUserOrders() {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getUserOrders(userId);
        return orderList;
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
}
