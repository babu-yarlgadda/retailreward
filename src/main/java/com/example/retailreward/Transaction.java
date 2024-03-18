package com.example.retailreward;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Transaction {
    private String customerId;
    private double amount;
    private LocalDate txnDate;
}

