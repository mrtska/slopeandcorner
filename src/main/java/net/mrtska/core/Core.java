package net.mrtska.core;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mrtska.block.SlopeTab;
import net.mrtska.corner.CornerBlock;
import net.mrtska.corner.CornerItem;
import net.mrtska.corner.CornerModel;
import net.mrtska.corner.merge.MergedCornerBlock;
import net.mrtska.corner.merge.MergedCornerModel;
import net.mrtska.edgecorner.EdgeCornerBlock;
import net.mrtska.edgecorner.EdgeCornerItem;
import net.mrtska.edgecorner.EdgeCornerModel;
import net.mrtska.halfslope.HalfSlopeBlock;
import net.mrtska.halfslope.HalfSlopeItem;
import net.mrtska.halfslope.HalfSlopeModel;
import net.mrtska.halfslope.merge.DoubleMergedHalfSlopeBlock;
import net.mrtska.model.SlopeModelLoader;
import net.mrtska.network.PacketHandler;
import net.mrtska.network.PacketInteger;
import net.mrtska.slope.SlopeBlock;
import net.mrtska.slope.SlopeItem;
import net.mrtska.slope.SlopeModel;
import net.mrtska.slope.merge.MergedSlopeBlock;
import net.mrtska.slope.merge.MergedSlopeModel;
import net.mrtska.tileentity.DoubleMergedSlopeTileEntity;
import net.mrtska.tileentity.MergedSlopeTileEntity;
import net.mrtska.tileentity.SlopeTileEntity;
import net.mrtska.util.GuiHandler;
import net.mrtska.util.SlopeEntry;
import net.mrtska.util.SlopeUtils;
import net.mrtska.workbench.SlopeWorkBench;
import net.mrtska.workbench.SlopeWorkBenchTileEntity;
import net.mrtska.workbench.SlopeWorkbenchItem;
import net.mrtska.workbench.gui.SlopeRecipeGuideButton;

/**
 [Module Core.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/17
 */
@Mod(modid = Core.modid, name = "斜面ブロックMOD", version = Core.version)
public class Core {

	//modID バージョン
	public static final String modid = "slopeandcorner";
	public static final String version = "1.0.0";

	//MODのインスタンス
	@Mod.Instance("slopeandcorner")
	public static Core instance;


	public static final PacketHandler HANDLER = new PacketHandler();

	@SideOnly(Side.CLIENT)
	//クライアントのみ モデルローダー関連を登録
	private void clientInit() {

		ModelLoaderRegistry.registerLoader(SlopeModelLoader.instance);

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CornerBlock.instance), 0, new ModelResourceLocation("slopeandcorner:cornerblock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SlopeBlock.instance), 0, new ModelResourceLocation("slopeandcorner:slopeblock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EdgeCornerBlock.instance), 0, new ModelResourceLocation("slopeandcorner:edgecornerblock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(HalfSlopeBlock.instance), 0, new ModelResourceLocation("slopeandcorner:halfslopeblock", "inventory"));


		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SlopeWorkBench.instance), 0, new ModelResourceLocation("slopeandcorner:slopeworkbench", "inventory"));

	}

	@EventHandler
	//ブロック初期化
	public void preInit(FMLPreInitializationEvent event) {



		CornerBlock.instance = new CornerBlock();
		SlopeUtils.cornerTab = new SlopeTab(CornerBlock.instance, "corner", "minecraft:planks", "CORNER:SOUTH", "planks_oak");
		CornerBlock.instance.setCreativeTab(SlopeUtils.cornerTab);

		MergedCornerBlock.instance = new MergedCornerBlock();

		SlopeBlock.instance = new SlopeBlock();
		SlopeUtils.slopeTab = new SlopeTab(SlopeBlock.instance, "slope", "minecraft:planks", "SLOPE:SOUTH", "planks_birch");
		SlopeBlock.instance.setCreativeTab(SlopeUtils.slopeTab);

		MergedSlopeBlock.instance = new MergedSlopeBlock();

		EdgeCornerBlock.instance = new EdgeCornerBlock();
		SlopeUtils.edgecornerTab = new SlopeTab(EdgeCornerBlock.instance, "edgecorner", "minecraft:planks", "EDGECORNER:SOUTH", "planks_spruce");
		EdgeCornerBlock.instance.setCreativeTab(SlopeUtils.edgecornerTab);

		HalfSlopeBlock.instance = new HalfSlopeBlock();
		SlopeUtils.halfslopeTab = new SlopeTab(HalfSlopeBlock.instance, "halfslope", "minecraft:planks", "HALFSLOPE:SOUTH", "planks_jungle");
		HalfSlopeBlock.instance.setCreativeTab(SlopeUtils.halfslopeTab);

		DoubleMergedHalfSlopeBlock.instance = new DoubleMergedHalfSlopeBlock();

		SlopeWorkBench.instance = new SlopeWorkBench();


		//ブロック登録
		GameRegistry.registerBlock(CornerBlock.instance, CornerItem.class, "cornerblock");
		GameRegistry.registerBlock(MergedCornerBlock.instance, "mergecornerblock");
		GameRegistry.registerBlock(SlopeBlock.instance, SlopeItem.class, "slopeblock");
		GameRegistry.registerBlock(MergedSlopeBlock.instance, "mergeslopeblock");
		GameRegistry.registerBlock(EdgeCornerBlock.instance, EdgeCornerItem.class, "edgecornerblock");
		GameRegistry.registerBlock(HalfSlopeBlock.instance, HalfSlopeItem.class, "halfslopeblock");
		GameRegistry.registerBlock(DoubleMergedHalfSlopeBlock.instance, "doublemergedhalfslopeblock");
		GameRegistry.registerBlock(SlopeWorkBench.instance, SlopeWorkbenchItem.class, "slopeworkbench");




		//クライアントのみ
		if(event.getSide().isClient()) {

			clientInit();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {


		//斜面ブロックのTileEntity登録
		GameRegistry.registerTileEntity(SlopeTileEntity.class, "slopeandcorner");
		GameRegistry.registerTileEntity(MergedSlopeTileEntity.class, "mergeslopeandcorner");
		GameRegistry.registerTileEntity(DoubleMergedSlopeTileEntity.class, "doublemergedslopeandcorner");
		//斜面作業台のTileEntity登録
		GameRegistry.registerTileEntity(SlopeWorkBenchTileEntity.class, "slopeworkbench");



		//GUIハンドラ登録
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());


		//斜面作業台を作るレシピ登録
		GameRegistry.addShapedRecipe(new ItemStack(SlopeWorkBench.instance), new Object[] { "X  ", " X ", "XXX", 'X', Blocks.crafting_table });

		HANDLER.initalise();
	}

	@EventHandler
	//モデルデータなどを初期化
	public void postInit(FMLPostInitializationEvent event) {

		CornerModel.instance.init();
		MergedCornerModel.instance.init();
		SlopeModel.instance.init();
		MergedSlopeModel.instance.init();
		EdgeCornerModel.instance.init();
		HalfSlopeModel.instance.init();

		SlopeEntry.init();
		SlopeRecipeGuideButton.init();

		HANDLER.postInitialise();

	}

	//パケットを使用するTileEntityを登録
	public void registerPacket(PacketHandler packetHandler) {

		packetHandler.registerPacket(PacketInteger.class);
	}

}



















