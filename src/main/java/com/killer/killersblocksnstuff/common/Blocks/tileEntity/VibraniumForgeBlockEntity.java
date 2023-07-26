package com.killer.killersblocksnstuff.common.Blocks.tileEntity;


import com.killer.killersblocksnstuff.common.Blocks.screen.*;
import com.killer.killersblocksnstuff.core.init.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.items.*;
import org.jetbrains.annotations.*;
import org.jetbrains.annotations.Nullable;

import javax.annotation.*;
import java.util.*;

public class VibraniumForgeBlockEntity extends BlockEntity implements MenuProvider {

    private final IItemHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public VibraniumForgeBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(KbnsBlockEntities.VIBRANIUM_FORGE_BLOCK_ENTITY.get() ,p_155229_, p_155230_);
    }
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.kbns.vibranium_forge_block_entity");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new VibraniumForgeMenu(id, inventory, this);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, VibraniumForgeBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
    }
    private static void craftItem(VibraniumForgeBlockEntity entity) {
        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.extractItem(1, 1, false);

        entity.itemHandler.insertItem(2, new ItemStack(ItemInit.VIBRANIUM_INGOT.get(),
                entity.itemHandler.getStackInSlot(2).getCount() + 1), false);
    }

    private static boolean hasRecipe(VibraniumForgeBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(0).getItem() == ItemInit.VIBRANIUM_INGOT.get();
        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(1).getItem() == ItemInit.VIBRANIUM_INGOT.get();

        return hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(VibraniumForgeBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getCount() < entity.itemHandler.getStackInSlot(2).getMaxStackSize();
    }
}




