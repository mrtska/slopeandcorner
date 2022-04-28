package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.mrtska.slopeandcorner.SlopeAndCorner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Block entity for slope blocks.
 */
public class SlopeBlockEntity extends BlockEntity implements IModelData {

    /**
     * Slope block texture.
     */
    private String texture;

    /**
     * Slope block direction.
     */
    private String direction;

    public SlopeBlockEntity(BlockPos pos, BlockState state) {
        super(SlopeAndCorner.BlockEntityTypes.slopeBlock, pos, state);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);

        this.direction = tag.getString("Direction");
        this.texture = tag.getString("Texture");
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putString("Direction", this.direction);
        tag.putString("Texture", this.texture);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {

        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @Nonnull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return this;
    }

    @Override
    public boolean hasProperty(ModelProperty<?> prop) {
        return false;
    }

    @Nullable
    @Override
    public <T> T getData(ModelProperty<T> prop) {
        return null;
    }

    @Nullable
    @Override
    public <T> T setData(ModelProperty<T> prop, T data) {
        return null;
    }
}
