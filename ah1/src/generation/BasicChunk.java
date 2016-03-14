package generation;

import com.civilgamers.ah1.base.AH1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Daniel on 13/03/2016.
 */
public class BasicChunk extends AHChunk {

    public BasicChunk(AH1 plugin, Player player){
        super(plugin, player);

        ores.put(Material.STONE, 1);
        ores.put(Material.IRON_ORE, 900);
        ores.put(Material.GOLD_ORE, 2000);
        ores.put(Material.DIAMOND_ORE, 7500);
    }

    public boolean create(Material surfaceType) {
        if(chunkExists()) {
            return false;
        }

        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"), chunk.getBlock(0,0,0).getX(), 80, chunk.getBlock(0,0,0).getZ());

        chunkGeneration.buildBlockBody(placeLocation, surfaceType, ores);

        placeLocation.setY(80);
        chunkGeneration.placeMobs(placeLocation);
        chunkGeneration.placeGrass(placeLocation);
        chunkGeneration.placeFlowers(placeLocation);
        chunkGeneration.placeTrees(placeLocation);

        registerChunk();
        return true;
    }
}
