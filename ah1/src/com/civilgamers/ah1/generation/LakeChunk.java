package com.civilgamers.ah1.generation;

import com.civilgamers.ah1.base.AH1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 14/03/2016.
 */
public class LakeChunk extends AHChunk {

    public LakeChunk(AH1 plugin, Player player) {
        super(plugin, player);

        ores.put(Material.STONE, 1);
        ores.put(Material.IRON_ORE, 900);
        ores.put(Material.GOLD_ORE, 2000);
        ores.put(Material.DIAMOND_ORE, 7500);
    }

    public boolean create(Material lakeType, Material surfaceType) {
        if(chunkExists()) {
            return false;
        }

        Location placeLocation = new Location(Bukkit.getServer().getWorld("world"), chunk.getBlock(0,0,0).getX(), 80, chunk.getBlock(0,0,0).getZ());

        chunkGeneration.buildBlockBody(placeLocation, surfaceType, ores);

        placeLocation.setY(80);
        chunkGeneration.placeMobs(placeLocation);
        chunkGeneration.placeGrass(placeLocation);
        chunkGeneration.placeFlowers(placeLocation);
        placeLocation.setY(79);
        chunkGeneration.createLake(placeLocation, lakeType, surfaceType);
        placeLocation.setY(80);
        chunkGeneration.placeTrees(placeLocation);
        chunkGeneration.placeBorderFences(placeLocation);

        registerChunk();
        return true;
    }
}
