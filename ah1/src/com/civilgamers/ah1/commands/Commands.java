package com.civilgamers.ah1.commands;

import com.civilgamers.ah1.base.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 13/03/2016.
 */
public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("advert")) {
            playerAdvertCommand(player, args[0]);
        }else if(cmd.getName().equalsIgnoreCase("example")){
            Util.msg(player, "This is a test fam");
        }else if(cmd.getName().equalsIgnoreCase("/ah")){
            gamemodeCommand(player, args[0]);
        }
        return true;
    }

    public boolean gamemodeCommand(Player player, String command){
        if(command.equalsIgnoreCase("help")){
            Util.msg(player, "----//Commands\\\\----");
            Util.msg(player, "§e/help§7: Lists gamemode commands");
            Util.msg(player, "--------------------");
        }else{
            Util.msg(player, "Command not recognised! Type /help for all available commands.");
        }
        return true;
    }

    public boolean playerAdvertCommand(Player sender, String message){
        Util.advert(sender, message);
        return true;
    }

}
