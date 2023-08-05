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

public class EnchantedVibraniumApple extends Item {

    public EnchantedVibraniumApple() {
        super(new Item.Properties().tab(KBNS.KBNS_TAB)
                .food(new FoodProperties.Builder()

                                .nutrition(5)
                                .saturationMod(10.0F)
                                .effect(new MobEffectInstance(MobEffects.REGENERATION, 1800, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12400, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12400, 2), .5F)
                                .effect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 1), .7F)
                                .effect(new MobEffectInstance(MobEffects.ABSORPTION, 3600, 2), 1.0F).alwaysEat().build()));


    }
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()){
            tooltip.add(new TranslatableComponent("tooltip.kbns.enchantedapple_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.kbns.enchantedapple"));
        }


    }
}

