package withoutaname.mods.withoutaxmas.modules.other.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Random;

public class CandleBlock extends TorchBlock {

	public CandleBlock() {
		super(Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel((state) -> 10)
				.sound(SoundType.WOOD),
				ParticleTypes.FLAME);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(@Nonnull BlockState stateIn, Level worldIn, BlockPos pos, @Nonnull Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.SMOKE, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.flameParticle, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

}
