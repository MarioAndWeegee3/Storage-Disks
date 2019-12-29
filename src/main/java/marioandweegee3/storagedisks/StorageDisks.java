package marioandweegee3.storagedisks;

import java.util.HashMap;

import marioandweegee3.ml3api.registry.RegistryHelper;
import marioandweegee3.storagedisks.blocks.StorageBarrelBlock;
import marioandweegee3.storagedisks.blocks.entities.StorageBarrelBlockEntity;
import marioandweegee3.storagedisks.items.DiskItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class StorageDisks implements ModInitializer {
    public static final String modid = "storagedisks";
    public static final RegistryHelper helper = new RegistryHelper(modid);

    public static final BlockEntityType<StorageBarrelBlockEntity> STORAGE_BARREL = helper.registerAndCreateBlockEntity("storage_barrel", StorageBarrelBlockEntity::new, StorageBarrelBlock.BLOCK);

    public static final Item.Settings DISK = new Item.Settings().group(ItemGroup.TOOLS).maxCount(1);

    @SuppressWarnings("serial")
    @Override
    public void onInitialize() {
        helper.registerBlock("storage_barrel", StorageBarrelBlock.BLOCK, ItemGroup.DECORATIONS);

        helper.registerAllItems(new HashMap<String, Item>(){{
            put("disk3x9", new DiskItem(DISK, 3, 9));
            put("disk6x9", new DiskItem(DISK, 6, 9));
        }});
    }

}