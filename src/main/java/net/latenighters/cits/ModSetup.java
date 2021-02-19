package net.latenighters.cits;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {
    public static final ItemGroup ITEM_GROUP = new ItemGroup(Cits.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.BRASS_GEAR.get());
        }
    };
}
