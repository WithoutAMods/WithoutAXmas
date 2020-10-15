package withoutaname.mods.withoutapresent.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
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
import org.jetbrains.annotations.Nullable;

public class PresentBlock extends Block {

	public static final EnumProperty<Color> COLOR_PROPERTY = EnumProperty.create("color", Color.class);

	protected VoxelShape shape = VoxelShapes.create(.125, .0, .125, .875, .75, .875);

	public PresentBlock() {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(2.5F));
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(COLOR_PROPERTY, Color.BLUE));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.shape;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, COLOR_PROPERTY);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState()
				.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing())
				.with(COLOR_PROPERTY, Color.BLUE);
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
