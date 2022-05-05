package net.mrtska.slopeandcorner.item;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.mrtska.slopeandcorner.block.SlopeBlockEntity;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;

import javax.annotation.Nonnull;

public abstract class SlopeItemBase extends BlockItem {

    public SlopeItemBase(Block block, Properties properties) {
        super(block, properties);
    }

    /**
     * Fill block entity information from compound tag and state.
     * @param entity target block entity.
     * @param state block state.
     * @param player player who placed block.
     * @param tag NBT from held item stack.
     */
    protected void fillBlockEntity(@Nonnull SlopeBlockEntity entity, @Nonnull BlockState state, @Nonnull Player player, @Nonnull CompoundTag tag) {

        entity.setBlockName(tag.getString("BlockName"));
        entity.setTexture(tag.getString("Texture"));
        entity.setBlockType(state.getValue(SlopeBlockStateProperties.SLOPE_TYPE).getSerializedName().toUpperCase());
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
            if (tag == null || player == null) {
                return InteractionResult.FAIL;
            }

            this.fillBlockEntity(entity, blockstate, player, tag);
        } else {
            return InteractionResult.FAIL;
        }

        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
        SoundType soundtype = state.getSoundType(level, pos, player);
        level.playSound(player, pos, this.getPlaceSound(state, level, pos, player), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public @Nonnull String getDescriptionId(ItemStack stack) {

        var tag = stack.getTag();
        if (tag == null) {
            return super.getDescriptionId(stack);
        }

        // Use vanilla block localization.
        var blockName = tag.getString("BlockName");
        var block = Registry.BLOCK.get(new ResourceLocation(blockName));

        return block.asItem().getDescriptionId();
    }
}
