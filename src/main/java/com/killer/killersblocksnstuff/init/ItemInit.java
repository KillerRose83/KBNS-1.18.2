package com.killer.killersblocksnstuff.init;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.init.customItems.*;
import com.killer.killersblocksnstuff.util.enums.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.*;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KBNS.MOD_ID);
    public static final RegistryObject<Item> VIBRANIUM_POWDER = ITEMS.register("telemberite_powder", VibraniumPowder::new);
    public static final RegistryObject<Item> VIBRANIUM_INGOT = ITEMS.register("telemberite_ingot", ItemBase::new);
    public static final RegistryObject<Item> REINFORCED_TOOL_ROD = ITEMS.register("reinforced_tool_rod", ItemBase::new);
    public static final RegistryObject<Item> VOID_STONE_POWDER = ITEMS.register("void_stone_powder", ItemBase::new);
    public static final RegistryObject<Item> REFINED_VIBRANIUM_POWDER = ITEMS.register("refined_telemberite_powder", ItemBase::new);


    public static final RegistryObject<Item> VIBRANIUM_ORE_ITEM = ITEMS.register("telemberite_ore", () -> {
        return new BlockItemBase((Block) BlockInit.VIBRANIUM_ORE.get());
    });
    public static final RegistryObject<Item> VIBRANIUM_BLOCK_ITEM = ITEMS.register("telemberite_block", () -> {
        return new BlockItemBase((Block) BlockInit.VIBRANIUM_BLOCK.get());
    });
    public static final RegistryObject<Item> VIBRANIUM_FORGE_BLOCK_ITEM = ITEMS.register("telemberite_forge", () -> {
        return new ForgeBlockItem((Block) BlockInit.VIBRANIUM_FORGE.get());
     });

    public static final RegistryObject<Item> SOLAR_GENERATOR_BLOCK_ITEM = ITEMS.register("solar_generator", () -> {
        return new ForgeBlockItem((Block) BlockInit.SOLAR_GENERATOR.get());
    });
    public static final RegistryObject<Item> REFINED_VIBRANIUM_POWDER_BLOCK_ITEM = ITEMS.register("refined_telemberite_powder_block", () -> {
        return new BlockItemBase((Block) BlockInit.REFINED_VIBRANIUM_POWDER_BLOCK.get());
    });

    public static final RegistryObject<Item> VOID_STONE_ITEM = ITEMS.register("void_stone", () -> {
        return new BlockItemBase((Block) BlockInit.VOID_STONE.get());
    });
    public static final RegistryObject<Item> CONCRETE_LAMP_WHITE_ITEM = ITEMS.register("concrete_lamp_white", () -> {
        return new BlockItemBase((Block) BlockInit.CONCRETE_LAMP_WHITE.get());
    });
    public static final RegistryObject<Item> CONCRETE_LAMP_ORANGE_ITEM = ITEMS.register("concrete_lamp_orange", () -> {
        return new BlockItemBase((Block) BlockInit.CONCRETE_LAMP_ORANGE.get());
    });
    public static final RegistryObject<Item> CONCRETE_LAMP_RED_ITEM = ITEMS.register("concrete_lamp_red", () -> {
        return new BlockItemBase((Block) BlockInit.CONCRETE_LAMP_RED.get());
    });
    public static final RegistryObject<Item> VIBRANIUM_SWORD = ITEMS.register("telemberite_sword", () -> {
        return new VibraniumSword(KbnsItemTier.VIBRANIUM, 1, -2.2F, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_PICKAXE = ITEMS.register("telemberite_pickaxe", () -> {
        return new PickaxeItem(KbnsItemTier.VIBRANIUM, -2, -2.2F, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_AXE = ITEMS.register("telemberite_axe", () -> {
        return new AxeItem(KbnsItemTier.VIBRANIUM, 2.0F, -2.9F, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_SHOVEL = ITEMS.register("telemberite_shovel", () -> {
        return new ShovelItem(KbnsItemTier.VIBRANIUM, -3.0F, -2.4F, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_HOE = ITEMS.register("telemberite_hoe", () -> {
        return new HoeItem(KbnsItemTier.VIBRANIUM, -3, -2.6F, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_HELMET = ITEMS.register("telemberite_helmet", () -> {
        return new ArmorItem(KbnsArmorMaterial.VIBRANIUM, EquipmentSlot.HEAD, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_CHESTPLATE = ITEMS.register("telemberite_chestplate", () -> {
        return new ArmorItem(KbnsArmorMaterial.VIBRANIUM, EquipmentSlot.CHEST, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_LEGGINGS = ITEMS.register("telemberite_leggings", () -> {
        return new ArmorItem(KbnsArmorMaterial.VIBRANIUM, EquipmentSlot.LEGS, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_BOOTS = ITEMS.register("telemberite_boots", () -> {
        return new ArmorItem(KbnsArmorMaterial.VIBRANIUM, EquipmentSlot.FEET, (new Item.Properties()).tab(KBNS.KBNS_TAB));
    });
    public static final RegistryObject<Item> VIBRANIUM_APPLE = ITEMS.register("telemberite_apple", VibraniumAppleFoodBase::new);
    public static final RegistryObject<Item> ENCHANTED_VIBRANIUM_APPLE = ITEMS.register("enchanted_telemberite_apple", EnchantedVibraniumApple::new);
    public static final RegistryObject<Item> HONEY_APPLE = ITEMS.register("honey_apple", HoneyApple::new);

}


