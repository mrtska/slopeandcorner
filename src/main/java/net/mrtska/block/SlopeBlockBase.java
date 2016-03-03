package net.mrtska.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.StringProperty;
/**
 [Module AbstractBlock.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
 */
public abstract class SlopeBlockBase extends BlockContainer {

	/**
	 * ブロックの方向を保持するプロパティ
	 */
	public static final StringProperty directionProperty = new StringProperty("direction");

	/**
	 * ブロックのテクスチャを保持するプロパティ
	 */
	public static final StringProperty textureProperty = new StringProperty("texture");


	/**
	 * 斜面MOD用に拡張したブロックステート
	 */
	protected ExtendedBlockState state;

	/**
	 * コンストラクタ
	 */
	protected SlopeBlockBase() {
		super(Material.wood);

		this.setHardness(1.0F);
		this.state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[] { directionProperty, textureProperty });
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos) {


		return -1.0F;
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {

		return -1.0F;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

		SlopeTileEntity entity = (SlopeTileEntity) world.getTileEntity(pos);

		if(!player.capabilities.isCreativeMode) {

			float f = 0.7F;
			double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d3 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			ItemStack stack = new ItemStack(this, 1, 0);
			EntityItem item = new EntityItem(world, pos.getX() + d1, pos.getY() + d2, pos.getZ() + d3, stack);

			NBTTagCompound compound = new NBTTagCompound();

			compound.setString("BlockString", entity.getBlockString());
			compound.setString("Texture", entity.getTexture());
			compound.setString("Direction", "SLOPE:SOUTH");

			item.getEntityItem().setTagCompound(compound);

			item.setDefaultPickupDelay();
			player.joinEntityItemWithWorld(item);
		}
	}

	//壊したときにブロックがドロップしないように
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {}




	@Override
	/**
	 * レンダータイプを返す
	 */
	public int getRenderType() {

		return 3;
	}

	@Override
	/**
	 * メタデータは使わないから0を返す
	 */
	public int getMetaFromState(IBlockState state) {

		return 0;
	}


	@Override
	/**
	 * @param state ブロックステート
	 * @param world ワールドインスタンス
	 * @param pos 座標
	 * 描画時にステートをロードする
	 */
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {

		SlopeTileEntity tileEntity = (SlopeTileEntity) world.getTileEntity(pos);

		if(tileEntity == null) {

			return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, "minecraft:blocks/stone").withProperty(directionProperty, "NORTH");
		}

		if(tileEntity.getBlockString() == null) {

			return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, "minecraft:blocks/stone").withProperty(directionProperty, "NORTH");
		}

		if(tileEntity.getDirection() == null || tileEntity.getDirection().isEmpty()) {

			return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, "minecraft:blocks/stone").withProperty(directionProperty, "NORTH");
		}

		return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, tileEntity.getTexture().split(":")[1]).withProperty(directionProperty, tileEntity.getDirection());
	}

	@Override
	//オーバーライド不可にする 処理を弄る際はblockClickedメソッドをオーバーライドする
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {


		ItemStack stack = player.getHeldItem();
		//素手の状態でブロックを右クリックするとnullが返ってくるためnullチェックをする
		if(stack == null) {

			return false;
		}

		return blockClickedWithItemStack(world, pos, stack, player);
	}

	//ブロックが右クリックされた時の処理 itemStackはnull以外が保障されている
	public boolean blockClickedWithItemStack(World world, BlockPos pos, ItemStack itemStack, EntityPlayer player) {

		return false;
	}



	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * レンダリング時の色を返す
	 */
	public int getRenderColor(IBlockState state) {

		return super.getRenderColor(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * 一応草ブロックのデフォルトカラーを返しておく
	 */
	public int getBlockColor() {

		return ColorizerGrass.getGrassColor(0.5D, 1.0D);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * テクスチャを透過させる
	 */
    public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}
	@Override
	/**
	 * カラーマルチプライヤー
	 */
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {

		return Blocks.grass.colorMultiplier(worldIn, pos, renderPass);
	}

	@Override
	/**
	 * 普通のブロックではないのでfalse
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	/**
	 * 普通のブロックでｈ（ｒｙ
	 */
	public boolean isFullCube() {
		return false;
	}

	@Override
	/**
	 * 普（ｒｙ
	 */
	public boolean isVisuallyOpaque() {
		return false;
	}

	/**
	 *
	 * @param world ワールドインスタンス
	 * @param pos 座標
	 * @param minX
	 * @param minY
	 * @param minZ
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 * @param alignedBB
	 * @param list
	 * @param entity
	 * 当たり判定をlistに追加する
	 */
	protected void addCollision(World world, BlockPos pos, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, AxisAlignedBB alignedBB, List list, Entity entity) {

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		AxisAlignedBB axisalignedbb1 = AxisAlignedBB.fromBounds(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
		if(axisalignedbb1 != null && alignedBB.intersectsWith(axisalignedbb1)) {
			list.add(axisalignedbb1);
		}
	}

	@Override
	/**
	 * ブロックを破壊した時に出るパーティクルを設定
	 */
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {

		SlopeTileEntity tile = (SlopeTileEntity) world.getTileEntity(pos);
		int metadata = Integer.parseInt(tile.getTexture().split(":")[0]);


		Block block = GameData.getBlockRegistry().getObject(tile.getBlockString());
		effectRenderer.func_180533_a(pos, block.getStateFromMeta(metadata));
		return true;
	}

	@Override
	public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer) {

		BlockPos pos = target.getBlockPos();
		SlopeTileEntity tile = (SlopeTileEntity) world.getTileEntity(pos);
		Random rand = new Random();

		//nullだった時はパーティクルを出さない
		if(tile == null) {

			return true;
		}

		Block block = GameData.getBlockRegistry().getObject(tile.getBlockString());
		int metadata = Integer.parseInt(tile.getTexture().split(":")[0]);
        IBlockState iblockstate = block.getStateFromMeta(metadata);

        if (block.getRenderType() != -1)
        {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            float f = 0.1F;
            double d0 = (double)i + rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinX();
            double d1 = (double)j + rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinY();
            double d2 = (double)k + rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - (double)(f * 2.0F)) + (double)f + block.getBlockBoundsMinZ();
            EntityDiggingFX entity = (EntityDiggingFX) new EntityDiggingFX.Factory().getEntityFX(0, world, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));

            effectRenderer.addEffect(entity.func_174846_a(pos).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
        }
		return true;
	}

	@Override
	//このメソッドをオーバーライドし、TileEntityを変更したら上記のSlopeTileEntityを使用しているメソッドを全てオーバーライドしなくてはならない
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new SlopeTileEntity();
	}



}
