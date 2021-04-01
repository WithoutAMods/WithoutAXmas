package withoutaname.mods.withoutaxmas.modules.other.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class AdventWreathBlock extends Block {

	public static final VoxelShape SHAPE = VoxelShapes.create(.125, .0, .125, .875, .1875, .875);

	public AdventWreathBlock() {
		super(Properties.create(Material.LEAVES)
				.sound(SoundType.PLANT)
				.hardnessAndResistance(1.5F)
				.setLightLevel((state) -> 5));
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

}