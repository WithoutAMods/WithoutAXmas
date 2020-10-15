package withoutaname.mods.withoutapresent.setup;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import withoutaname.mods.withoutapresent.blocks.PresentBlock;

import static withoutaname.mods.withoutapresent.WithoutAPresent.MODID;

public class Registration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<PresentBlock> PRESENT_BLOCK = BLOCKS.register("present", PresentBlock::new);
	public static final RegistryObject<Item> PRESENT_BLUE_ITEM = ITEMS.register("present_blue", () -> new BlockItem(PRESENT_BLOCK.get(), ModSetup.defaultItemProperties));
	public static final RegistryObject<Item> PRESENT_GREEN_ITEM = ITEMS.register("present_green", () -> new BlockItem(PRESENT_BLOCK.get(), ModSetup.defaultItemProperties));
	public static final RegistryObject<Item> PRESENT_PURPLE_ITEM = ITEMS.register("present_purple", () -> new BlockItem(PRESENT_BLOCK.get(), ModSetup.defaultItemProperties));
	public static final RegistryObject<Item> PRESENT_RED_ITEM = ITEMS.register("present_red", () -> new BlockItem(PRESENT_BLOCK.get(), ModSetup.defaultItemProperties));
	public static final RegistryObject<Item> PRESENT_YELLOW_ITEM = ITEMS.register("present_yellow", () -> new BlockItem(PRESENT_BLOCK.get(), ModSetup.defaultItemProperties));

}
