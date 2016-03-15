package com.civilgamers.ah1.drops;

import com.civilgamers.ah1.base.AH1;
import com.civilgamers.ah1.base.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
    private AH1 plugin;

    Random rand = new Random();

    HashMap<Material, HashMap<ItemStack, Integer>> blockDrops = new HashMap<Material, HashMap<ItemStack, Integer>>();

    public BlockDrops(AH1 plugin){
        this.plugin = plugin;

        HashMap<ItemStack, Integer> smoothStoneDrops = new HashMap<ItemStack, Integer>();
        smoothStoneDrops.put(new ItemStack(Material.COBBLESTONE, 1), 1);
        smoothStoneDrops.put(new ItemStack(Material.COAL, 1), 50);

        HashMap<ItemStack, Integer> grassDrops = new HashMap<ItemStack, Integer>();
        grassDrops.put(new ItemStack(Material.DIRT, 1), 1);
        grassDrops.put(new ItemStack(Material.REDSTONE, 1), 13);
        grassDrops.put(new ItemStack(Material.SEEDS, 1), 8);

        HashMap<ItemStack, Integer> dirtDrops = new HashMap<ItemStack, Integer>();
        dirtDrops.put(new ItemStack(Material.DIRT, 1), 1);
        dirtDrops.put(new ItemStack(Material.REDSTONE, 1), 17);
        dirtDrops.put(new ItemStack(Material.SEEDS, 1), 15);

        blockDrops.put(Material.DIRT, dirtDrops);
        blockDrops.put(Material.STONE, smoothStoneDrops);
        blockDrops.put(Material.GRASS, grassDrops);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        // stop if its not these blocks
        if(e.getBlock().getType() != Material.STONE && e.getBlock().getType() != Material.GRASS && e.getBlock().getType() != Material.DIRT){
            return;
        }

        // cancel original drop
        Material blockMaterial = e.getBlock().getType();
        e.getBlock().getDrops().clear();

        final HashMap<ItemStack, Integer> drops = blockDrops.get(blockMaterial);
        final Location loc = e.getBlock().getLocation().clone();
        // drop replacement
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {
                for(ItemStack i: drops.keySet()){
                    if(rand.nextInt(drops.get(i)) == 0){
                        Bukkit.getWorld("world").dropItem(loc, i);
                    }
                }
            }
        }, 1l);
    }
}
