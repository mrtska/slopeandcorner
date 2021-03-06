package net.mrtska.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**[Module MergedSlopeBlockTileEntity.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/20
*/
public class MergedSlopeTileEntity extends TileEntity {

	//上側のブロックのデータ
	public SlopeTileEntity top;

	//下側のブロックのデータ
	public SlopeTileEntity bottom;

	public MergedSlopeTileEntity() {

		top = new SlopeTileEntity();
		bottom = new SlopeTileEntity();
	}


	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);

		return new S35PacketUpdateTileEntity(pos, 1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		readFromNBT(pkt.getNbtCompound());
	}


	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		NBTTagCompound top = (NBTTagCompound) compound.getTag("Top");
		NBTTagCompound bottom = (NBTTagCompound) compound.getTag("Bottom");

		this.top.localReadFromNBT(top);
		this.bottom.localReadFromNBT(bottom);


	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);

		NBTTagCompound top = new NBTTagCompound();
		NBTTagCompound bottom = new NBTTagCompound();

		this.top.localWriteToNBT(top);
		this.bottom.localWriteToNBT(bottom);

		compound.setTag("Top", top);
		compound.setTag("Bottom", bottom);

	}
}
