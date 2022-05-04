package net.mrtska.slopeandcorner.model;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Slope item renderer overrides.
 */
public class SlopeItemOverrides extends ItemOverrides {

    @Nullable
    @Override
    public BakedModel resolve(@NotNull BakedModel originalModel, @NotNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity livingEntity, int seed) {

        CompoundTag tag = stack.getTag();

        if (tag == null || !stack.hasTag()) {

            return originalModel;
        }

        if (originalModel instanceof BakedSlopeModel slopeModel) {

            slopeModel.setModel(new String[]{ tag.getString("BlockType") }, new String[]{ tag.getString("Texture") });
            return slopeModel;
        }

        return originalModel;
    }
}
