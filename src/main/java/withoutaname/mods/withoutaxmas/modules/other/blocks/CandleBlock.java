package withoutaname.mods.withoutaxmas.modules.other.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CandleBlock extends TorchBlock {

	public CandleBlock() {
		super(Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.lightValue(10)
				.sound(SoundType.WOOD));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.SMOKE, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

}
