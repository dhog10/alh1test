package com.civilgamers.ah1.commands;

import com.civilgamers.ah1.base.AH1;
import com.civilgamers.ah1.base.Util;
import generation.BasicChunk;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 13/03/2016.
 */
public class Commands implements CommandExecutor {

    public AH1 plugin;

    public Commands(AH1 plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        // if they enter nothing
            if (cmd.getName().equalsIgnoreCase("advert")) {
                if(args.length == 0){
                    Util.msg(player, "Enter an advert to send!");
                }else {
                    playerAdvertCommand(player, args[0]);
                }
            } else if (cmd.getName().equalsIgnoreCase("createchunk")) {
                Util.msg(player, "Creating basic chunk...");
                plugin.getBasicChunk().create(player);
                Util.msg(player, "Placed basic chunk at you...");
            } else if (cmd.getName().equalsIgnoreCase("ah")) {
                if(args.length == 0){
                    printHelp(player);
                }else {
                    gamemodeCommand(player, args[0]);
                }
            }
        return true;
    }

    public boolean gamemodeCommand(Player player, String command){
        if(command.equalsIgnoreCase("help")){
            printHelp(player);
        }else if(command.equalsIgnoreCase("balance")){
            Util.msg(player, "Your balance is: " + plugin.getEconomy().getBalance(player.getUniqueId().toString()));
        } else if (command.equalsIgnoreCase("placelake")) {
            Util.msg(player, "Creating lake chunk...");
            plugin.getLakeChunk().create(player);
            Util.msg(player, "Placed lake chunk at you...");
        }else if(command.equalsIgnoreCase("placelavalake")){
            Util.msg(player, "Creating lava lake chunk...");
            plugin.getLakeChunk().create(player, Material.LAVA);
            Util.msg(player, "Placed lava lake chunk at you...");
        } else{
            Util.msg(player, "Command not recognised! Type /help for all available commands.");
        }
        return true;
    }

    public void printHelp(Player player){
        Util.msg(player, "----//Commands\\\\----");
        Util.msg(player, "§e/ah help§7: Lists gamemode commands");
        Util.msg(player, "§e/ah balance§7: Displays your money balance");
        Util.msg(player, "--------------------");
    }

    public boolean playerAdvertCommand(Player sender, String message){
        Util.advert(sender, message);
        return true;
    }

}
