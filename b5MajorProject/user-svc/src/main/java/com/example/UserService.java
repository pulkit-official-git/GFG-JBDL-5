package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void createUser(User user) {
        this.userRepository.save(user);

        JSONObject jsonObject = this.objectMapper.convertValue(user, JSONObject.class);
        this.kafkaTemplate.send("b5userCreated", jsonObject.toString());
    }
}
