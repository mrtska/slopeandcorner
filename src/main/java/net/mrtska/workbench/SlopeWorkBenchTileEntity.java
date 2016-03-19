package net.mrtska.workbench;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**[Module SlopeWorkBenchTileEntity.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/03
*/
public class SlopeWorkBenchTileEntity extends TileEntity {


	private String direction;


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {

		this.direction = direction;
	}



	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);

		return new SPacketUpdateTileEntity(this.pos, 1, compound);

	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {

		this.readFromNBT(pkt.getNbtCompound());
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		this.direction = compound.getString("Direction");

	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setString("Direction", this.direction);

	}


















}
