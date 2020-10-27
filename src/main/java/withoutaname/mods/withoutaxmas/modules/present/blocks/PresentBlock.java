package withoutaname.mods.withoutaxmas.modules.present.blocks;

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
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

public class PresentBlock extends Block {

	public static final EnumProperty<Color> COLOR_PROPERTY = EnumProperty.create("color", Color.class);
	public static final IntegerProperty SIZE_PROPERTY = IntegerProperty.create("size", 0, 2);

	public static final VoxelShape SHAPE_0 = VoxelShapes.create(.25, .0, .25, .75, .5, .75);
	public static final VoxelShape SHAPE_1 = VoxelShapes.create(.1875, .0, .1875, .8125, .625, .8125);
	public static final VoxelShape SHAPE_2 = VoxelShapes.create(.125, .0, .125, .875, .75, .875);

	public PresentBlock() {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(2.5F));
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(COLOR_PROPERTY, Color.BLUE)
				.with(SIZE_PROPERTY, 0));
	}

	@Override
	public Item asItem() {
		return PresentRegistration.PRESENT_BLUE_ITEM.get();
	}


	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PresentTile();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, COLOR_PROPERTY, SIZE_PROPERTY);
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(SIZE_PROPERTY)) {
			default:
			case 0:
				return SHAPE_0;
			case 1:
				return SHAPE_1;
			case 2:
				return SHAPE_2;
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
		if (!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof PresentTile) {
				PresentTile presentTile = ((PresentTile) tileEntity);
				presentTile.setWorldAndPos(world, pos);

				if (player.getUniqueID().equals(presentTile.getPlacer())) {
					INamedContainerProvider containerProvider = new INamedContainerProvider() {
						@Override
						public ITextComponent getDisplayName() {
							return new TranslationTextComponent("screen.withoutaxmas.present");
						}

						@Override
						public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
							return new PresentContainer(i, world, pos, playerInventory, playerEntity);
						}
					};
					NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
				} else {
					presentTile.openPresent(world, pos);
				}
			} else {
				throw new IllegalStateException("No tile entity found!");
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
		((PresentTile) world.getTileEntity(pos)).dropInventory(world, pos);
		super.onBlockExploded(state, world, pos, explosion);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		((PresentTile) worldIn.getTileEntity(pos)).dropInventory(worldIn, pos);
		super.onBlockHarvested(worldIn, pos, state, player);
	}

}