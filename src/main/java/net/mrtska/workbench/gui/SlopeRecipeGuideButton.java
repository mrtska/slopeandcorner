package net.mrtska.workbench.gui;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.corner.CornerBlock;


@SideOnly(Side.CLIENT)
/**[Module SlopeButton.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
*/
public class SlopeRecipeGuideButton extends GuiButton {

	private static ArrayList<ItemStack> drawList = new ArrayList<ItemStack>();
	//public static ArrayList<SlopeTexture> drawTexture = new ArrayList<SlopeTexture>();
	private final RenderItem itemRenderer;
	private final GuiSlopeWorkBench gui;

	private static int drawIndex = 0;

	public SlopeRecipeGuideButton(GuiSlopeWorkBench gui) {
		super(0, 0, 0, 0, 0, "");
		this.width = 50;
		this.height = 50;
		this.gui = gui;

		this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {

		int xStart = (int) (xPosition / 3.2F);
		int yStart = (int) (yPosition / 3.2F);

		ItemStack stack = drawList.get(drawIndex);
		glPushMatrix();
		glScalef(3.2F, 3.2F, 1);
		itemRenderer.renderItemAndEffectIntoGUI(stack, xStart, yStart);
		glPopMatrix();

	}

	public void nextDrawIndex() {

		if(getSize() - 1 == getDrawIndex()) {

			this.drawIndex = 0;
		} else {

			this.drawIndex++;
		}
	}

	public void setDrawIndex(int index) {
		this.drawIndex = index;
	}
	public int getDrawIndex() {
		return this.drawIndex;

	}

	public int getSize() {

		return drawList.size();
	}

	public ItemStack getItemStack() {

		return drawList.get(drawIndex);
	}

	private static ItemStack getItemStack(Block block, String blockString, String direction, String texture) {

		ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("BlockString", blockString);
		compound.setString("Direction", direction);
		compound.setString("Texture", texture);

		stack.setTagCompound(compound);

		return stack;
	}

	public static ItemStack getDrawedItemStack(int index) {

		return drawList.get(index);
	}


	public static void init() {

		drawList.add(getItemStack(CornerBlock.instance, "minecraft:planks", "SOUTH", "0:planks_oak"));
		drawList.add(getItemStack(CornerBlock.instance, "minecraft:planks", "RSOUTH", "0:planks_oak"));
		drawList.add(getItemStack(CornerBlock.instance, "minecraft:planks", "SOUTH2", "0:planks_oak"));
		drawList.add(getItemStack(CornerBlock.instance, "minecraft:planks", "RSOUTH2", "0:planks_oak"));



	}



}
