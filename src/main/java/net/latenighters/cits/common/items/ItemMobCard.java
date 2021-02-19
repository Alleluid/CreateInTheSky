package net.latenighters.cits.common.items;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.latenighters.cits.ModSetup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Map;

public class ItemMobCard extends Item {
    private static final Map<EntityType<?>, ItemMobCard> EGGS = Maps.newIdentityHashMap();
    private final int primaryColor;
    private final int secondaryColor;
    private final EntityType<?> typeIn;

    public ItemMobCard(EntityType<?> typeIn, int primaryColor, int secondaryColor) {
        super(new Properties()
                .maxStackSize(1)
                .group(ModSetup.ITEM_GROUP)
        );
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.typeIn = typeIn;
        EGGS.put(typeIn, this);
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    public static ItemMobCard getMobcard(@Nullable EntityType<?> type) { return EGGS.get(type); }

    public static Iterable<ItemMobCard> getMobcards() {
        return Iterables.unmodifiableIterable(EGGS.values());
    }
}
