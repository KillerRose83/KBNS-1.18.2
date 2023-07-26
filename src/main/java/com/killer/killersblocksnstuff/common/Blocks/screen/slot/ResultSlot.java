package com.killer.killersblocksnstuff.common.Blocks.screen.slot;

import net.minecraft.world.item.*;
import net.minecraftforge.items.*;

public class ResultSlot extends SlotItemHandler {
    public ResultSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }
}
