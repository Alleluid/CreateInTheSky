package net.latenighters.cits.client.renderer.color;

import net.latenighters.cits.common.items.ItemMobCard;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.latenighters.cits.Cits.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobcardColors {
    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
        Iterable<ItemMobCard> mobcards = ItemMobCard.getMobcards();
        ItemColors itemColors = event.getItemColors();
        for (ItemMobCard card : mobcards) {
            itemColors.register((stack, color) -> {
                return card.getColor(color);
            }, card);
        }
    }
}
