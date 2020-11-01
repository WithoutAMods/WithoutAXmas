package withoutaname.mods.withoutaxmas;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import withoutaname.mods.withoutaxmas.modules.other.setup.OtherRegistration;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;
import withoutaname.mods.withoutaxmas.setup.ClientSetup;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WithoutAXmas.MODID)
public class WithoutAXmas {

	public static final String MODID = "withoutaxmas";

	public static final Logger LOGGER = LogManager.getLogger();

	public WithoutAXmas() {
		OtherRegistration.init();
		PresentRegistration.init();
		XmasTreeRegistration.init();

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
	}

}
