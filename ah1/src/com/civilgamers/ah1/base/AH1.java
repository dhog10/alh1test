package com.civilgamers.ah1.base;

import java.util.logging.Logger;

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

    public void onEnable(){
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.getPlayer().sendMessage("You fucked a block in half");
        //e.getPlayer().setHealth(0);
    }

    /*public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(cmd.getName().equals("goto")) {
            player.teleport(getServer().getPlayer(args[0]));
        }
        return true;
    }*/

}
