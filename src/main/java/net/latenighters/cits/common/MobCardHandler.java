package net.latenighters.cits.common;

import net.latenighters.cits.common.items.ItemMobCard;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class MobCardHandler {
    public static final String[] mobcardEntitiesStringArray = new String[] {
            "minecraft:creeper",
            "minecraft:chicken",
            "minecraft:ocelot",
            "minecraft:panda",
            "minecraft:parrot",
            "minecraft:phantom",
            "minecraft:pig",
            "minecraft:piglin",
            "minecraft:polar_bear",
            "minecraft:rabbit",
            "minecraft:sheep",
            "minecraft:shulker",
            "minecraft:skeleton",
            "minecraft:slime",
            "minecraft:spider",
            "minecraft:squid",
            "minecraft:strider",
            "minecraft:turtle",
            "minecraft:witch",
            "minecraft:wither_skeleton",
            "minecraft:zombie",
            "minecraft:zombified_piglin",
            "minecraft:mooshroom",
            "minecraft:llama",
            "minecraft:horse",
            "minecraft:guardian",
            "minecraft:ghast",
            "minecraft:enderman",
            "minecraft:cow",
            "minecraft:blaze"
    };
    private static final HashSet<String> mobcardEntities = new HashSet<>(Arrays.asList(mobcardEntitiesStringArray));

    public static ArrayList<RegistryObject<ItemMobCard>> MOBCARD_REGISTRIES = new ArrayList<>();

    public static void mobCardsInit(DeferredRegister<Item> itemDeferredRegister) {
        // Occurs during registration at game load
        Map<EntityType<?>, SpawnEggItem> eggs = SpawnEggItem.EGGS;
        for (Map.Entry<EntityType<?>, SpawnEggItem> eggEntry : eggs.entrySet()) {
            EntityType<?> entityType = eggEntry.getKey();
            SpawnEggItem spawnEgg = eggEntry.getValue();

            String entityRegistryName = entityType.getRegistryName().toString();
            if (mobcardEntities.contains(entityRegistryName)) {
                ItemMobCard newCard = new ItemMobCard(entityType, spawnEgg.getColor(0), spawnEgg.getColor(1));
                String name = entityType.getRegistryName().getPath();
                MOBCARD_REGISTRIES.add(itemDeferredRegister.register(String.format("%s_mobcard", name), () -> newCard));
                ItemMobCard.CARDS.put(entityType, newCard);
            }
        }

    }

    public static void mobCardOnLootLoad(LootTableLoadEvent event) {
        // Occurs at world load.
//        Cits.LOGGER.info(String.format("MobCardHandler - LootLoad: %s", event.getName().toString()));

    }

    public static void mobCardColor(ColorHandlerEvent.Item event) {
        Iterable<ItemMobCard> mobcards = ItemMobCard.getMobcards();
        ItemColors itemColors = event.getItemColors();
        for (ItemMobCard card : mobcards) {
            itemColors.register((stack, color) -> {
                return card.getColor(color);
            }, card);
        }
    }

}
