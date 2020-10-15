package withoutaname.mods.withoutapresent.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import withoutaname.mods.withoutapresent.WithoutAPresent;
import withoutaname.mods.withoutapresent.blocks.PresentBlock;

public class Language extends LanguageProvider {
	
	private final String locale;

	public Language(DataGenerator gen, String locale) {
		super(gen, WithoutAPresent.MODID, locale);
		this.locale = locale;
	}

	@Override
	protected void addTranslations() {
		for (PresentBlock.Color color : PresentBlock.getAllColors()) {
			add((Item) color.getItemRegistryObject().get(), "Geschenk", "Present");
		}

		add("itemGroup.withoutapresent", "WithoutAPresent", "WithoutAPresent");
	}

	private void add(String key, String de_de, String en_us) {
		switch(locale) {
			case "de_de":
				add(key, de_de);
				break;
			case "en_us":
				add(key, en_us);
				break;
		}
	}

	private void add(Item key, String de_de, String en_us) {
		add(key.getTranslationKey(), de_de, en_us);
	}

}
