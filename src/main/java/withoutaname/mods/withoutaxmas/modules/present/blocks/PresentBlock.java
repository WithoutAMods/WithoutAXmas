package withoutaname.mods.withoutaxmas.modules.present.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import withoutaname.mods.withoutaxmas.modules.present.setup.PresentRegistration;
import withoutaname.mods.withoutaxmas.modules.present.tools.Color;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PresentBlock extends BaseEntityBlock {

	public static final EnumProperty<Color> COLOR_PROPERTY = EnumProperty.create("color", Color.class);
	public static final IntegerProperty SIZE_PROPERTY = IntegerProperty.create("size", 0, 2);

	public static final VoxelShape SHAPE_0 = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	public static final VoxelShape SHAPE_1 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	public static final VoxelShape SHAPE_2 = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public PresentBlock() {
		super(Properties.of(Material.WOOD)
				.sound(SoundType.WOOD)
				.strength(2.5F));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.setValue(COLOR_PROPERTY, Color.BLUE)
				.setValue(SIZE_PROPERTY, 0));
	}

	@Nonnull
	@Override
	public Item asItem() {
		return PresentRegistration.PRESENT_BLUE_ITEM.get();
	}
	
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new PresentTile(pos, state);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, COLOR_PROPERTY, SIZE_PROPERTY);
	}

	@Nonnull
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return switch (state.getValue(SIZE_PROPERTY)) {
			default -> SHAPE_0;
			case 1 -> SHAPE_1;
			case 2 -> SHAPE_2;
		};
	}
	
	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState pState) {
		return RenderShape.MODEL;
	}

	@Nonnull
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(@Nonnull BlockState state, Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult trace) {
		if (!world.isClientSide) {
			BlockEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof PresentTile presentTile) {
				if (player.getUUID().equals(presentTile.getPlacer())) {
					SimpleMenuProvider menuProvider = new SimpleMenuProvider(new MenuConstructor() {
						@Nonnull
						@Override
						public AbstractContainerMenu createMenu(int pContainerId, @Nonnull Inventory pInventory, @Nonnull Player pPlayer) {
							return new PresentContainer(pContainerId, world, pos, pInventory, pPlayer);
						}
					}, new TranslatableComponent("screen.withoutaxmas.present"));
					NetworkHooks.openGui((ServerPlayer) player, menuProvider, tileEntity.getBlockPos());
				} else {
					presentTile.openPresent();
				}
			} else {
				throw new IllegalStateException("No tile entity found!");
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
		((PresentTile) world.getBlockEntity(pos)).dropInventory();
		super.onBlockExploded(state, world, pos, explosion);
	}

	@Override
	public void playerWillDestroy(Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
		((PresentTile) worldIn.getBlockEntity(pos)).dropInventory();
		super.playerWillDestroy(worldIn, pos, state, player);
	}

}