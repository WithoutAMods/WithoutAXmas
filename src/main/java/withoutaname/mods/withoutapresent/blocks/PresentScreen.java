package withoutaname.mods.withoutapresent.blocks;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import withoutaname.mods.withoutalib.blocks.BaseScreen;
import withoutaname.mods.withoutapresent.WithoutAPresent;

public class PresentScreen extends BaseScreen<PresentContainer> {

	public PresentScreen(PresentContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, new ResourceLocation(WithoutAPresent.MODID, "textures/gui/container/present_gui.png"), playerInventory, title, 176, 133);
	}

}
