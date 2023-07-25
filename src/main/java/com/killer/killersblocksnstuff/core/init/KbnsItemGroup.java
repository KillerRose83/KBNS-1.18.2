package com.killer.killersblocksnstuff.core.init;

import net.minecraft.world.item.*;

public class KbnsItemGroup extends CreativeModeTab {
    public KbnsItemGroup(String x0) {
            super(x0);
        }

        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.VIBRANIUM_INGOT.get());
        }
    }


