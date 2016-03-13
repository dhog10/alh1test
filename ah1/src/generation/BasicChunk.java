package generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 13/03/2016.
 */
public class BasicChunk {

    Location placeLocation;

    public void create(Player player, int posX, int posZ) {
        placeLocation = new Location(Bukkit.getServer().getWorld("world"), player.getLocation().getChunk().getBlock(0,0,0).getX(), 80, player.getLocation().getChunk().getBlock(0,0,0).getZ());
        for(int y = 80; y > 0; y--){
            if(y == 80) {
                placeLayer(Material.GRASS, placeLocation);
            }

            if(y < 80 && y > 70) {
                placeLayer(Material.DIRT, placeLocation);
            }

            if(y <= 70) {
                placeLayer(Material.STONE, placeLocation);
            }
        }
    }

    public void placeLayer(Material material, Location loc) {

        //generate layer
        Location initialLoc = new Location(Bukkit.getServer().getWorld("world"), loc.getX(), loc.getY(), loc.getZ());
        Location tempLoc = initialLoc;

        for(int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                tempLoc.setX(initialLoc.getX() + x);
                tempLoc.setZ(initialLoc.getZ() + z);
                loc.getBlock().setType(material);
            }
        }
    }

}
