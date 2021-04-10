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

import net.minecraft.block.AbstractBlock.Properties;

public class AdventWreathBlock extends Block {

	public static final VoxelShape SHAPE = VoxelShapes.box(.125, .0, .125, .875, .1875, .875);

	public AdventWreathBlock() {
		super(Properties.of(Material.LEAVES)
				.sound(SoundType.GRASS)
				.strength(1.5F)
				.lightLevel((state) -> 5));
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

}