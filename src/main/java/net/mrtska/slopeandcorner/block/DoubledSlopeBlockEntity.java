package net.mrtska.slopeandcorner.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.mrtska.slopeandcorner.SlopeAndCorner;

import javax.annotation.Nonnull;

/**
 * Block entity for doubled slope blocks.
 */
public class DoubledSlopeBlockEntity extends SlopeBlockEntity {

    /**
     * Other side slope block texture.
     */
    private String texture2 = "";

    /**
     * Other side slope block direction.
     */
    private String blockType2 = "";

    /**
     * Other side original block registry name.
     */
    private String blockName2 = "";

    public DoubledSlopeBlockEntity(BlockPos pos, BlockState state) {
        super(SlopeAndCorner.BlockEntityTypes.doubledSlopeBlock, pos, state);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);

        this.blockType2 = tag.getString("BlockType2");
        this.texture2 = tag.getString("Texture2");
        this.blockName2 = tag.getString("BlockName2");
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putString("BlockType2", this.blockType2);
        tag.putString("Texture2", this.texture2);
        tag.putString("BlockName2", this.blockName2);
    }

    public String getBlockType2() {
        return blockType2;
    }

    public void setBlockType2(String blockType) {
        this.blockType2 = blockType;
    }

    public String getTexture2() {
        return texture2;
    }

    public void setTexture2(String texture) {
        this.texture2 = texture;
    }

    public String getBlockName2() {
        return blockName2;
    }

    public void setBlockName2(String blockName) {
        this.blockName2 = blockName;
    }
}
