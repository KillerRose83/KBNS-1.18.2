package com.killer.killersblocksnstuff.common.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import net.minecraftforge.common.*;

public class RefinedVibraniumPowderBlock extends FallingBlock {
   public RefinedVibraniumPowderBlock() {
      super(Properties.of(Material.METAL).strength(4.0F, -1.0F).sound(SoundType.SAND).requiresCorrectToolForDrops());
   }
}
