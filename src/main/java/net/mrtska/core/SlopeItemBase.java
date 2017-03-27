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
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;

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
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float fx, float fy, float fz) {

		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();

        ItemStack stack = player.getHeldItem(hand);

		if(block == Blocks.SNOW_LAYER && ((Integer) iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1) {
			side = EnumFacing.UP;
		} else if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(side);
		}

		if (stack.getCount() == 0) { //スタックサイズが0のItemStackが来る場合があるので、そうだった場合は即処理終了
			return EnumActionResult.FAIL;
		} else if (!player.canPlayerEdit(pos, side, stack)) {
			return EnumActionResult.FAIL;
		} else if (pos.getY() == 255 && this.block.getDefaultState().getMaterial().isSolid()) {
			return EnumActionResult.FAIL;
		} else if (world.mayPlace(this.getBlock(), pos, false, side, (Entity) null)) {
			this.setBlock(world, stack, player, pos, this.block, side, fx, fy, fz);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
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
/*
	@Override
	@SideOnly(Side.CLIENT)
	*//**
	 * 草ブロックの上面をデフォルトカラーに
	 *//*
	public int getColorFromItemStack(ItemStack stack, int renderPass) {

		return this.block.getBlockColor();
	}*/

	@Override
	/**
	 * ブロック名をローカライズする
	 */
	public String getItemStackDisplayName(ItemStack stack) {

		NBTTagCompound compound = stack.getTagCompound();

		if(compound == null) {

			return I18n.translateToLocal("tile.brokenblock.name");
		}

		String name = compound.getString("BlockString");
		String tex = compound.getString("Texture");

		Item item = GameData.getItemRegistry().getObject(new ResourceLocation(name));

		return I18n.translateToLocal(item.getUnlocalizedName(new ItemStack(item, 1, Integer.valueOf(tex.split(":")[0]))) + ".name");
	}
}
