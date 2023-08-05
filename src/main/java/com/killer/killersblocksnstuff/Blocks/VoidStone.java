package com.killer.killersblocksnstuff.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;

import javax.tools.*;

public class VoidStone extends Block {
   public VoidStone() {
      super(BlockBehaviour.Properties.of(Material.GLASS).strength(0.2F, 3.0F).sound(SoundType.GLASS).lightLevel((lightLevel) -> 15));
   }
}
