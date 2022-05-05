package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.SlopeEntry;
import net.mrtska.slopeandcorner.item.SlopeItemBase;

import javax.annotation.Nonnull;

/**
 * Slope block item.
 */
public class SlopeItem extends SlopeItemBase {

    public SlopeItem(SlopeBlock block, CreativeModeTab tab) {
        super(block, new Properties().tab(tab));
        this.setRegistryName(SlopeAndCorner.MODID, "slopeitem");
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> list) {

        if (tab != this.getItemCategory()) {

            return;
        }

        // Register slope blocks for each vanilla blocks to creative mode tab.
        for (var entry : SlopeEntry.getSlopeBlocks()) {

            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "SOUTH");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "RSOUTH");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
            {
                var tag = new CompoundTag();
                tag.putString("BlockName", entry.getBlockName());
                tag.putString("BlockType", "SOUTH2");
                tag.putString("Texture", entry.getTexture());

                var stack = new ItemStack(this);
                stack.setTag(tag);

                list.add(stack);
            }
        }
    }
}
