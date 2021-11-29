package com.Serebriakov.database.entity;

import com.Serebriakov.database.entity.type.Role;

public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private String name;
    private int discount;
    private int spentMoney;

    public int getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(int spentMoney) {
        this.spentMoney = spentMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public User(){}

    public User(int id, String login, String password, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static User createUser(String login, String password, String email, Role role){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
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

    public Role getRole() {
        return role;
    }

    public String getRoleString(){
        return Role.getStringRole(role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
