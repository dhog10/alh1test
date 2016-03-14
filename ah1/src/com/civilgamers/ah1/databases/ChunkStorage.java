package com.civilgamers.ah1.databases;

import com.civilgamers.ah1.base.AH1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChunkStorage {

    private final String table = "chunks";
    private AH1 plugin;

    public ChunkStorage(AH1 plugin) {
        this.plugin = plugin;
    }

    public void init() {
        createTable();
    }

    public boolean setOwner(String chunk, String owner) {
        PreparedStatement statement = null;
        boolean success = true;
        try {
            if (!existsInDatabase(chunk)) {
                statement = plugin.getAHDatabase().getConnection().prepareStatement("INSERT INTO " + table + " VALUES(?,?);");
                statement.setString(1, chunk);
                statement.setString(2, owner);
            } else {
                statement = plugin.getAHDatabase().getConnection().prepareStatement("UPDATE " + table + " SET owner=? WHERE chunk=?;");
                statement.setString(1, owner);
                statement.setString(2, chunk);
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

    public String getOwner(String chunk) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String owner = null;

        if(!existsInDatabase(chunk)) {
            return null;
        }

        try {
            /* Don't need to check if the resultSet is null as that's done in !existsInDatabase() */
            statement = plugin.getAHDatabase().getConnection().prepareStatement("SELECT * FROM " + table + " WHERE chunk=?;");
            statement.setString(1, chunk);
            resultSet = statement.executeQuery();
            resultSet.next();

            owner = resultSet.getString("owner");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return owner;
    }

    public boolean existsInDatabase(String chunk) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        boolean chunkExistsInDB = false;

        try {
            statement = plugin.getAHDatabase().getConnection().prepareStatement("SELECT * FROM " + table + " WHERE chunk=?;");
            statement.setString(1, chunk);
            resultSet = statement.executeQuery();

            if (resultSet == null)
                chunkExistsInDB = false;

            chunkExistsInDB = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return chunkExistsInDB;
    }

    private void createTable() {
        PreparedStatement createChunkTable = null;
        try {
            createChunkTable = plugin.getAHDatabase().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(chunk VARCHAR(13) NOT NULL, owner VARCHAR(32) NOT NULL);");
            createChunkTable.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                createChunkTable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String chunkToString(int x, int y) {
        return x + ":" + y;
    }
}
