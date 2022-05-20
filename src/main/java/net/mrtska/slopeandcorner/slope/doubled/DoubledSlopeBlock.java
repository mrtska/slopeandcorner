package net.mrtska.slopeandcorner.slope.doubled;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.DoubledSlopeBlockBase;
import net.mrtska.slopeandcorner.block.DoubledSlopeBlockEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DoubledSlopeBlock extends DoubledSlopeBlockBase {

    public DoubledSlopeBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "doubledslopeblock");
    }


    @Override
    public void playerDestroy(@Nonnull Level level, @Nonnull Player player, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable BlockEntity entity, @Nonnull ItemStack stack) {

        if (!(entity instanceof DoubledSlopeBlockEntity blockEntity)) {
            return;
        }

        var f = EntityType.ITEM.getHeight() / 2.0F;
        var rx = (double)((float)pos.getX() + 0.5F) + Mth.nextDouble(level.random, -0.25D, 0.25D);
        var ry = (double)((float)pos.getY() + 0.5F) + Mth.nextDouble(level.random, -0.25D, 0.25D) - (double)f;
        var rz = (double)((float)pos.getZ() + 0.5F) + Mth.nextDouble(level.random, -0.25D, 0.25D);

        var type = blockEntity.getBlockType();
        var newType = "";
        if (type.startsWith("R")) {
            newType += "R";
        }
        // Only use south direction.
        newType += "SOUTH";
        if (type.endsWith("2")) {
            newType += "2";
        }

        stack = new ItemStack(SlopeAndCorner.slopeBlock);
        var tag = new CompoundTag();
        tag.putString("BlockName", blockEntity.getBlockName());
        tag.putString("BlockType", newType);
        tag.putString("Texture", blockEntity.getTexture());

        stack.setTag(tag);
        var item = new ItemEntity(level, rx, ry, rz, stack);
        item.setDefaultPickUpDelay();

        level.addFreshEntity(item);

        var type2 = blockEntity.getBlockType2();
        var newType2 = "";
        if (type2.startsWith("R")) {
            newType2 += "R";
        }
        // Only use south direction.
        newType2 += "SOUTH";
        if (type2.endsWith("2")) {
            newType2 += "2";
        }

        stack = new ItemStack(SlopeAndCorner.slopeBlock);
        tag = new CompoundTag();
        tag.putString("BlockName", blockEntity.getBlockName2());
        tag.putString("BlockType", newType2);
        tag.putString("Texture", blockEntity.getTexture2());

        stack.setTag(tag);
        item = new ItemEntity(level, rx, ry, rz, stack);
        item.setDefaultPickUpDelay();

        level.addFreshEntity(item);
    }
}
