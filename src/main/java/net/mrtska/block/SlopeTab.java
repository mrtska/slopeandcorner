package net.mrtska.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 [Module SlopeTab.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/28
 */
public class SlopeTab extends CreativeTabs {

	/**
	 * アイコンにするブロック
	 */
	private final Block block;

	//ブロックの種類、向き、テクスチャ
	private final String blockString;
	private final String direction;
	private final String texture;

	/**
	 *
	 * @param block
	 * @param tabName
	 * @param blockString ブロックの種類
	 * @param direction 向き
	 * @param texture テクスチャ
	 */
	public SlopeTab(Block block, String tabName, String blockString, String direction, String texture) {
		super(tabName);
		this.block = block;
		this.blockString = blockString;
		this.direction = direction;
		this.texture = texture;
	}

	@Override
	/**
	 * 何に使うのか分かってない とりあえずアイテムを返しておく
	 */
	public Item getTabIconItem() {
		return Item.getItemFromBlock(block);
	}

	@Override
	/**
	 * クリエイティブタブにするアイコンを設定
	 */
	public ItemStack getIconItemStack() {

		ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("BlockString", this.blockString);
		compound.setString("Direction", this.direction);
		compound.setString("Texture", this.texture);
		stack.setTagCompound(compound);

		return stack;
	}

}
