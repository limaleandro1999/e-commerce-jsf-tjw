package com.ecommerce.helpers;

import com.ecommerce.model.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {
    private static SessionContext instance;

    public static SessionContext getInstance(){
        if (instance == null){
            instance = new SessionContext();
        }

        return instance;
    }

    private ExternalContext currentExternalContext(){
        if (FacesContext.getCurrentInstance() == null){
            throw new RuntimeException("O FacesContext não pode serchamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public User getLoggedUser(){
        return (User) getAttribute("loggedUser");
    }

    public void setLoggedUser(User usuario){
        setAttribute("loggedUser", usuario);
    }

    public void finishSession(){
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String name){
        return currentExternalContext().getSessionMap().get(name);
    }

    public void setAttribute(String name, Object value){
        currentExternalContext().getSessionMap().put(name, value);
    }
}
