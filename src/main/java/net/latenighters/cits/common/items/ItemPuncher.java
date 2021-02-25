package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ItemPuncher extends Item {
    public ItemPuncher() {
        super(new Properties()
            .group(ModSetup.ITEM_GROUP)
            .maxStackSize(1)
        );
    }

    public static void onLivingDeath(LivingDeathEvent event){
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
}
