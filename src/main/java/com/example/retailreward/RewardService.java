package com.example.retailreward;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RewardService {

    public List<Transaction> getCustomerTransactions(){
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setCustomerId("A");
        transaction.setAmount(120.0);
        transaction.setTxnDate(LocalDate.now());

        Transaction transaction1 = new Transaction();
        transaction1.setCustomerId("A");
        transaction1.setAmount(120.0);
        transaction1.setTxnDate(LocalDate.now().minusMonths(1));

        Transaction transaction2 = new Transaction();
        transaction2.setCustomerId("B");
        transaction2.setAmount(120.0);
        transaction2.setTxnDate(LocalDate.now().minusMonths(2));
        transactionList.add(transaction);
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        //similarly we can dd multiple customer transaction records...but ideally it will come from database
        return transactionList;
    }
}
