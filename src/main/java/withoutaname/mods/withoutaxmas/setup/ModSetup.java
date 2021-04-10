package withoutaname.mods.withoutaxmas.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

public class ModSetup {

	public static final ItemGroup defaultItemGroup = new ItemGroup("withoutaxmas") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(XmasTreeRegistration.XMAS_TREE_ITEM.get());
		}

	};

	public static void init(FMLCommonSetupEvent event) {
	}

	public static final Item.Properties defaultItemProperties = new Item.Properties().tab(defaultItemGroup);

}
