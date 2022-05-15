package net.mrtska.slopeandcorner.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelBlock;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class SlopeModelBase {

    //頂点データのHashMap
    protected HashMap<String, SlopeModelBlock> vertex = new HashMap<>();

    /**
     * モデル初期化メソッド
     */
    public abstract void init();



    //頂点データを返す テクスチャUV込み
    public List<BakedQuad> getVertex(String blockType, String texture, @Nullable Direction side) {

        List<BakedQuad> ret = new ArrayList<>();

        TextureAtlasSprite textureBottom = null;
        TextureAtlasSprite textureTop = null;
        TextureAtlasSprite textureNorth = null;
        TextureAtlasSprite textureEast = null;
        TextureAtlasSprite textureSouth = null;
        TextureAtlasSprite textureWest = null;

        if (!texture.contains(",")) {

            var sprite = getTextureAtlasSprite(texture);

            textureBottom = side == null || side == Direction.DOWN ? sprite : null;
            textureTop =  side == null || side == Direction.UP ? sprite : null;
            textureNorth =  side == null || side == Direction.NORTH ? sprite : null;
            textureEast =  side == null || side == Direction.EAST ? sprite : null;
            textureSouth =  side == null || side == Direction.SOUTH ? sprite : null;
            textureWest =  side == null || side == Direction.WEST ? sprite : null;
        } else {

            var faces = texture.split(",");

            for (var face : faces) {

                var tmp = face.split("=");
                var direction = tmp[0];
                var sideSprite = getTextureAtlasSprite(tmp[1]);

                switch (direction) {
                    case "top" -> textureTop = side == null || side == Direction.UP ? sideSprite : null;
                    case "bottom" -> textureBottom = side == null || side == Direction.DOWN ? sideSprite : null;
                    case "north" -> textureNorth = side == null || side == Direction.NORTH ? sideSprite : null;
                    case "east" -> textureEast = side == null || side == Direction.EAST ? sideSprite : null;
                    case "south" -> textureSouth = side == null || side == Direction.SOUTH ? sideSprite : null;
                    case "west" -> textureWest = side == null || side == Direction.WEST ? sideSprite : null;
                    case "side" -> {
                        textureNorth = side == null || side == Direction.NORTH ? sideSprite : null;
                        textureEast = side == null || side == Direction.EAST ? sideSprite : null;
                        textureSouth = side == null || side == Direction.SOUTH ? sideSprite : null;
                        textureWest = side == null || side == Direction.WEST ? sideSprite : null;
                    }
                }
            }
        }

        HashMap<String, SlopeModelBlock> map;

        if(true) {

            vertex.clear();
            init();
            map = vertex;
        } else {

            map = vertex;
        }

        SlopeModelBlock model = map.get(blockType);
        if(model == null) {

            return ret;
        }

        ret.addAll(model.makeBakedQuad(textureBottom, textureTop, textureNorth, textureEast, textureSouth, textureWest));
        return ret;
    }

    //テクスチャのファイル名を書くとTextureAtlasSpriteを返す
    public static TextureAtlasSprite getTextureAtlasSprite(String name) {

        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation("minecraft:block/" + name));
    }
}
