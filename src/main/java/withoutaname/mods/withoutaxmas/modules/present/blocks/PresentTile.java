package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;

import javax.annotation.Nonnull;

public class PresentTile extends TileEntity {

	private ItemStackHandler itemHandler = createItemHandler();

	private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

	public PresentTile() {
		super(PresentRegistration.PRESENT_TILE.get());
	}

	public void dropInventory(World world, BlockPos pos) {
		this.setWorldAndPos(world, pos);
		ItemStack itemStack;
		for(int i = 0; i < 3; i++) {
			itemStack = itemHandler.getStackInSlot(i);
			world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
		}
		markDirty();
	}

	@Override
	public void remove() {
		super.remove();
		handler.invalidate();
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("inv", itemHandler.serializeNBT());
		return super.write(tag);
	}

	private ItemStackHandler createItemHandler() {
		return new ItemStackHandler(3) {

			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == null) {
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}

}
