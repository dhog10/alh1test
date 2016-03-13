package com.civilgamers.ah1.databases;

import java.sql.Connection;
import java.sql.DriverManager;

public class AHDatabase {

    enum DBType {
        SQLITE,
        MYSQL;
    }

    /* Mysql connection data*/
    private String ip;
    private String database;
    private int port;

    private String username;
    private String password;

    /* SQLite connection data */
    private String url;

    private Connection connection;
    private DBType dbType;
    private boolean ready = false;

    public synchronized void connect() {
        if(ready == false)
            return;

        try {
            if(dbType == DBType.MYSQL)
                this.connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database, username, password);
            else if(dbType == DBType.SQLITE) {
                Class.forName("org.sqlite.JDBC");
                this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void disconnect() {
         if(ready == false)
             return;

        try {
            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Param IP - The target database IP
     * @Param Port
     * @Param Database - The target database
     * @Param username - The username you're connecting with
     * @Param password - The password which you're using to connect with
     */
    public void setConnectionInfo(String ip, int port, String database, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.dbType = DBType.MYSQL;
        this.ready = true;
    }

    /**
     * @param url
     */
    public void setConnectionInfo(String url) {
        this.url = url;
        this.dbType = DBType.SQLITE;
        this.ready = true;
    }

    /**
     *
     * @return connection - The connection object that's been intialized in this class
     */
    public Connection getConnection() {
        return connection;
    }

}
