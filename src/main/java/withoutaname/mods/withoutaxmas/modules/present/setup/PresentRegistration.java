package withoutaname.mods.withoutaxmas.modules.present.setup;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
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
	private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

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

	public static final RegistryObject<BlockEntityType<PresentTile>> PRESENT_TILE = TILES.register("present", () -> BlockEntityType.Builder.of(PresentTile::new, PRESENT_BLOCK.get()).build(null));

	public static final RegistryObject<MenuType<PresentContainer>> PRESENT_CONTAINER = CONTAINERS.register("present", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new PresentContainer(windowId, world, pos, inv, inv.player);
	}));

}
