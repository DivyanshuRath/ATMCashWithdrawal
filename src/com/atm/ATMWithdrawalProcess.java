package com.atm;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Logger;

public class ATMWithdrawalProcess {
    private final Logger logger = Logger.getLogger(ATMWithdrawalProcess.class.getName());

    private LinkedHashMap<Integer, Integer> denominationCountMap = new LinkedHashMap<>();

    private long balanceInATM = 0;

    public ATMWithdrawalProcess() {
        denominationCountMap.put(500, 10);
        denominationCountMap.put(200, 20);
        denominationCountMap.put(100, 20);
        denominationCountMap.put(50, 30);
        denominationCountMap.put(20, 100);
        denominationCountMap.put(10, 200);

        Set<Integer> keys = denominationCountMap.keySet();
        for(int key: keys) {
            balanceInATM = balanceInATM + (key * denominationCountMap.get(key));
        }
    }

    public boolean isWithdrawalAmountValid(int withdrawalAmount){
        if(withdrawalAmount <= 0 && (withdrawalAmount%10 != 0))
            return false;
        return true;
    }

    public synchronized int withdrawAmount(int amount){
        if(!isWithdrawalAmountValid(amount)){
            logger.info("Input value should be greater than 0 and multiple of 10");
            return -1;
        }

        if(balanceInATM < amount){
            logger.info("Insufficient balance in ATM, please try later");
            return -1;
        }

        int remainingAmount = amount;
        Set<Integer> denomination = denominationCountMap.keySet();

            for(int deno : denomination){
                if(remainingAmount == 0){
                    return 1;
                }
                if(balanceInATM < remainingAmount){
                    break;
                }
                if(remainingAmount >= deno){
                    int noOfNote = remainingAmount/deno;
                    denominationCountMap.put(deno, denominationCountMap.get(deno)-noOfNote);
                    balanceInATM = balanceInATM - (remainingAmount - remainingAmount%deno);
                    remainingAmount = remainingAmount%deno;
                }
            }
        return -1;
    }
}
