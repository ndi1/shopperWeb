package com.jh.shopperweb.users_foods;

import com.jh.shopperweb.food.Food;
import com.jh.shopperweb.user.User;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table (name = "users_foods")
@IdClass(UsersFoodsId.class)
public class UsersFoods implements Serializable {



    @Id
    @ManyToOne
    @JoinColumn (name = "food_id")
    private Food foodId;


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column
    private String date;



    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
