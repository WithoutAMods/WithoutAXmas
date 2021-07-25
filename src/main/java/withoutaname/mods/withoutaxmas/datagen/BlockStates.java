package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
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
			int size = blockState.getValue(PresentBlock.SIZE_PROPERTY);
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
			return getPresentModelFile(blockState.getValue(PresentBlock.COLOR_PROPERTY), size, parent);
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
		horizontalBlock(XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_bottom")), 90);
		horizontalBlock(XmasTreeRegistration.XMAS_TREE_MIDDLE_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_middle")), 90);
		horizontalBlock(XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get(), models().getExistingFile(modLoc("block/xmas_tree_top")), 90);
	}

}
