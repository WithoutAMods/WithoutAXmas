package withoutaname.mods.withoutaxmas.modules.xmastree.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import withoutaname.mods.withoutaxmas.modules.xmastree.blocks.XmasTreeBlock;
import withoutaname.mods.withoutaxmas.modules.xmastree.items.XmasTreeItem;

import static withoutaname.mods.withoutaxmas.WithoutAXmas.MODID;

public class XmasTreeRegistration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<XmasTreeBlock> XMAS_TREE_BOTTOM_BLOCK = BLOCKS.register("xmas_tree_bottom", XmasTreeBlock::new);
	public static final RegistryObject<XmasTreeBlock> XMAS_TREE_MIDDLE_BLOCK = BLOCKS.register("xmas_tree_middle", XmasTreeBlock::new);
	public static final RegistryObject<XmasTreeBlock> XMAS_TREE_TOP_BLOCK = BLOCKS.register("xmas_tree_top", XmasTreeBlock::new);

	public static final RegistryObject<Item> XMAS_TREE_ITEM = ITEMS.register("xmas_tree", XmasTreeItem::new);

}