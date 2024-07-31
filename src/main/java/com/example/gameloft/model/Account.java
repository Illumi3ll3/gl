package com.example.gameloft.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy
            = GenerationType.AUTO)
    Integer id;
    String name;
    String credential;
    Timestamp created;
    Timestamp modified;
    Timestamp lastSession;
    @Column(name = "TOTALSPENT")
    Double totalSpent;
    @Column(name = "TOTALREFUND")
    Double totalRefund;
    Timestamp lastPurchase;
    @Column(name = "ACTIVE_CAMPAIGNS")
    String activeCampaignsString;
    @Transient
    List<Campaign> activeCampaigns=new ArrayList<>();
   // List<Device> devices;
    Integer level;
    Integer xp;
    Duration totalPlaytime;
    String country;
    String language;
    Timestamp birthdate;
    String gender;
    @Transient
    Map<String,Integer> inventory=new HashMap<>();
@Column(name = "INVENTORY")
    String inventoryString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getLastSession() {
        return lastSession;
    }

    public void setLastSession(Timestamp lastSession) {
        this.lastSession = lastSession;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Double getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Double totalRefund) {
        this.totalRefund = totalRefund;
    }


    public Timestamp getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(Timestamp lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public List<Campaign> getActiveCampaigns() {
        return activeCampaigns;
    }

    public void setActiveCampaigns(List<Campaign> activeCampaigns) {
        this.activeCampaigns = activeCampaigns;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Duration getTotalPlaytime() {
        return totalPlaytime;
    }

    public void setTotalPlaytime(Duration totalPlaytime) {
        this.totalPlaytime = totalPlaytime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public String getInventoryString() {
        return inventoryString;
    }

    public void setInventoryString(String inventoryString) {
        this.inventoryString = inventoryString;
    }

    public String getActiveCampaignsString() {
        return activeCampaignsString;
    }

    public void setActiveCampaignsString(String activeCampaignsString) {
        this.activeCampaignsString = activeCampaignsString;
    }
}
