package net.mrtska.slopeandcorner.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.mrtska.slopeandcorner.slope.SlopeModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BakedSlopeModel implements BakedModel {

    private ResourceLocation modelLocation;

    private SlopeModelBase modelBase;

    public BakedSlopeModel(ResourceLocation modelLocation) {

        this.modelLocation = modelLocation;
        this.modelBase = new SlopeModel();
        this.modelBase.init();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        if (side != null) {

            return Collections.emptyList();
        }

        return this.modelBase.getVertex(new String[] { "SLOPE:NORTH" }, new String[] { "spruce_planks" });
    }
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState p_119123_, @Nullable Direction p_119124_, Random p_119125_) {
        return null;
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
        return true;
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return SlopeModelBase.getTextureAtlasSprite("spruce_planks");
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }
}
