package com.ecommerce.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "navigationBean", eager = true)
@ApplicationScoped
public class NavigationBean implements Serializable {
    public String goToHomePage() {
        return "home";
    }

    public String goToLoginPage() {
        return "login";
    }

    public String goToRegistrationPage() {
        return "registration";
    }

    public String goToOrdersPage() {
        return "user-orders";
    }

    public String goToCartPage() {
        return "cart";
    }

    public String goToOrderForm() {
        return "order-form";
    }
}
