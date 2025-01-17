package com.example.gameloft.controller;

import com.example.gameloft.model.Account;
import com.example.gameloft.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;
    @GetMapping(value = "/get_client_config/{player_id}")
    public Account findById(@PathVariable("player_id") Integer id) {

        return service.findById(id);
    }
}
