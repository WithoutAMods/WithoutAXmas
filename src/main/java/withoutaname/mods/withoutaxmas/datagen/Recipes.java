package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import withoutaname.mods.withoutaxmas.modules.other.setup.OtherRegistration;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider{

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		registerOtherRecipes(consumer);
		registerPresentRecipes(consumer);
		registerXmasTreeRecipes(consumer);
	}

	private void registerOtherRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(OtherRegistration.ADVENT_WREATH_BLOCK.get())
				.pattern("LCL")
				.pattern("C C")
				.pattern("LCL")
				.define('L', Items.SPRUCE_LEAVES)
				.define('C', OtherRegistration.CANDLE_ITEM.get())
				.unlockedBy("candle", InventoryChangeTrigger.Instance.hasItems(OtherRegistration.CANDLE_ITEM.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(OtherRegistration.CANDLE_ITEM.get())
				.pattern(" S ")
				.pattern("CSC")
				.pattern("CSC")
				.define('C', Items.HONEYCOMB)
				.define('S', Items.STRING)
				.unlockedBy("honeycomb", InventoryChangeTrigger.Instance.hasItems(Items.HONEYCOMB))
				.save(consumer);
	}

	private void registerPresentRecipes(Consumer<IFinishedRecipe> consumer) {
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_BLUE_ITEM.get(), Items.BLUE_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_GREEN_ITEM.get(), Items.LIME_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_PURPLE_ITEM.get(), Items.PURPLE_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_RED_ITEM.get(), Items.RED_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_YELLOW_ITEM.get(), Items.YELLOW_WOOL);
	}

	private void addColoredPresentRecipes(Consumer<IFinishedRecipe> consumer, Item present, Item wool) {
		ShapedRecipeBuilder.shaped(present)
				.pattern("WSW")
				.pattern("SSS")
				.pattern("WSW")
				.define('W', wool)
				.define('S', Tags.Items.STRING)
				.unlockedBy(wool.toString(), InventoryChangeTrigger.Instance.hasItems(wool))
				.save(consumer);
	}

	private void registerXmasTreeRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(XmasTreeRegistration.XMAS_TREE_ITEM.get())
				.pattern("DDD")
				.pattern("DGD")
				.pattern("DSD")
				.define('D', Tags.Items.DYES)
				.define('G', Items.GOLD_NUGGET)
				.define('S', Items.SPRUCE_SAPLING)
				.unlockedBy("spruce_sapling", InventoryChangeTrigger.Instance.hasItems(Items.SPRUCE_SAPLING))
				.save(consumer);
	}

}
