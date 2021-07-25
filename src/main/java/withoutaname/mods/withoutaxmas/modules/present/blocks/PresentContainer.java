package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import withoutaname.mods.withoutalib.blocks.BaseContainer;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;

import javax.annotation.Nonnull;

public class PresentContainer extends BaseContainer {

	private final BlockEntity tileEntity;
	private final Player playerEntity;

	public PresentContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
		super(PresentRegistration.PRESENT_CONTAINER.get(), windowId, 3);
		tileEntity = world.getBlockEntity(pos);
		this.playerEntity = player;

		if (tileEntity != null) {
			tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
				addSlotRow(h, 0, 44, 20, 3, 36);
			});
		}
		addPlayerInventorySlots(new InvWrapper(playerInventory), 8, 51);
	}

	@Override
	public boolean stillValid(@Nonnull Player playerIn) {
		return stillValid(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, PresentRegistration.PRESENT_BLOCK.get());
	}

}