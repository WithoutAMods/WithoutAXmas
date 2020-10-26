package withoutaname.mods.withoutaxmas.modules.xmastree.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import withoutaname.mods.withoutaxmas.modules.xmastree.setup.XmasTreeRegistration;

public class XmasTreeBlock extends Block{

	protected VoxelShape shape = VoxelShapes.create(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);

	public XmasTreeBlock() {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(2));
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.shape;
	}
	
	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
		if(!world.isRemote) {
			removeTree(world, pos);
		}
		super.onBlockExploded(state, world, pos, explosion);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if(!worldIn.isRemote) {
			removeTree(worldIn, pos);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	public static boolean createTree(World world, BlockPos pos) {
		boolean enoughSpace = true;
		for(int i = 0; i < 3; i++) {
			if(!world.getBlockState(pos.up(i)).getMaterial().isReplaceable()) {
				enoughSpace = false;
				break;
			}
		}
		
		if(enoughSpace) {
			world.setBlockState(pos, XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get().getDefaultState());
			world.setBlockState(pos.up(), XmasTreeRegistration.XMAS_TREE_MIDDLE_BLOCK.get().getDefaultState());
			world.setBlockState(pos.up(2), XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get().getDefaultState());
		}
		
		return enoughSpace;
	}
	
	public void removeTree(World world, BlockPos pos) {
		if(!(world.getBlockState(pos).getBlock() == XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get())) {
			boolean end;
			for(int i = 1; world.getBlockState(pos.down(i)).getBlock() instanceof XmasTreeBlock; i++) {
				end = world.getBlockState(pos.down(i)).getBlock() == XmasTreeRegistration.XMAS_TREE_BOTTOM_BLOCK.get();
				world.setBlockState(pos.down(i), Blocks.AIR.getDefaultState());
				if(end) {
					break;
				}
			}
		}
		if(!(world.getBlockState(pos).getBlock() == XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get())) {
			boolean end;
			for(int i = 1; world.getBlockState(pos.up(i)).getBlock() instanceof XmasTreeBlock; i++) {
				end = world.getBlockState(pos.up(i)).getBlock() == XmasTreeRegistration.XMAS_TREE_TOP_BLOCK.get();
				world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState());
				if(end) {
					break;
				}
			}
		}
	}

}
