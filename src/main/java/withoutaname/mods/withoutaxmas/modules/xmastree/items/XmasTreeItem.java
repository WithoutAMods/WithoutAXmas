package withoutaname.mods.withoutaxmas.modules.xmastree.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import withoutaname.mods.withoutaxmas.modules.xmastree.blocks.XmasTreeBlock;
import withoutaname.mods.withoutaxmas.setup.ModSetup;

import javax.annotation.Nonnull;

public class XmasTreeItem extends Item {

	public XmasTreeItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(ModSetup.DEFAULT_CREATIVE_TAB));
	}

	@Nonnull
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = level.getBlockState(context.getClickedPos()).getMaterial().isReplaceable() ? context.getClickedPos() : context.getClickedPos().relative(context.getClickedFace());
		if (XmasTreeBlock.isEnoughSpace(level, pos)) {
			if (!level.isClientSide) {
				XmasTreeBlock.createTree(level, pos, context.getHorizontalDirection());
				context.getItemInHand().shrink(1);
			}
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}

}
