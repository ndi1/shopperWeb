package com.jh.shopperweb.item;

import com.jh.shopperweb.user.User;


import javax.persistence.*;
import javax.validation.constraints.*;

////Entity for an item object
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=3, max=25, message = "Name must be between 3 and 25 characters.")
    private String itemName;
    @Column
    @Size(min=5,max = 75, message = "Item description must be between 5 and 75 characters.")
    @NotEmpty(message = "Item description cannot be empty.")
    private String itemDesc;
    @Column
    @NotNull(message = "Price Cannot Be Empty")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userItem;

    public User getUserItem() {
        return userItem;
    }

    public void setUserItem(User userItem) {
        this.userItem = userItem;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", price=" + price +
                ", userItem=" + userItem +
                '}';
    }
}
