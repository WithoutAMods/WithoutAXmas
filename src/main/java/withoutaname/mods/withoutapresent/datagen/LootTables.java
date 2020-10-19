package withoutaname.mods.withoutapresent.datagen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import withoutaname.mods.withoutalib.datagen.BaseLootTableProvider;
import withoutaname.mods.withoutapresent.blocks.PresentBlock;
import withoutaname.mods.withoutapresent.setup.Registration;
import withoutaname.mods.withoutapresent.tools.Color;

public class LootTables extends BaseLootTableProvider {

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		Block block = Registration.PRESENT_BLOCK.get();
		AlternativesLootEntry.Builder lootEntry = AlternativesLootEntry.builder(
				ItemLootEntry.builder(Registration.PRESENT_BLUE_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.BLUE))))
				.alternatively(
				ItemLootEntry.builder(Registration.PRESENT_GREEN_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.GREEN))))
				.alternatively(
				ItemLootEntry.builder(Registration.PRESENT_PURPLE_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.PURPLE))))
				.alternatively(
				ItemLootEntry.builder(Registration.PRESENT_RED_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.RED))))
				.alternatively(
				ItemLootEntry.builder(Registration.PRESENT_YELLOW_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.YELLOW))));
		lootTables.put(block, getStandardLootTable(getStandardLootPool(block.getRegistryName().toString(), lootEntry)));
	}

}
