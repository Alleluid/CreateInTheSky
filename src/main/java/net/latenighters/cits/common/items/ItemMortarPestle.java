package net.latenighters.cits.common.items;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import net.latenighters.cits.ModSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Optional;

public class ItemMortarPestle extends Item {
    public ItemMortarPestle() {
        super(new Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // TODO: Polish this up to be like sandpaper.

        //grindingRecipes.put("create:limestone", "cits:calamine");
        //grindingRecipes.put("create:weathered_limestone", "cits:malachite");

        ItemStack itemStack = playerIn.getHeldItem(handIn);
        ActionResult<ItemStack> FAIL = new ActionResult<>(ActionResultType.FAIL, itemStack);
        Hand otherHand = handIn == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack itemInOtherHand = playerIn.getHeldItem(otherHand);

        RecipeWrapper recipeWrapper = new MillingInv(itemInOtherHand);
        Optional<MillingRecipe> recipe = AllRecipeTypes.MILLING.find(recipeWrapper, worldIn);
        if (!recipe.isPresent()) {
            return FAIL;
        } else if (!worldIn.isRemote) {
            itemInOtherHand.shrink(1);
            recipe.get().rollResults().forEach((item) -> {
                playerIn.addItemStackToInventory(item);
            });
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private static class MillingInv extends RecipeWrapper {
        public MillingInv(ItemStack stack) {
            super(new ItemStackHandler(1));
            inv.setStackInSlot(0, stack);
        }
    }
}
