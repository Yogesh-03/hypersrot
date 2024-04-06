package com.example.hypersoft.service;

import com.example.hypersoft.responsemodel.PaymentResponse;
import com.example.hypersoft.entity.Transaction;
import com.example.hypersoft.error.TransactionException;
import com.example.hypersoft.repository.TransactionRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private static final String[] STATUSES = {"successful", "failed", "failed", "failed", "failed", "failed"};
    private static final String[] DESCRIPTIONS = {
            "Payment Failed as amount is invalid",
            "Payment Failed from bank",
            "Payment Failed due to invalid order id",
            "No response from payment server",
            "Order is already paid for"
    };

    @Getter
    public  String transactionId;

    @Override
    public PaymentResponse processPayment(Long userId, Long orderId, int amount) throws TransactionException {
        Random random = new Random();
        int index = random.nextInt(STATUSES.length);

        Transaction transaction = new Transaction();
        Date date = new Date();
        transaction.setUserId(userId);
        transaction.setOrderId(orderId);
        transaction.setAmount(amount);
        transaction.setStatus(STATUSES[0]);
        transaction.setDescription("Payment Successful");
        transaction.setDate(date);
        transaction.setTransactionId(generateTransactionId()
        );





        if (index != 0 && index<=4){
            index -= 1;
            transaction.setStatus(STATUSES[1]);
            transaction.setDescription(DESCRIPTIONS[index]);
            transactionRepository.save(transaction);
            throw new TransactionException(DESCRIPTIONS[index]);
        }

        transactionRepository.save(transaction);

        return new PaymentResponse(userId, orderId, transaction.getTransactionId().toString(), transaction.getStatus(), "Successful");
    }

    private String generateTransactionId(){
        this.transactionId = UUID.randomUUID().toString();
        return transactionId;
    }

}
