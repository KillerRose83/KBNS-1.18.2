package com.killer.killersblocksnstuff.common.Blocks.screen;

import com.killer.killersblocksnstuff.*;
import net.minecraft.world.inventory.*;
import net.minecraftforge.common.extensions.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.network.*;
import net.minecraftforge.registries.*;

public class KbnsMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, KBNS.MOD_ID);

    public static final RegistryObject<MenuType<VibraniumForgeMenu>> VibraniumForgeMenu =
            registerMenuType(VibraniumForgeMenu::new, "vibranium_forge_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

