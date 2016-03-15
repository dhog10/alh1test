package com.civilgamers.ah1.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Daniel on 14/03/2016.
 */
public class BlockDrops implements Listener {

    Random rand = new Random();

    HashMap<Material, HashMap<ItemStack, Integer>> blockDrops = new HashMap<Material, HashMap<ItemStack, Integer>>();

    public BlockDrops(){

        HashMap<ItemStack, Integer> smoothStoneDrops = new HashMap<ItemStack, Integer>();
        smoothStoneDrops.put(new ItemStack(Material.REDSTONE, 1), 1);

        blockDrops.put(Material.STONE, smoothStoneDrops);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        // stop if its not these blocks
        if(e.getBlock().getType() != Material.STONE){
            return;
        }

        // drop replacement
        e.getBlock().getDrops().clear();

        HashMap<ItemStack, Integer> drops = new HashMap<ItemStack, Integer>();

        for(ItemStack i: drops.keySet()){
            if(rand.nextInt(drops.get(i)) == 0){
                Bukkit.getWorld("world").dropItemNaturally(e.getBlock().getLocation(), i);
            }
        }

    }
}
