package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;

public class ItemMortarPestle extends Item {
    public ItemMortarPestle() {
        super(new Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // TODO: Polish this up to be like sandpaper.

        HashMap<Item, Item> grindingRecipes = new HashMap<>();
        grindingRecipes.put(Items.COBBLESTONE, Items.GRAVEL);
        grindingRecipes.put(Items.GRAVEL, Items.SAND);
        //grindingRecipes.put("create:limestone", "cits:calamine");
        //grindingRecipes.put("create:weathered_limestone", "cits:malachite");

        ItemStack itemStack = playerIn.getHeldItem(handIn);
        Hand otherHand = handIn == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack itemInOtherHand = playerIn.getHeldItem(otherHand);

        // implement proper recipe check here.
        Item recipeResult = grindingRecipes.get(itemInOtherHand.getItem());
        if (recipeResult != null && !worldIn.isRemote) {
            ItemEntity droppedResult = playerIn.entityDropItem(recipeResult, 1);
            itemInOtherHand.shrink(1);
        }


        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
