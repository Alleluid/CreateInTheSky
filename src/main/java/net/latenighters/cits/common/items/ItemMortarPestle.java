package net.latenighters.cits.common.items;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import net.latenighters.cits.ModSetup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

public class ItemMortarPestle extends Item {

    public ItemMortarPestle() {
        super(new Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1));
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        // TODO: Polish this up to be like sandpaper.

        ItemStack itemInOffHand = playerIn.getHeldItem(Hand.OFF_HAND);

        boolean recipeResult = preformRecipe(worldIn, playerIn, itemInOffHand);
        if (recipeResult)
            itemInOffHand.setCount(0);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public boolean preformRecipe(World worldIn, PlayerEntity playerIn, ItemStack inputStack) {
        NonNullList<ItemStack> recipeResultItems = NonNullList.create();
        RecipeWrapper recipeWrapper = new MillingInv(inputStack);
        Optional<MillingRecipe> recipe = AllRecipeTypes.MILLING.find(recipeWrapper, worldIn);
        if (recipe.isPresent()) {
            if (!worldIn.isRemote) {
                for (int i=0; i < inputStack.getCount(); i++) {
                    addItemsToList(recipe.get().rollResults(), recipeResultItems);
                }

                recipeResultItems.forEach((item) -> {
                    ItemEntity itemEntity = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY() + 0.1, playerIn.getPosZ(), item);
                    itemEntity.setPickupDelay(0);
                    worldIn.addEntity(itemEntity);
                });
                recipeResultItems.clear();
            }
            return true;
        }
        return false;
    }

    public boolean preformRecipe(World worldIn, PlayerEntity playerIn, Item inputItem){
        return preformRecipe(worldIn, playerIn, new ItemStack(inputItem));
    }

    private boolean preformRecipe(ItemUseContext context) {
        return preformRecipe(context.getWorld(), context.getPlayer(), context.getWorld().getBlockState(context.getPos()).getBlock().asItem());
    }

    private static class MillingInv extends RecipeWrapper {
        public MillingInv(ItemStack stack) {
            super(new ItemStackHandler(1));
            inv.setStackInSlot(0, stack);
        }
    }

    private void addItemsToList(Collection<ItemStack> items, NonNullList<ItemStack> recipeResultItems) {
        for (ItemStack newItem : items) {
            final boolean[] itemHasBeenStored = {false}; // Why Java.
            recipeResultItems.forEach((storedItem) -> {
                if (storedItem.getItem() == newItem.getItem()
                        && ItemStack.areItemStackTagsEqual(storedItem, newItem)
                        && (storedItem.getCount() + newItem.getCount()) <= storedItem.getMaxStackSize()) {
                    storedItem.grow(newItem.getCount());
                    newItem.setCount(0);
                    itemHasBeenStored[0] = true;
                }
            });
            if (!itemHasBeenStored[0]) {
                recipeResultItems.add(newItem);
            }
        }
    }

}
