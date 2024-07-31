package com.example.gameloft.service;

import com.example.gameloft.model.Account;
import com.example.gameloft.model.Campaign;
import com.example.gameloft.repo.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Account findById(Integer id) {

        Account account = accountRepository.findById(id).orElseThrow();


        List<Campaign> campaigns = CampaignService.getCurrentCampaigns();
        //We can talk a while about how the inventory should be implemented.
        try {
            account.getInventory().putAll(objectMapper.readValue(account.getInventoryString(), new TypeReference<Map<String, Integer>>() {
            }));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (Campaign campaign : campaigns) {
            if (isCompatible(account, campaign)) {
                account.getActiveCampaigns().add(campaign);
            }
        }
        //I think this update should be somewhere else.
        updateActiveCampaignForAccount(account);
        return account;
    }

    private void updateActiveCampaignForAccount(Account account) {
        try {
            List<String> names = new ArrayList<>();
            account.getActiveCampaigns().forEach(e -> {
                names.add(e.getName());
            });
            account.setActiveCampaignsString(objectMapper.writeValueAsString(names));
            accountRepository.save(account);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private boolean isCompatible(Account account, Campaign campaign) {
        try {
            JsonNode jsonNode = objectMapper.readTree(campaign.getMatchers());
            /*-----------------------------------*/
            if (jsonNode.get("level").get("min").asInt() > account.getLevel())
                return false;
            if (jsonNode.get("level").get("max").asInt() < account.getLevel())
                return false;
            /*-----------------------------------*/
            AtomicReference<Boolean> hasCountry = new AtomicReference<>(false);
            jsonNode.get("has").get("country").elements().forEachRemaining(e -> {
                hasCountry.set(hasCountry.get() ? hasCountry.get() : e.asText().equals(account.getCountry()));
            });

            if (!hasCountry.get())
                return false;
            /*-----------------------------------*/
            AtomicReference<Boolean> hasItem = new AtomicReference<>(true);
            jsonNode.get("has").get("items").elements().forEachRemaining(e -> {
                hasItem.set(!hasItem.get() ? hasItem.get() : account.getInventory().containsKey(e.asText()));
            });

            if (!hasItem.get())
                return false;
            /*-----------------------------------*/
            AtomicReference<Boolean> doesNotHaveItem = new AtomicReference<>(true);
            jsonNode.get("does_not_have").get("items").elements().forEachRemaining(e -> {
                doesNotHaveItem.set(!doesNotHaveItem.get() ? doesNotHaveItem.get() : !account.getInventory().containsKey(e.asText()));
            });

            if (!doesNotHaveItem.get())
                return false;
            /*-----------------------------------*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;
    }
}
