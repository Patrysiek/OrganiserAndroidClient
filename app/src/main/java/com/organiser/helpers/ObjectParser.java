package com.organiser.helpers;

import com.organiser.task.Task;
import com.organiser.user.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectParser {

    public static User parseUser(String data){
        try {
            return new ObjectMapper().readValue(data,User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Task parserTask(String data){
        try {
            return new ObjectMapper().readValue(data,Task.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String parserDateToString(Date date){
        try {
            return new SimpleDateFormat("dd/MM/YYYY").format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Date parserStringToDate(String date){
        try {
            return new SimpleDateFormat("dd/MM/YYYY").parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Task> parserTaskList(String s){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Task> myObjects = mapper.readValue(s, mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
            return myObjects;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
