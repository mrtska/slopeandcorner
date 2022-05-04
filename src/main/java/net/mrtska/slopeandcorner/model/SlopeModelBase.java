package net.mrtska.slopeandcorner.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelBlock;

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
    public List<BakedQuad> getVertex(String blockType, String texture) {

        List<BakedQuad> ret = new ArrayList<>();

        TextureAtlasSprite textureDown = null;
        TextureAtlasSprite textureUp = null;
        TextureAtlasSprite textureNorth = null;
        TextureAtlasSprite textureEast = null;
        TextureAtlasSprite textureSouth = null;
        TextureAtlasSprite textureWest = null;

        if (!texture.contains(",")) {

            textureDown = getTextureAtlasSprite(texture);
            textureUp = textureDown;
            textureNorth = textureDown;
            textureEast = textureDown;
            textureSouth = textureDown;
            textureWest = textureDown;
        } else {

            var faces = texture.split(",");

            for (var face : faces) {

                var tmp = face.split("=");
                var side = tmp[0];
                var sideTexture = tmp[1];

                switch (side) {
                    case "up" -> textureUp = getTextureAtlasSprite(sideTexture);
                    case "down" -> textureDown = getTextureAtlasSprite(sideTexture);
                    case "north" -> textureNorth = getTextureAtlasSprite(sideTexture);
                    case "east" -> textureEast = getTextureAtlasSprite(sideTexture);
                    case "south" -> textureSouth = getTextureAtlasSprite(sideTexture);
                    case "west" -> textureWest = getTextureAtlasSprite(sideTexture);
                    case "side" -> {
                        textureNorth = getTextureAtlasSprite(sideTexture);
                        textureEast = textureNorth;
                        textureSouth = textureNorth;
                        textureWest = textureNorth;
                    }
                }
            }
        }

        HashMap<String, SlopeModelBlock> map;

        if(false) {

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

        ret.addAll(model.makeBakedQuad(textureDown, textureUp, textureNorth, textureEast, textureSouth, textureWest));
        return ret;
    }

    //テクスチャのファイル名を書くとTextureAtlasSpriteを返す
    public static TextureAtlasSprite getTextureAtlasSprite(String name) {

        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation("minecraft:block/" + name));
    }
}
