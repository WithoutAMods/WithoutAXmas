package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import withoutaname.mods.withoutaxmas.WithoutAXmas;
import withoutaname.mods.withoutaxmas.modules.other.setup.OtherRegistration;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WithoutAXmas.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		registerOther();
		registerPresent();
		registerXmasTree();
	}

	private void registerOther() {
		simpleBlock(OtherRegistration.ADVENT_WREATH_BLOCK.get(), models().getExistingFile(modLoc("block/advent_wreath")));
		itemModels().withExistingParent("advent_wreath", modLoc("block/advent_wreath"));

		ModelFile candle = models().getBuilder("block/candle")
				.parent(models().getExistingFile(mcLoc("block/block")))
				.texture("candle", modLoc("custom/candle"))
				.texture("wick", modLoc("custom/baumstamm"))
				.texture("particle", "#candle")
				.element().from(7, 0, 7).to(9, 8.5F, 9)
					.face(Direction.NORTH).texture("#candle").uvs(7, 7.5F, 9, 16).end()
					.face(Direction.EAST).texture("#candle").uvs(7, 7.5F, 9, 16).end()
					.face(Direction.SOUTH).texture("#candle").uvs(7, 7.5F, 9, 16).end()
					.face(Direction.WEST).texture("#candle").uvs(7, 7.5F, 9, 16).end()
					.face(Direction.UP).texture("#candle").uvs(7, 8, 7.1F, 8.1F).end()
					.face(Direction.DOWN).texture("#candle").uvs(7, 8, 7.1F, 8.1F).end()
					.end()
				.element().from(7.9F, 8.5F, 7.9F).to(8.1F, 10, 8.1F)
					.face(Direction.NORTH).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.face(Direction.EAST).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.face(Direction.SOUTH).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.face(Direction.WEST).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.face(Direction.UP).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.face(Direction.DOWN).texture("#wick").uvs(0, 0, 0.1F, 0.1F).end()
					.end();
		simpleBlock(OtherRegistration.CANDLE_BLOCK.get(), candle);
		itemModels().withExistingParent("candle", mcLoc("item/generated"))
				.texture("layer0", modLoc("item/candle"));
	}

	private void registerPresent() {
		ModelFile present0 = models().getBuilder("block/present_0")
				.parent(models().getExistingFile(mcLoc("block/block")))
				.texture("particle", "#side")
				.element().from(4, 0, 4).to(12, 8, 12)
					.face(Direction.NORTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.EAST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.SOUTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.WEST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.UP).texture("#top").uvs(0, 0, 16, 16).end()
					.face(Direction.DOWN).texture("#side").uvs(0, 0, 16, 16).end()
					.end();
		ModelFile present1 = models().getBuilder("block/present_1")
				.parent(models().getExistingFile(mcLoc("block/block")))
				.texture("particle", "#side")
				.element().from(3, 0, 3).to(13, 10, 13)
					.face(Direction.NORTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.EAST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.SOUTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.WEST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.UP).texture("#top").uvs(0, 0, 16, 16).end()
					.face(Direction.DOWN).texture("#side").uvs(0, 0, 16, 16).end()
					.end();
		ModelFile present2 = models().getBuilder("block/present_2")
				.parent(models().getExistingFile(mcLoc("block/block")))
				.texture("particle", "#side")
				.element().from(2, 0, 2).to(14, 12, 14)
					.face(Direction.NORTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.EAST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.SOUTH).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.WEST).texture("#side").uvs(0, 0, 16, 16).end()
					.face(Direction.UP).texture("#top").uvs(0, 0, 16, 16).end()
					.face(Direction.DOWN).texture("#side").uvs(0, 0, 16, 16).end()
					.end();
		horizontalBlock(PresentRegistration.PRESENT_BLOCK.get(), (blockState) -> {
			ModelFile parent;
			int size = blockState.get(PresentBlock.SIZE_PROPERTY);
			switch (size) {
				default:
				case 0:
					parent = present0;
					break;
				case 1:
					parent = present1;
					break;
				case 2:
					parent = present2;
					break;
			}
			return getPresentModelFile(blockState.get(PresentBlock.COLOR_PROPERTY), size, parent);
		});
		for (Color color : Color.getValues()) {
			itemModels().withExistingParent("present_" + color, modLoc("block/present_2_" + color));
		}
	}

	private ModelFile getPresentModelFile(Color color, int size, ModelFile parent) {
		return models().getBuilder("block/present_" + size + "_" + color)
				.parent(parent)
				.texture("side", modLoc("block/present_" + color))
				.texture("top", modLoc("block/present_" + color + "_top"));
	}

	private void registerXmasTree() {
		simpleBlock(XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_bottom")));
		simpleBlock(XmasTreeRegistration.XMAS_TREE_MIDDLE_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_middle")));
		simpleBlock(XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_top")));
	}

}
