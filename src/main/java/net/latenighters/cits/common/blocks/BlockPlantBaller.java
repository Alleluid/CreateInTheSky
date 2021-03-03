package net.latenighters.cits.common.blocks;

import net.latenighters.cits.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockPlantBaller extends ComposterBlock {
    public BlockPlantBaller() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(1f)
                .sound(SoundType.SCAFFOLDING)
                .notSolid()
        );
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int i = state.get(LEVEL);
        ItemStack itemstack = player.getHeldItem(handIn);
        if (i < 8 && CHANCES.containsKey(itemstack.getItem())) {
            if (i < 7 && !worldIn.isRemote) {
                BlockState blockstate = attemptCompost(state, worldIn, pos, itemstack);
                worldIn.playEvent(1500, pos, state != blockstate ? 1 : 0);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else if (i == 8) {
            empty(state, worldIn, pos);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }

    private static BlockState attemptCompost(BlockState state, IWorld world, BlockPos pos, ItemStack stack) {
        int i = state.get(LEVEL);
        float f = CHANCES.getFloat(stack.getItem());
        if ((i != 0 || !(f > 0.0F)) && !(world.getRandom().nextDouble() < (double)f)) {
            return state;
        } else {
            int j = i + 1;
            BlockState blockstate = state.with(LEVEL, Integer.valueOf(j));
            world.setBlockState(pos, blockstate, 3);
            if (j == 7) {
                world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 20);
            }

            return blockstate;
        }
    }


    public static BlockState empty(BlockState state, World world, BlockPos pos) {
        if (!world.isRemote) {
            float f = 0.7F;
            double d0 = (double)(world.rand.nextFloat() * 0.7F) + (double)0.15F;
            double d1 = (double)(world.rand.nextFloat() * 0.7F) + (double)0.060000002F + 0.6D;
            double d2 = (double)(world.rand.nextFloat() * 0.7F) + (double)0.15F;
            ItemEntity itementity = new ItemEntity(world, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, new ItemStack(Registration.PLANT_BALL.get()));
            itementity.setDefaultPickupDelay();
            world.addEntity(itementity);
        }

        BlockState blockstate = resetFillState(state, world, pos);
        world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        return blockstate;
    }

    private static BlockState resetFillState(BlockState state, IWorld world, BlockPos pos) {
        BlockState blockstate = state.with(LEVEL, Integer.valueOf(0));
        world.setBlockState(pos, blockstate, 3);
        return blockstate;
    }
}
