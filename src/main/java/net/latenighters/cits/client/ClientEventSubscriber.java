package net.latenighters.cits.client;

import net.latenighters.cits.common.MobCardHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.latenighters.cits.Cits.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventSubscriber {
    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) { MobCardHandler.mobCardColor(event); }
}
