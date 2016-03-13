package com.civilgamers.ah1.base;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 13/03/2016.
 */
public class Util {

    public static void msg(Player player, String message){
        player.sendMessage("§e*§4Gamemode§e*§7: " + message);
    }

    public static void msg(CommandSender player, String message){
        player.sendMessage("§e<<§4Gamemode§e>>§7 "  + message);
    }

    public static void broadcast(String message){
        Bukkit.getServer().broadcastMessage("§e<<§4Gamemode§e>>§7 " + message);
    }

    public static void advert(String message){
        Bukkit.getServer().broadcastMessage("§fServer: §eADVERT§7: " + message);
    }

    public static void advert(CommandSender sender, String message){
        Player senderPlayer = (Player) sender;
        Bukkit.getServer().broadcastMessage("§f" + senderPlayer.getDisplayName() + " - §eADVERT§7: " + message);
    }

}
