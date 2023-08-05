package com.killer.killersblocksnstuff.init.customItems;

import com.killer.killersblocksnstuff.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.*;
import java.util.*;
import java.util.logging.*;

public class VibraniumPowder extends Item {

    public VibraniumPowder() {
        super(new Properties().tab(KBNS.KBNS_TAB));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level world, List<Component> tooltip, TooltipFlag p_41424_) {
        {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslatableComponent("tooltip.kbns.vibranium_powder_shift") {
                });
            } else {
                tooltip.add(new TranslatableComponent("tooltip.kbns.vibranium_powder"));
            }
        }
    }
}
