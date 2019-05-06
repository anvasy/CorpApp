package com.anvasy.rest;

import com.anvasy.model.User;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestUserConnector {

    private static String GET_USER_URL = "http://localhost:8000/corp-server/user";
    private static String USER_URL = "http://localhost:8080/corp-server/article/%s";

    private Logger logger = org.apache.log4j.Logger.getLogger(RestUserConnector.class);

    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(new User());
        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange(GET_USER_URL,
                        HttpMethod.GET, request, new ParameterizedTypeReference<List<User>>() {
                        });
        return userResponse.getBody();
    }
}
