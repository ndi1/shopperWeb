package com.jh.shopperweb.users_goals;

import com.jh.shopperweb.user.User;

import javax.persistence.*;

@Entity
@Table
public class UsersGoals {

    @Id
    @Column(name = "user_id")
    private Integer userGoalsId;

    @Column
    private Double calorieGoal;

    @Column
    private Double carbGoal;

    @Column
    private Double proteinGoal;

    @Column
    private Double fatGoal;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserGoalsId() {
        return userGoalsId;
    }

    public void setUserGoalsId(Integer userDetailsId) {
        this.userGoalsId = userDetailsId;
    }

    public Double getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public Double getCarbGoal() {
        return carbGoal;
    }

    public void setCarbGoal(Double carbGoal) {
        this.carbGoal = carbGoal;
    }

    public Double getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(Double proteinGoal) {
        this.proteinGoal = proteinGoal;
    }

    public Double getFatGoal() {
        return fatGoal;
    }

    public void setFatGoal(Double fatGoal) {
        this.fatGoal = fatGoal;
    }
}
