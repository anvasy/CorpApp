package com.anvasy.utils;

import com.anvasy.model.User;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;

public class OAuthUtils {

    private final static String VK_URL = "https://oauth.vk.com/access_token";
    private final static String VK_CLIENT_ID = "6910428";
    private final static String VK_CLIENT_SECRET = "7fKZDlsdGWXYhA8HRzsJ";
    private final static String VK_GET_USERS = "https://api.vk.com/method/users.get";
    private final static String VK_VERSION = "5.92";

    private final static String GIT_URL = "https://github.com/login/oauth/access_token";
    private final static String GIT_CLIENT_ID = "7c2663d064ffe9eea9d9";
    private final static String GIT_CLIENT_SECRET = "bd8a41e04240a69eb1c2bbc4ff9ebd238df10410";
    private final static String GIT_GET_ACCESS_TOKEN = "https://api.github.com/user?access_token=";

    private final static String REDIRECT_URI = "http://localhost:8000/corp-client/auth";
    private final static String REDIRECT_URI_VK = "http://localhost:8000/corp-client/authvk";

    @SuppressWarnings("Duplicates")
    public static User getVkAccess(String code) throws IOException {
        PostMethod post = new PostMethod(VK_URL);
        post.addParameter("client_id", VK_CLIENT_ID);
        post.addParameter("client_secret", VK_CLIENT_SECRET);
        post.addParameter("redirect_uri", REDIRECT_URI_VK);
        post.addParameter("code", code);

        new HttpClient().executeMethod(post);

        JSONObject json = new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
        return getVkUser(String.valueOf(json.get("access_token")), String.valueOf(json.get("user_id")));
    }

    private static User getVkUser(String token, String userId) throws IOException {
        PostMethod userDataPostMethod = new PostMethod(VK_GET_USERS);
        userDataPostMethod.addParameter("user_ids", userId);
        userDataPostMethod.addParameter("access_token", token);
        userDataPostMethod.addParameter("v", VK_VERSION);

        new HttpClient().executeMethod(userDataPostMethod);


        JSONObject userJson = (JSONObject) new JSONObject(new JSONTokener(new InputStreamReader(userDataPostMethod.getResponseBodyAsStream()))).getJSONArray("response").get(0);
        return new User(userId, String.valueOf(userJson.get("first_name")), String.valueOf(userJson.get("last_name")), "vk");
    }

    @SuppressWarnings("Duplicates")
    public static User getGitAccess(String code) throws IOException {
        PostMethod post = new PostMethod(GIT_URL);
        post.addRequestHeader("Accept", "application/json");
        post.addParameter("client_id", GIT_CLIENT_ID);
        post.addParameter("client_secret", GIT_CLIENT_SECRET);
        post.addParameter("redirect_uri", REDIRECT_URI);
        post.addParameter("code", code);

        new HttpClient().executeMethod(post);

        return getGitUser(String.valueOf(new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream()))).get("access_token")));
    }

    private static User getGitUser(String token) throws IOException {
        GetMethod getMethod = new GetMethod(GIT_GET_ACCESS_TOKEN + token);
        new HttpClient().executeMethod(getMethod);

        JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(getMethod.getResponseBodyAsStream())));

        String name = String.valueOf(jsonObject.get("name"));
        int space = name.indexOf(" ");

        return new User(String.valueOf(jsonObject.get("id")), name.substring(0, space), name.substring(space + 1),"git");
    }

}