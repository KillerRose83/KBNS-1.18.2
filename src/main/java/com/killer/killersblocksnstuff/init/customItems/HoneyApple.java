package com.killer.killersblocksnstuff.init.customItems;

import com.killer.killersblocksnstuff.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.*;
import java.util.*;
import java.util.logging.*;

public class HoneyApple extends Item {

    public HoneyApple() {
        super(new Item.Properties().tab(KBNS.KBNS_TAB)
                .food(new FoodProperties.Builder()


                        .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 1), 1.0F)
                        .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 0), 1.0F).alwaysEat().build()));


    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslatableComponent("tooltip.kbns.haste_food_shift"));
            } else {
                tooltip.add(new TranslatableComponent("tooltip.kbns.haste_food"));
            }
        }
    }
}
