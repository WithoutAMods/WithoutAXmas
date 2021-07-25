package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import withoutaname.mods.withoutalib.blocks.BaseScreen;
import withoutaname.mods.withoutaxmas.WithoutAXmas;

@OnlyIn(Dist.CLIENT)
public class PresentScreen extends BaseScreen<PresentContainer> {

	public PresentScreen(PresentContainer container, Inventory playerInventory, Component title) {
		super(container, new ResourceLocation(WithoutAXmas.MODID, "textures/gui/container/present_gui.png"), playerInventory, title, 176, 133);
	}

}
