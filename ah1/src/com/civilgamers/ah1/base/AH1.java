package com.civilgamers.ah1.base;

import java.io.File;
import java.util.logging.Logger;

import com.civilgamers.ah1.commands.Commands;
import com.civilgamers.ah1.databases.AHDatabase;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AH1 extends JavaPlugin implements Listener {

    private AHDatabase database;
    private Commands commands;

    public void onEnable(){
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        preInit();
        init();
    }

    public void preInit() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        database = new AHDatabase();
        commands = new Commands();

        if(getConfig().getBoolean("database.mysql.enabled")) {
            database.setConnectionInfo(
                    getConfig().getString("database.mysql.credentials.ip"),
                    getConfig().getInt("database.mysql.credentials.port"),
                    getConfig().getString("database.mysql.credentials.database"),
                    getConfig().getString("database.mysql.credentials.username"),
                    getConfig().getString("database.mysql.credentials.password"));
        } else {
            database.setConnectionInfo(getDataFolder().getPath() + File.separator + getConfig().getString("database.sqlite.database"));
        }
    }

    public void init() {
        database.connect();

        getCommand("ah").setExecutor(commands);
        getCommand("advert").setExecutor(commands);
        getCommand("example").setExecutor(commands);
        Location loc = getServer().getWorld("world").getSpawnLocation();
        Location tempLoc = loc.clone();
        for(int x = 0; x < 16; x++){
            for(int i = 0; i < 16; i++) {
                tempLoc.setX((loc.getX() + x) - 8);
                tempLoc.setZ((loc.getZ() + i) - 8);
                tempLoc.getBlock().setType(Material.GRASS);
            }
        }
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
        return new AH1WorldGenerator();
    }
    public AHDatabase getAHDatabase() {
        return database;
    }


}
