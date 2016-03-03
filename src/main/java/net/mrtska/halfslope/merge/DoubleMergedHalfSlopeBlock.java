package net.mrtska.halfslope.merge;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.mrtska.block.DoubleMergedSlopeBlockBase;
import net.mrtska.halfslope.HalfSlopeItem;
import net.mrtska.tileentity.DoubleMergedSlopeTileEntity;
import net.mrtska.util.SlopeUtils;

/**[Module DoubleMergedHalfSlopeBlock.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/30
*/
public class DoubleMergedHalfSlopeBlock extends DoubleMergedSlopeBlockBase {

	public static DoubleMergedHalfSlopeBlock instance;

	public DoubleMergedHalfSlopeBlock() {

		this.setUnlocalizedName("slopeandcorner:doublemergedhalfslopeblock");
	}

	@Override
	public boolean blockClickedWithItemStack(World world, BlockPos pos, ItemStack itemStack, EntityPlayer player) {

		//所持しているアイテムがスロープブロックでは無かったら
		if(!(itemStack.getItem() instanceof HalfSlopeItem)) {

			return false;
		}

		DoubleMergedSlopeTileEntity tileEntity = (DoubleMergedSlopeTileEntity) world.getTileEntity(pos);

		String topDirection = tileEntity.top.getDirection();
		String bottomDirection = tileEntity.bottom.getDirection();
		String top2Direction = tileEntity.top2.getDirection();
		String bottom2Direction = tileEntity.bottom2.getDirection();

		if(top2Direction.equals("null")) {

			int directionInteger = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5F) & 3;
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

			NBTTagCompound compound = itemStack.getTagCompound();
			tileEntity.top2.setBlockString(compound.getString("BlockString"));
			tileEntity.top2.setTexture(compound.getString("Texture"));
			tileEntity.top2.setDirection(directionString);

			world.markBlockForUpdate(pos);
			return true;
		}

		if(bottom2Direction.equals("null")) {


			NBTTagCompound compound = itemStack.getTagCompound();
			tileEntity.bottom2.setBlockString(compound.getString("BlockString"));
			tileEntity.bottom2.setTexture(compound.getString("Texture"));
			tileEntity.bottom2.setDirection("HALFSLOPE:R" + SlopeUtils.convertDirection(top2Direction.split(":")[1]));


			world.markBlockForUpdate(pos);
			return true;
		}



		return false;
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB alignedBB, List list, Entity entity) {

		DoubleMergedSlopeTileEntity tileEntity = (DoubleMergedSlopeTileEntity) world.getTileEntity(pos);

		String topDirection = tileEntity.top.getDirection();
		String top2Direction = tileEntity.top2.getDirection();
		String bottom2Direction = tileEntity.bottom2.getDirection();

		if(topDirection == null) {

			return;
		}

		if(topDirection.contains(":R") && !topDirection.contains("2")) {

			this.addCollision(world, pos, 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F, alignedBB, list, entity);
		}

		if(topDirection.contains("2")) {

			float smooth = 0.001F;
			switch(topDirection.split(":")[1]) {
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
					this.addCollision(world, pos, 0.00F, 0.5F, 0.00F, f1, f / 2F + 0.5F, 1.00F, alignedBB, list, entity);
				}
				break;
			}
			return;
		}



		if(!top2Direction.contains(":R")) {

			if(!top2Direction.contains(":")) {

				return;
			}

			float smooth = 0.001F;
			switch(top2Direction.split(":")[1]) {
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
					this.addCollision(world, pos, 0.00F, 0.5F, 0.00F, f1, f / 2F + 0.5F, 1.00F, alignedBB, list, entity);
				}
				break;
			}
			return;
		} else {

			this.addCollision(world, pos, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, alignedBB, list, entity);
		}



	}

}
