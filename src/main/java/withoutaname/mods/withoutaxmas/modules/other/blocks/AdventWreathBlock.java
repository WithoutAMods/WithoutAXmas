package withoutaname.mods.withoutaxmas.modules.other.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentContainer;
import withoutaname.mods.withoutaxmas.modules.present.blocks.PresentTile;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

public class AdventWreathBlock extends Block {

	public static final VoxelShape SHAPE = VoxelShapes.create(.125, .0, .125, .875, .1875, .875);

	public AdventWreathBlock() {
		super(Properties.create(Material.LEAVES)
				.sound(SoundType.PLANT)
				.hardnessAndResistance(1.5F)
				.lightValue(5));
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

}