package withoutaname.mods.withoutaxmas.modules.xmastree.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

import javax.annotation.Nonnull;

public class XmasTreeBlock extends Block {
	
	protected VoxelShape shape = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	
	public XmasTreeBlock() {
		super(Properties.of(Material.WOOD)
				.sound(SoundType.WOOD)
				.strength(2));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
	}
	
	public static boolean createTree(Level world, BlockPos pos, Direction facing) {
		if (isEnoughSpace(world, pos)) {
			world.setBlockAndUpdate(pos, XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get()
					.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing));
			world.setBlockAndUpdate(pos.above(), XmasTreeRegistration.XMAS_TREE_MIDDLE_BLOCK.get()
					.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing));
			world.setBlockAndUpdate(pos.above(2), XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get()
					.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing));
			return true;
		}
		return false;
	}
	
	public static boolean isEnoughSpace(Level world, BlockPos pos) {
		boolean enoughSpace = true;
		for (int i = 0; i < 3; i++) {
			if (!world.getBlockState(pos.above(i)).getMaterial().isReplaceable()) {
				enoughSpace = false;
				break;
			}
		}
		return enoughSpace;
	}
	
	@Override
	public Item asItem() {
		return XmasTreeRegistration.XMAS_TREE_ITEM.get();
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING);
	}
	
	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return this.shape;
	}
	
	@Override
	public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		if (!world.isClientSide) {
			removeTree(world, pos);
		}
		super.onBlockExploded(state, world, pos, explosion);
	}
	
	@Override
	public void playerWillDestroy(Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
		if (!worldIn.isClientSide) {
			removeTree(worldIn, pos);
		}
		super.playerWillDestroy(worldIn, pos, state, player);
	}
	
	public void removeTree(Level world, BlockPos pos) {
		if (!(world.getBlockState(pos).getBlock() == XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get())) {
			boolean end;
			for (int i = 1; world.getBlockState(pos.below(i)).getBlock() instanceof XmasTreeBlock; i++) {
				end = world.getBlockState(pos.below(i)).getBlock() == XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get();
				world.setBlockAndUpdate(pos.below(i), Blocks.AIR.defaultBlockState());
				if (end) {
					break;
				}
			}
		}
		if (!(world.getBlockState(pos).getBlock() == XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get())) {
			boolean end;
			for (int i = 1; world.getBlockState(pos.above(i)).getBlock() instanceof XmasTreeBlock; i++) {
				end = world.getBlockState(pos.above(i)).getBlock() == XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get();
				world.setBlockAndUpdate(pos.above(i), Blocks.AIR.defaultBlockState());
				if (end) {
					break;
				}
			}
		}
	}
	
}
