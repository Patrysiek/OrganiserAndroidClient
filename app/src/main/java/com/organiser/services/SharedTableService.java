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
    public void insertIntoUserSharedTablesTable(String userTableName,String tableName,String hiddenName,String password) throws Exception {
        sharedTableDAO.insertIntoUserSharedTablesTable(userTableName,tableName, hiddenName, password);
        }
    public void addNewTableToSharedTables(String tableName, String password) throws Exception {
        sharedTableDAO.addNewTableToSharedTables(tableName, password);
    }
    public List<SharedTable> getUserAllSharedTables(String tableName)throws Exception{
        return sharedTableDAO.getUserAllSharedTables(tableName);
    }

}
