package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import withoutaname.mods.withoutalib.datagen.BaseLootTableProvider;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

public class LootTables extends BaseLootTableProvider {

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		Block block = PresentRegistration.PRESENT_BLOCK.get();
		AlternativesLootEntry.Builder lootEntry = AlternativesLootEntry.builder(
				ItemLootEntry.builder(PresentRegistration.PRESENT_BLUE_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.BLUE))))
				.alternatively(
				ItemLootEntry.builder(PresentRegistration.PRESENT_GREEN_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.GREEN))))
				.alternatively(
				ItemLootEntry.builder(PresentRegistration.PRESENT_PURPLE_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.PURPLE))))
				.alternatively(
				ItemLootEntry.builder(PresentRegistration.PRESENT_RED_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.RED))))
				.alternatively(
				ItemLootEntry.builder(PresentRegistration.PRESENT_YELLOW_ITEM.get())
					.acceptCondition(BlockStateProperty.builder(block)
						.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(PresentBlock.COLOR_PROPERTY, Color.YELLOW))));
		lootTables.put(block, getStandardLootTable(getStandardLootPool(block.getRegistryName().toString(), lootEntry)));
	}

}
