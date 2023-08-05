package com.killer.killersblocksnstuff.init.customItems;

import com.killer.killersblocksnstuff.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;

public class VibraniumAppleFoodBase extends Item {

    public VibraniumAppleFoodBase() {
        super(new Item.Properties().tab(KBNS.KBNS_TAB)
                .food
                        (new FoodProperties.Builder()

                                .nutrition(5)
                                .saturationMod(10.0F)
                                .effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F).alwaysEat().build()));


    }
}





