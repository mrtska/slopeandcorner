package net.mrtska.slopeandcorner.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelBlock;

public abstract class SlopeModelBase {

    //頂点データのHashMap
    protected HashMap<String, SlopeModelBlock> vertex = new HashMap<>();

    /**
     * モデル初期化メソッド
     */
    public abstract void init();



    //頂点データを返す テクスチャUV込み
    public List<BakedQuad> getVertex(String[] directions, String[] textureStrings) {

        List<BakedQuad> ret = new ArrayList<>();

        for(int i = 0; i < directions.length; i++) {

            String direction = directions[i];
            String textureString = textureStrings[i];

            if(direction == null || textureString == null) {

                continue;
            }



            TextureAtlasSprite texture_down = getTextureAtlasSprite(textureString);
            TextureAtlasSprite texture_up = texture_down;
            TextureAtlasSprite texture_north = texture_down;
            TextureAtlasSprite texture_east = texture_down;
            TextureAtlasSprite texture_south = texture_down;
            TextureAtlasSprite texture_west = texture_down;

//            if(textureString.contains(",")) {
//
//                TextureAtlasSprite[] textures = getTextureAtlasSprites(textureString);
//                texture_down = textures[0];
//                texture_up = textures[1];
//                texture_north = textures[2];
//                texture_east = textures[3];
//                texture_south = textures[4];
//                texture_west = textures[5];
//            }
//            if(texture_down == null) {
//
//                texture_down = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
//                texture_up = texture_down;
//                texture_north = texture_down;
//                texture_east = texture_down;
//                texture_south = texture_down;
//                texture_west = texture_down;
//            }


            HashMap<String, SlopeModelBlock> map;

            if(false) {

                vertex.clear();
                init();
                map = vertex;
            } else {

                map = vertex;
            }

            SlopeModelBlock model = map.get(direction);
            if(model == null) {

                return ret;
            }

            ret.addAll(model.makeBakedQuad(texture_down, texture_up, texture_north, texture_east, texture_south, texture_west));
        }
        return ret;
    }

    //テクスチャのファイル名を書くとTextureAtlasSpriteを返す
    public static TextureAtlasSprite getTextureAtlasSprite(String name) {

        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation("minecraft:block/" + name));
    }
}
