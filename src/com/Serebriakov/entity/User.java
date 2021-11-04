package com.Serebriakov.entity;

public class User {

    private String login;
    private int id;
    private int roleId;

    private User(){}

    public User(String login, int id, int roleId) {
        this.login = login;
        this.id = id;
        this.roleId = roleId;
    }

    public static User getUser(String login){
        User user = new User();
        user.setLogin(login);
        return user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
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
