package generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Daniel on 14/03/2016.
 */
public class LakeChunk {

    Random rand = new Random();
    Material surfaceType = Material.GRASS;
    Material lakeType;

    public void create(Player player) {
        int startX = player.getLocation().getChunk().getBlock(0,0,0).getX();
        int startZ = player.getLocation().getChunk().getBlock(0,0,0).getZ();
        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"),startX, 80, startZ);

        // set lake type
        if(rand.nextInt(10) == 1){
            lakeType = Material.LAVA;
        }else{
            lakeType = Material.WATER;
        }

        buildBlockBody(placeLocation);

        placeLocation.setY(80);
        placeMobs(placeLocation);
        placeGrass(placeLocation);
        placeFlowers(placeLocation);
        placeLocation.setY(79);
        createLake(placeLocation);
        placeLocation.setY(80);
        placeTrees(placeLocation);
    }

    public void create(Player player, Material lakeType) {
        int startX = player.getLocation().getChunk().getBlock(0,0,0).getX();
        int startZ = player.getLocation().getChunk().getBlock(0,0,0).getZ();
        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"),startX, 80, startZ);

        // set lake type
        this.lakeType = lakeType;

        buildBlockBody(placeLocation);

        placeLocation.setY(80);
        placeGrass(placeLocation);
        placeFlowers(placeLocation);
        placeLocation.setY(79);
        createLake(placeLocation);
        placeLocation.setY(80);
        placeTrees(placeLocation);
    }

    private void buildBlockBody(Location loc){
        for(int y = 80; y > 0; y--){
            loc.setY(y);

            if(y == 79) {
                placeLayer(surfaceType, loc);
            }

            if(y < 79 && y > 72) {
                placeLayer(Material.DIRT, loc);
            }

            if(y <= 72) {
                placeLayer(Material.STONE, loc);
                placeIron(loc);
                placeGold(loc);
            }
        }
    }

    private void placeLayer(Material material, Location loc) {

        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                initialLoc.getBlock().setType(material);
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    private void placeIron(Location loc){
        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                if(rand.nextInt(900) == 50) {
                    initialLoc.getBlock().setType(Material.IRON_ORE);
                }
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    private void placeGold(Location loc){
        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                if(rand.nextInt(2000) == 50) {
                    initialLoc.getBlock().setType(Material.GOLD_ORE);
                }
            }
            initialLoc.setX(initialLoc.getX() + 1);
            initialLoc.setZ(loc.getZ());
        }
    }

    private void placeGrass(Location loc){
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

    private void placeFlowers(Location loc){
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

    private void placeTrees(Location loc){
        Location tempTreeLocation;
        int numOfTrees = rand.nextInt(2) + 4;
        for(int i = 0; i <= numOfTrees; i++){
            tempTreeLocation = loc.clone();
            tempTreeLocation.setX(tempTreeLocation.getX() + rand.nextInt(13) + 2);
            tempTreeLocation.setZ(tempTreeLocation.getZ() + rand.nextInt(13) + 2);
            Bukkit.getWorld("world").generateTree(tempTreeLocation, TreeType.TREE);
        }
    }

    private int lakeWidthX, lakeWidthZ;
    private int lakeStartX, lakeStartZ;
    private void createLake(Location loc){
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
                                placeReed(tempLakeLocation);
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

    private void placeReed(Location placeReedLocation){
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
    private void placeMobs(Location loc){
        amountOfMobs = rand.nextInt(2) + 6;
        for(int i = 0; i < amountOfMobs; i++) {
            int counter = 0;
            while(true){
                tempMobSpawnLocation = loc.clone();
                tempMobSpawnLocation.setX(tempMobSpawnLocation.getX() + rand.nextInt(11) + 3);
                tempMobSpawnLocation.setZ(tempMobSpawnLocation.getZ() + rand.nextInt(11) + 3);
                counter++;
                if(tempMobSpawnLocation.getBlock().getType() == Material.AIR){
                    break;
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
}
