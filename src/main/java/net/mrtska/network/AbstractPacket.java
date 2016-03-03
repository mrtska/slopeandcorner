package net.mrtska.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 [Module AbstractPacket.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/19
 */
public interface AbstractPacket {


	public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);
	public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);
	public abstract void handleClientSide(EntityPlayer player);
	public abstract void handleServerSide(EntityPlayer player);
}