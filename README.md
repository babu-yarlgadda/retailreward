# retailreward

This project exposes below endpoints which returns all customer's total reward points as well as monthly wise reward points.
URL -> http://localhost:8080/reward/calculate
This API internally getting all the transactions(currently we have hardcoded two customers with 1-2 trnsactions) and iterating over it to calculate total and monthly wise reward points for all customers.
Response-> [{"customerId":"A","totalPoints":180,"monthlyPoints":{"MARCH":90,"FEBRUARY":90}},{"customerId":"B","totalPoints":90,"monthlyPoints":{"JANUARY":90}}]


Problem Statement -> A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over  in each transaction, plus 1 point for every dollar spent between  and  in each transaction. (e.g. a  purchase = 2x + 1x = 90 points). Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total. 
