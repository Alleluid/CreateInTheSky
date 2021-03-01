package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemSkyTool extends PickaxeItem {
    static IItemTier skyTier = new IItemTier() {
        @Override
        public int getMaxUses() {
            return 1300;
        }

        @Override
        public float getEfficiency() {
            return 4.0f;
        }

        @Override
        public float getAttackDamage() {
            return 2.0f;
        }

        @Override
        public int getHarvestLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 5;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS);
        }
    };


    public ItemSkyTool() {
        super(skyTier, 1, -2.8F, (new Item.Properties()).group(ModSetup.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(@Nullable ItemStack stack, @Nullable BlockState state) {
        if (state != null) {
            if (state.getMaterial() == Material.LEAVES) { // Instantly break leaves.
                return 20f;
            } else if (state.getMaterial() == Material.WOOD) { // Normal speed to break wood.
                return skyTier.getEfficiency();
            }
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F  && state.getMaterial() != Material.LEAVES) { // Tool is not damaged by breaking leaves.
            stack.damageItem(1, entityLiving, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState blockState = context.getWorld().getBlockState(context.getPos());
        if (blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
            context.getWorld().removeBlock(context.getPos(), false);
            context.getWorld().playEvent(2001, context.getPos(), Block.getStateId(blockState));
        } else if (blockState.getBlock() == Blocks.OBSIDIAN) {
            context.getWorld().setBlockState(context.getPos(), Blocks.LAVA.getDefaultState(), 3);
            context.getWorld().playEvent(2001, context.getPos(), Block.getStateId(blockState));
        }
        return super.onItemUse(context);
    }
}
