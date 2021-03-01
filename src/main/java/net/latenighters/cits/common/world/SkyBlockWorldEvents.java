package net.latenighters.cits.common.world;

import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SkyBlockWorldEvents {
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        World world = event.getPlayer().world;
        if (SkyBlockChunkGenerator.isWorldSkyblock(world)) {
            // island gen code here
        }
    }
}
