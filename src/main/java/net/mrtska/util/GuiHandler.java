package net.mrtska.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.mrtska.workbench.ContainerSlopeWorkBench;
import net.mrtska.workbench.gui.GuiSlopeWorkBench;

/**[Module GuiHandler.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/04
*/
public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == GuiSlopeWorkBench.GuiID) {
			return new ContainerSlopeWorkBench(world, player, new BlockPos(x, y, z));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {


		if(ID == GuiSlopeWorkBench.GuiID) {
			return new GuiSlopeWorkBench(world, player, new BlockPos(x, y, z));
		}

		return null;
	}

}
