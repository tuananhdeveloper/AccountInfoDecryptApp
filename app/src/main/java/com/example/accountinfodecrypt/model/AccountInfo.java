package com.example.accountinfodecrypt.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tuan Anh on 19/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class AccountInfo implements Serializable {
    private String website;
    private String username;
    private String nameOnCard;
    private int cardNumber;
    private String expiryDate;
    private int CVV;

    public AccountInfo() {}

    public AccountInfo(String website, String username, String nameOnCard, int cardNumber, String expiryDate, int CVV) {
        this.website = website;
        this.username = username;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
}
