package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import withoutaname.mods.withoutalib.blocks.BaseScreen;
import withoutaname.mods.withoutaxmas.WithoutAXmas;

public class PresentScreen extends BaseScreen<PresentContainer> {

	public PresentScreen(PresentContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, new ResourceLocation(WithoutAXmas.MODID, "textures/gui/container/present_gui.png"), playerInventory, title, 176, 133);
	}

}
