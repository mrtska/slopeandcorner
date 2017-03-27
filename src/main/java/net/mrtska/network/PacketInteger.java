package net.mrtska.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.mrtska.workbench.ContainerSlopeWorkBench;

/**[Module PacketInteger.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/19
*/
public class PacketInteger implements AbstractPacket {

	public int index;
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

		buffer.writeInt(index);
	}


	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

		index = buffer.readInt();
	}


	@Override
	public void handleClientSide(EntityPlayer player) {


	}


	@Override
	public void handleServerSide(EntityPlayer player) {

		ContainerSlopeWorkBench container = null;
		if(player.openContainer != null && player.openContainer instanceof ContainerSlopeWorkBench) {

			container = (ContainerSlopeWorkBench) player.openContainer;
			container.drawIndex = index;
			container.onCraftMatrixChanged(container.inventory);
		}
	}
}
