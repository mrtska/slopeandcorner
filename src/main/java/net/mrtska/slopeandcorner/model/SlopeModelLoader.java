package net.mrtska.slopeandcorner.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;
import org.jetbrains.annotations.NotNull;

/**
 * Model loader for slope blocks.
 */
public class SlopeModelLoader implements IModelLoader<SlopeModelGeometry> {

    @Override
    public @NotNull SlopeModelGeometry read(@NotNull JsonDeserializationContext deserializationContext, @NotNull JsonObject modelContents) {
        return new SlopeModelGeometry();
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager manager) {
        // Nothing to do anything even resources is reloading.
    }
}
