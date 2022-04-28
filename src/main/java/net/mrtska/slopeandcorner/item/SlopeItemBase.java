package net.mrtska.slopeandcorner.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.mrtska.slopeandcorner.slope.SlopeBlockEntity;

import javax.annotation.Nonnull;

public abstract class SlopeItemBase extends BlockItem {

    public SlopeItemBase(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @Nonnull InteractionResult place(BlockPlaceContext blockPlaceContext) {

        if (!blockPlaceContext.canPlace()) {
            return InteractionResult.FAIL;
        }

        BlockPlaceContext blockplacecontext = this.updatePlacementContext(blockPlaceContext);
        if (blockplacecontext == null) {
            return InteractionResult.FAIL;
        }
        BlockState blockstate = this.getPlacementState(blockplacecontext);
        if (blockstate == null) {
            return InteractionResult.FAIL;
        } else if (!this.placeBlock(blockplacecontext, blockstate)) {
            return InteractionResult.FAIL;
        } else {
            BlockPos pos = blockplacecontext.getClickedPos();
            Level level = blockplacecontext.getLevel();
            Player player = blockplacecontext.getPlayer();
            ItemStack stack = blockplacecontext.getItemInHand();

            BlockState state = level.getBlockState(pos);

            if (level.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

                var tag = stack.getTag();

                entity.setTexture(tag.getString("Texture"));
                entity.setDirection(tag.getString("Direction"));
            }

            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            SoundType soundtype = state.getSoundType(level, pos, blockPlaceContext.getPlayer());
            level.playSound(player, pos, this.getPlaceSound(state, level, pos, blockPlaceContext.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            if (player == null || !player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }
}
