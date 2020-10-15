package withoutaname.mods.withoutapresent.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeClient()) {
			generator.addProvider(new Blockstates(generator, event.getExistingFileHelper()));
			generator.addProvider(new Language(generator, "de_de"));
			generator.addProvider(new Language(generator, "en_us"));
		}
		if (event.includeServer()) {
			generator.addProvider(new Recipes(generator));
		}
	}

}
