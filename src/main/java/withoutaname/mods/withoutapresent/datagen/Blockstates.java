package withoutaname.mods.withoutapresent.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import withoutaname.mods.withoutapresent.WithoutAPresent;
import withoutaname.mods.withoutapresent.blocks.PresentBlock;

public class Blockstates extends BlockStateProvider {

	public Blockstates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WithoutAPresent.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		ModelFile present =models().getBuilder("block/present")
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
		for (PresentBlock.Color color : PresentBlock.getAllColors()) {
			ModelFile coloredPresent = models().getBuilder("block/present_" + color)
					.parent(present)
					.texture("side", modLoc("block/present_" + color))
					.texture("top", modLoc("block/present_" + color + "_top"));
			horizontalBlock((Block) color.getBlockRegistryObject().get(), coloredPresent);
			itemModels().withExistingParent("present_" + color, modLoc("block/present_" + color));
		}

	}

}
