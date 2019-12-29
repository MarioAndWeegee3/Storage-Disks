package marioandweegee3.storagedisks.blocks.entities;

import marioandweegee3.storagedisks.StorageDisks;
import marioandweegee3.storagedisks.items.DiskItem;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class StorageBarrelBlockEntity extends BlockEntity implements Inventory {
    private DefaultedList<ItemStack> disk;
    private DefaultedList<ItemStack> diskInv;

    public StorageBarrelBlockEntity() {
        super(StorageDisks.STORAGE_BARREL);
        disk = DefaultedList.ofSize(1, ItemStack.EMPTY);
        diskInv = null;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag = super.toTag(tag);
        if(diskInv != null){
            tag.put("Disk Inventory", Inventories.toTag(new CompoundTag(), diskInv));
        }
        tag.put("Disk", Inventories.toTag(new CompoundTag(), disk));
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag.getCompound("Disk"), disk);
        if(tag.contains("Disk Inventory") && disk.get(0).getItem() instanceof DiskItem){
            diskInv = DefaultedList.ofSize(((DiskItem)disk.get(0).getItem()).columns * ((DiskItem)disk.get(0).getItem()).rows, ItemStack.EMPTY);
            Inventories.fromTag(tag.getCompound("Disk Inventory"), diskInv);
        }
    }

    @Override
    public void clear() {
        disk.clear();
        diskInv.clear();
    }

    public void setDiskInvNull(){
        diskInv = null;
    }

    public boolean isDiskInvNull(){
        return diskInv == null;
    }

    @Override
    public int getInvSize() {
        int size = 1;
        if(diskInv != null){
            size += diskInv.size();
        }
        return size;
    }

    @Override
    public boolean isInvEmpty() {
        if(!disk.get(0).isEmpty()){
            return false;
        } else {
            if(diskInv == null){
                return true;
            } else if(diskInv.size() == 0){
                return true;
            } else {
                for(ItemStack stack : diskInv){
                    if(!stack.isEmpty()){
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public ItemStack getInvStack(int slot) {
        if(slot == 0){
            return disk.get(0);
        } else {
            return diskInv.get(slot - 1);
        }
    }

    @Override
    public ItemStack takeInvStack(int slot, int amount) {
        if(slot == 0){
            return Inventories.splitStack(disk, slot, amount);
        } else {
            return Inventories.splitStack(diskInv, slot - 1, amount);
        }
    }

    @Override
    public ItemStack removeInvStack(int slot) {
        if(slot == 0){
            return Inventories.removeStack(disk, slot);
        } else {
            return Inventories.removeStack(diskInv, slot - 1);
        }
    }

    @Override
    public void setInvStack(int slot, ItemStack stack) {
        if(slot == 0){
            disk.set(slot, stack);
        } else {
            diskInv.set(slot-1, stack);
        }
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        if(this.diskInv == null){
            return false;
        } else {
            return player.squaredDistanceTo(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

}