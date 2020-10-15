package withoutaname.mods.withoutapresent.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	public static final ItemGroup defaultItemGroup = new ItemGroup("withoutapresent") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(Registration.PRESENT_BLUE_ITEM.get());
		}

	};

	public static void init(FMLCommonSetupEvent event) {
	}

	public static final Item.Properties defaultItemProperties = new Item.Properties().group(defaultItemGroup);

}
