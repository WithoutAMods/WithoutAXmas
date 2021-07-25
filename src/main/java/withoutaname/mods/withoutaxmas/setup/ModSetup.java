package withoutaname.mods.withoutaxmas.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

import javax.annotation.Nonnull;

public class ModSetup {
	
	public static final CreativeModeTab DEFAULT_CREATIVE_TAB = new CreativeModeTab("WithoutAXmas") {
		
		@Nonnull
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(XmasTreeRegistration.XMAS_TREE_ITEM.get());
		}
		
	};
	public static final Item.Properties DEFAULT_ITEM_PROPERTIES = new Item.Properties().tab(DEFAULT_CREATIVE_TAB);
	
	public static void init(FMLCommonSetupEvent event) {
	}
	
}
