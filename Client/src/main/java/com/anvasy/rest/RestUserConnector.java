package com.anvasy.rest;

import com.anvasy.model.User;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestUserConnector {

    /*private static String GET_USER_URL = "http://an_vasy:8080/corp-server/user";
    private static String USER_URL = "http://an_vasy:8080/corp-server/user/%s";
    private static String CHANGE_USER_URL = "http://an_vasy:8080/corp-server/user/%s/%s";*/

    private static String GET_USER_URL = "http://localhost:8080/corp-server/user";
    private static String USER_URL = "http://localhost:8080/corp-server/user/%s";
    private static String CHANGE_USER_URL = "http://localhost:8080/corp-server/user/%s/%s";

    public List<User> getUsers() {
        ResponseEntity<List<User>> userResponse = new RestTemplate().exchange(GET_USER_URL, HttpMethod.GET, new HttpEntity<>(new User()),
                new ParameterizedTypeReference<List<User>>() { });
        return userResponse.getBody();
    }

    public User logInOrRegister(User user, String isNew) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        return restTemplate.postForObject(String.format(USER_URL, isNew), user, User.class);
    }

    public void changeRole(String username, String role) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        restTemplate.getForObject(String.format(CHANGE_USER_URL, Arrays.asList(username, role)), User.class);
    }
}
