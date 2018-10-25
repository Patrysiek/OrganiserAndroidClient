package com.organiser.entities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectParser {
    public static  User parseUser(String userData) throws IOException {
        return new ObjectMapper().readValue(userData, User.class);
    }

}
