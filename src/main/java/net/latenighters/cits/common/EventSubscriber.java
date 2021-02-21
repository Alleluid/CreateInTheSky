package net.latenighters.cits.common;

import net.latenighters.cits.common.items.ItemCardstock;
import net.latenighters.cits.common.items.ItemMobCard;
import net.latenighters.cits.common.items.ItemPuncher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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

                for (int i=0; i<mainInv.size(); i++) {
                    ItemStack stack = mainInv.get(i);
                    if (stack.getItem() instanceof ItemCardstock) {
                        if (player.inventory.addItemStackToInventory(new ItemStack(ItemMobCard.getMobcard(entityKilledType))) )
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
}
