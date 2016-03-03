package net.mrtska.corner;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.mrtska.core.SlopeItemBase;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.SlopeEntry;

/**[Module CornerItem.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
*/
public class CornerItem extends SlopeItemBase {

	public CornerItem(Block block) {
		super(block);

	}

	@Override
	//ブロック、TileEntityを設置 最後に効果音を再生
	public void setBlock(World world, ItemStack itemstack, EntityPlayer player, BlockPos pos, Block block, EnumFacing side, float fx, float fy, float fz) {

		int direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F)) & 3;

		String sourceDirection = itemstack.getTagCompound().getString("Direction").split(":")[1];

		String directionString = "CORNER:";

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

		Block b = GameData.getBlockRegistry().getObject(tileEntity.getBlockString());
		SoundType sound = b.stepSound;
		world.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), sound.getPlaceSound(), (sound.getVolume() + 1.0F) / 2.0F, sound.getFrequency() * 0.8F);
		itemstack.stackSize--;

		world.markBlockForUpdate(pos);
	}


	@Override
	//クリエイティブタブにブロックを追加
	public void getSubItems(Item item, CreativeTabs tab, List list) {

		Iterator<SlopeEntry> itr = SlopeEntry.getSlopeEntries();
		while(itr.hasNext()) {

			SlopeEntry entry = itr.next();
			Block block = entry.getBlock();

			if(block.getMaterial().isSolid()) {

				String[] textures = entry.getTexturesName();
				for(String texture : textures) {

					String blockString = GameRegistry.findUniqueIdentifierFor(block).toString();

					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "CORNER:SOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}
					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "CORNER:RSOUTH");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}

					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "CORNER:SOUTH2");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}

					{
						ItemStack stack = new ItemStack(item, 1, 0);
						NBTTagCompound compound = new NBTTagCompound();

						compound.setString("BlockString", blockString);
						compound.setString("Direction", "CORNER:RSOUTH2");
						compound.setString("Texture", texture);

						stack.setTagCompound(compound);
						list.add(stack);
					}

				}

			}
		}
	}

}
