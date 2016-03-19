package net.mrtska.workbench;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
	public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer) {

		Block block = Blocks.crafting_table;
		effectRenderer.addBlockDestroyEffects(pos, block.getStateFromMeta(0));
		return true;
	}

	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, EffectRenderer effectRenderer) {

		return super.addHitEffects(state, worldObj, target, effectRenderer);
	}

}
