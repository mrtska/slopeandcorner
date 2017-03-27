package net.mrtska.slope;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
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
 [Module SlopeItem.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/29
 */
public class SlopeItem extends SlopeItemBase {

	public SlopeItem(Block block) {
		super(block);

	}

	@Override
	public void setBlock(World world, ItemStack itemstack, EntityPlayer player, BlockPos pos, Block block, EnumFacing side, float fx, float fy, float fz) {

		int direction = 0;

		String sourceDirection = itemstack.getTagCompound().getString("Direction").split(":")[1];
		if(sourceDirection.endsWith("2")) {

			direction = MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F)) & 3;
		} else {

			direction = MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5F) & 3;
		}

		String directionString = "SLOPE:";

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
		SoundType sound = b.getSoundType(block.getDefaultState(), world, pos, player);
		world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
		itemstack.shrink(1);

	}


	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {

		Iterator<SlopeEntry> itr = SlopeEntry.getSlopeEntries();
		while(itr.hasNext()) {

			SlopeEntry entry = itr.next();
			Block block = entry.getBlock();

			if(block.getDefaultState().getMaterial().isSolid()) {

				String[] textures = entry.getTexturesName();
				for(String texture : textures) {


					String blockString = GameRegistry.findRegistry(Block.class).getKey(block).toString();


					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "SLOPE:SOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}
					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "SLOPE:RSOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}

					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "SLOPE:SOUTH2");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}


				}

			}
		}
	}

}
