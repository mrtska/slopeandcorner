package net.mrtska.slope.merge;

import net.mrtska.model.SlopeModelBase;
import net.mrtska.slope.SlopeModel;

/**[Module MergeSlopeModel.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/20
*/
public class MergedSlopeModel extends SlopeModelBase {

	public static final MergedSlopeModel instance = new MergedSlopeModel();

	@Override
	public void init() {

		this.vertex.putAll(SlopeModel.instance.getVertex());
	}




}
