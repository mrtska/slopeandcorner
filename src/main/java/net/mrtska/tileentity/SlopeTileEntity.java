package net.mrtska.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**[Module AbstractTileEntity.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
*/
public class SlopeTileEntity extends TileEntity {

	/**
	 * Blockの固有文字列
	 */
	private String block;

	public void setBlockString(String s) {

		this.block = s;
	}

	public String getBlockString() {

		return this.block;
	}

	/**
	 * テクスチャ
	 */
	private String texture;

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	/**
	 * 向き
	 */
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {

		this.direction = direction;
	}


	@Override
	/**
	 * パケットを送る
	 */
	public Packet getDescriptionPacket() {

		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);

		return new S35PacketUpdateTileEntity(this.pos, 1, compound);
	}

	@Override
	/**
	 * パケットを受け取る
	 */
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		this.readFromNBT(pkt.getNbtCompound());
	}


	//NBTと同期
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		localReadFromNBT(compound);
	}

	//座標以外のデータをNBTから読み込む
	public void localReadFromNBT(NBTTagCompound compound) {

		this.block = compound.getString("BlockString");
		this.direction = compound.getString("Direction");
		this.texture = compound.getString("Texture");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		localWriteToNBT(compound);
	}
	//座標以外のデータをNBTに書き込む
	public void localWriteToNBT(NBTTagCompound compound) {

		compound.setString("BlockString", this.block);
		compound.setString("Direction", this.direction);
		compound.setString("Texture", this.texture);
	}
}
