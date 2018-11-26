package com.organiser.services;

import com.organiser.sharedTable.SharedTable;
import com.organiser.sharedTable.SharedTableDAO;

import java.util.List;

public class SharedTableService {

    private SharedTableDAO sharedTableDAO;

    public SharedTableService(SharedTableDAO sharedTableDAO){
        this.sharedTableDAO = sharedTableDAO;
    }
    public  List<SharedTable> getParticularSharedTable(String hiddenName,String password)throws Exception{
        return sharedTableDAO.getParticularSharedTable(hiddenName,password);
    }
    public void insertIntoUserSharedTablesTable(String userTableName,String tableName,String hiddenName,String password,String firstOwner) throws Exception {
        sharedTableDAO.insertIntoUserSharedTablesTable(userTableName,tableName, hiddenName, password,firstOwner);
        }
    public void addNewTableToSharedTables(String tableName, String password,String firstOwner) throws Exception {
        sharedTableDAO.addNewTableToSharedTables(tableName, password,firstOwner);
    }
    public List<SharedTable> getUserAllSharedTables(String tableName)throws Exception{
        return sharedTableDAO.getUserAllSharedTables(tableName);
    }
    public void createUserSharedTablesTable(String tableName) throws Exception {
        sharedTableDAO.createUserSharedTablesTable(tableName);
    }

    public void deleteFromUserSharedTablesTable(String userSharedTableName,String hiddenName) throws Exception {
        sharedTableDAO.deleteFromUserSharedTablesTable(userSharedTableName,hiddenName);
    }

    public void dropSharedTable(String tableName) throws Exception  {
        sharedTableDAO.dropSharedTable(tableName);
    }

    public void deleteTableFromAllSharedTablesTable(String hiddenName) throws Exception  {
        sharedTableDAO.deleteTableFromAllSharedTablesTable(hiddenName);
    }
}
