package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import withoutaname.mods.withoutaxmas.WithoutAXmas;
import withoutaname.mods.withoutaxmas.modules.other.setup.OtherRegistration;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

public class Language extends LanguageProvider {

	private final String locale;

	public Language(DataGenerator gen, String locale) {
		super(gen, WithoutAXmas.MODID, locale);
		this.locale = locale;
	}

	@Override
	protected void addTranslations() {
		addOtherTranslations();
		addPresentTranslations();
		addXmasTreeTranslations();

		add("itemGroup.withoutaxmas", "WithoutAXmas", "WithoutAXmas");
	}

	private void addOtherTranslations() {
		add(OtherRegistration.ADVENT_WREATH_BLOCK.get(), "Adventskranz", "Advent Wreath");
	}

	public static final String PRESENT_DE_DE = "Geschenk";
	public static final String PRESENT_EN_US = "Present";

	private void addPresentTranslations() {
		add(PresentRegistration.PRESENT_BLOCK.get(), PRESENT_DE_DE, PRESENT_EN_US);

		add(PresentRegistration.PRESENT_BLUE_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(PresentRegistration.PRESENT_GREEN_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(PresentRegistration.PRESENT_PURPLE_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(PresentRegistration.PRESENT_RED_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);
		add(PresentRegistration.PRESENT_YELLOW_ITEM.get(), PRESENT_DE_DE, PRESENT_EN_US);

		add("screen.withoutaxmas.present", PRESENT_DE_DE, PRESENT_EN_US);
	}

	public static final String XMAS_TREE_DE_DE = "Weihnachtsbaum";
	public static final String XMAS_TREE_EN_US = "Xmas Tree";

	private void addXmasTreeTranslations() {
		add(XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get(), XMAS_TREE_DE_DE, XMAS_TREE_EN_US);
		add(XmasTreeRegistration.XMAS_TREE_MIDDLE_BLOCK.get(), XMAS_TREE_DE_DE, XMAS_TREE_EN_US);
		add(XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get(), XMAS_TREE_DE_DE, XMAS_TREE_EN_US);

		add(XmasTreeRegistration.XMAS_TREE_ITEM.get(), XMAS_TREE_DE_DE, XMAS_TREE_EN_US);
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
		add(key.getDescriptionId(), de_de, en_us);
	}

	private void add(Block key, String de_de, String en_us) {
		add(key.getDescriptionId(), de_de, en_us);
	}

}
