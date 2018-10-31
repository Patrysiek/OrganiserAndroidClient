package com.organiser.somePackage;

import com.organiser.task.Task;
import com.organiser.user.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectParser {

    public static User parseUser(String data) throws IOException {
        return new ObjectMapper().readValue(data,User.class);
    }

    public static Task parserTask(String data)throws IOException{
        return new ObjectMapper().readValue(data,Task.class);
    }

    public static String parserDateToString(Date date){
        return new SimpleDateFormat("dd/MM/YYYY").format(date);
    }
    public static Date parserStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/YYYY").parse(date);
    }

    public static ArrayList<Task> parserTaskList(String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Task> myObjects = mapper.readValue(s, mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
        return myObjects;
    }
}
