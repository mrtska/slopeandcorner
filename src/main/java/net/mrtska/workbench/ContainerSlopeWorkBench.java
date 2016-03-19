package net.mrtska.workbench;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.mrtska.util.SlopeEntry;
import net.mrtska.workbench.gui.SlopeRecipeGuideButton;

/**[Module ContainerSlopeWorkBench.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
*/
public class ContainerSlopeWorkBench extends Container {

	//ワールドインスタンス
	public final World world;

	//座標
	public final BlockPos pos;

	//斜面作業台のインベントリ
	public final InventorySlopeWorkBench inventory;

	//クラフト結果
	public final InventorySlopeWorkbenchResult result;

	//斜面作業台のTileEntity
	public final SlopeWorkBenchTileEntity tileEntity;

	//ガイド
	public static int drawIndex = 0;

	//スロットの初期化など
	public ContainerSlopeWorkBench(World world, EntityPlayer player, BlockPos pos) {

		this.world = world;
		this.pos = pos;

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, 23 + i * 18, 136));
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i + 9, 23 + i * 18, 78));
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i + 18, 23 + i * 18, 96));
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i + 27, 23 + i * 18, 114));
		}

		this.tileEntity = (SlopeWorkBenchTileEntity) world.getTileEntity(pos);
		inventory = new InventorySlopeWorkBench(this);
		this.addSlotToContainer(new Slot(this.inventory, 0, 32, 31));

		result = new InventorySlopeWorkbenchResult(inventory, this);
		this.addSlotToContainer(new SlopeWorkbenchSlot(this.inventory, 0, 95, 32, player, this.result));
	}

	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if(!this.world.isRemote) {

			if(inventory.getStackInSlot(0) != null) {
				playerIn.dropPlayerItemWithRandomChoice(inventory.getStackInSlot(0), false);
			}
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {

		ItemStack source = this.inventory.item;

		if(source == null) {
			result.setInventorySlotContents(0, null);
			return;
		}

		Block block = Block.getBlockFromItem(source.getItem());
		int metadata = source.getItemDamage();
		if(block == null) {

			return;
		}
		UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(block);
		SlopeEntry entry = SlopeEntry.getSlopeEntry(identifier.name);
		if(entry == null) {
			result.setInventorySlotContents(0, null);
			return;
		}
		String actTexture = "";
		for(String texture : entry.getTexturesName()) {

			int index = Integer.parseInt(texture.split(":")[0]);
			if(metadata == index) {

				actTexture = texture;
			}
		}
		ItemStack target = ItemStack.copyItemStack(SlopeRecipeGuideButton.getDrawedItemStack(this.drawIndex));
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("BlockString", identifier.toString());
		compound.setString("Direction", SlopeRecipeGuideButton.getDrawedItemStack(this.drawIndex).getTagCompound().getString("Direction"));
		compound.setString("Texture", actTexture);

		target.setTagCompound(compound);
		result.setInventorySlotContents(0, target);
		this.detectAndSendChanges();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	protected void retrySlotClick(int par1, int par2, boolean flag, EntityPlayer player) {
	}

}
