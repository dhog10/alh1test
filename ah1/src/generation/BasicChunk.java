package generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Daniel on 13/03/2016.
 */
public class BasicChunk {

    Location placeLocation;
    Random rand = new Random();

    public void create(Player player, int posX, int posZ) {
        placeLocation = new Location(Bukkit.getServer().getWorld("world"), player.getLocation().getChunk().getBlock(0,0,0).getX(), 80, player.getLocation().getChunk().getBlock(0,0,0).getZ());
        for(int y = 80; y > 0; y--){
            placeLocation.setY(y);
            if(y == 80) {
                placeLayer(Material.GRASS, placeLocation);
            }

            if(y < 80 && y > 70) {
                placeLayer(Material.DIRT, placeLocation);
            }

            if(y <= 70) {
                placeLayer(Material.STONE, placeLocation);
                placeIron(placeLocation);
                placeGold(placeLocation);
            }
        }
    }

    public void placeLayer(Material material, Location loc) {

        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            initialLoc.setX(initialLoc.getX() + 1);
            for (int z = 0; z < 16; z++) {
                initialLoc.setZ(initialLoc.getZ() + 1);
                initialLoc.getBlock().setType(material);
            }
        }
    }

    public void placeIron(Location loc){
        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            initialLoc.setX(initialLoc.getX() + 1);
            for (int z = 0; z < 16; z++) {
                if(rand.nextInt(900) == 50) {
                    initialLoc.setZ(initialLoc.getZ() + 1);
                    initialLoc.getBlock().setType(Material.IRON_ORE);
                }
            }
        }
    }

    public void placeGold(Location loc){
        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());

        for(int x = 0; x < 16; x++) {
            initialLoc.setX(initialLoc.getX() + 1);
            for (int z = 0; z < 16; z++) {
                if(rand.nextInt(2000) == 50) {
                    initialLoc.setZ(initialLoc.getZ() + 1);
                    initialLoc.getBlock().setType(Material.GOLD_ORE);
                }
            }
        }
    }

}
