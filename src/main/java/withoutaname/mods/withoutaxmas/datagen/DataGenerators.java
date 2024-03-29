package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeClient()) {
			generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new Language(generator, "de_de"));
			generator.addProvider(new Language(generator, "en_us"));
		}
		if (event.includeServer()) {
			generator.addProvider(new LootTables(generator));
			generator.addProvider(new Recipes(generator));
		}
	}

}
