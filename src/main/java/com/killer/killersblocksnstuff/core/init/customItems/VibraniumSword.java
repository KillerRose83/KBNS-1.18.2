package com.killer.killersblocksnstuff.core.init.customItems;

import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import javax.annotation.*;
import java.util.List;

public class VibraniumSword extends SwordItem {
    private static final double M = Math.random() ;

    public VibraniumSword(Tier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Here we can add effects on hit with this weapon to the target
        attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60));
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 2));
        target.setSecondsOnFire(3);


        if(M < .03F){
            attacker.addEffect(new MobEffectInstance(MobEffects.WITHER,200,2));
        }
          else{
              attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,2));
        }



        return super.hurtEnemy(stack, target, attacker);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.add(new TranslatableComponent("tooltip.kbns.sword_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.kbns.sword"));
        }


    }
}
