package com.example.reward;

import org.springframework.web.bind.annotation.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reward")
public class RewardController {

    @GetMapping("/calculate")
    public List<CustomerReward> calculateRewardPoints() {
        Map<String, CustomerReward> customerRewards = new HashMap<>();

        // Calculate rewards for each transaction
        //for (Transaction transaction : transactions) {
            String customerId = transaction.getCustomerId();
            double amount = transaction.getAmount();
            int points = calculatePoints(amount);
            
            customerRewards.putIfAbsent(customerId, new CustomerReward());
            CustomerReward customerReward = customerRewards.get(customerId);
            customerReward.setCustomerId(customerId);
            customerReward.setTotalPoints(customerReward.getTotalPoints() + points);
            
            Month month = Month.of(transaction.getTxnDate().getMonthValue()); // Assuming current month
            customerReward.getMonthlyPoints().put(month.toString(), customerReward.getMonthlyPoints().getOrDefault(month.toString(), 0) + points);
       // }

        return new ArrayList<>(customerRewards.values());
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2); // 2 points per dollar for amount over $100
            points += 50; // 50 points for first $100
        } else if (amount > 50) {
            points += (int) (amount - 50); // 1 point per dollar for amount between $50 and $100
        }
        return points;
    }
}
