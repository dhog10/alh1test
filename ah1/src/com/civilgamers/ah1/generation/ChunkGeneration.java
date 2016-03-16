package com.civilgamers.ah1.generation;

import com.civilgamers.ah1.base.AH1;
import com.civilgamers.ah1.base.Util;
import org.bukkit.*;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Daniel on 14/03/2016.
 */
public class ChunkGeneration {

    private Random rand = new Random();
    private AH1 plugin;

    public ChunkGeneration(AH1 ah1){
        this.plugin = ah1;
    }

    void buildBlockBody(Location loc, Material surfaceType, HashMap<Material, Integer> ores){
        for(int y = 80; y > 0; y--){
            loc.setY(y);

            if(y == 79) {
                placeLayer(loc, Material.GRASS, 1);
            }

            if(y < 79 && y >= 76) {
                placeLayer(loc, Material.DIRT, 1);
                placeLayer(loc, Material.SAND, 6);
            }

            if(y <= 75 && y > 1) {
                for(Material m: ores.keySet()){
                    placeLayer(loc, m, ores.get(m));
                }
            }
            if(y == 2){
                placeLayer(loc, Material.BEDROCK, 2);
            }
            if(y == 1){
                placeLayer(loc, Material.BEDROCK, 1);
            }
        }
    }

    void placeLayer(Location loc, Material oreType, int spawnChance){
        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                if(rand.nextInt(spawnChance) == 0) {
                    if(oreType == Material.SAND){
                        if(x != 0 && x != 15 && z != 0 && z != 15){
                            initialLoc.getBlock().setType(oreType);
                        }
                    }else{
                        initialLoc.getBlock().setType(oreType);
                    }
                }
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    void placeGrass(Location loc){
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                if(rand.nextInt(10) == 1) {
                    initialLoc.getBlock().setType(Material.LONG_GRASS);
                    initialLoc.getBlock().setData((byte)1);
                }
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    void placeFlowers(Location loc){
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                if(rand.nextInt(35) == 1) {
                    switch(rand.nextInt(2)) {
                        case 0:
                            initialLoc.getBlock().setType(Material.RED_ROSE);
                            break;
                        case 1:
                            initialLoc.getBlock().setType(Material.YELLOW_FLOWER);
                            break;
                        default:
                            break;
                    }
                }
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    private int numOfTrees;
    void placeTrees(Location loc){
        Location tempTreeLocation;
        if(rand.nextInt(11) == 1){
            int numOfTrees = 3;
            tempTreeLocation = loc.clone();
            tempTreeLocation.setX(tempTreeLocation.getX() + rand.nextInt(7) + 5);
            tempTreeLocation.setZ(tempTreeLocation.getZ() + rand.nextInt(7) + 5);
            Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.JUNGLE);
            for(int i = 0; i <= numOfTrees; i++){
                while(true) {
                    tempTreeLocation = loc.clone();
                    tempTreeLocation.setX(tempTreeLocation.getX() + rand.nextInt(13) + 2);
                    tempTreeLocation.setZ(tempTreeLocation.getZ() + rand.nextInt(13) + 2);
                    if(tempTreeLocation.getBlock().getType() == Material.AIR || tempTreeLocation.getBlock().getType() == Material.LONG_GRASS || tempTreeLocation.getBlock().getType() == Material.RED_ROSE){
                        break;
                    }
                }
                if(rand.nextInt(4) == 1){
                    Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.BIRCH);
                }else {
                    Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.TREE);
                }
            }
        }else{
            numOfTrees = rand.nextInt(2) + 5;
            for(int i = 0; i <= numOfTrees; i++){
                tempTreeLocation = loc.clone();
                tempTreeLocation.setX(tempTreeLocation.getX() + rand.nextInt(13) + 2);
                tempTreeLocation.setZ(tempTreeLocation.getZ() + rand.nextInt(13) + 2);
                if(rand.nextInt(4) == 1){
                    Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.BIRCH);
                }else {
                    Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.TREE);
                }
            }
        }

    }

    private int lakeWidthX, lakeWidthZ;
    private int lakeStartX, lakeStartZ;
    void createLake(Location loc, Material lakeType, Material surfaceType){
        Location tempLakeLocation = loc.clone();
        tempLakeLocation.setY(79);
        lakeWidthX = rand.nextInt(4) + 4;
        lakeWidthZ = rand.nextInt(4) + 4;

        lakeStartX = rand.nextInt(3) + 3;
        lakeStartZ = rand.nextInt(3) + 3;

        for(int x = 0; x < lakeWidthX; x++) {
            for (int z = 0; z < lakeWidthZ; z++) {
                tempLakeLocation = loc.clone();
                tempLakeLocation.setX((tempLakeLocation.getX() + x) + lakeStartX);
                tempLakeLocation.setZ((tempLakeLocation.getZ() + z) + lakeStartZ);
                if(!(x == 0 && z == 0) && !(x == 0 && z == lakeWidthZ - 1) && !(x == lakeWidthX - 1 && z == 0) && !(x == lakeWidthX - 1 && z == lakeWidthZ - 1)){
                    if((x == 1 && z == 0) || (x == 0 && z == 1) || (x == 1 && z == lakeWidthZ - 1) || (x == 0 && z == lakeWidthZ - 2) ||(x == lakeWidthX - 1 && z == 1) || (x == lakeWidthX - 2 && z == 0) || (x == lakeWidthX - 2 && z == lakeWidthZ - 1) || (x == lakeWidthX - 1 && z == lakeWidthZ -2)){
                        // random corner blocks
                        if(rand.nextInt(2) == 1){
                            tempLakeLocation.getBlock().setType(lakeType);
                        }else{
                            if(lakeType == Material.LAVA){
                                tempLakeLocation.getBlock().setType(Material.STONE);
                            }else {
                                placeReed(tempLakeLocation, surfaceType);
                            }
                        }
                    }else{
                        // normal lake blocks
                        tempLakeLocation.getBlock().setType(lakeType);
                        // place water lily
                        if(rand.nextInt(7) == 1 && lakeType != Material.LAVA){
                            tempLakeLocation.setY(tempLakeLocation.getY() + 1);
                            tempLakeLocation.getBlock().setType(Material.WATER_LILY);
                        }
                        // border stone if lava
                        if(lakeType == Material.LAVA){
                            if(x == 0){
                                Location stonePlaceLocation = tempLakeLocation.clone();
                                stonePlaceLocation.setX(stonePlaceLocation.getX() + 1);
                                tempLakeLocation.getBlock().setType(Material.STONE);
                            }
                            if (z == 0){
                                Location stonePlaceLocation = tempLakeLocation.clone();
                                stonePlaceLocation.setZ(stonePlaceLocation.getZ() + 1);
                                tempLakeLocation.getBlock().setType(Material.STONE);
                            }
                            if(x == lakeWidthX - 1){
                                Location stonePlaceLocation = tempLakeLocation.clone();
                                stonePlaceLocation.setX(stonePlaceLocation.getX() - 1);
                                tempLakeLocation.getBlock().setType(Material.STONE);
                            }
                            if (z == lakeWidthZ - 1){
                                Location stonePlaceLocation = tempLakeLocation.clone();
                                stonePlaceLocation.setZ(stonePlaceLocation.getZ() - 1);
                                tempLakeLocation.getBlock().setType(Material.STONE);
                            }
                        }
                    }
                }
            }
        }
    }

    void placeReed(Location placeReedLocation, Material surfaceType){
        int caneHeight = rand.nextInt(2) + 3;
        for(int i = 0; i < caneHeight; i++){
            Location tempReedLoc = placeReedLocation.clone();
            tempReedLoc.setY(tempReedLoc.getY() + i);
            if(i == 0){
                tempReedLoc.getBlock().setType(surfaceType);
            }else {
                tempReedLoc.getBlock().setType(Material.SUGAR_CANE_BLOCK);
            }
        }
    }

    private Location tempMobSpawnLocation;
    private int mobNumber;
    private int amountOfMobs;
    void placeMobs(Location loc){
        amountOfMobs = rand.nextInt(2) + 6;
        for(int i = 0; i < amountOfMobs; i++) {
            int counter = 0;
            while(true){
                tempMobSpawnLocation = loc.clone();
                tempMobSpawnLocation.setX(tempMobSpawnLocation.getX() + rand.nextInt(11) + 3);
                tempMobSpawnLocation.setZ(tempMobSpawnLocation.getZ() + rand.nextInt(11) + 3);
                counter++;
                if(tempMobSpawnLocation.getBlock().getType() == Material.AIR){
                    Location tempMobSpawnLocation2 = tempMobSpawnLocation.clone();
                    tempMobSpawnLocation2.setY(tempMobSpawnLocation2.getY() + 1);
                    if(tempMobSpawnLocation2.getBlock().getType() == Material.AIR){
                        break;
                    }
                }
                if(counter > 50){
                    break;
                }
            }

            mobNumber = rand.nextInt(100);
            if(mobNumber  <= 10){
                Bukkit.getWorld("world").spawnEntity(tempMobSpawnLocation, EntityType.PIG);
            }else if(mobNumber <=20){
                if(rand.nextInt(25) == 1){
                    Bukkit.getWorld("world").spawnEntity(tempMobSpawnLocation, EntityType.MUSHROOM_COW);
                }else {
                    Bukkit.getWorld("world").spawnEntity(tempMobSpawnLocation, EntityType.COW);
                }
            }else if(mobNumber <=30) {
                Bukkit.getWorld("world").spawnEntity(tempMobSpawnLocation, EntityType.SHEEP);
            }else{
                Bukkit.getWorld("world").spawnEntity(tempMobSpawnLocation, EntityType.CHICKEN);
            }
        }
    }

    public void placeBorderFences(Location loc){
        Location tempFenceLocation = loc.clone();
        Chunk tempChunk = tempFenceLocation.getChunk();

        Util.broadcast("Place fences");

        if(!plugin.getChunkStorage().existsInDatabase((Integer.toString(tempChunk.getX() + 1)) + ":" + Integer.toString(tempChunk.getZ()))){
            Util.broadcast("Place fences X + 1");
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setX(tempFenceLocation.getX() + 15);
                tempFenceLocation.setZ(tempFenceLocation.getZ() + i + 1);
                tempFenceLocation.getBlock().setType(Material.FENCE);
            }
        }else{
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setX(tempFenceLocation.getX() + 16);
                tempFenceLocation.setZ(tempFenceLocation.getZ() + i + 1);
                if(tempFenceLocation.getBlock().getType() == Material.FENCE && (i != 0 && i != 15)){
                    tempFenceLocation.getBlock().setType(Material.AIR);
                }
            }
        }

        if(!plugin.getChunkStorage().existsInDatabase((Integer.toString(tempChunk.getX() - 1)) + ":" + Integer.toString(tempChunk.getZ()))){
            Util.broadcast("Place fences X - 1");
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setZ(tempFenceLocation.getZ() + i + 1);
                tempFenceLocation.getBlock().setType(Material.FENCE);
            }
        }else{
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setX(tempFenceLocation.getX() - 1);
                tempFenceLocation.setZ(tempFenceLocation.getZ() + i + 1);
                if(tempFenceLocation.getBlock().getType() == Material.FENCE && (i != 0 && i != 15)){
                    tempFenceLocation.getBlock().setType(Material.AIR);
                }
            }
        }

        if(!plugin.getChunkStorage().existsInDatabase((Integer.toString(tempChunk.getX())) + ":" + Integer.toString(tempChunk.getZ() + 1))){
            Util.broadcast("Place fences Y + 1");
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setZ(tempFenceLocation.getZ() + 15 + 1);
                tempFenceLocation.setX(tempFenceLocation.getX() + i);
                tempFenceLocation.getBlock().setType(Material.FENCE);
            }
        }else{
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setZ(tempFenceLocation.getZ() + 16 + 1);
                tempFenceLocation.setX(tempFenceLocation.getX() + i);
                if(tempFenceLocation.getBlock().getType() == Material.FENCE && (i != 0 && i != 15)){
                    tempFenceLocation.getBlock().setType(Material.AIR);
                }
            }
        }

        if(!plugin.getChunkStorage().existsInDatabase((Integer.toString(tempChunk.getX())) + ":" + Integer.toString(tempChunk.getZ() - 1))){
            Util.broadcast("Place fences Y - 1");
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setZ(tempFenceLocation.getZ() + 1);
                tempFenceLocation.setX(tempFenceLocation.getX() + i);
                tempFenceLocation.getBlock().setType(Material.FENCE);
            }
        }else{
            for(int i = 0; i < 16; i++){
                tempFenceLocation = loc.clone();
                tempFenceLocation.setZ(tempFenceLocation.getZ() - 1 + 1);
                tempFenceLocation.setX(tempFenceLocation.getX() + i);
                if(tempFenceLocation.getBlock().getType() == Material.FENCE && (i != 0 && i != 15)){
                    tempFenceLocation.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
