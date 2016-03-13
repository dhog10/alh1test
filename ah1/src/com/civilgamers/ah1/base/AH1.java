package com.civilgamers.ah1.base;

import java.util.logging.Logger;

import com.civilgamers.ah1.commands.Commands;
import com.civilgamers.ah1.databases.AHDatabase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AH1 extends JavaPlugin implements Listener {

    private AHDatabase database;

    public void onEnable(){
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        getServer().getPluginManager().registerEvents(this, new Commands());
    }

    public void preInit() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        database = new AHDatabase();
    }

    public void init() {
        //database.connect();
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
