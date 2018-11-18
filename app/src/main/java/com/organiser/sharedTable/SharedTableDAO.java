package com.organiser.sharedTable;

import com.organiser.connection.ConnectionHandler;
import com.organiser.helpers.ObjectParser;

import java.util.List;

public class SharedTableDAO {

    private ConnectionHandler connection;
    private String url;

    public SharedTableDAO(){
        url ="http://10.0.2.2:8080/OrganiserWebService/";
    }
    ////////////GETTING TABLE FROM ALL SHARED TABLES////////////////////////////////////////////////
    public  List<SharedTable> getParticularSharedTable(String hiddenName,String password) throws Exception {
        String url = this.url+"particularSharedTables";
        String postData = "hiddenname=" + hiddenName+"&password="+password;

        connection = new ConnectionHandler(url,postData);
        String table = connection.readPage();
        connection.close();
        connection.disconnect();
        return ObjectParser.parserSharedTable(table);
    }
    ////////////ADDING TABLE FROM ALL SHARED TABLES TO USER SHARED TABLES///////////////////////////
    public void insertIntoUserSharedTablesTable(String tableName,String hiddenName,String password) throws Exception {
        String url = this.url+"insertIntoUserSharedTablesTable";
        String postData = "tablename="+tableName+"&hiddenname=" + hiddenName+"&password="+password;

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }
    ///////////////////////////////////////////INSERTING NEW SHARED TABLE TO ALL SHARED TABLE LIST////////////////////////////////
    public void addNewTableToSharedTables(String tableName, String password) throws Exception {
        String url = this.url+"newTableSharedTables";
        String postData = "tablename="+tableName+"&password="+password;

        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }
    public List<SharedTable> getUserAllSharedTables(String tableName)throws Exception{
        String url = this.url+"userAllSharedTables";
        String postData = "tablename="+tableName;

        connection = new ConnectionHandler(url,postData);
        String table = connection.readPage();
        connection.close();
        connection.disconnect();
        return ObjectParser.parserSharedTable(table);
    }


}
