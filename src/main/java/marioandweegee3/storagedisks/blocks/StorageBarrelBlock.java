package marioandweegee3.storagedisks.blocks;

import marioandweegee3.storagedisks.blocks.entities.StorageBarrelBlockEntity;
import marioandweegee3.storagedisks.items.DiskItem;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StorageBarrelBlock extends FacingBlock implements BlockEntityProvider {
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public static final Block BLOCK = new StorageBarrelBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());

    protected StorageBarrelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new StorageBarrelBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        StorageBarrelBlockEntity be = (StorageBarrelBlockEntity) world.getBlockEntity(pos);
        if(stack.isEmpty() || !(stack.getItem() instanceof DiskItem)){
            if(!be.isDiskInvNull()){
                
            }
        }
    }

}