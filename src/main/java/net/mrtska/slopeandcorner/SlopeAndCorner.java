package net.mrtska.slopeandcorner;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mrtska.slopeandcorner.block.SlopeCreativeModeTab;
import net.mrtska.slopeandcorner.model.SlopeModelLoader;
import net.mrtska.slopeandcorner.slope.SlopeBlock;
import net.mrtska.slopeandcorner.slope.SlopeBlockEntity;
import net.mrtska.slopeandcorner.slope.SlopeItem;


/**
 * Slope Mod core class.
 */
@Mod(SlopeAndCorner.MODID)
public class SlopeAndCorner {

    public static final String MODID = "slopeandcorner";

    private static SlopeBlock slopeBlock;


    public SlopeAndCorner() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event) {
    }


    private void doClientStuff(ModelRegistryEvent event) {

        ModelLoaderRegistry.registerLoader(new ResourceLocation(MODID, "slope"), new SlopeModelLoader());


        ItemBlockRenderTypes.setRenderLayer(slopeBlock, RenderType.translucent());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    public static class BlockEntityTypes {

        public static BlockEntityType<?> slopeBlock;

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {

            slopeBlock = new SlopeBlock();

            // Register a new block here
            event.getRegistry().register(slopeBlock);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

            event.getRegistry().registerAll(new SlopeItem(slopeBlock, new SlopeCreativeModeTab(slopeBlock)));
        }
        @SubscribeEvent
        public static void onBlockEntityTypeRegistry(final RegistryEvent.Register<BlockEntityType<?>> event) {

            BlockEntityTypes.slopeBlock = BlockEntityType.Builder.of(SlopeBlockEntity::new, slopeBlock).build(null).setRegistryName(MODID, "slopeblock");
            // Register a block entity types.
            event.getRegistry().register(BlockEntityTypes.slopeBlock);
        }
    }
}
