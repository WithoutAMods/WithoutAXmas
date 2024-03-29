package withoutaname.mods.withoutaxmas.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentScreen;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;

public class ClientSetup {

	public static void init(final FMLClientSetupEvent event) {
		//Present
		MenuScreens.register(PresentRegistration.PRESENT_CONTAINER.get(), PresentScreen::new);
	}

}
