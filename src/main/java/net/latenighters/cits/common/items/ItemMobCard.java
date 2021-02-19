package net.latenighters.cits.common.items;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.latenighters.cits.ModSetup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ItemMobCard extends Item {
    private static final Map<EntityType<?>, ItemMobCard> EGGS = Maps.newIdentityHashMap();
    private final int primaryColor;
    private final int secondaryColor;
    private final EntityType<?> typeIn;

    public ItemMobCard(EntityType<?> typeIn, int primaryColor, int secondaryColor) {
        super(new Properties()
                .group(ModSetup.ITEM_GROUP)
        );
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.typeIn = typeIn;
        EGGS.put(typeIn, this);
    }

    @Override
    public String getTranslationKey() {return "item.cits.mobcard";}


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.typeIn.getName());
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    public static ItemMobCard getMobcard(@Nullable EntityType<?> type) { return EGGS.get(type); }

    public static Iterable<ItemMobCard> getMobcards() {
        return Iterables.unmodifiableIterable(EGGS.values());
    }
}
