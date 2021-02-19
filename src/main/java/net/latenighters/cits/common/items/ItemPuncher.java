package net.latenighters.cits.common.items;

import net.latenighters.cits.ModSetup;
import net.minecraft.item.Item;

public class ItemPuncher extends Item {
    public ItemPuncher() {
        super(new Properties()
            .group(ModSetup.ITEM_GROUP)
            .maxStackSize(1)
        );
    }
}
