package withoutaname.mods.withoutapresent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import withoutaname.mods.withoutapresent.setup.ModSetup;
import withoutaname.mods.withoutapresent.setup.Registration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WithoutAPresent.MODID)
public class WithoutAPresent {

	public static final String MODID = "withoutapresent";

	public static final Logger LOGGER = LogManager.getLogger();

	public WithoutAPresent() {
		Registration.init();

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
	}

}
