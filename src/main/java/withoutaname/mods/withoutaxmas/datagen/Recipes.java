package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import withoutaname.mods.withoutaxmas.modules.other.setup.OtherRegistration;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider{

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		registerOtherRecipes(consumer);
		registerPresentRecipes(consumer);
		registerXmasTreeRecipes(consumer);
	}

	private void registerOtherRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(OtherRegistration.ADVENT_WREATH_BLOCK.get())
				.patternLine("LCL")
				.patternLine("C C")
				.patternLine("LCL")
				.key('L', Items.SPRUCE_LEAVES)
				.key('C', OtherRegistration.CANDLE_ITEM.get())
				.addCriterion("candle", InventoryChangeTrigger.Instance.forItems(OtherRegistration.CANDLE_ITEM.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(OtherRegistration.CANDLE_ITEM.get())
				.patternLine(" S ")
				.patternLine("CSC")
				.patternLine("CSC")
				.key('C', Items.HONEYCOMB)
				.key('S', Items.STRING)
				.addCriterion("honeycomb", InventoryChangeTrigger.Instance.forItems(Items.HONEYCOMB))
				.build(consumer);
	}

	private void registerPresentRecipes(Consumer<IFinishedRecipe> consumer) {
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_BLUE_ITEM.get(), Items.BLUE_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_GREEN_ITEM.get(), Items.LIME_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_PURPLE_ITEM.get(), Items.PURPLE_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_RED_ITEM.get(), Items.RED_WOOL);
		addColoredPresentRecipes(consumer, PresentRegistration.PRESENT_YELLOW_ITEM.get(), Items.YELLOW_WOOL);
	}

	private void addColoredPresentRecipes(Consumer<IFinishedRecipe> consumer, Item present, Item wool) {
		ShapedRecipeBuilder.shapedRecipe(present)
				.patternLine("WSW")
				.patternLine("SSS")
				.patternLine("WSW")
				.key('W', wool)
				.key('S', Tags.Items.STRING)
				.addCriterion(wool.toString(), InventoryChangeTrigger.Instance.forItems(wool))
				.build(consumer);
	}

	private void registerXmasTreeRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(XmasTreeRegistration.XMAS_TREE_ITEM.get())
				.patternLine("DDD")
				.patternLine("DGD")
				.patternLine("DSD")
				.key('D', Tags.Items.DYES)
				.key('G', Items.GOLD_NUGGET)
				.key('S', Items.SPRUCE_SAPLING)
				.addCriterion("spruce_sapling", InventoryChangeTrigger.Instance.forItems(Items.SPRUCE_SAPLING))
				.build(consumer);
	}

}
