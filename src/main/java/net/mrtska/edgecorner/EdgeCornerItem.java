package net.mrtska.edgecorner;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.mrtska.core.SlopeItemBase;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.SlopeEntry;


/**
 [Module EdgeCornerItem.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/05/15
 */
public class EdgeCornerItem extends SlopeItemBase {

	public EdgeCornerItem(Block block) {
		super(block);

	}

	@Override
	//ブロックを設置
	public void setBlock(World world, ItemStack itemstack, EntityPlayer player, BlockPos pos, Block block, EnumFacing side, float fx, float fy, float fz) {

		int direction = 0;

		String sourceDirection = itemstack.getTagCompound().getString("Direction").split(":")[1];
		if(sourceDirection.endsWith("2")) {

			direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F)) & 3;
		} else {

			direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F)) & 3;
		}

		String directionString = "EDGECORNER:";

		if(sourceDirection.startsWith("R")) {

			directionString += "R";
		}

		switch(direction) {
		case 2:
			directionString += "NORTH";
			break;
		case 3:
			directionString += "EAST";
			break;
		case 0:
			directionString += "SOUTH";
			break;
		case 1:
			directionString += "WEST";
			break;
		}

		if(sourceDirection.endsWith("2")) {

			directionString += "2";
		}

		this.placeBlockAt(itemstack, player, world, pos, side, fx, fy, fz, block.getBlockState().getBaseState());


		world.setTileEntity(pos, ((BlockContainer)block).createNewTileEntity(world, 0));

		SlopeTileEntity tileEntity = (SlopeTileEntity) world.getTileEntity(pos);
		tileEntity.setDirection(directionString);
		tileEntity.setBlockString(itemstack.getTagCompound().getString("BlockString")); //TileEntityにブロックを設定する
		tileEntity.setTexture(itemstack.getTagCompound().getString("Texture"));

		Block b = GameData.getBlockRegistry().getObject(new ResourceLocation(tileEntity.getBlockString()));
		SoundType sound = b.getStepSound();
		world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
		itemstack.stackSize--;

		world.markBlockRangeForRenderUpdate(pos, pos);
	}


	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {

		Iterator<SlopeEntry> itr = SlopeEntry.getSlopeEntries();
		while(itr.hasNext()) {

			SlopeEntry entry = itr.next();
			Block block = entry.getBlock();

			if(block.getDefaultState().getMaterial().isSolid()) {

				String[] textures = entry.getTexturesName();
				for(String texture : textures) {

					String blockString = GameRegistry.findUniqueIdentifierFor(block).toString();

					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "EDGECORNER:SOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}
					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "EDGECORNER:RSOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}

				}

			}
		}
	}

}
