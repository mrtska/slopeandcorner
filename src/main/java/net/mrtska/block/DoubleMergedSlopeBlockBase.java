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
import net.mrtska.tileentity.DoubleMergedSlopeTileEntity;
import net.mrtska.util.StringProperty;

/**[Module DoubleMergedSlopeBlockBase.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/30
*/
public abstract class DoubleMergedSlopeBlockBase extends MergedSlopeBlockBase {

	public DoubleMergedSlopeBlockBase() {

		this.state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[] { directionProperty, textureProperty, directionProperty2, textureProperty2, directionProperty3, textureProperty3, directionProperty4, textureProperty4 });
	}

	/**
	 * ブロックの方向を保持するプロパティ2
	 */
	public static final StringProperty directionProperty3 = new StringProperty("direction3");
	public static final StringProperty directionProperty4 = new StringProperty("direction4");

	/**
	 * ブロックのテクスチャを保持するプロパティ2
	 */
	public static final StringProperty textureProperty3 = new StringProperty("texture3");
	public static final StringProperty textureProperty4 = new StringProperty("texture4");



	@Override
	//描画時に使用するデータを送る
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {

		DoubleMergedSlopeTileEntity tileEntity = (DoubleMergedSlopeTileEntity) world.getTileEntity(pos);


		if(tileEntity == null) {
			return null;
		}

		//---TileEntityから各種文字列を取得
		String topTexture = tileEntity.top.getTexture();
		String topDirecion = tileEntity.top.getDirection();
		String bottomTexture = tileEntity.bottom.getTexture();
		String bottomDirection = tileEntity.bottom.getDirection();

		String top2Texture = tileEntity.top2.getTexture();
		String top2Direcion = tileEntity.top2.getDirection();
		String bottom2Texture = tileEntity.bottom2.getTexture();
		String bottom2Direction = tileEntity.bottom2.getDirection();
		//------



		return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, topTexture.split(":")[1]).withProperty(directionProperty, topDirecion)
				.withProperty(textureProperty2, bottomTexture.split(":")[1]).withProperty(directionProperty2, bottomDirection)
				.withProperty(textureProperty3, top2Texture.split(":")[1]).withProperty(directionProperty3, top2Direcion)
				.withProperty(textureProperty4, bottom2Texture.split(":")[1]).withProperty(directionProperty4, bottom2Direction);
	}

	@Override
	/**
	 * ブロックを破壊した時に出るパーティクルを設定
	 */
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {


		try {

			DoubleMergedSlopeTileEntity tile = (DoubleMergedSlopeTileEntity) world.getTileEntity(pos);

			if(tile == null) {

				return true;
			}

			int metadata = Integer.parseInt(tile.top.getTexture().split(":")[0]);

			Block block = GameData.getBlockRegistry().getObject(tile.top.getBlockString());
			effectRenderer.func_180533_a(pos, block.getStateFromMeta(metadata));
			return true;

		} catch(Exception e) {

			return true;
		}

	}

	@Override
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {

		return true;
	}


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new DoubleMergedSlopeTileEntity();
	}

}
