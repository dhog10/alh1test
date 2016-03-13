package com.civilgamers.ah1.databases;

import com.civilgamers.ah1.base.AH1;
import com.civilgamers.ah1.base.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Economy {
    private final String table = "money";
    private final int startingCash = 50;
    private AH1 plugin;

    public Economy(AH1 plugin) {
        this.plugin = plugin;
    }

    public void init() {
        createTable();
    }

    public boolean setBalance(String uniqueID, int amount) {
        PreparedStatement statement = null;
        boolean success = true;
        try {
            if (!existsInDatabase(uniqueID)) {
                statement = plugin.getAHDatabase().getConnection().prepareStatement("INSERT INTO " + table + " VALUES(?,?);");
                statement.setString(1, uniqueID);
                statement.setInt(2, amount);
            } else {
                statement = plugin.getAHDatabase().getConnection().prepareStatement("UPDATE " + table + " SET balance=? WHERE uid=?;");
                statement.setInt(1, amount);
                statement.setString(2, uniqueID);
            }

            statement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                statement.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public int getBalance(String uniqueID) {
        ResultSet resultSet = null;
        int balance = 0;

        if(!existsInDatabase(uniqueID)) {
            setBalance(uniqueID, startingCash);
            return startingCash;
        }

        try {
            /* Don't need to check if the resultSet is null as that's done in !existsInDatabase() */
            resultSet = getResultSet(uniqueID);
            resultSet.next();

            balance = resultSet.getInt("balance");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return balance;
    }

    public boolean existsInDatabase(String uniqueID) {
        ResultSet resultSet = null;
        boolean playerExistsInDB = false;

        try {
            resultSet = getResultSet(uniqueID);

            if(resultSet == null) {
                playerExistsInDB = false;
                resultSet.close();
            }

            playerExistsInDB = resultSet.next();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return playerExistsInDB;
    }

    private ResultSet getResultSet(String uniqueID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = plugin.getAHDatabase().getConnection().prepareStatement("SELECT * FROM " + table + " WHERE uid=?;");
            statement.setString(1, uniqueID);
            resultSet = statement.executeQuery();
            Util.broadcast("Broadcasting...");
            while(resultSet.next()) {
                Util.broadcast("ID: " + resultSet.getString("uid") + "\t" + resultSet.getInt("balance"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return resultSet;
    }

    private void createTable() {
        PreparedStatement createEconomyTable = null;
        try {
            createEconomyTable = plugin.getAHDatabase().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(uid VARCHAR NOT NULL, balance INTEGER NOT NULL);");
            createEconomyTable.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                createEconomyTable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
