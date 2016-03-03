package net.mrtska.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameData;
import net.mrtska.tileentity.MergedSlopeTileEntity;
import net.mrtska.util.StringProperty;

/**
 [Module MergedSlopeBlockBase.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/23
 */
public abstract class MergedSlopeBlockBase extends SlopeBlockBase {


	public MergedSlopeBlockBase() {

		this.state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[] { directionProperty, textureProperty, directionProperty2, textureProperty2 });
	}

	/**
	 * ブロックの方向を保持するプロパティ2
	 */
	public static final StringProperty directionProperty2 = new StringProperty("direction2");

	/**
	 * ブロックのテクスチャを保持するプロパティ2
	 */
	public static final StringProperty textureProperty2 = new StringProperty("texture2");



	@Override
	//描画時に使用するデータを送る
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {

		MergedSlopeTileEntity tileEntity = (MergedSlopeTileEntity) world.getTileEntity(pos);


		if(tileEntity == null) {
			return null;
		}

		//---TileEntityから各種文字列を取得
		String topBlockString = tileEntity.top.getBlockString();
		String topTexture = tileEntity.top.getTexture();
		String topDirecion = tileEntity.top.getDirection();
		String bottomBlockString = tileEntity.bottom.getBlockString();
		String bottomTexture = tileEntity.bottom.getTexture();
		String bottomDirection = tileEntity.bottom.getDirection();
		//------

		return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, topTexture.split(":")[1]).withProperty(directionProperty, topDirecion)
				.withProperty(textureProperty2, bottomTexture.split(":")[1]).withProperty(directionProperty2, bottomDirection);
	}

	@Override
	/**
	 * ブロックを破壊した時に出るパーティクルを設定
	 */
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {

		MergedSlopeTileEntity tile = (MergedSlopeTileEntity) world.getTileEntity(pos);

		if(tile == null) {

			return true;
		}

		int metadata = Integer.parseInt(tile.top.getTexture().split(":")[0]);

		Block block = GameData.getBlockRegistry().getObject(tile.top.getBlockString());
		effectRenderer.func_180533_a(pos, block.getStateFromMeta(metadata));
		return true;
	}

	@Override
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {

		return true;
	}


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new MergedSlopeTileEntity();
	}



}
