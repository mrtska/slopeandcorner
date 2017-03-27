package net.mrtska.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

/**
 [Module InventorySlopeWorkbenchResult.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
 */
public class InventorySlopeWorkbenchResult implements IInventory {

	private ItemStack craftResult;

	private final InventorySlopeWorkBench inventory;
	private final ContainerSlopeWorkBench workbench;

	public InventorySlopeWorkbenchResult(InventorySlopeWorkBench inventory, ContainerSlopeWorkBench workbench) {

		this.inventory = inventory;
		this.workbench = workbench;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return craftResult;
	}

	@Override
	public ItemStack decrStackSize(int index, int j) {

		return craftResult;
	}


	@Override
	public void setInventorySlotContents(int var1, ItemStack stack) {

		this.craftResult = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	@Override
	public String getName() {

		return null;
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

	@Override
	public boolean isEmpty() {
		return false;
	}

}
