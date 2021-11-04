package com.Serebriakov.entity;

public class User {

    private String login;
    private int id;
    private String role;

    private User(){}

    public User(String login, int id, String role) {
        this.login = login;
        this.id = id;
        this.role = role;
    }

    public static User getUser(String login){
        User user = new User();
        user.setLogin(login);
        user.setRole("user");
        return user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            return login.equals(((User) obj).getLogin());
        }
        return false;
    }

    @Override
    public String toString() {
        return login;
    }
}
