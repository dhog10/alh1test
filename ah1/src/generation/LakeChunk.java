package generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Daniel on 14/03/2016.
 */
public class LakeChunk {

    private Random rand = new Random();
    private ChunkGeneration chunkGeneration = new ChunkGeneration();
    private HashMap<Material, Integer> ores = new HashMap<Material, Integer>();

    public LakeChunk(){
        ores.put(Material.STONE, 1);
        ores.put(Material.IRON_ORE, 900);
        ores.put(Material.GOLD_ORE, 2000);
        ores.put(Material.DIAMOND_ORE, 7500);
    }

    public void create(Player player, Material lakeType, Material surfaceType) {
        int startX = player.getLocation().getChunk().getBlock(0,0,0).getX();
        int startZ = player.getLocation().getChunk().getBlock(0,0,0).getZ();
        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"),startX, 80, startZ);

        chunkGeneration.buildBlockBody(placeLocation, surfaceType, ores);

        placeLocation.setY(80);
        chunkGeneration.placeGrass(placeLocation);
        chunkGeneration.placeFlowers(placeLocation);
        placeLocation.setY(79);
        chunkGeneration.createLake(placeLocation, lakeType, surfaceType);
        placeLocation.setY(80);
        chunkGeneration.placeTrees(placeLocation);
    }
}
