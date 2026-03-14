package com.example;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    Logger log = LoggerFactory.getLogger(WalletService.class);

//    @Value("${wallet.initial.balance}")
//    private int initialBalance;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(groupId = "b5walletconsumergroup",topics = "b5userCreated")
    public void createWallet(String message) {

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message);

        Long userId = (Long) jsonObject.get("id");

        Wallet wallet = this.walletRepository.findByUserId(userId);

        if (wallet != null) {
            log.info("wallet already exists");
            return;
        }

        wallet = Wallet.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .balance(10000L)
                .status(WalletStatus.ACTIVE)
                .build();

        this.walletRepository.save(wallet);

        log.info("wallet created {}", jsonObject.toString());
    }


/*
* 1. Create a notification svc as a micro servic
* 2. After wallet is created, now push this into a new kafka topic/partition as wallet created
* 3. Notification service will read from this queue.
* 4. Notification svc will push a new kafka queue as notification generated
* 5. User service will read from this queue.
* */
//

    @KafkaListener(topics = "transaction-creation-done",groupId = "txngroups")
    public void updateWallet(String message){

        JSONObject jsonObject = (JSONObject) JSONValue.parse(message);

        Long senderId = (Long) jsonObject.get("sender");
        Long receiverId = (Long) jsonObject.get("receiver");
        Long amount = (Long) jsonObject.get("amount");

        String txnId = (String) jsonObject.get("txnId");

        Wallet senderWallet = this.walletRepository.findByUserId(senderId);
        Wallet receiverWallet = this.walletRepository.findByUserId(receiverId);

        if(senderWallet == null || receiverWallet == null || amount<0 || senderWallet.getBalance()<amount){

            this.log.info("sender and/or receiver were not found");

            JSONObject event = new JSONObject();
            event.put("status","FAILED");
            event.put("sender",senderId);
            event.put("receiver",receiverId);
            event.put("amount",amount);
            event.put("txnId",txnId);

            this.kafkaTemplate.send("txn-updation-done",event.toString());

        }
        receiverWallet.setBalance(receiverWallet.getBalance()+amount);
        senderWallet.setBalance(senderWallet.getBalance()-amount);

        this.walletRepository.saveAll(List.of(receiverWallet,senderWallet));


        JSONObject event = new JSONObject();
        event.put("status","SUCCESS");
        event.put("sender",senderId);
        event.put("receiver",receiverId);
        event.put("amount",amount);
        event.put("txnId",txnId);

        this.kafkaTemplate.send("txn-updation-done",event.toString());

    }



}
