package net.latenighters.cits.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockMobEgg extends Block {
    private static final String[] hostileEntities = { // TODOCONFIG
            "minecraft:blaze",
            "minecraft:cave_spider",
            "minecraft:creeper",
            "minecraft:drowned",
            "minecraft:guardian",
            "minecraft:elder_guardian",
            "minecraft:enderman",
            "minecraft:ghast",
            "minecraft:hoglin",
            "minecraft:husk",
            "minecraft:illusioner",
            "minecraft:magma_cube",
            "minecraft:phantom",
            "minecraft:piglin",
            "minecraft:piglin_brute",
            "minecraft:pillager",
            "minecraft:shulker",
            "minecraft:silverfish",
            "minecraft:skeleton",
            "minecraft:slime",
            "minecraft:spider",
            "minecraft:stray",
            "minecraft:vindicator",
            "minecraft:witch",
            "minecraft:wither_skeleton",
            "minecraft:zoglin",
            "minecraft:zombie",
            "minecraft:zombie_villager",
            "minecraft:zombified_piglin"
    };

    public BlockMobEgg() {
        super(AbstractBlock.Properties.create(Material.DRAGON_EGG)
                .hardnessAndResistance(1f)
                .sound(SoundType.FUNGUS)
                .noDrops()
                .notSolid()
        );
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        Random random = worldIn.getRandom();
        CompoundNBT compoundNBT = new CompoundNBT();
        int idx = random.nextInt(hostileEntities.length);
        String entityString = hostileEntities[idx];
        compoundNBT.putString("id", entityString);
        Entity entity = EntityType.loadEntityAndExecute(compoundNBT, worldIn, (entity1) -> {
            entity1.setLocationAndAngles(pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, entity1.rotationYaw, entity1.rotationPitch);
            return entity1;
        });
        worldIn.addEntity(entity);
        MinecraftServer server = player.getServer();
        if (server != null && entity instanceof MobEntity) {
            ((MobEntity) entity).onInitialSpawn(server.getWorld(player.world.getDimensionKey()), worldIn.getDifficultyForLocation(entity.getPosition()),
                    SpawnReason.COMMAND,
                    (ILivingEntityData)null, (CompoundNBT)null
        );
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
}