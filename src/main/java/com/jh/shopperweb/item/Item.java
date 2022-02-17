package com.jh.shopperweb.item;

import com.jh.shopperweb.user.User;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column
    private String itemName;
    @Column
    private String itemDesc;
    @Column
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
}
