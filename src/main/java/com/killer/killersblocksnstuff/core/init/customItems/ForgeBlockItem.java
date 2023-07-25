package com.killer.killersblocksnstuff.core.init.customItems;

import com.killer.killersblocksnstuff.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import javax.annotation.*;
import java.util.*;

public class ForgeBlockItem extends BlockItem {

    public ForgeBlockItem(Block block) {
        super(block, new Item.Properties().tab(KBNS.KBNS_TAB));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.kbns.forge_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.kbns.forge"));
        }

    }
}
