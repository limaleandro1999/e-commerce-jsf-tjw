package com.ecommerce.bean;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.helpers.SessionContext;
import com.ecommerce.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
    private String errorMsg;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;

    public User getUser() {
        return (User) SessionContext.getInstance().getLoggedUser();
    }

    public String login() {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);

        if (user == null) {
            errorMsg = "Usuário não cadastrado";
            return null;
        } else if (!password.equals(user.getPassword())) {
            errorMsg = "Credenciais incorretas";
            return null;
        }

        SessionContext session = SessionContext.getInstance();
        session.setLoggedUser(user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userFirstName", user.getFirstName());
        session.setAttribute("userLastName", user.getLastName());
        return "home";
    }

    public String logout() {
        SessionContext session = SessionContext.getInstance();
        session.finishSession();
        return "home";
    }

    public String register() {
        User user = new User();
        UserDAO userDAO = new UserDAO();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCpf(cpf);
        userDAO.createUser(user);

        SessionContext session = SessionContext.getInstance();
        session.setLoggedUser(user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userFirstName", user.getFirstName());
        session.setAttribute("userLastName", user.getLastName());

        return "user-details";
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
