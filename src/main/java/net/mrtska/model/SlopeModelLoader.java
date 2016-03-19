package net.mrtska.model;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.corner.CornerModel;
import net.mrtska.corner.merge.MergedCornerModel;
import net.mrtska.edgecorner.EdgeCornerModel;
import net.mrtska.halfslope.HalfSlopeModel;
import net.mrtska.slope.SlopeModel;
import net.mrtska.slope.merge.MergedSlopeModel;

/**
 * [Module SlopeModelLoader.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/24
 */
@SideOnly(Side.CLIENT)
public class SlopeModelLoader implements ICustomModelLoader {

	//モデルローダーインスタンス
	public static final SlopeModelLoader instance = new SlopeModelLoader();

	@Override
	//よく分からん
	public void onResourceManagerReload(IResourceManager resourceManager) {
	}

	@Override
	//ドメインがmodidと一致したら
	public boolean accepts(ResourceLocation modelLocation) {


		String s = modelLocation.getResourcePath();

		if(s.endsWith(".model")) {

			return true;
		}
		//System.out.println(modelLocation.getResourcePath());
		return false;
		//return modelLocation.getResourceDomain().equals(Core.modid);
	}

	@Override
	//モデルをロード
	public IModel loadModel(ResourceLocation modelLocation) {

		String s = modelLocation.getResourcePath();

		if(s.endsWith("/cornerblock.model")) {

			return CornerModel.instance;
		}

		if(s.endsWith("/mergecornerblock.model")) {

			return MergedCornerModel.instance;
		}

		if(s.endsWith("/slopeblock.model")) {

			return SlopeModel.instance;
		}

		if(s.endsWith("/mergeslopeblock.model")) {

			return MergedSlopeModel.instance;
		}

		if(s.endsWith("/edgecornerblock.model")) {

			return EdgeCornerModel.instance;
		}

		if(s.endsWith("/halfslopeblock.model")) {

			return HalfSlopeModel.instance;
		}

		if(s.endsWith("/slopeworkbench.model")) {

			return SlopeModel.instance;
		}

		if(s.endsWith("/doublemergedhalfslopeblock.model")) {

			return HalfSlopeModel.instance;
		}


		System.out.println(modelLocation);
		return HalfSlopeModel.instance;
	}

}
