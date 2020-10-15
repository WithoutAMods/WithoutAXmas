package withoutaname.mods.withoutapresent.blocks;

import com.sun.org.apache.regexp.internal.RE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import org.jetbrains.annotations.Nullable;
import withoutaname.mods.withoutapresent.setup.Registration;

public class PresentBlock extends Block {

	public static final EnumProperty<Color> SIGNAL_MASTSIGN = EnumProperty.create("mastsign", Color.class);

	protected VoxelShape shape = VoxelShapes.create(.125, .0, .125, .875, .75, .875);

	public PresentBlock() {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(2.5F));
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.shape;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	public enum Color implements IStringSerializable {
		BLUE("blue"),
		GREEN("green"),
		PURPLE("purple"),
		RED("red"),
		YELLOW("yellow");

		private final String name;

		Color(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}

		@Override
		public String toString() {
			return getString();
		}

		public static Color[] getAll() {
			return new Color[] {BLUE, GREEN, PURPLE, RED, YELLOW};
		}

	}

}
