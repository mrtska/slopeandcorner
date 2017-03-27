package net.mrtska.corner.merge;

import net.mrtska.corner.CornerModel;
import net.mrtska.edgecorner.EdgeCornerModel;
import net.mrtska.model.SlopeModelBase;

/**[Module MergeCornerModel.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/23
*/
public class MergedCornerModel extends SlopeModelBase {

	public static final MergedCornerModel instance = new MergedCornerModel();

	@Override
	public void init() {

		this.vertex.putAll(CornerModel.instance.getVertex());
		this.vertex.putAll(EdgeCornerModel.instance.getVertex());
	}


}
