package withoutaname.mods.withoutaxmas.modules.present.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentTile;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

import java.util.UUID;

public class PresentItem extends Item {

	private final Color color;

	public PresentItem(Color color) {
		super(ModSetup.DEFAULT_ITEM_PROPERTIES);
		this.color = color;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = world.getBlockState(context.getClickedPos()).getMaterial().isReplaceable() ? context.getClickedPos() : context.getClickedPos().relative(context.getClickedFace());
		if (world.getBlockState(pos).getMaterial().isReplaceable()) {
			if (!world.isClientSide) {
				Direction facing = context.getHorizontalDirection();
				world.setBlockAndUpdate(pos, PresentRegistration.PRESENT_BLOCK.get().defaultBlockState()
						.setValue(BlockStateProperties.HORIZONTAL_FACING, facing)
						.setValue(PresentBlock.COLOR_PROPERTY, this.color)
						.setValue(PresentBlock.SIZE_PROPERTY, 0));
				BlockEntity tileEntity = world.getBlockEntity(pos);
				if (tileEntity instanceof PresentTile) {
					UUID player = context.getPlayer().getUUID();
					((PresentTile) tileEntity).setPlacer(player);
				} else {
					throw new IllegalStateException("No tile entity found!");
				}
				context.getItemInHand().shrink(1);
			}
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}

}
