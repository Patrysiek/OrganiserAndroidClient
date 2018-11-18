package com.organiser.helpers;

import com.organiser.sharedTable.SharedTable;
import com.organiser.task.Task;
import com.organiser.user.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ObjectParser {

    public static User parseUser(String data){
        try {
            return new ObjectMapper().readValue(data,User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String parserDateToString(Date date){
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "blabla";
    }


    public static ArrayList<Task> parserTaskList(String s){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(s, mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<SharedTable> parserSharedTable(String s){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(s, mapper.getTypeFactory().constructCollectionType(List.class, SharedTable.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
