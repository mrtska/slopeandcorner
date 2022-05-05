package net.mrtska.slopeandcorner.block;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import org.jetbrains.annotations.NotNull;

/**
 * Creative mode tab for slope blocks.
 */
public class SlopeCreativeModeTab extends CreativeModeTab {

    private final Block block;

    public SlopeCreativeModeTab(Block block, String label) {
        super(label);
        this.block = block;
    }

    @Override
    public @NotNull ItemStack makeIcon() {

        CompoundTag tag = new CompoundTag();
        tag.putString("BlockType", "SOUTH");
        tag.putString("Texture", "spruce_planks");

        ItemStack stack = new ItemStack(block);
        stack.setTag(tag);

        return stack;
    }
}
