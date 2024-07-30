package com.example.gameloft.service;

import com.example.gameloft.model.Account;
import com.example.gameloft.model.Campaign;
import com.example.gameloft.repo.AccountRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    JsonParser p=null;
    private ObjectMapper objectMapper=new ObjectMapper();

    public Account findById(Integer id) {
        Account account= accountRepository.findById(id).get();
        List<Campaign> campaigns=CampaignService.getCurrentCampaigns();

        for(int i=0;i<campaigns.size();i++){
            if(isCompatible(account,campaigns.get(i))){
                account.getActiveCampaigns().add(campaigns.get(i));
            }
        }
        return account;
    }

    private boolean isCompatible(Account account, Campaign campaign) {
        try {
            JsonNode jsonNode = objectMapper.readTree(campaign.getMatchers());
            System.out.println(jsonNode.get("level").asText());
            if(jsonNode.get("level").get("min").asInt()<account.getLevel())
                return false;
            if(jsonNode.get("level").get("max").asInt()>account.getLevel())
                return false;
            AtomicReference<Boolean> hasCountry= new AtomicReference<>(false);
            jsonNode.get("has").get("country").fieldNames().forEachRemaining(e->{
                hasCountry.set(hasCountry.get() ? hasCountry.get() : e.equals(account.getCountry()));
            });
            if(!hasCountry.get())
                return false;

            if(!hasCountry.get())
                return false;
        }catch (Exception e){

        }
return true;
    }

    public List<Campaign> getCurrentCampaigns(){
        return new ArrayList<>();
    }
}
