package net.mrtska.workbench;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.mrtska.block.SlopeBlockBase;
import net.mrtska.core.Core;
import net.mrtska.workbench.gui.GuiSlopeWorkBench;

/**[Module SlopeWorkBench.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/03
*/
public class SlopeWorkBench extends SlopeBlockBase {


	public static Block instance;


	public SlopeWorkBench() {

		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(2.5F);
		this.setUnlocalizedName("slopeworkbench");
	}


	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB alignedBB, List list, Entity entity) {

		SlopeWorkBenchTileEntity tileEntity = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);



		float smooth = 0.001F;
		switch(tileEntity.getDirection().split(":")[1]) {
		case "NORTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, 1.00F, f, f1, alignedBB, list, entity);
			}
			break;
		case "EAST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				this.addCollision(world, pos, f, 0.00F, 0.00F, 1.00F, f, 1.00F, alignedBB, list, entity);
			}
			break;
		case "SOUTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, f, 1.00F, f, 1.00F, alignedBB, list, entity);
			}
			break;
		case "WEST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, f1, f, 1.00F, alignedBB, list, entity);
			}
			break;
		}
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ) {

		player.openGui(Core.instance, GuiSlopeWorkBench.GuiID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;

	}

	@Override
	/**
	 * @param state ブロックステート
	 * @param world ワールドインスタンス
	 * @param pos 座標
	 * 描画時にステートをロードする
	 */
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {

		SlopeWorkBenchTileEntity tileEntity = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);
		final String tex = "down=planks_oak,up=crafting_table_top,north=crafting_table_front,east=crafting_table_side,south=crafting_table_side,west=crafting_table_front";


		if(tileEntity == null) {

			return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, tex).withProperty(directionProperty, "NORTH");
		}


		if(tileEntity.getDirection() == null || tileEntity.getDirection().isEmpty()) {

			return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, tex).withProperty(directionProperty, "NORTH");
		}

		return ((IExtendedBlockState)this.state.getBaseState()).withProperty(textureProperty, tex).withProperty(directionProperty, tileEntity.getDirection());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new SlopeWorkBenchTileEntity();
	}



	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

		if(!player.capabilities.isCreativeMode) {

			SlopeWorkBenchTileEntity entity = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);

			float f = 0.7F;
			double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d3 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			ItemStack stack = new ItemStack(this, 1, 0);
			EntityItem item = new EntityItem(world, pos.getX() + d1, pos.getY() + d2, pos.getZ() + d3, stack);

			NBTTagCompound compound = new NBTTagCompound();

			compound.setString("Direction", "SLOPE:SOUTH");

			item.getEntityItem().setTagCompound(compound);

			item.setDefaultPickupDelay();

			player.dropItemAndGetStack(item);
		}
	}

	@Override
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {

		Block block = Blocks.crafting_table;
		effectRenderer.addBlockDestroyEffects(pos, block.getStateFromMeta(0));
		return true;
	}

	@Override
	public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, EffectRenderer effectRenderer) {

		BlockPos pos = target.getBlockPos();
		SlopeWorkBenchTileEntity tile = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);
		Random rand = new Random();

		//nullだった時はパーティクルを出さない
		if(tile == null) {

			return true;
		}

		Block block = Blocks.crafting_table;
		IBlockState iblockstate = block.getStateFromMeta(0);

		if (block.getRenderType(state) != EnumBlockRenderType.INVISIBLE) {
			int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			float f = 0.1F;
			AxisAlignedBB aabb = block.getBoundingBox(state, world, pos);

			double d0 = (double)i + rand.nextDouble() * (aabb.maxX - aabb.minX - (double)(f * 2.0F)) + (double)f + aabb.minX;
			double d1 = (double)j + rand.nextDouble() * (aabb.maxY - aabb.minY - (double)(f * 2.0F)) + (double)f + aabb.minY;
			double d2 = (double)k + rand.nextDouble() * (aabb.maxZ - aabb.minZ - (double)(f * 2.0F)) + (double)f + aabb.minZ;
			EntityDiggingFX entity = (EntityDiggingFX) new EntityDiggingFX.Factory().getEntityFX(0, world, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));

			effectRenderer.addEffect(entity.setBlockPos(pos).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
		}
		return true;
	}
}
