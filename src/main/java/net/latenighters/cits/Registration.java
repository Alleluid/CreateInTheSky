package net.latenighters.cits;

import net.latenighters.cits.common.items.ItemMobCard;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.latenighters.cits.Cits.MOD_ID;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Item Registration
//    public static final RegistryObject<ItemGeode> INTACT_GEODE = ITEMS.register("intact_geode", ItemGeode::new);

    // Fluid Registration


    // Block Registration
//    public static final RegistryObject<BlockGeodeMachine> GEODE_MACHINE_BLOCK = BLOCKS.register("geode_machine", BlockGeodeMachine::new);
//    public static final RegistryObject<Item> GEODE_MACHINE_ITEM = ITEMS.register("geode_machine", () -> new BlockItem(GEODE_MACHINE_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    // Tile Entity Registration
//    public static final RegistryObject<TileEntityType<TileGeodeMachine>> GEODE_MACHINE_TILE = TILES.register("geode_machine_tile", () -> TileEntityType.Builder.create(TileGeodeMachine::new, GEODE_MACHINE_BLOCK.get()).build(null));

    // Mobcard Registration
    public static final RegistryObject<ItemMobCard> BLAZE_MOB_CARD = ITEMS.register("blaze_mobcard", () ->
            new ItemMobCard(EntityType.BLAZE, 16167425, 16775294));
    public static final RegistryObject<ItemMobCard> CREEPER_MOB_CARD = ITEMS.register("creeper_mobcard", () ->
            new ItemMobCard(EntityType.CREEPER, 894731, 0));
}