package com.killer.killersblocksnstuff.Blocks.tileEntity;


import com.killer.killersblocksnstuff.Blocks.screen.*;
import com.killer.killersblocksnstuff.util.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.energy.*;
import net.minecraftforge.items.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.*;

import javax.annotation.*;

public class SolarGeneratorBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private final KbnsEnergyStorage energyHandler = new KbnsEnergyStorage(110, 0, 100, 0) {
        @Override
        public void onEnergyChanged() {
            setChanged();
        }
    };
    int isGenerating = 0;
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final int energyGenPerTick = 10;
    private final double solarEfficiency = 0.80;
    private final int maxTransfer = 100;
    private long nightTime = 0;

    private int energy = energyHandler.getEnergyStored();
    private int maxEnergy = energyHandler.getMaxEnergyStored();

    public SolarGeneratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(KbnsBlockEntities.SOLAR_GENERATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return SolarGeneratorBlockEntity.this.energy;
                    case 1:
                        return SolarGeneratorBlockEntity.this.maxEnergy;
                    case 2:
                        return SolarGeneratorBlockEntity.this.isGenerating;
                    default:
                        return 0;
                }

            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        SolarGeneratorBlockEntity.this.energy = value;
                        break;
                    case 1:
                        SolarGeneratorBlockEntity.this.maxEnergy = value;
                        break;
                    case 2:
                        SolarGeneratorBlockEntity.this.isGenerating = value;
                        break;
                }
            }

            public int getCount() {
                return 3;
            }
        };
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SolarGeneratorBlockEntity pBlockEntity) {
        if (!pLevel.isClientSide) {
            if (canGenerate(pBlockEntity, pPos)) {
                pBlockEntity.generateEnergy(pLevel);
                pBlockEntity.setIsGenerating();
                pBlockEntity.sendEnergy(pBlockEntity);
            } else if (!canGenerate(pBlockEntity, pPos)) {
                pBlockEntity.setIsntGenerating();
            }
        }
    }

    public void sendEnergy(BlockEntity entity) {
        if (energy > 0 && SolarGeneratorBlockEntity.this.energyHandler.canExtract()) {
            final var direction = Direction.DOWN;
            final BlockEntity neighbor = SolarGeneratorBlockEntity.this.level.getBlockEntity(this.getBlockPos().below());

            if (neighbor != null) {
                System.out.println("neighbor pos = " + neighbor.getBlockPos().toString());
                System.out.println("has energy Cap? = " + neighbor.getCapability(CapabilityEnergy.ENERGY));
                neighbor.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if (neighbor != this && storage.canReceive() && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
                        final int canReceive = Math.min(storage.getMaxEnergyStored() - storage.getEnergyStored(), maxTransfer);
                        System.out.println("canReceive = " + canReceive);
                        final int toSend = SolarGeneratorBlockEntity.this.energyHandler.extractEnergy(canReceive,false);
                        System.out.println("toSend = " + toSend);
                        final int received = storage.receiveEnergy(toSend, false);
                        neighbor.setChanged();

                        System.out.println("received = " + received);
                        SolarGeneratorBlockEntity.this.energyHandler.setEnergy(SolarGeneratorBlockEntity.this.energy + toSend - received);
                        System.out.println("Final Send = " + (energy + toSend - received));
                    }
                });
            }
        }
    }

    private static boolean canGenerate(SolarGeneratorBlockEntity entity, BlockPos blockPos) {
        Level level = entity.level;
        assert level != null;
        boolean canSeeSky = level.canSeeSky(blockPos);
        if (!level.isClientSide && canSeeSky) {
            if (level.getDayTime() > 0 && level.getDayTime() < 12000) {
                return true;

            }
        }
        return false;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.kbns.solar_generator_block_entity");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> energyHandler);
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new SolarGeneratorMenu(id, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("energy", energy);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        energy = nbt.getInt("energy");
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    private void generateEnergy(Level level) {

        long time = level.getDayTime();


        if (!(this.energy >= maxEnergy)) {
            if (time > 0 && time <= 4500) {

                //double idk = energyGenPerTick * solarEfficiency + ((double) time / 100);
                this.energy = (int) (energy + 10);


            } else if (time > 4500 && time <= 7500) {
               // double idk = energyGenPerTick * solarEfficiency + ((double) time / 80);
                this.energy = (int) (energy + 10);


            } else if (time > 7500 && time < 12000) {
               // double idk = energyGenPerTick * solarEfficiency + ((double) time / 100);
                this.energy = (int) (energy + 10);
            }
        }
    }

    private void setIsGenerating() {
        this.isGenerating = 1;
    }

    private void setIsntGenerating() {
        this.isGenerating = 0;
    }
}





