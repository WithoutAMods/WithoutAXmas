package withoutaname.mods.withoutapresent.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import withoutaname.mods.withoutapresent.blocks.PresentScreen;

public class ClientSetup {

	public static void init(final FMLClientSetupEvent event) {
		ScreenManager.registerFactory(Registration.PRESENT_CONTAINER.get(), PresentScreen::new);
	}

}
