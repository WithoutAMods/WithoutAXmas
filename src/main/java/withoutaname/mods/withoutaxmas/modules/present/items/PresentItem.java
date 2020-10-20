package withoutaname.mods.withoutaxmas.modules.present.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentBlock;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.setup.ModSetup;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

public class PresentItem extends Item {

	private final Color color;

	public PresentItem(Color color) {
		super(ModSetup.defaultItemProperties);
		this.color = color;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		if (!world.isRemote) {
			BlockPos pos = world.getBlockState(context.getPos()).getMaterial().isReplaceable() ? context.getPos() : context.getPos().offset(context.getFace());
			Direction facing = context.getPlacementHorizontalFacing();
			world.setBlockState(pos, PresentRegistration.PRESENT_BLOCK.get().getDefaultState()
					.with(BlockStateProperties.HORIZONTAL_FACING, facing)
					.with(PresentBlock.COLOR_PROPERTY, this.color));
			context.getItem().shrink(1);
			return ActionResultType.SUCCESS;

		}
		return super.onItemUse(context);
	}

}
