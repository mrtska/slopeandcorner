package net.mrtska.slopeandcorner.block;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class SlopeCreativeModeTab extends CreativeModeTab {

    public SlopeCreativeModeTab() {
        super("slope");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(Blocks.ACACIA_WOOD);
    }
}
