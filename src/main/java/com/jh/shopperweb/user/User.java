package com.jh.shopperweb.user;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.item.Item;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.users_foods.UsersFoods;
import com.jh.shopperweb.users_items.UsersItems;
import com.jh.shopperweb.users_recipes.UsersRecipes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false, unique = true, length=45, name="username")
    private String username;
    @Column(unique = true, nullable = false, length=55)
    private String email;
    @Column(length = 200)
    private String password;
    @Column(length = 45, name = "first_name")
    private String firstName;
    @Column(length = 45, name = "last_name")
    private String lastName;
    @Column
    private Boolean enabled;

    @OneToMany (mappedBy = "userItem")
    private Set<Item> items;

    @OneToMany(mappedBy = "user")
    private Set<Food> foods;

    @OneToMany(mappedBy = "userRecipe")
    private Set<Recipe> recipes;

    @OneToMany(mappedBy = "userId")
    private Set<UsersFoods> usersFoods;

    @OneToMany(mappedBy = "userId")
    private Set<UsersRecipes> usersRecipes;

    @OneToMany(mappedBy = "userId")
    private Set<UsersItems> usersItems;

    public Set<UsersItems> getUsersItems() {
        return usersItems;
    }

    public void setUsersItems(Set<UsersItems> usersItems) {
        this.usersItems = usersItems;
    }

    public Set<UsersRecipes> getUsersRecipes() {
        return usersRecipes;
    }

    public void setUsersRecipes(Set<UsersRecipes> usersRecipes) {
        this.usersRecipes = usersRecipes;
    }

    public Set<UsersFoods> getUsersFoods() {
        return usersFoods;
    }

    public void setUsersFoods(Set<UsersFoods> usersFoods) {
        this.usersFoods = usersFoods;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;


    }
}
