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
        creeperDrops.put(new ItemStack(Material.SAPLING, 1), 2 );

        HashMap<ItemStack, Integer> spiderDrops = new HashMap<ItemStack, Integer>();
        spiderDrops.put(new ItemStack(Material.REDSTONE, 3), 5 );
        spiderDrops.put(new ItemStack(Material.WEB, 1), 6 );
        spiderDrops.put(new ItemStack(Material.WEB, 2), 6 );
        spiderDrops.put(new ItemStack(Material.REDSTONE_BLOCK, 1), 60 );
        spiderDrops.put(new ItemStack(Material.COOKIE, 1), 60 );
        spiderDrops.put(new ItemStack(Material.SEEDS, 1), 20 );
        spiderDrops.put(new ItemStack(Material.STRING, 2), 12 );
        spiderDrops.put(new ItemStack(Material.POTATO, 1), 50 );
        spiderDrops.put(new ItemStack(Material.CARROT, 1), 50 );

        HashMap<ItemStack, Integer> skeletonDrops = new HashMap<ItemStack, Integer>();
        skeletonDrops.put(new ItemStack(Material.REDSTONE, 1), 10 );
        skeletonDrops.put(new ItemStack(Material.BONE, 2), 5 );
        skeletonDrops.put(new ItemStack(Material.BONE, 3), 5 );
        skeletonDrops.put(new ItemStack(Material.BUCKET, 1), 35 );
        skeletonDrops.put(new ItemStack(Material.IRON_SWORD, 1), 40 );
        skeletonDrops.put(new ItemStack(Material.DIAMOND_SWORD, 1), 150 );
        skeletonDrops.put(new ItemStack(Material.IRON_SPADE, 1), 30 );
        skeletonDrops.put(new ItemStack(Material.IRON_AXE, 1), 40 );
        skeletonDrops.put(new ItemStack(Material.ARROW, 3), 7 );
        skeletonDrops.put(new ItemStack(Material.BOW, 1), 12 );
        skeletonDrops.put(new ItemStack(Material.STRING, 2), 12 );

        HashMap<ItemStack, Integer> zombieDrops = new HashMap<ItemStack, Integer>();
        zombieDrops.put(new ItemStack(Material.REDSTONE, 2), 4 );
        zombieDrops.put(new ItemStack(Material.ROTTEN_FLESH, 2), 2 );
        zombieDrops.put(new ItemStack(Material.BOOK_AND_QUILL, 1), 80 );
        zombieDrops.put(new ItemStack(Material.SAPLING , 1), 10 );
        zombieDrops.put(new ItemStack(Material.IRON_SPADE, 1), 30 );
        zombieDrops.put(new ItemStack(Material.IRON_AXE, 1), 35 );
        zombieDrops.put(new ItemStack(Material.PUMPKIN, 1), 60 );
        zombieDrops.put(new ItemStack(Material.PUMPKIN_PIE, 1), 80 );
        zombieDrops.put(new ItemStack(Material.BREAD, 1), 20 );
        zombieDrops.put(new ItemStack(Material.WHEAT, 1), 15 );
        zombieDrops.put(new ItemStack(Material.FLOWER_POT_ITEM, 1), 65 );
        zombieDrops.put(new ItemStack(Material.APPLE, 1), 17 );
        zombieDrops.put(new ItemStack(Material.SEEDS, 1), 20 );
        zombieDrops.put(new ItemStack(Material.POTATO, 1), 50 );
        zombieDrops.put(new ItemStack(Material.CARROT, 1), 50 );

        mobDrops.put(EntityType.CREEPER, creeperDrops);
        mobDrops.put(EntityType.SKELETON, skeletonDrops);
        mobDrops.put(EntityType.ZOMBIE, zombieDrops);
        mobDrops.put(EntityType.SPIDER, spiderDrops);
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

        HashMap<ItemStack, Integer> drops = mobDrops.get(e.getEntityType());

        for(ItemStack i : drops.keySet()) {
            if(rand.nextInt(drops.get(i)) == 0){
                Bukkit.getWorld("world").dropItemNaturally(e.getEntity().getLocation(), i);
            }
        }
    }
}
