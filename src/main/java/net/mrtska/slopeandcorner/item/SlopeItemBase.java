package net.mrtska.slopeandcorner.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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

        BlockPlaceContext context = this.updatePlacementContext(blockPlaceContext);
        if (context == null) {
            return InteractionResult.FAIL;
        }
        BlockState blockstate = this.getPlacementState(context);
        if (blockstate == null) {
            return InteractionResult.FAIL;
        } else if (!this.placeBlock(context, blockstate)) {
            return InteractionResult.FAIL;
        }

        var pos = context.getClickedPos();
        var level = context.getLevel();
        var player = context.getPlayer();
        var stack = context.getItemInHand();

        var state = level.getBlockState(pos);

        if (level.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

            var tag = stack.getTag();
            if (tag == null) {
                return InteractionResult.FAIL;
            }

            var a = ((int)Mth.wrapDegrees((player.getYRot() + 180F) * 4F / 360F + 0.5F)) & 3;

            var direction = "";

            switch (a) {
                case 0 -> direction += "NORTH";
                case 1 -> direction += "EAST";
                case 2 -> direction += "SOUTH";
                case 3 -> direction += "WEST";
            }

            entity.setTexture(tag.getString("Texture"));
            entity.setDirection(direction);
        }


        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
        SoundType soundtype = state.getSoundType(level, pos, player);
        level.playSound(player, pos, this.getPlaceSound(state, level, pos, player), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        if (player == null || !player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
