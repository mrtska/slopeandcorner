	package net.mrtska.model;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**[Module CustomItemOverride.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2016/03/19
*/
public class CustomItemOverride extends ItemOverrideList {

	public CustomItemOverride() {

		this(new ArrayList<ItemOverride>());
	}

	public CustomItemOverride(List<ItemOverride> overridesIn) {
		super(overridesIn);

	}


	@Override
	public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {


		if(!stack.hasTagCompound()) {

			System.out.println("BakedSlopeModel.handleItemState()");
			return originalModel;
		}

		NBTTagCompound compound = stack.getTagCompound();
		String block = compound.getString("BlockString");


		String texture = compound.getString("Texture");

		if(texture.contains(":")) {

			texture = compound.getString("Texture").split(":")[1];
		}


		if(originalModel instanceof BakedSlopeModel) {

			BakedSlopeModel model = (BakedSlopeModel) originalModel;
			model.texture = new String[] { texture };
			model.direction = new String[] { compound.getString("Direction") };
			model.vertexData = model.getModel().getVertex(model.direction, model.texture, model.format);

			return model;
		}



		return super.handleItemState(originalModel, stack, world, entity);
	}


}
