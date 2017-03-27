package net.mrtska.tileentity;

import net.minecraft.nbt.NBTTagCompound;


/**[Module DoubleMergedSlopeTileEntity.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/30
*/
public class DoubleMergedSlopeTileEntity extends MergedSlopeTileEntity {

	//上側のブロックのデータ
	public SlopeTileEntity top2;

	//下側のブロックのデータ
	public SlopeTileEntity bottom2;

	public DoubleMergedSlopeTileEntity() {

		this.top2 = new SlopeTileEntity();
		this.top2.setBlockString("null");
		this.top2.setDirection("null");
		this.top2.setTexture("0:null");

		this.bottom2 = new SlopeTileEntity();
		this.bottom2.setBlockString("null");
		this.bottom2.setDirection("null");
		this.bottom2.setTexture("0:null");
	}



	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		NBTTagCompound top2 = (NBTTagCompound) compound.getTag("Top2");
		NBTTagCompound bottom2 = (NBTTagCompound) compound.getTag("Bottom2");

		this.top2.localReadFromNBT(top2);
		this.bottom2.localReadFromNBT(bottom2);


	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);


		NBTTagCompound top2 = new NBTTagCompound();
		NBTTagCompound bottom2 = new NBTTagCompound();

		this.top2.localWriteToNBT(top2);
		this.bottom2.localWriteToNBT(bottom2);

		compound.setTag("Top2", top2);
		compound.setTag("Bottom2", bottom2);

		return compound;
	}



}
