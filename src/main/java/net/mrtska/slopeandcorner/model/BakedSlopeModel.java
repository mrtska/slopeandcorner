package net.mrtska.slopeandcorner.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.common.model.TransformationHelper;
import net.mrtska.slopeandcorner.SlopeEntry;
import net.mrtska.slopeandcorner.block.DoubledSlopeBlockEntity;
import net.mrtska.slopeandcorner.block.SlopeBlockEntity;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Baked model for slope blocks.
 */
public class BakedSlopeModel implements BakedModel {

    private final ResourceLocation modelLocation;

    private final SlopeModelBase modelBase;

    private List<BakedQuad> vertexData = new ArrayList<>();

    public BakedSlopeModel(ResourceLocation modelLocation, @Nonnull SlopeModelBase modelBase) {

        this.modelLocation = modelLocation;
        this.modelBase = modelBase;
        this.modelBase.init();
    }

    /**
     * Returns slope model vertex data.
     *
     * @return A model instance.
     */
    public @Nonnull SlopeModelBase getModel() {

        return this.modelBase;
    }

    /**
     * Set model information for item rendering.
     *
     * @param blockType List of directions.
     * @param texture List of textures.
     */
    public void setModel(String blockType, String texture) {

        this.vertexData = this.modelBase.getVertex(blockType, texture, null);
    }

    @Override
    public @Nonnull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        // Ignore it when side is specified.
        if (side != null) {

            return Collections.emptyList();
        }

        // Returns fixed vertex data when block state is null (e.g. item rendering)
        if (state == null) {

            return this.vertexData;
        }

        if (extraData instanceof SlopeBlockEntity entity) {

            var texture = entity.getTexture();
            if (texture == null) {
                this.vertexData = this.modelBase.getVertex("NORTH", "spruce_planks", null);
            } else {
                this.vertexData = this.modelBase.getVertex(state.getValue(SlopeBlockStateProperties.SLOPE_TYPE).getSerializedName().toUpperCase(), texture, side);
            }

            if (extraData instanceof DoubledSlopeBlockEntity doubledEntity) {

                var texture2 = doubledEntity.getTexture2();
                if (texture2 == null) {
                    this.vertexData.addAll(this.modelBase.getVertex("RSOUTH", "spruce_planks", null));
                } else {
                    this.vertexData.addAll(this.modelBase.getVertex(state.getValue(SlopeBlockStateProperties.SLOPE_TYPE2).getSerializedName().toUpperCase(), texture2, side));
                }
            }


        } else {

            this.vertexData = this.modelBase.getVertex("NORTH", "spruce_planks", null);
        }


        return this.vertexData;
    }
    @Override
    public @Nonnull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand) {

        if (side != null) {

            return Collections.emptyList();
        }
        // Returns fixed vertex data when block state is null (e.g. item rendering)
        if (state == null) {

            return this.vertexData;
        }

        this.vertexData = this.modelBase.getVertex("NORTH","spruce_planks", null);
        return this.vertexData;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public boolean doesHandlePerspectives() {
        return true;
    }

    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack poseStack) {

        Transformation tr = Transformation.identity();

        // Rotate and scale the models.
        switch (cameraTransformType) {
            case GUI -> {
                poseStack.translate(0, 0, 0);
                poseStack.scale(0.625F, 0.625F, 0.625F);
                poseStack.mulPose(TransformationHelper.quatFromXYZ(new Vector3f(30, 225, 0), true));
            }
            case FIRST_PERSON_RIGHT_HAND -> {
                poseStack.translate(0, 0, 0);
                poseStack.scale(0.4F, 0.4F, 0.4F);
                poseStack.mulPose(TransformationHelper.quatFromXYZ(new Vector3f(0, 45, 0), true));
            }
            case FIRST_PERSON_LEFT_HAND -> {
                poseStack.translate(0, 0, 0);
                poseStack.scale(0.4F, 0.4F, 0.4F);
                poseStack.mulPose(TransformationHelper.quatFromXYZ(new Vector3f(0, 225, 0), true));
            }
            case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                poseStack.translate(0, 0, 0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                poseStack.mulPose(TransformationHelper.quatFromXYZ(new Vector3f(75, 45, 0), true));
            }
            case GROUND -> {
                poseStack.translate(0, 0, 0);
                poseStack.scale(0.25F, 0.25F, 0.25F);
                poseStack.mulPose(TransformationHelper.quatFromXYZ(new Vector3f(0, 0, 0), true));
            }
        }

        tr.push(poseStack);

        return this;
    }

    @Override
    public @Nonnull TextureAtlasSprite getParticleIcon() {
        return SlopeModelBase.getTextureAtlasSprite("missingno");
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull IModelData data) {

        if (data instanceof SlopeBlockEntity entity) {

            var sprite = SlopeEntry.getSlopeEntry(entity.getBlockName());

            if (sprite != null) {
                return SlopeModelBase.getTextureAtlasSprite(sprite.getParticle());
            }
        }
        return SlopeModelBase.getTextureAtlasSprite("missingno");
    }

    @Override
    public @Nonnull ItemOverrides getOverrides() {
        return new SlopeItemOverrides();
    }
}
