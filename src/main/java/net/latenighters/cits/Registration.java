package net.latenighters.cits;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import com.simibubi.create.repack.registrate.util.entry.TileEntityEntry;
import net.latenighters.cits.common.MobCardHandler;
import net.latenighters.cits.common.blocks.BlockMobEgg;
import net.latenighters.cits.common.blocks.BlockPlantBaller;
import net.latenighters.cits.common.blocks.BlockSteamputer;
import net.latenighters.cits.common.blocks.jukebox.BlockJukebox;
import net.latenighters.cits.common.blocks.jukebox.RendererJukebox;
import net.latenighters.cits.common.blocks.jukebox.TileJukebox;
import net.latenighters.cits.common.items.*;
import net.latenighters.cits.common.world.WorldTypeSkyblock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static net.latenighters.cits.Cits.MOD_ID;

@SuppressWarnings("unused")
public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    private static final DeferredRegister<ForgeWorldType> WORLD_TYPES = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, MOD_ID);

    private static final CreateRegistrate REGISTRATE = Cits.registrate()
            .itemGroup(() -> Create.baseCreativeTab);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        WORLD_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MobCardHandler.mobCardsInit(ITEMS);
    }

    // Item Registration
    public static final RegistryObject<ItemPuncher> PUNCHER = ITEMS.register("puncher", ItemPuncher::new);
    public static final RegistryObject<ItemCardstock> CARDSTOCK = ITEMS.register("cardstock", ItemCardstock::new);
    public static final RegistryObject<ItemMortarPestle> MORTAR_PESTLE = ITEMS.register("mortar_pestle", ItemMortarPestle::new);
    public static final RegistryObject<ItemSkyTool> SKYTOOL = ITEMS.register("skytool", ItemSkyTool::new);
    public static final RegistryObject<ItemPlantBall> PLANT_BALL = ITEMS.register("plant_ball", ItemPlantBall::new);
    public static final RegistryObject<ItemSoylent> SOYLENT = ITEMS.register("soylent", ItemSoylent::new);
    public static final RegistryObject<Item> BRASS_GEAR = ITEMS.register("brass_gear", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> DUST_WOOD = ITEMS.register("dust_wood", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> WOOD_PULP = ITEMS.register("wood_pulp", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> CALAMINE = ITEMS.register("calamine", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> CALAMINE_CHARCOAL_MIX = ITEMS.register("calamine_charcoal_mix", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );
    public static final RegistryObject<Item> CARNELIAN = ITEMS.register("carnelian", () ->
            new Item(new Item.Properties().group(ModSetup.ITEM_GROUP))
    );


    // Fluid Registration


    // Block Registration
    public static final RegistryObject<BlockSteamputer> STEAMPUTER_BLOCK = BLOCKS.register("steamputer", BlockSteamputer::new);
    public static final RegistryObject<Item> STEAMPUTER_BLOCK_ITEM = ITEMS.register("steamputer", () -> new BlockItem(STEAMPUTER_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<BlockMobEgg> MOB_EGG_BLOCK = BLOCKS.register("weird_egg", BlockMobEgg::new);
    public static final RegistryObject<Item> MOB_EGG_BLOCK_ITEM = ITEMS.register("weird_egg", () -> new BlockItem(MOB_EGG_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<BlockPlantBaller> PLANT_BALLER_BLOCK = BLOCKS.register("plant_baller", BlockPlantBaller::new);
    public static final RegistryObject<Item> PLANT_BALLER_BLOCK_ITEM = ITEMS.register("plant_baller", () -> new BlockItem(PLANT_BALLER_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    //public static final RegistryObject<BlockJukebox> JUKEBOX_BLOCK = BLOCKS.register("jukebox", BlockJukebox::new);
    //public static final RegistryObject<Item> JUKEBOX_BLOCK_ITEM = ITEMS.register("jukebox", () -> new BlockItem(JUKEBOX_BLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));

    // Tile Entity Registration
//    public static final RegistryObject<TileEntityType<TileGeodeMachine>> GEODE_MACHINE_TILE = TILES.register("geode_machine_tile", () -> TileEntityType.Builder.create(TileGeodeMachine::new, GEODE_MACHINE_BLOCK.get()).build(null));
//    public static final RegistryObject<TileEntityType<TileJukebox>> JUKEBOX_TILE = TILES.register("jukebox_tile", () -> TileEntityType.Builder.create(TileJukebox::new, JUKEBOX_BLOCK.get()).build(null));

    // World Type Registration
    public static final RegistryObject<WorldTypeSkyblock> SKYBLOCK_WORLD_TYPE = WORLD_TYPES.register("createinthesky", WorldTypeSkyblock::new);

    //Create Block Registration
    public static final BlockEntry<BlockJukebox> JUKEBOX_BLOCK =
            REGISTRATE.block("jukebox",BlockJukebox::new)
            .initialProperties(()-> Blocks.JUKEBOX)
            .item()
            .transform(customItemModel())
            .register();


    //Create Tile Entity Registration
    public static final TileEntityEntry<TileJukebox> JUKEBOX_TILE = Cits.registrate()
            .tileEntity("jukebox", TileJukebox::new)
            .validBlocks(JUKEBOX_BLOCK)
            .renderer(()-> RendererJukebox::new)
            .register();

}