package com.example.gameloft.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Inventory {

Integer id;
    Account account;
    Item item;
    Integer count;
}
