package com.civilgamers.ah1.base;

import java.util.logging.Logger;

import com.civilgamers.ah1.commands.Commands;
import com.civilgamers.ah1.databases.AHDatabase;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AH1 extends JavaPlugin implements Listener {

    private AHDatabase database;
    Commands commands;

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
    }

    public void init() {
        //database.connect();
        getCommand("ah").setExecutor(commands);
        getCommand("advert").setExecutor(commands);
        getCommand("example").setExecutor(commands);
        Location loc = getServer().getWorld("world").getSpawnLocation();
        Location tempLoc = loc.clone();
        for(int x = 0; x < 16; x++){
            for(int i = 0; i < 16; i++) {
                tempLoc.setX((loc.getX() + x) - 8);
                tempLoc.setY((loc.getZ() + i) - 8);
                tempLoc.getBlock().setType(Material.GRASS);
            }
        }
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
        return new AH1WorldGenerator();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.getPlayer().sendMessage("You fucked a block in half");
        //e.getPlayer().setHealth(0);
    }

    public AHDatabase getAHDatabase() {
        return database;
    }


}
