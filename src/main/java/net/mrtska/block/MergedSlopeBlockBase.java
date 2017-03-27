package net.mrtska.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

		MergedSlopeTileEntity entity = (MergedSlopeTileEntity) world.getTileEntity(pos);

		if(!player.capabilities.isCreativeMode) {

			float f = 0.7F;
			double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d3 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			ItemStack stack = new ItemStack(this, 1, 0);
			EntityItem item = new EntityItem(world, pos.getX() + d1, pos.getY() + d2, pos.getZ() + d3, stack);

			NBTTagCompound compound1 = new NBTTagCompound();

			compound1.setString("BlockString", entity.top.getBlockString());
			compound1.setString("Texture", entity.top.getTexture());
			compound1.setString("Direction", "SLOPE:SOUTH");

			item.getEntityItem().setTagCompound(compound1);

			item.setDefaultPickupDelay();

			player.dropItemAndGetStack(item);

			ItemStack stack2 = new ItemStack(this, 1, 0);
			EntityItem item2 = new EntityItem(world, pos.getX() + d1, pos.getY() + d2, pos.getZ() + d3, stack2);

			NBTTagCompound compound2 = new NBTTagCompound();

			compound2.setString("BlockString", entity.bottom.getBlockString());
			compound2.setString("Texture", entity.bottom.getTexture());
			compound2.setString("Direction", "SLOPE:SOUTH");

			item2.getEntityItem().setTagCompound(compound2);

			item2.setDefaultPickupDelay();

			player.dropItemAndGetStack(item2);
		}
	}

	@Override
	/**
	 * ブロックを破壊した時に出るパーティクルを設定
	 */
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager effectRenderer) {

		MergedSlopeTileEntity tile = (MergedSlopeTileEntity) world.getTileEntity(pos);

		if(tile == null) {

			return true;
		}

		int metadata = Integer.parseInt(tile.top.getTexture().split(":")[0]);

		Block block = GameData.getBlockRegistry().getObject(new ResourceLocation(tile.top.getBlockString()));
		effectRenderer.addBlockDestroyEffects(pos, block.getStateFromMeta(metadata));
		return true;
	}

	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager effectRenderer) {

		return true;
	}


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new MergedSlopeTileEntity();
	}



}
