package withoutaname.mods.withoutaxmas.modules.other.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import withoutaname.mods.withoutaxmas.modules.other.blocks.AdventWreathBlock;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

import static withoutaname.mods.withoutaxmas.WithoutAXmas.MODID;

public class OtherRegistration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<AdventWreathBlock> ADVENT_WREATH_BLOCK = BLOCKS.register("advent_wreath", AdventWreathBlock::new);
	public static final RegistryObject<BlockItem> ADVENT_WREATH_ITEM = ITEMS.register("advent_wreath", () -> new BlockItem(ADVENT_WREATH_BLOCK.get(), ModSetup.DEFAULT_ITEM_PROPERTIES));

}
