package net.latenighters.cits.common;

import net.latenighters.cits.common.items.ItemCardstock;
import net.latenighters.cits.common.items.ItemMobCard;
import net.latenighters.cits.common.items.ItemMortarPestle;
import net.latenighters.cits.common.items.ItemPuncher;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventSubscriber {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        // TODO: move to puncher class
        Entity entitySource = event.getSource().getTrueSource();
        EntityType<?> entityKilledType = event.getEntity().getType();
        if (entitySource instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entitySource;

            if (player.getHeldItemOffhand().getItem() instanceof ItemPuncher) {
                NonNullList<ItemStack> mainInv = player.inventory.mainInventory;

                for (ItemStack stack : mainInv) {
                    if (stack.getItem() instanceof ItemCardstock) {
                        if (player.inventory.addItemStackToInventory(new ItemStack(ItemMobCard.getMobcard(entityKilledType))))
                            stack.shrink(1);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        MobCardHandler.mobCardOnLootLoad(event);
    }

    @SubscribeEvent
    public static void onFluidPlaceBlockEvent(BlockEvent.FluidPlaceBlockEvent event)
    {
        if(event.getNewState().getBlock().equals(Blocks.STONE))
            event.setNewState(Blocks.ANDESITE.getDefaultState());
    }

    @SubscribeEvent
    public static void onPlayerPickupItem(PlayerEvent.ItemPickupEvent event) {
        Item offhandItem = event.getPlayer().getHeldItem(Hand.OFF_HAND).getItem();
        if (offhandItem instanceof ItemMortarPestle) {
            // TODO: Add animation here?
            Item item = event.getStack().getItem();
            int count = event.getStack().getCount();
            boolean recipePreformed = false;
            for (int i=0; i < count; i++) {
                recipePreformed = ((ItemMortarPestle) offhandItem).preformRecipe( // TODO: change this method to handle multiple inputs.
                        event.getPlayer().world,
                        event.getPlayer(),
                        item
                );
            }
                event.setCanceled(recipePreformed);
        }
    }

    @SubscribeEvent
    public static void onBonemealEvent(BonemealEvent event) {
        World world = event.getWorld();
        BlockState blockState = event.getBlock();
        if (blockState.getBlock().equals(Blocks.SEAGRASS)) {
            if (blockState.getFluidState().isTagged(FluidTags.WATER) && !world.isRemote) {
                world.setBlockState(event.getPos(), Blocks.KELP.getDefaultState());
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
