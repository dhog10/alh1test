package com.civilgamers.ah1.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Daniel on 14/03/2016.
 */
public class MobDrops implements Listener {

    HashMap<EntityType, HashMap<ItemStack, Integer>> mobDrops = new HashMap<EntityType, HashMap<ItemStack, Integer>>();
    Random rand = new Random();

    public MobDrops(){

        // mob drop lists
        HashMap<ItemStack, Integer> creeperDrops = new HashMap<ItemStack, Integer>();
        creeperDrops.put(new ItemStack(Material.SLIME_BALL), 150 );
        creeperDrops.put(new ItemStack(Material.REDSTONE, 3), 10 );

        HashMap<ItemStack, Integer> spiderDrops = new HashMap<ItemStack, Integer>();
        spiderDrops.put(new ItemStack(Material.REDSTONE, 3), 5 );
        spiderDrops.put(new ItemStack(Material.WEB, rand.nextInt(2)+1), 4 );
        spiderDrops.put(new ItemStack(Material.REDSTONE_BLOCK, 1), 60 );

        HashMap<ItemStack, Integer> skeletonDrops = new HashMap<ItemStack, Integer>();
        skeletonDrops.put(new ItemStack(Material.REDSTONE, 1), 10 );
        skeletonDrops.put(new ItemStack(Material.BONE, rand.nextInt(3)+1), 3 );
        skeletonDrops.put(new ItemStack(Material.BUCKET, 1), 30 );

        HashMap<ItemStack, Integer> zombieDrops = new HashMap<ItemStack, Integer>();
        zombieDrops.put(new ItemStack(Material.REDSTONE, 2), 4 );
        zombieDrops.put(new ItemStack(Material.ROTTEN_FLESH, rand.nextInt(2)+1), 2 );
        zombieDrops.put(new ItemStack(Material.BOOK_AND_QUILL, 1), 80 );

        //mobDrops.put(EntityType.CREEPER, new HashMap<>)
    }

    @EventHandler
    private void onMobDeath(EntityDeathEvent e){
        if(e.getEntityType() == EntityType.PLAYER){
            return;
        }
        // delete drops
        if(e.getEntityType() == EntityType.PIG
                || e.getEntityType() == EntityType.COW
                || e.getEntityType() == EntityType.CHICKEN
                || e.getEntityType() == EntityType.SHEEP
                || e.getEntityType() == EntityType.RABBIT
                || e.getEntityType() == EntityType.MUSHROOM_COW
                || e.getEntityType() == EntityType.SQUID
                || e.getEntityType() == EntityType.SLIME
                || e.getEntityType() == EntityType.BAT
                || e.getEntityType() == EntityType.HORSE
            ){
                return;
        }
        e.getDrops().clear();
    }

}
