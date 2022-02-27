package com.jh.shopperweb.users_items;

import com.jh.shopperweb.item.Item;
import com.jh.shopperweb.recipe.Recipe;
import com.jh.shopperweb.user.User;

import javax.persistence.*;

//Entity which describes a user's items
@Entity
@Table(name = "users_items")
public class UsersItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uiId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUiId() {
        return uiId;
    }

    public void setUiId(Integer uiId) {
        this.uiId = uiId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}



