package com.killer.killersblocksnstuff.init;

import com.killer.killersblocksnstuff.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block) {
        super(block, new Item.Properties().tab(KBNS.KBNS_TAB));
    }
}