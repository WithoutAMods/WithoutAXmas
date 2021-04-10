package withoutaname.mods.withoutaxmas.modules.xmastree.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import withoutaname.mods.withoutaxmas.modules.xmastree.blocks.XmasTreeBlock;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

import net.minecraft.item.Item.Properties;

public class XmasTreeItem extends Item {

	public XmasTreeItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(ModSetup.defaultItemGroup));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = world.getBlockState(context.getClickedPos()).getMaterial().isReplaceable() ? context.getClickedPos() : context.getClickedPos().relative(context.getClickedFace());
		if (XmasTreeBlock.isEnoughSpace(world, pos)) {
			if (!world.isClientSide) {
				XmasTreeBlock.createTree(world, pos, context.getHorizontalDirection());
				context.getItemInHand().shrink(1);
			}
			return ActionResultType.SUCCESS;
		}
		return super.useOn(context);
	}

}
