package net.mrtska.slopeandcorner.model;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.mrtska.slopeandcorner.corner.CornerModel;
import net.mrtska.slopeandcorner.edgecorner.EdgeCornerModel;
import net.mrtska.slopeandcorner.slope.SlopeModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class SlopeModelGeometry implements IModelGeometry<SlopeModelGeometry> {
    @Override
    public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {

        var path = modelLocation.getPath();

        return switch (path) {
            case "slope", "slopeitem" -> new BakedSlopeModel(modelLocation, new SlopeModel());
            case "corner", "corneritem" -> new BakedSlopeModel(modelLocation, new CornerModel());
            case "edgecorner", "edgecorneritem" -> new BakedSlopeModel(modelLocation, new EdgeCornerModel());
            default -> throw new IllegalStateException("Could not find baked model for " + path);
        };
    }

    @Override
    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return Collections.emptyList();
    }
}
