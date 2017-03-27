package net.mrtska.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 [Module SlopeWorkbenchSlot.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
 */
public class SlopeWorkbenchSlot extends Slot {

	private final IInventory inventory;
	private final EntityPlayer thePlayer;

	public SlopeWorkbenchSlot(IInventory inventory, int index, int x, int y, EntityPlayer player, IInventory result) {
		super(result, index, x, y);
		this.inventory = inventory;
		this.thePlayer = player;
	}

	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		this.onCrafting(stack);

		ItemStack itemstack1 = this.inventory.getStackInSlot(0);
		if(itemstack1 != null) {
			this.inventory.decrStackSize(0, 1);

			if(itemstack1.getItem().hasContainerItem()) {
				ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

				if(itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage()) {
					itemstack2 = null;
				}

				if(itemstack2 != null || !this.thePlayer.inventory.addItemStackToInventory(itemstack2)) {
					if(this.inventory.getStackInSlot(0) != null) {
						this.thePlayer.dropItem(itemstack2, false);
					}
				}
			}
		}
		((ContainerSlopeWorkBench)this.thePlayer.openContainer).detectAndSendChanges();

	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}

}
