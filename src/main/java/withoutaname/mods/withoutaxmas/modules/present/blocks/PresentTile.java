package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class PresentTile extends BlockEntity {

	private UUID placer;

	private ItemStackHandler itemHandler = createItemHandler();

	private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

	public PresentTile(BlockPos pos, BlockState state) {
		super(PresentRegistration.PRESENT_TILE.get(), pos, state);
	}

	public void dropInventory() {
		ItemStack itemStack;
		for (int i = 0; i < 3; i++) {
			itemStack = itemHandler.getStackInSlot(i);
			level.addFreshEntity(new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), itemStack));
		}
		setChanged();
	}

	public void openPresent() {
		if (!this.isEmpty()) {
			level.setBlockAndUpdate(worldPosition, Blocks.AIR.defaultBlockState());
			this.dropInventory();
		}
	}

	public void setPlacer(UUID placer) {
		this.placer = placer;
	}

	public UUID getPlacer() {
		return this.placer;
	}

	public int getSize() {
		return switch (getStackAmount()) {
			default -> 0;
			case 2 -> 1;
			case 3 -> 2;
		};
	}

	public int getStackAmount() {
		int stackAmount = 0;
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			if (!itemHandler.getStackInSlot(i).isEmpty()) {
				stackAmount++;
			}
		}
		return stackAmount;
	}

	public boolean isEmpty() {
		return getStackAmount() == 0;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		handler.invalidate();
	}

	@Override
	public void load(@Nonnull CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		this.placer = nbt.getUUID("placer");
	}

	@Nonnull
	@Override
	public CompoundTag save(@Nonnull CompoundTag tag) {
		super.save(tag);
		tag.put("inv", itemHandler.serializeNBT());
		tag.putUUID("placer", this.placer);
		return tag;
	}

	private ItemStackHandler createItemHandler() {
		return new ItemStackHandler(3) {

			@Override
			protected void onContentsChanged(int slot) {
				level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(PresentBlock.SIZE_PROPERTY, getSize()));
				setChanged();
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
