package withoutaname.mods.withoutapresent.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
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
		return getDefaultState().with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing());
	}

	public static Color[] getAllColors() {
		return new Color[]{Color.BLUE, Color.GREEN, Color.PURPLE, Color.RED, Color.YELLOW};
	}

	public enum  Color implements IStringSerializable {
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

		public RegistryObject<PresentBlock> getBlockRegistryObject() {
			switch (this.name) {
				case "blue":
					return Registration.PRESENT_BLUE_BLOCK;
				case "green":
					return Registration.PRESENT_GREEN_BLOCK;
				case "purple":
					return Registration.PRESENT_PURPLE_BLOCK;
				case "red":
					return Registration.PRESENT_RED_BLOCK;
				case "yellow":
					return Registration.PRESENT_YELLOW_BLOCK;
			}
			return null;
		}

		public RegistryObject<Item> getItemRegistryObject() {
			switch (this.name) {
				case "blue":
					return Registration.PRESENT_BLUE_ITEM;
				case "green":
					return Registration.PRESENT_GREEN_ITEM;
				case "purple":
					return Registration.PRESENT_PURPLE_ITEM;
				case "red":
					return Registration.PRESENT_RED_ITEM;
				case "yellow":
					return Registration.PRESENT_YELLOW_ITEM;
			}
			return null;
		}
	}

}
