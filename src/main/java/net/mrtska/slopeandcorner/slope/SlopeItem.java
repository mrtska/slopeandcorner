package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.SlopeEntry;
import net.mrtska.slopeandcorner.item.SlopeItemBase;
import org.jetbrains.annotations.NotNull;

/**
 * Slope block item.
 */
public class SlopeItem extends SlopeItemBase {

    public SlopeItem(SlopeBlock block, CreativeModeTab tab) {
        super(block, new Properties().tab(tab));
        this.setRegistryName(SlopeAndCorner.MODID, "slopeitem");
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> list) {

        if (tab != this.getItemCategory()) {

            return;
        }

        // Register slope blocks for each vanilla blocks to creative mode tab.
        for (SlopeEntry entry : SlopeEntry.getSlopeBlocks()) {

            {
                CompoundTag tag = new CompoundTag();
                tag.putString("Direction", "SLOPE:SOUTH");
                tag.putString("Texture", entry.getTexturesName()[0]);

                ItemStack stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
            {
                CompoundTag tag = new CompoundTag();
                tag.putString("Direction", "SLOPE:RSOUTH");
                tag.putString("Texture", entry.getTexturesName()[0]);

                ItemStack stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
        }

    }
}
