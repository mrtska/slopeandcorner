package net.mrtska.core;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**[Module AbstractItem.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
*/
public abstract class SlopeItemBase extends ItemBlock {

	//ダメージは使用しない
	public SlopeItemBase(Block block) {
		super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}

	@Override
	/**
	 * アイテムを使った時の処理
	 */
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float fx, float fy, float fz) {

		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if(block == Blocks.snow_layer && ((Integer) iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1) {
			side = EnumFacing.UP;
		} else if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(side);
		}

		if (stack.stackSize == 0) { //スタックサイズが0のItemStackが来る場合があるので、そうだった場合は即処理終了
			return false;
		} else if (!player.canPlayerEdit(pos, side, stack)) {
			return false;
		} else if (pos.getY() == 255 && this.block.getMaterial().isSolid()) {
			return false;
		} else if (world.canBlockBePlaced(this.getBlock(), pos, false, side, (Entity) null, stack)) {
			this.setBlock(world, stack, player, pos, this.block, side, fx, fy, fz);
			return true;
		} else {
			return false;
		}
	}

	public abstract void setBlock(World world, ItemStack itemstack, EntityPlayer player, BlockPos pos, Block block, EnumFacing i, float fx, float fy, float fz);

	@Override
	/**
	 * デバッグ用info
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {

		NBTTagCompound compound = stack.getTagCompound();

		if(compound == null) {

			return;
		}

		list.add("BlockString " + compound.getString("BlockString"));
		list.add("Texture     " + compound.getString("Texture"));
		list.add("Direction   " + compound.getString("Direction"));

	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * 草ブロックの上面をデフォルトカラーに
	 */
	public int getColorFromItemStack(ItemStack stack, int renderPass) {

		return this.block.getBlockColor();
	}

	@Override
	/**
	 * ブロック名をローカライズする
	 */
	public String getItemStackDisplayName(ItemStack stack) {

		NBTTagCompound compound = stack.getTagCompound();

		if(compound == null) {

			return StatCollector.translateToLocal("tile.brokenblock.name");
		}

		String name = compound.getString("BlockString");
		String tex = compound.getString("Texture");

		Item item = GameData.getItemRegistry().getObject(name);

		return StatCollector.translateToLocal(item.getUnlocalizedName(new ItemStack(item, 1, Integer.valueOf(tex.split(":")[0]))) + ".name");
	}
}
