package net.mrtska.workbench;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**[Module SlopeWorkbenchItem.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
*/
public class SlopeWorkbenchItem extends ItemBlock {

	public SlopeWorkbenchItem(Block block) {
		super(block);

	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if(block == Blocks.snow_layer && ((Integer) iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1) {
			side = EnumFacing.UP;
		} else if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(side);
		}

		if (stack.stackSize == 0) { //スタックサイズが0のItemStackが来る場合があるので、そうだった場合は即処理終了
			return EnumActionResult.FAIL;
		} else if (!player.canPlayerEdit(pos, side, stack)) {
			return EnumActionResult.FAIL;
		} else if (pos.getY() == 255 && this.block.getDefaultState().getMaterial().isSolid()) {
			return EnumActionResult.FAIL;
		} else if (world.canBlockBePlaced(this.getBlock(), pos, false, side, (Entity) null, stack)) {
			int direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5F) & 3;
			String directionString = "SLOPE:";

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

			this.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, this.block.getBlockState().getBaseState());

			world.setTileEntity(pos, ((BlockContainer)this.block).createNewTileEntity(world, 0));

			SlopeWorkBenchTileEntity tileEntity = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);
			tileEntity.setDirection(directionString);

			Block b = Blocks.crafting_table;
			SoundType sound = b.getStepSound();
			world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
			stack.stackSize--;

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List list) {

		ItemStack stack = new ItemStack(itemIn);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Direction", "SLOPE:SOUTH");
		compound.setString("Texture", "0:down=planks_oak,up=crafting_table_top,north=crafting_table_front,east=crafting_table_side,south=crafting_table_side,west=crafting_table_front");

		stack.setTagCompound(compound);

		list.add(stack);
	}

	@Override
	public CreativeTabs getCreativeTab() {

		return CreativeTabs.tabDecorations;
	}

}
