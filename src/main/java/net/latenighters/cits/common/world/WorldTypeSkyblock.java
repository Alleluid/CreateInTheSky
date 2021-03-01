package net.latenighters.cits.common.world;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraftforge.common.world.ForgeWorldType;

import javax.annotation.Nonnull;

public class WorldTypeSkyblock extends ForgeWorldType {

    public WorldTypeSkyblock() {
        super(WorldTypeSkyblock::getChunkGenerator);
    }

    private static ChunkGenerator getChunkGenerator(@Nonnull Registry<Biome> biomes, @Nonnull Registry<DimensionSettings> dimensionSettings, long seed) {
        return new SkyBlockChunkGenerator(new OverworldBiomeProvider(seed, false, false, biomes), seed,
                () -> dimensionSettings.getOrThrow(DimensionSettings.field_242734_c));
    }

    @Override
    public String getTranslationKey() {
        return "generator.cits";
    }
}
