package withoutaname.mods.withoutaxmas.modules.present.setup;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentContainer;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentTile;
import withoutaname.mods.withoutaxmas.modules.present.items.PresentItem;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

import static withoutaname.mods.withoutaxmas.WithoutAXmas.MODID;

public class PresentRegistration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
	private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
		CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<PresentBlock> PRESENT_BLOCK = BLOCKS.register("present", PresentBlock::new);

	public static final RegistryObject<Item> PRESENT_BLUE_ITEM = ITEMS.register("present_blue", () -> new PresentItem(Color.BLUE));
	public static final RegistryObject<Item> PRESENT_GREEN_ITEM = ITEMS.register("present_green", () -> new PresentItem(Color.GREEN));
	public static final RegistryObject<Item> PRESENT_PURPLE_ITEM = ITEMS.register("present_purple", () -> new PresentItem(Color.PURPLE));
	public static final RegistryObject<Item> PRESENT_RED_ITEM = ITEMS.register("present_red", () -> new PresentItem(Color.RED));
	public static final RegistryObject<Item> PRESENT_YELLOW_ITEM = ITEMS.register("present_yellow", () -> new PresentItem(Color.YELLOW));

	public static final RegistryObject<TileEntityType<PresentTile>> PRESENT_TILE = TILES.register("present", () -> TileEntityType.Builder.of(PresentTile::new, PRESENT_BLOCK.get()).build(null));

	public static final RegistryObject<ContainerType<PresentContainer>> PRESENT_CONTAINER = CONTAINERS.register("present", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		World world = inv.player.getCommandSenderWorld();
		return new PresentContainer(windowId, world, pos, inv, inv.player);
	}));

}
