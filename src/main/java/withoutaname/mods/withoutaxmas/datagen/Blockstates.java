package withoutaname.mods.withoutaxmas.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import withoutaname.mods.withoutaxmas.WithoutAXmas;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

import java.util.HashMap;

public class Blockstates extends BlockStateProvider {

	public Blockstates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WithoutAXmas.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		registerPresent();
	}

	private void registerPresent() {
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
		HashMap<Color, ModelFile> coloredPresent = new HashMap<>();
		for (Color color : Color.getValues()) {
			coloredPresent.put(color, models().getBuilder("block/present_" + color)
					.parent(present)
					.texture("side", modLoc("block/present_" + color))
					.texture("top", modLoc("block/present_" + color + "_top")));
			itemModels().withExistingParent("present_" + color, modLoc("block/present_" + color));
		}
		horizontalBlock(PresentRegistration.PRESENT_BLOCK.get(), (blockState) -> coloredPresent.get(blockState.get(PresentBlock.COLOR_PROPERTY)));
	}

}
