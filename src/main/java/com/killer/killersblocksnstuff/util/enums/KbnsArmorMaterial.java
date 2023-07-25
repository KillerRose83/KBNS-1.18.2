package com.killer.killersblocksnstuff.util.enums;



import com.killer.killersblocksnstuff.core.init.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;

import java.util.function.*;

public enum KbnsArmorMaterial implements ArmorMaterial {
   VIBRANIUM("kbns:vibranium", 41, new int[]{5, 8, 11, 5}, 23, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.2F, () -> {
      return Ingredient.of(ItemInit.VIBRANIUM_INGOT.get());
   });

   private static final int[] baseDurability = new int[]{11, 16, 15, 13};
   private final String name;
   private final int durabilityMultiplier;
   private final int[] armorVal;
   private final int enchantability;
   private final SoundEvent SoundEvent;
   private final float toughness;
   private final float knockbackResistance;
   private final Ingredient repairIngredient;

   private KbnsArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier repairIngredient) {
      this.name = name;
      this.durabilityMultiplier = maxDamageFactor;
      this.armorVal = damageReductionAmountArray;
      this.enchantability = enchantability;
      this.SoundEvent = soundEvent;
      this.toughness = toughness;
      this.knockbackResistance = knockbackResistance;
      this.repairIngredient = (Ingredient)repairIngredient.get();
   }

   @Override
   public int getDurabilityForSlot(EquipmentSlot slot) {
      return baseDurability[slot.getIndex()] * this.durabilityMultiplier;
   }

   @Override
   public int getDefenseForSlot(EquipmentSlot slot) {
      return this.armorVal[slot.getIndex()];
   }

   @Override
   public int getEnchantmentValue()  {
      return this.enchantability;
   }

   @Override
   public net.minecraft.sounds.SoundEvent getEquipSound() {
      return this.SoundEvent;
   }

   @Override
   public Ingredient getRepairIngredient()  {
      return this.repairIngredient;
   }

   @Override
   public String getName()  {
      return this.name;
   }

   @Override
   public float getToughness()  {
      return this.toughness;
   }

   @Override
   public float getKnockbackResistance()  {
      return this.knockbackResistance;
   }
}
