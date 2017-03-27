package net.mrtska.halfslope;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.mrtska.block.SlopeBlockBase;
import net.mrtska.halfslope.merge.DoubleMergedHalfSlopeBlock;
import net.mrtska.tileentity.DoubleMergedSlopeTileEntity;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.SlopeUtils;

/**[Module HalfSlopeBlock.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/27
*/
public class HalfSlopeBlock extends SlopeBlockBase {

	public static HalfSlopeBlock instance;

	public HalfSlopeBlock() {


		setUnlocalizedName("slopeandcorner:halfslopeblock");
	}


	@Override
	public boolean blockClickedWithItemStack(World world, BlockPos pos, ItemStack itemStack, EntityPlayer player) {

		//所持しているアイテムがスロープブロックでは無かったら
		if(!(itemStack.getItem() instanceof HalfSlopeItem)) {

			return false;
		}

		SlopeTileEntity beforeTileEntity = (SlopeTileEntity) world.getTileEntity(pos);

		//TileEntityを取得出来なかったら
		if(beforeTileEntity == null) {

			return false;
		}

		//クリエイティブモード以外だったら手持ちアイテムを減らす
		if(!player.capabilities.isCreativeMode) {

			itemStack.shrink(1);
		}

		//TileEntityからデータを取得
		String blockString = beforeTileEntity.getBlockString();
		String texture = beforeTileEntity.getTexture();
		String direction = beforeTileEntity.getDirection();

		//既存のTileEntityを消去する
		world.removeTileEntity(pos);

		//ブロックを設置
		world.setBlockState(pos, DoubleMergedHalfSlopeBlock.instance.getStateFromMeta(0));

		//TileEntityを設置
		world.setTileEntity(pos, DoubleMergedHalfSlopeBlock.instance.createNewTileEntity(world, 0));

		//新しいTileEntityを取得
		DoubleMergedSlopeTileEntity newTileEntity = (DoubleMergedSlopeTileEntity) world.getTileEntity(pos);


		//---TileEntityを設定---
		newTileEntity.bottom.setBlockString(blockString);
		newTileEntity.bottom.setTexture(texture);
		newTileEntity.bottom.setDirection(direction);

		//手持ちアイテムのItemStackのNBTからデータを取得
		NBTTagCompound compound = itemStack.getTagCompound();

		if(direction.contains(":R")) {

			int directionInteger = MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5F) & 3;
			String directionString = "HALFSLOPE:";

			switch(directionInteger) {
			case 2:
				directionString += "NORTH2";
				break;
			case 3:
				directionString += "EAST2";
				break;
			case 0:
				directionString += "SOUTH2";
				break;
			case 1:
				directionString += "WEST2";
				break;
			}


			newTileEntity.top.setBlockString(compound.getString("BlockString"));
			newTileEntity.top.setTexture(compound.getString("Texture"));
			newTileEntity.top.setDirection(directionString);
		} else {

			newTileEntity.top.setBlockString(compound.getString("BlockString"));
			newTileEntity.top.setTexture(compound.getString("Texture"));
			newTileEntity.top.setDirection("HALFSLOPE:" + SlopeUtils.convertDirection(direction.split(":")[1]));

		}
		//------


		return true;
	}



	@Override
	//松明などが刺さる向きを設定
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {

		return false;
	}

	@Override
	//当たり判定を設定
	public void addCollisionBoxToList(IBlockState state,World world, BlockPos pos, AxisAlignedBB alignedBB, List list, @Nullable Entity entity, boolean p_185477_7_) {

		SlopeTileEntity tileEntity = (SlopeTileEntity) world.getTileEntity(pos);


		float smooth = 0.001F;
		switch(tileEntity.getDirection().split(":")[1]) {
		case "NORTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, 1.00F, f / 2F, f1, alignedBB, list, entity);
			}
			break;
		case "EAST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				this.addCollision(world, pos, f, 0.00F, 0.00F, 1.00F, f / 2F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "SOUTH":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, f, 1.00F, f / 2F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "WEST":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.00F, 0.00F, f1, f / 2F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "RNORTH":
			this.addCollision(world, pos, 0.0F, 0.00F, 0.00F, 1.00f, 0.5F, 1.00F, alignedBB, list, entity);
			break;
		case "REAST":
			this.addCollision(world, pos, 0.0F, 0.00F, 0.00F, 1.00f, 0.5F, 1.00F, alignedBB, list, entity);
			break;
		case "RSOUTH":
			this.addCollision(world, pos, 0.0F, 0.00F, 0.00F, 1.00f, 0.5F, 1.00F, alignedBB, list, entity);
			break;
		case "RWEST":
			this.addCollision(world, pos, 0.0F, 0.00F, 0.00F, 1.00f, 0.5F, 1.00F, alignedBB, list, entity);
			break;
		case "NORTH2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.5F, 0.0F, 1.00F, f / 2 + 0.5F, f1, alignedBB, list, entity);
			}
			break;
		case "EAST2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, f, 0.5F, 0.00F, 1.00F, f / 2 + 0.5F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "SOUTH2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.5F, f, 1.00F, f / 2F + 0.5F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "WEST2":
			for(float f = 0.00F; f < 1.00F; f += smooth) {
				float f1 = 1.00F - f;
				this.addCollision(world, pos, 0.00F, 0.5F, 0.00F, f1, f / 2F +0.5F, 1.00F, alignedBB, list, entity);
			}
			break;
		case "RNORTH2":
			this.addCollision(world, pos, 0.0F, 0.50F, 0.00F, 1.00f, 1.0F, 1.00F, alignedBB, list, entity);
			break;
		case "REAST2":
			this.addCollision(world, pos, 0.0F, 0.50F, 0.00F, 1.00f, 1.0F, 1.00F, alignedBB, list, entity);
			break;
		case "RSOUTH2":
			this.addCollision(world, pos, 0.0F, 0.50F, 0.00F, 1.00f, 1.0F, 1.00F, alignedBB, list, entity);
			break;
		case "RWEST2":
			this.addCollision(world, pos, 0.0F, 0.50F, 0.00F, 1.00f, 1.0F, 1.00F, alignedBB, list, entity);
			break;

		}
	}





}
