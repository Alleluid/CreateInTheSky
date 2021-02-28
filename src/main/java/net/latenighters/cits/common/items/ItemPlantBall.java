package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;

import java.util.Random;
import java.util.Set;

public class ItemPlantBall extends Item {
    private static final Block[] saplingBlocks = { //TODOCONFIG
            Blocks.ACACIA_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.OAK_SAPLING,
            Blocks.DARK_OAK_SAPLING,
            Blocks.JUNGLE_SAPLING,
            Blocks.SPRUCE_SAPLING
    };
    public ItemPlantBall() {
        super(new Properties()
                .group(ModSetup.ITEM_GROUP)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        Random random = context.getWorld().getRandom();
        int idx = random.nextInt(saplingBlocks.length);

        BlockState blockState = context.getWorld().getBlockState(context.getPos());
        Set<ResourceLocation> tags = blockState.getBlock().getTags();
        ResourceLocation saplingsTag = new ResourceLocation("minecraft", "saplings");
        if (tags.contains(saplingsTag)) {
            context.getWorld().setBlockState(context.getPos(), saplingBlocks[idx].getDefaultState(), 3);
            context.getItem().shrink(1);
        }
        return super.onItemUse(context);
    }
}
