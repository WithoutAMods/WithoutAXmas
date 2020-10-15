package withoutaname.mods.withoutapresent.datagen;

import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import withoutaname.mods.withoutapresent.WithoutAPresent;
import withoutaname.mods.withoutapresent.blocks.PresentBlock;
import withoutaname.mods.withoutapresent.setup.Registration;

import java.util.HashMap;
import java.util.function.Function;

public class Blockstates extends BlockStateProvider {

	public Blockstates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WithoutAPresent.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		ModelFile present = models().getBuilder("block/present")
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
		HashMap<PresentBlock.Color, ModelFile> coloredPresent = new HashMap<>();
		for (PresentBlock.Color color : PresentBlock.Color.getAll()) {
			coloredPresent.put(color, models().getBuilder("block/present_" + color)
					.parent(present)
					.texture("side", modLoc("block/present_" + color))
					.texture("top", modLoc("block/present_" + color + "_top")));
			itemModels().withExistingParent("present_" + color, modLoc("block/present_" + color));
		}
		horizontalBlock(Registration.PRESENT_BLOCK.get(), new Function<BlockState, ModelFile>() {
			@Override
			public ModelFile apply(BlockState blockState) {
				return coloredPresent.get(blockState.get(PresentBlock.COLOR_PROPERTY));
			}
		});
	}

}
