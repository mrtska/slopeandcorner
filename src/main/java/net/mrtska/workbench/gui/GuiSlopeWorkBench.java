package net.mrtska.workbench.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.mrtska.core.Core;
import net.mrtska.network.PacketInteger;
import net.mrtska.workbench.ContainerSlopeWorkBench;

/**[Module GuiSlopeWorkBench.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
*/
public class GuiSlopeWorkBench extends GuiContainer {

	public static final int GuiID = 0;

	//GUI
	public static final ResourceLocation GUI = new ResourceLocation("slopeandcorner", "gui/crafting.png");

	//スロープボタン
	public SlopeRecipeGuideButton button = new SlopeRecipeGuideButton(this);

	//コンテナ
	public final ContainerSlopeWorkBench container;

	public GuiSlopeWorkBench(World world, EntityPlayer player, BlockPos pos) {
		super(new ContainerSlopeWorkBench(world, player, pos));

		this.container = (ContainerSlopeWorkBench) inventorySlots;
		xSize = 200;
		ySize = 166;
	}

	@Override
	public void initGui() {

		super.initGui();
		this.buttonList.add(button);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {


		int xStart = width - xSize >> 1;
		int yStart = height - ySize >> 1;
		button.xPosition = xStart + 137;
		button.yPosition = yStart + 12;

		this.mc.renderEngine.bindTexture(GUI);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);	//GUI描画

	}

	//ボタン押下処理
	@Override
	protected void actionPerformed(GuiButton button) {

		this.button.nextDrawIndex();

		PacketInteger index = new PacketInteger();
		index.index = this.button.getDrawIndex();
		container.drawIndex = index.index;
		Core.HANDLER.sendToServer(index);
	}



}































