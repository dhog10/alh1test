package generation;

import com.civilgamers.ah1.base.AH1;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class AHChunk {
    protected AH1 plugin;
    protected UUID player;

    protected Random rand;
    protected ChunkGeneration chunkGeneration;
    protected HashMap<Material, Integer> ores;

    protected Chunk chunk;

    public AHChunk(AH1 plugin, Player player) {
        this.plugin = plugin;
        this.player = player.getUniqueId();
        this.rand = new Random();
        this.chunkGeneration = new ChunkGeneration();
        this.ores = new HashMap<Material, Integer>();

        this.chunk = player.getLocation().getChunk();
    }

    public boolean chunkExists() {
        return plugin.getChunkStorage().existsInDatabase(plugin.getChunkStorage().chunkToString(chunk.getX(), chunk.getZ()));
    }

    public void registerChunk() {
        plugin.getChunkStorage().setOwner(plugin.getChunkStorage().chunkToString(chunk.getX(), chunk.getZ()), player.toString());
    }

    public OfflinePlayer getPlayer() {
        return plugin.getServer().getOfflinePlayer(player);
    }

}
