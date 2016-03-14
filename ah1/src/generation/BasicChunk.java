package generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Daniel on 13/03/2016.
 */
public class BasicChunk {

    Random rand = new Random();
    Material surfaceType = Material.GRASS;
    ChunkGeneration chunkGeneration = new ChunkGeneration();

    public void create(Player player) {
        int startX = player.getLocation().getChunk().getBlock(0,0,0).getX();
        int startZ = player.getLocation().getChunk().getBlock(0,0,0).getZ();
        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"),startX, 80, startZ);

        chunkGeneration.buildBlockBody(placeLocation, surfaceType);

        placeLocation.setY(80);
        chunkGeneration.placeMobs(placeLocation);
        chunkGeneration.placeGrass(placeLocation);
        chunkGeneration.placeFlowers(placeLocation);
        placeLocation.setY(79);
        placeLocation.setY(80);
        chunkGeneration.placeTrees(placeLocation);
    }

    public void create(Player player, Material surfaceType) {
        int startX = player.getLocation().getChunk().getBlock(0,0,0).getX();
        int startZ = player.getLocation().getChunk().getBlock(0,0,0).getZ();
        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"),startX, 80, startZ);

        // set lake type
        this.surfaceType = surfaceType;

        chunkGeneration.buildBlockBody(placeLocation, surfaceType);

        placeLocation.setY(80);
        chunkGeneration.placeGrass(placeLocation);
        chunkGeneration.placeFlowers(placeLocation);
        placeLocation.setY(79);
        placeLocation.setY(80);
        chunkGeneration.placeTrees(placeLocation);
    }

}
