package com.jh.shopperweb.users_foods;

import java.io.Serializable;
import java.util.Objects;

//Custom class to describe the user's food composite key
public class UsersFoodsId implements Serializable {

    private Integer foodId;

    private Integer userId;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersFoodsId)) return false;
        UsersFoodsId that = (UsersFoodsId) o;
        return foodId.equals(that.foodId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, userId);
    }
}
