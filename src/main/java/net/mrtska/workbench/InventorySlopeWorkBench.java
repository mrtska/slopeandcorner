package net.mrtska.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 [Module InventorySlopeWorkbench.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
 */
public class InventorySlopeWorkBench implements IInventory {

	public ItemStack item;
	private ContainerSlopeWorkBench container;

	public InventorySlopeWorkBench(ContainerSlopeWorkBench container) {
		this.container = container;

	}


	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int num) {
		return item;
	}

	@Override
	public ItemStack decrStackSize(int index, int j) {
		if(item != null) {
			ItemStack itemstack;
			if(item.stackSize <= j) {
				itemstack = item;
				item = null;
				container.onCraftMatrixChanged(this);
				return itemstack;
			} else {
				itemstack = item.splitStack(j);
				if(item.stackSize == 0) {
					item = null;
				}
				this.container.onCraftMatrixChanged(this);
				return itemstack;
			}
		} else {
			return null;
		}
	}


	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

		item = stack;
		container.onCraftMatrixChanged(this);

	}


	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}


	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}


	@Override
	public String getName() {

		return "slopeinventory";
	}


	@Override
	public boolean hasCustomName() {

		return false;
	}


	@Override
	public ITextComponent getDisplayName() {

		return null;
	}


	@Override
	public void openInventory(EntityPlayer player) {
	}


	@Override
	public void closeInventory(EntityPlayer player) {
	}


	@Override
	public int getField(int id) {

		return 0;
	}


	@Override
	public void setField(int id, int value) {
	}


	@Override
	public int getFieldCount() {

		return 0;
	}


	@Override
	public void clear() {
	}


	@Override
	public ItemStack removeStackFromSlot(int index) {

		return null;
	}

}
