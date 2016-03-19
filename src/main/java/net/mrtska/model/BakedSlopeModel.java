package net.mrtska.model;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.block.DoubleMergedSlopeBlockBase;
import net.mrtska.block.MergedSlopeBlockBase;
import net.mrtska.block.SlopeBlockBase;
/**
 * [Module BakedSlopeModel.java]
Copyright(c) 2015 mrtska.starring
This software is released under the MIT License.
http://opensource.org/licenses/mit-license.php
Created on: 2015/04/24
 */
@SideOnly(Side.CLIENT)
public class BakedSlopeModel implements IPerspectiveAwareModel {


	private IModelState state;

	protected VertexFormat format;

	//テクスチャ
	protected String[] texture;

	//モデル
	private SlopeModelBase model;


	//頂点データ
	protected List<BakedQuad> vertexData = new ArrayList<BakedQuad>();

	//ブロックレンダリングかアイテムレンダリングか
	private boolean isItemRendering = false;

	//向き
	protected String[] direction = new String[4];

	//初期化
	public BakedSlopeModel(SlopeModelBase vertex, IModelState state, VertexFormat format) {

		this.state = state;
		this.format = format;
		this.texture = new String[4];
		this.direction = new String[4];

		this.model = vertex;
	}

	@Override
	//スムースライティングをするか ここはマイクラの設定を参照しなくてもスムースライティングがオフにされていれば自動的にオフになるので常にtrue
	public boolean isAmbientOcclusion() {

		return true;
	}

	@Override
	//GUIは3Dにします
	public boolean isGui3d() {

		return true;
	}

	@Override
	//ビルトインレンダラではないのでfalse
	public boolean isBuiltInRenderer() {

		return false;
	}

	@Override
	//このモデルはダミーなのでここではMissingTextureを返す
	public TextureAtlasSprite getParticleTexture() {

		//return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/" + this.texture);
		return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
	}

	@Override
	//デフォルトに
	public ItemCameraTransforms getItemCameraTransforms() {

		return ItemCameraTransforms.DEFAULT;
	}


	@Override
	//頂点データを返す
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {


		if(side != null) {

			return new ArrayList<>();
		}

		if(state == null) {

			return vertexData;
		}


		this.texture[0] = ((IExtendedBlockState)state).getValue(SlopeBlockBase.textureProperty);
		this.direction[0] = ((IExtendedBlockState)state).getValue(SlopeBlockBase.directionProperty);


		if(((IExtendedBlockState)state).getBlock() instanceof MergedSlopeBlockBase) {

			this.texture[1] = ((IExtendedBlockState)state).getValue(MergedSlopeBlockBase.textureProperty2);
			this.direction[1] = ((IExtendedBlockState)state).getValue(MergedSlopeBlockBase.directionProperty2);
		}
		if(((IExtendedBlockState)state).getBlock() instanceof DoubleMergedSlopeBlockBase) {

			this.texture[2] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.textureProperty3);
			this.direction[2] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.directionProperty3);
			this.texture[3] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.textureProperty4);
			this.direction[3] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.directionProperty4);

		}

		this.vertexData = model.getVertex(this.direction, this.texture, format);


		//アイテムレンダリングの時はそのまま頂点データを返す
		if(isItemRendering) {

			return vertexData;
		}

		return vertexData;
	}


	public SlopeModelBase getModel() {
		return model;
	}
/*
	@Override
	//デフォルトで
	public VertexFormat getFormat() {

		return Attributes.DEFAULT_BAKED_FORMAT;
	}
*/
/*	@Override
	//ブロックステートを受け取る ブロックレンダリング時
	public IBakedModel handleBlockState(IBlockState state) {

		this.state = (IExtendedBlockState)state;

		if(this.state == null) {

			return this;
		}

		this.texture[0] = ((IExtendedBlockState)state).getValue(SlopeBlockBase.textureProperty);
		this.direction[0] = this.state.getValue(SlopeBlockBase.directionProperty);


		if(((IExtendedBlockState)state).getBlock() instanceof MergedSlopeBlockBase) {

			this.texture[1] = ((IExtendedBlockState)state).getValue(MergedSlopeBlockBase.textureProperty2);
			this.direction[1] = ((IExtendedBlockState)state).getValue(MergedSlopeBlockBase.directionProperty2);
		}
		if(((IExtendedBlockState)state).getBlock() instanceof DoubleMergedSlopeBlockBase) {

			this.texture[2] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.textureProperty3);
			this.direction[2] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.directionProperty3);
			this.texture[3] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.textureProperty4);
			this.direction[3] = ((IExtendedBlockState)state).getValue(DoubleMergedSlopeBlockBase.directionProperty4);

		}

		this.vertexData = model.getVertex(this.direction, this.texture);

		this.isItemRendering = false;
		return this;
	}

	@Override
	//ItemStackを受け取る アイテムレンダリング時
	public IBakedModel handleItemState(ItemStack stack) {

		if(!stack.hasTagCompound()) {

			System.out.println("BakedSlopeModel.handleItemState()");
			return this;
		}

		NBTTagCompound compound = stack.getTagCompound();
		String block = compound.getString("BlockString");


		String texture = compound.getString("Texture");

		if(texture.contains(":")) {

			texture = compound.getString("Texture").split(":")[1];
		}

		this.texture = new String[] { texture };
		this.direction = new String[] { compound.getString("Direction") };
		this.vertexData = model.getVertex(this.direction, this.texture);
		this.isItemRendering = true;
		return this;
	}
*/

	@Override
	public ItemOverrideList getOverrides() {



		return new CustomItemOverride();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType type) {

		return IPerspectiveAwareModel.MapWrapper.handlePerspective(this, state, type);
	}
}
