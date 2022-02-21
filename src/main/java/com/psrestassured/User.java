package com.psrestassured;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//JsonIgnoreProperties allows us to not implement all fields needed in the request
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String login;
    public int id;
    public int public_repos;
    /*
    OR
    @JsonProperty("public_repos")
    public int publicRepos;
     */

    public String getLogin(){
        return login;
    }

    public int getId(){
        return id;
    }

    public int getPublicRepos(){
        return public_repos;
    }
}
