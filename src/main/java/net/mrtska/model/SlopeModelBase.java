package net.mrtska.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.model.SlopeModelEntry.SlopeModelBlock;

/**[Module AbstractModel.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
*/
@SideOnly(Side.CLIENT)
public abstract class SlopeModelBase implements IModel {

	//頂点データのHashMap
	protected HashMap<String, SlopeModelBlock> vertex = new HashMap<String, SlopeModelBlock>();

	//デバッグモードか否か
	public boolean isDebug = false;

	@Override
	//依存関係などないので空のリストを返す
	public Collection<ResourceLocation> getDependencies() {

		return Collections.emptyList();
	}

	@Override
	//モデルクッキング
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {


		return new BakedSlopeModel(this, state, format);
	}

	@Override
	//これはダミー
	public Collection<ResourceLocation> getTextures() {

		return Collections.emptyList();
	}

	@Override
	//デフォルトで
	public IModelState getDefaultState() {

		return TRSRTransformation.identity();
	}

	//頂点データを返す テクスチャUV込み
	public List<BakedQuad> getVertex(String[] directions, String[] textureStrings, VertexFormat format) {

		List<BakedQuad> ret = new ArrayList<BakedQuad>();

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

			if(textureString.contains(",")) {

				TextureAtlasSprite[] textures = getTextureAtlasSprites(textureString);
				texture_down = textures[0];
				texture_up = textures[1];
				texture_north = textures[2];
				texture_east = textures[3];
				texture_south = textures[4];
				texture_west = textures[5];
			}
			if(texture_down == null) {

				texture_down = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
				texture_up = texture_down;
				texture_north = texture_down;
				texture_east = texture_down;
				texture_south = texture_down;
				texture_west = texture_down;
			}


			HashMap<String, SlopeModelBlock> map;

			if(isDebug) {

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

			ret.addAll(model.makeBakedQuad(format, texture_down, texture_up, texture_north, texture_east, texture_south, texture_west));
		}
		return ret;
	}

	/**
	 * モデル初期化メソッド
	 */
	public abstract void init();


	public HashMap<String, SlopeModelBlock> getVertex() {

		return this.vertex;
	}


	//テクスチャのファイル名を書くとTextureAtlasSpriteを返す
	public static TextureAtlasSprite getTextureAtlasSprite(String name) {

		return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry("minecraft:blocks/" + name);
	}

	//面によってテクスチャが違うブロックで使用する
	public static TextureAtlasSprite[] getTextureAtlasSprites(String textures) {

		TextureAtlasSprite[] ret = new TextureAtlasSprite[6];
		String[] faces = textures.split(",");

		for(String face : faces) {

			String[] s = face.split("=");

			String side = s[0];
			String tex = s[1];
			switch(side) {

			case "down":

				ret[0] = getTextureAtlasSprite(tex);
				break;
			case "up":
				ret[1] = getTextureAtlasSprite(tex);
				break;
			case "north":
				ret[2] = getTextureAtlasSprite(tex);
				break;
			case "east":
				ret[3] = getTextureAtlasSprite(tex);
				break;
			case "south":
				ret[4] = getTextureAtlasSprite(tex);
				break;
			case "west":
				ret[5] = getTextureAtlasSprite(tex);
				break;
			case "side":

				ret[2] = getTextureAtlasSprite(tex);
				ret[3] = ret[2];
				ret[4] = ret[3];
				ret[5] = ret[4];
				break;
			}
		}
		return ret;
	}

}
