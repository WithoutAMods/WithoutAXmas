package withoutaname.mods.withoutaxmas.modules.other.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class AdventWreathBlock extends Block {

	public static final VoxelShape SHAPE = Shapes.box(.125, .0, .125, .875, .1875, .875);

	public AdventWreathBlock() {
		super(Properties.of(Material.LEAVES)
				.sound(SoundType.GRASS)
				.strength(1.5F)
				.lightLevel((state) -> 5));
	}

	@Nonnull
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return SHAPE;
	}

}