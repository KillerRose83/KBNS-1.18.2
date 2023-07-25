package com.killer.killersblocksnstuff.common.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class VibraniumOre extends OreBlock {
   public VibraniumOre() {
      super(Properties.of(Material.STONE).strength(10.0F, 100.0F).sound(SoundType.STONE));
   }
}
