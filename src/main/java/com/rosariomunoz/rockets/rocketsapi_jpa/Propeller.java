package com.rosariomunoz.rockets.rocketsapi_jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "propellers")
public class Propeller {
    @Id
    private String propellerId = UUID.randomUUID().toString();
    private int maxPower;
    private int currentPower = 0;
    private static final int SPEED_CHANGE = 10;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rocket_id")
    @JsonIgnore

    private Rocket rocket;


    public Propeller() {

    }

    public Propeller(int maxPower) throws Exception {
        checkMaxPower(maxPower);
        this.maxPower = maxPower;
    }


    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) throws Exception {
        checkMaxPower(maxPower);
        this.maxPower = maxPower;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(int currentPower) throws Exception {
        this.currentPower = currentPower;
    }

    private void checkMaxPower(int maxPower) throws Exception {
        if (maxPower <= 0) throw new Exception();
    }

    public int increasePower() {
        this.currentPower += SPEED_CHANGE;
        if (currentPower >= maxPower) {
            currentPower = maxPower;
        }
        return SPEED_CHANGE;
    }

    public int reducePower() {
        this.currentPower -= SPEED_CHANGE;
        if (currentPower < 0) {
            currentPower = 0;
        }
        return SPEED_CHANGE;
    }

    public String getpropellerId() {
        return propellerId;
    }

}
