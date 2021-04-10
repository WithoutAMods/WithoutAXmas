package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import withoutaname.mods.withoutalib.blocks.BaseContainer;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;

public class PresentContainer extends BaseContainer {

	private TileEntity tileEntity;
	private PlayerEntity playerEntity;

	public PresentContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
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
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, PresentRegistration.PRESENT_BLOCK.get());
	}

}