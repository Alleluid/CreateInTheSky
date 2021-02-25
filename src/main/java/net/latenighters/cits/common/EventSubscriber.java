package net.latenighters.cits.common;

import net.latenighters.cits.common.items.ItemPuncher;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventSubscriber { // Move logic into existing classes when possible.
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();

        if (item == Items.ANDESITE) {
            event.getToolTip().add(new TranslationTextComponent("tooltip.cits.andesite"));
        }
    }


    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        ItemPuncher.onLivingDeath(event);
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
    public static void onBonemealEvent(BonemealEvent event) {

        World world = event.getWorld();
        BlockState blockState = event.getBlock();
        if (blockState.getBlock().equals(Blocks.SEAGRASS)) {
            if (!world.isRemote && blockState.getFluidState().isTagged(FluidTags.WATER)) {
                world.setBlockState(event.getPos(), Blocks.KELP.getDefaultState());
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}

