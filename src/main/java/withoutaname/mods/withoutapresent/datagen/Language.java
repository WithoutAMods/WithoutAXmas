package withoutaname.mods.withoutapresent.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import withoutaname.mods.withoutapresent.WithoutAPresent;
import withoutaname.mods.withoutapresent.setup.Registration;

public class Language extends LanguageProvider {

	public static final String PRESENT_DE_DE = "Geschenk";
	public static final String PRESENT_EN_US = "Present";

	private final String locale;

	public Language(DataGenerator gen, String locale) {
		super(gen, WithoutAPresent.MODID, locale);
		this.locale = locale;
	}

	@Override
	protected void addTranslations() {
		add(Registration.PRESENT_BLOCK.get(), PRESENT_DE_DE, PRESENT_EN_US);

		add(Registration.PRESENT_BLUE_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(Registration.PRESENT_GREEN_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(Registration.PRESENT_PURPLE_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(Registration.PRESENT_RED_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(Registration.PRESENT_YELLOW_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);

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

	private void add(Block key, String de_de, String en_us) {
		add(key.getTranslationKey(), de_de, en_us);
	}

}
