package com.killer.killersblocksnstuff.common.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import net.minecraftforge.common.*;

public class RefinedVibraniumPowderBlock extends Block {
   public RefinedVibraniumPowderBlock() {
      super(Properties.of(Material.METAL).strength(4.0F, -1.0F).sound(SoundType.NETHER_BRICKS));
   }
}
