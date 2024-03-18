package com.example.retailreward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Month;
import java.util.*;


@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @GetMapping("/calculate")
    public List<CustomerReward> calculateRewardPoints() {
        Map<String, CustomerReward> customerRewards = new HashMap<>();
        List<Transaction> transactions = rewardService.getCustomerTransactions();
        // Calculate rewards for each transaction
        for (Transaction transaction : transactions) {
            String customerId = transaction.getCustomerId();
            double amount = transaction.getAmount();
            int points = calculatePoints(amount);
            if (Objects.nonNull(customerRewards.get(customerId))) {
                CustomerReward customerReward = customerRewards.get(customerId);
                customerReward.setTotalPoints(customerReward.getTotalPoints() + points);
                Month month = Month.of(transaction.getTxnDate().getMonthValue()); // Assuming current month
                customerReward.getMonthlyPoints().put(month.toString(), customerReward.getMonthlyPoints().getOrDefault(month.toString(), 0) + points);
            } else {
                CustomerReward reward = new CustomerReward();
                reward.setCustomerId(customerId);
                reward.setTotalPoints(points);
                Month month = Month.of(transaction.getTxnDate().getMonthValue()); // Assuming current month
                Map<String, Integer> monthlyPointsMap = new HashMap<>();
                monthlyPointsMap.put(month.toString(), points);
                reward.setMonthlyPoints(monthlyPointsMap);
                customerRewards.put(customerId, reward);
            }

       }

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
