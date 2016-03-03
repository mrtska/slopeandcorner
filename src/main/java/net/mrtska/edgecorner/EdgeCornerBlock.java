package net.mrtska.edgecorner;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.mrtska.block.SlopeBlockBase;
import net.mrtska.corner.CornerItem;
import net.mrtska.corner.merge.MergedCornerBlock;
import net.mrtska.tileentity.MergedSlopeTileEntity;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.SlopeUtils;

/**
 [Module EdgeCornerBlock.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/05/15
 */
public class EdgeCornerBlock extends SlopeBlockBase {

	//ブロックインスタンス
	public static EdgeCornerBlock instance;

	public EdgeCornerBlock() {

		setUnlocalizedName("slopeandcorner:edgecornerblock");
	}

	@Override
	public boolean blockClickedWithItemStack(World world, BlockPos pos, ItemStack itemStack, EntityPlayer player) {

		//所持しているアイテムがスロープブロックでは無かったら
		if(!(itemStack.getItem() instanceof CornerItem)) {

			return false;
		}

		SlopeTileEntity beforeTileEntity = (SlopeTileEntity) world.getTileEntity(pos);

		//TileEntityを取得出来なかったら
		if(beforeTileEntity == null) {

			return false;
		}

		//クリエイティブモード以外だったら手持ちアイテムを減らす
		if(!player.capabilities.isCreativeMode) {

			itemStack.stackSize--;
		}

		//TileEntityからデータを取得
		String blockString = beforeTileEntity.getBlockString();
		String texture = beforeTileEntity.getTexture();
		String direction = beforeTileEntity.getDirection();

		//既存のTileEntityを消去する
		world.removeTileEntity(pos);

		//ブロックを設置
		world.setBlockState(pos, MergedCornerBlock.instance.getStateFromMeta(0));

		//TileEntityを設置
		world.setTileEntity(pos, MergedCornerBlock.instance.createNewTileEntity(world, 0));

		//新しいTileEntityを取得
		MergedSlopeTileEntity newTileEntity = (MergedSlopeTileEntity) world.getTileEntity(pos);

		//---TileEntityを設定---
		newTileEntity.bottom.setBlockString(blockString);
		newTileEntity.bottom.setTexture(texture);
		newTileEntity.bottom.setDirection(direction);

		//手持ちアイテムのItemStackのNBTからデータを取得
		NBTTagCompound compound = itemStack.getTagCompound();
		newTileEntity.top.setBlockString(compound.getString("BlockString"));
		newTileEntity.top.setTexture(compound.getString("Texture"));
		newTileEntity.top.setDirection("CORNER:" + SlopeUtils.convertDirection(direction.split(":")[1]));
		//------

		return true;
	}

	@Override
	//松明などが刺さる向きを設定
	public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side) {

		SlopeTileEntity tileEntity = (SlopeTileEntity) world.getTileEntity(pos);
		String direction = tileEntity.getDirection().split(":")[1];

		if((!direction.startsWith("R") && !direction.contains("2")) && side == EnumFacing.UP) {

			return false;
		}

		if(direction.startsWith("R")) {

			if(side == EnumFacing.UP) {

				return true;
			}
			direction = direction.substring(1);
		}

		switch(direction) {
		case "NORTH":
			if(side == EnumFacing.NORTH || side == EnumFacing.EAST) {

				return true;
			}
			return false;
		case "EAST":
			if(side == EnumFacing.EAST || side == EnumFacing.SOUTH) {

				return true;
			}
			return false;
		case "SOUTH":
			if(side == EnumFacing.SOUTH || side == EnumFacing.WEST) {

				return true;
			}
			return false;
		case "WEST":
			if(side == EnumFacing.WEST || side == EnumFacing.NORTH) {

				return true;
			}
			return false;
		case "NORTH2":
			if(side == EnumFacing.UP || side == EnumFacing.NORTH || side == EnumFacing.EAST) {

				return true;
			}
			return false;
		case "EAST2":
			if(side == EnumFacing.UP || side == EnumFacing.EAST || side == EnumFacing.SOUTH) {

				return true;
			}
			return false;
		case "SOUTH2":
			if(side == EnumFacing.UP || side == EnumFacing.SOUTH || side == EnumFacing.WEST) {

				return true;
			}
			return false;
		case "WEST2":
			if(side == EnumFacing.UP || side == EnumFacing.WEST || side == EnumFacing.NORTH) {

				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	//当たり判定を設定
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB alignedBB, List list, Entity entity) {

		SlopeTileEntity tileEntity = (SlopeTileEntity) world.getTileEntity(pos);

		float smooth = 0.001F;
		switch(tileEntity.getDirection().split(":")[1]) {
		case "NORTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, 1.00F, f, f1, alignedBB, list, entity);
				this.addCollision(world, pos, f, 0.00F, 0.00F, 1.00F, f, 1.00F, alignedBB, list, entity);
			}
			break;
		case "EAST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				this.addCollision(world, pos, f, 0.00F, 0.00F, 1.00F, f, 1.00F, alignedBB, list, entity);
				this.addCollision(world, pos, 0.00F, 0.00F, f, 1.00F, f, 1.00F, alignedBB, list, entity);
			}
			break;
		case "SOUTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, f, 1.00F, f, 1.00F, alignedBB, list, entity);
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, f1, f, 1.00F, alignedBB, list, entity);
			}
			break;
		case "WEST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, f1, f, 1.00F, alignedBB, list, entity);
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, 1.00F, f, f1, alignedBB, list, entity);
			}
			break;
		case "RNORTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, f, 0.00F, 1.00F, 1.00F, f, alignedBB, list, entity);
			}
			break;
		case "REAST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, f1, f, 0.00F, 1.00F, 1.00F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "RSOUTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, f, f1, 1.00F, 1.00F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "RWEST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				this.addCollision(world, pos, 0.00F, f, 0.00F, f, 1.00F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "NORTH2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, f1, 0.00F, 0.00F, 1.00f, 1.00F, f1, alignedBB, list, entity);
			}
			break;
		case "EAST2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, f1, 0.00F, f, 1.00F, 1.00F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "SOUTH2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, f, f, 1.00F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "WEST2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, f, 1.00F, f1, alignedBB, list, entity);
			}
			break;
		}
	}
}
