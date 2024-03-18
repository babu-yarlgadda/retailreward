package com.example.retailreward;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class CustomerReward {
    private String customerId;
    private int totalPoints;
    private Map<String, Integer> monthlyPoints; // Map<Month, Points>
}
