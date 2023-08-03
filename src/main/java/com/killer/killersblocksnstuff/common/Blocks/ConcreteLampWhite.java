package com.killer.killersblocksnstuff.common.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class ConcreteLampWhite extends Block {
   public ConcreteLampWhite() {
      super(Properties.of(Material.STONE)
              .strength(1.8F, 1.8F)
              .sound(SoundType.STONE)
              .lightLevel((lightLevel) -> { return 15; }).requiresCorrectToolForDrops());
   }
}
