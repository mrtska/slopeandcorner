package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.SlopeEntry;
import net.mrtska.slopeandcorner.block.DoubledSlopeBlockEntity;
import net.mrtska.slopeandcorner.block.SlopeBlockEntity;
import net.mrtska.slopeandcorner.item.SlopeItemBase;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;
import net.mrtska.slopeandcorner.util.SlopeType;

import javax.annotation.Nonnull;

/**
 * Slope block item.
 */
public class SlopeItem extends SlopeItemBase {

    public SlopeItem(SlopeBlock block, CreativeModeTab tab) {
        super(block, new Properties().tab(tab));
        this.setRegistryName(SlopeAndCorner.MODID, "slopeitem");
    }

    @Override
    protected boolean placeBlock(@Nonnull BlockPlaceContext context, @Nonnull BlockState state) {

        var tag = context.getItemInHand().getTag();
        if (tag == null) {

            return false;
        }
        var texture2 = tag.getString("Texture");

        var opposite = context.getClickedPos().relative(context.getClickedFace().getOpposite());
        if (context.getLevel().getBlockEntity(opposite) instanceof SlopeBlockEntity entity && !(entity instanceof DoubledSlopeBlockEntity)) {

            var blockType = SlopeType.parse(entity.getBlockType());

            var newState = SlopeAndCorner.doubledSlopeBlock.defaultBlockState()
                    .setValue(SlopeBlockStateProperties.SLOPE_TYPE, blockType)
                    .setValue(SlopeBlockStateProperties.SLOPE_TYPE2, blockType.opposite(!blockType.getSerializedName().contains("2")))
                    .setValue(SlopeBlockStateProperties.TRANSPARENT, entity.getTexture().contains("glass") || texture2.contains("glass"));
            var bool = context.getLevel().setBlock(opposite, newState, 11);
            if (bool && context.getLevel().getBlockEntity(opposite) instanceof DoubledSlopeBlockEntity doubledEntity) {

                doubledEntity.setBlockType(entity.getBlockType());
                doubledEntity.setTexture(entity.getTexture());
                doubledEntity.setBlockName(entity.getBlockName());
                doubledEntity.setBlockType2(blockType.opposite(!blockType.getSerializedName().contains("2")).getSerializedName().toUpperCase());
                doubledEntity.setTexture2(texture2);
            }

            return bool;
        }

        return context.getLevel().setBlock(context.getClickedPos(), state, 11);
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> list) {

        if (tab != this.getItemCategory()) {

            return;
        }

        // Register slope blocks for each vanilla blocks to creative mode tab.
        for (var entry : SlopeEntry.getSlopeBlocks()) {

            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "SOUTH");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "RSOUTH");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "SOUTH2");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
        }
    }
}
