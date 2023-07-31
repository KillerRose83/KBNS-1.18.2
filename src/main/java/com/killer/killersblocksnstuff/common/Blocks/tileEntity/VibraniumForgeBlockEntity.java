package com.killer.killersblocksnstuff.common.Blocks.tileEntity;


import com.killer.killersblocksnstuff.common.Blocks.*;
import com.killer.killersblocksnstuff.common.Blocks.screen.*;
import com.killer.killersblocksnstuff.recipe.*;
import com.killer.killersblocksnstuff.util.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.energy.*;
import net.minecraftforge.items.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.*;

import javax.annotation.*;
import java.util.*;

public class VibraniumForgeBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private final KbnsEnergyStorage energyHandler = new KbnsEnergyStorage(10000, 100, 0, 0) {
        @Override
        public void onEnergyChanged() {
            setChanged();
        }
    };
    private final int energyPerTick = 10;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private int progress = 0;
    private int maxProgress = 100;
    private int energy = energyHandler.getEnergyStored();
    private int maxEnergy = energyHandler.getMaxEnergyStored();

    public VibraniumForgeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(KbnsBlockEntities.VIBRANIUM_FORGE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return VibraniumForgeBlockEntity.this.progress;
                    case 1:
                        return VibraniumForgeBlockEntity.this.maxProgress;
                    case 2:
                        return VibraniumForgeBlockEntity.this.energy;
                    case 3:
                        return VibraniumForgeBlockEntity.this.maxEnergy;
                    default:
                        return 0;
                }

            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        VibraniumForgeBlockEntity.this.progress = value;
                        break;
                    case 1:
                        VibraniumForgeBlockEntity.this.maxProgress = value;
                        break;
                    case 2:
                        VibraniumForgeBlockEntity.this.energy = value;
                        break;
                    case 3:
                        VibraniumForgeBlockEntity.this.maxEnergy = value;
                        break;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, VibraniumForgeBlockEntity pBlockEntity) {
        int fePerTick = pBlockEntity.energyPerTick;
        int energy = pBlockEntity.energy;
        pBlockEntity.receiveEnergy(pBlockEntity);
        if (!pLevel.isClientSide) {
            if (hasRecipe(pBlockEntity) && energy >= fePerTick) {
                pState = pState.setValue(VibraniumForgeBlock.LIT, Boolean.TRUE);
                pLevel.setBlock(pPos, pState, 3);
                setChanged(pLevel, pPos, pState);
                pBlockEntity.progress++;
                pBlockEntity.subtractEnergy();
                setChanged(pLevel, pPos, pState);


                if (pBlockEntity.progress > pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);

                }
            } else {
                pBlockEntity.resetProgress();
                setChanged(pLevel, pPos, pState);
            }
            if (!hasRecipe(pBlockEntity) || energy < fePerTick) {
                //System.out.println("Made it though if statement with " + energy + " energy, and hasRecipe = " + hasRecipe(pBlockEntity) + ". The blockstate = "+pBlockEntity.getBlockState());
                pState = pState.setValue(VibraniumForgeBlock.LIT, Boolean.FALSE);
                pLevel.setBlock(pPos, pState, 3);
                setChanged(pLevel, pPos, pState);
            }
        }

    }

    private static boolean hasRecipe(VibraniumForgeBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<VibraniumForgeRecipe> match = level.getRecipeManager()
                .getRecipeFor(VibraniumForgeRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(VibraniumForgeBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<VibraniumForgeRecipe> match = level.getRecipeManager()
                .getRecipeFor(VibraniumForgeRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent()) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);

            entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.kbns.vibranium_forge_block_entity");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> energyHandler);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new VibraniumForgeMenu(id, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("vibranium_forge.progress", progress);
        tag.putInt("energy", energy);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("vibranium_forge.progress");
        energy = nbt.getInt("energy");
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void receiveEnergy(BlockEntity entity) {
        if (VibraniumForgeBlockEntity.this.energyHandler.canReceive()) {
            for (final var direction : Direction.values()) {
                final BlockEntity neighbor = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (neighbor == null) {
                    continue;
                }

                    int dif = maxEnergy - energy;
                    neighbor.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                        final int canReceive = Math.min(storage.getEnergyStored(), Math.min(energyHandler.maxReceive(), dif));
                        if (neighbor != this && storage.canExtract() && maxEnergy > canReceive + energy) {
                            final int extractedEnergy = storage.extractEnergy(canReceive, false);
                            neighbor.setChanged();
                            energy = energy + canReceive;
                        } else if (energy < maxEnergy + dif){
                            storage.extractEnergy(dif,false);
                            energy = energy + dif;
                        }
                    });
            }
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void subtractEnergy() {
        this.energy = energy - energyPerTick;
    }

    private void light(BlockState pState, BlockPos pPos, Level pLevel) {

        pState = pState.setValue(AbstractFurnaceBlock.LIT, Boolean.TRUE);
        pLevel.setBlock(pPos, pState, 3);
        setChanged();
    }

    private void dark(BlockState pState, BlockPos pPos, Level pLevel) {

        pState = pState.setValue(AbstractFurnaceBlock.LIT, Boolean.FALSE);
        pLevel.setBlock(pPos, pState, 3);
        setChanged();
    }
}




