package com.killer.killersblocksnstuff.Blocks;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

import java.util.*;

public class VibraniumBlock extends Block {
   public VibraniumBlock() {
      super(Properties.of(Material.METAL).strength(7.0F, -1.0F).sound(SoundType.STONE).requiresCorrectToolForDrops());
   }
}
