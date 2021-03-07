package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemSoylent extends Item {
    private static final Food SOYLENT = (new Food.Builder()).hunger(4).saturation(1.5F).fastToEat().build();
    public ItemSoylent() {
        super(new Properties()
                .group(ModSetup.ITEM_GROUP)
                .maxStackSize(1)
                .food(SOYLENT)
        );
    }
}
