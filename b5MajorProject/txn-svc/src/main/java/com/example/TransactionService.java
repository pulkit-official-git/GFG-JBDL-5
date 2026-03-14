package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String initiate(Long sender, Long receiver, Long amount) {

        Transaction transaction = Transaction.builder()
                .txnId(UUID.randomUUID().toString())
                .amount(amount)
                .sender(sender)
                .receiver(receiver)
                .status(TransactionStatus.PENDING)
                .build();

        this.transactionRepository.save(transaction);

        JSONObject jsonObject = this.objectMapper.convertValue(transaction, JSONObject.class);
        this.kafkaTemplate.send("transaction-creation-done", jsonObject.toString());

        return transaction.getTxnId();
    }

    @KafkaListener(topics = "txn-updation-done",groupId = "txnUpdatedGroup")
    public void listen(String message){

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message);

        String txnId = jsonObject.get("txnId").toString();
        String status = jsonObject.get("status").toString();

        Transaction txn = this.transactionRepository.findByTxnId(txnId);

        if(txn.getStatus() != TransactionStatus.PENDING){
            logger.info("txn already updated");
            return;
        }
        if(status.equals("SUCCESS")){
            txn.setStatus(TransactionStatus.SUCCESS);
        }
        else if(status.equals("FAILED")){
            txn.setStatus(TransactionStatus.FAILED);
        }

        /*
        * Create again here kafka for notification service
        * */

        this.transactionRepository.save(txn);

    }
}
