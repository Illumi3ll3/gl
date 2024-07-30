package com.example.gameloft.service;

import com.example.gameloft.model.Campaign;

import java.util.ArrayList;
import java.util.List;

public class CampaignService {
    static List<Campaign> getCurrentCampaigns(){
        List<Campaign> campaigns=new ArrayList<>();
        Campaign c=new Campaign();
        c.setId(1);
        c.setMatchers("{\"level\": {\"min\": 1,\"max\": 3 },\"has\": {\"country\": [\"US\",\"RO\",\"CA\"],\"items\": [\"item_1\"] },\"does_not_have\": {\"items\": [\"item_4\"] }}");
        campaigns.add(c);
        return campaigns;
    }
}
