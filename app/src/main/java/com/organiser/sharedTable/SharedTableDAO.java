package com.organiser.sharedTable;


import com.organiser.connection.ConnectionHandler;
import com.organiser.helpers.ObjectParser;

import java.util.List;

public class SharedTableDAO {

    private ConnectionHandler connection;

    ////////////GETTING TABLE FROM ALL SHARED TABLES////////////////////////////////////////////////
    public  List<SharedTable> getParticularSharedTable(String hiddenName,String password) throws Exception {
        String url = "particularSharedTables";
        String postData = "hiddenname=" + hiddenName+"&password="+password;

        connection = new ConnectionHandler(url,postData);
        String table = connection.readPage();
        connection.close();
        connection.disconnect();
        return ObjectParser.parserSharedTable(table);
    }
    ////////////ADDING TABLE FROM ALL SHARED TABLES TO USER SHARED TABLES///////////////////////////
    public void insertIntoUserSharedTablesTable(String userTableName,String tableName,String hiddenName,String password,String firstOwner) throws Exception {
        String url = "insertIntoUserSharedTablesTable";
        String postData = "tablename="+userTableName+"&name="+tableName+"&hiddenname=" + hiddenName+"&password="+password+"&firstOwner="+firstOwner;

        performConnection(url,postData);
    }
    ///////////////////////////////////////////INSERTING NEW SHARED TABLE TO ALL SHARED TABLE LIST////////////////////////////////
    public void addNewTableToSharedTables(String tableName, String password,String firstOwner) throws Exception {
        String url = "newTableToSharedTables";
        String firstOwnerTableName=firstOwner+"sharedtable";
        String postData = "tablename="+tableName+"&password="+password+"&firstOwner="+firstOwner+"&firstOwnerTablename="+firstOwnerTableName;
        performConnection(url,postData);

    }
    public List<SharedTable> getUserAllSharedTables(String tableName)throws Exception{
        String url = "userAllSharedTables";
        String postData = "tablename="+tableName;
        connection = new ConnectionHandler(url,postData);
        String table = connection.readPage();
        connection.close();
        connection.disconnect();
        return ObjectParser.parserSharedTable(table);
    }
    public void createUserSharedTablesTable(String tableName)throws Exception{
        String postData = "tablename="+tableName;
        String url = "createUserSharedTablesTable";
        performConnection(url,postData);
    }

    public void deleteFromUserSharedTablesTable(String userSharedTableName,String hiddenName)throws Exception {
        String postData = "tablename="+userSharedTableName+"&hiddenname="+hiddenName;
        String url = "deleteFromUserSharedTablesTable";
        performConnection(url,postData);
    }

    public void dropSharedTable(String hiddenName) throws Exception {
        String postData = "tablename="+hiddenName;
        String url = "dropTable";
        performConnection(url,postData);
    }

    public void deleteTableFromAllSharedTablesTable(String tableName)throws Exception  {
        String postData = "hiddenName="+tableName;
        String url = "deleteTableFromAllSharedTables";
        performConnection(url,postData);
    }

    public void performConnection(String url, String postData)throws Exception{
        connection = new ConnectionHandler(url,postData);
        connection.close();
        connection.disconnect();
    }
}
