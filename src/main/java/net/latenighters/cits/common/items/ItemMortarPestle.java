package net.latenighters.cits.common.items;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import net.latenighters.cits.ModSetup;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import java.util.Optional;

public class ItemMortarPestle extends Item {
    public ItemMortarPestle() {
        super(new Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        // TODO: Polish this up to be like sandpaper.

        Hand otherHand = handIn == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack itemInOtherHand = playerIn.getHeldItem(otherHand);

        boolean recipeResult = preformRecipe(worldIn, playerIn, itemInOtherHand.getItem());
        if (recipeResult)
            itemInOtherHand.shrink(1);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ActionResultType onItemUse(@Nonnull ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState blockState = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();

        boolean recipeResult = preformRecipe(context);

        if (recipeResult && blockState.getBlockHardness(world, pos) != -1.0){
            BlockEvent.BreakEvent e = new BlockEvent.BreakEvent(world, pos, blockState, player);
            if (!MinecraftForge.EVENT_BUS.post(e)) {
                if (player != null) {
                    blockState.getBlock().onBlockHarvested(world, pos, blockState, player);
                }
                world.removeBlock(pos, false);
            }
        }
        return super.onItemUse(context);
    }

    private boolean preformRecipe(World worldIn, PlayerEntity playerIn, Item inputItem) {
        RecipeWrapper recipeWrapper = new MillingInv(inputItem);
        Optional<MillingRecipe> recipe = AllRecipeTypes.MILLING.find(recipeWrapper, worldIn);
        if (recipe.isPresent()) {
            if (!worldIn.isRemote) {
                recipe.get().rollResults().forEach((item) -> {
                    ItemEntity itemEntity = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY() + 0.1, playerIn.getPosZ(), item);
                    itemEntity.setPickupDelay(0);
                    worldIn.addEntity(itemEntity);
                });
            }
            return true;
        }
        return false;
    }

    private boolean preformRecipe(ItemUseContext context) {
        return preformRecipe(context.getWorld(), context.getPlayer(), context.getWorld().getBlockState(context.getPos()).getBlock().asItem());
    }

    private static class MillingInv extends RecipeWrapper {
        public MillingInv(Item item) {
            super(new ItemStackHandler(1));
            inv.setStackInSlot(0, new ItemStack(item));
        }
    }
}
