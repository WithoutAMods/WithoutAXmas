package withoutaname.mods.withoutaxmas.modules.xmastree.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import withoutaname.mods.withoutaxmas.modules.xmastree.blocks.XmasTreeBlock;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

public class XmasTreeItem extends Item {

	public XmasTreeItem() {
		super(new Properties()
				.maxStackSize(64)
				.group(ModSetup.defaultItemGroup));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = world.getBlockState(context.getPos()).getMaterial().isReplaceable() ? context.getPos() : context.getPos().offset(context.getFace());
		if (XmasTreeBlock.isEnoughSpace(world, pos)) {
			if (!world.isRemote) {
				XmasTreeBlock.createTree(world, pos, context.getPlacementHorizontalFacing());
				context.getItem().shrink(1);
			}
			return ActionResultType.SUCCESS;
		}
		return super.onItemUse(context);
	}

}
