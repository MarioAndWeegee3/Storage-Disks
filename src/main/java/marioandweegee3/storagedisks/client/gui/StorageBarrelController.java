package marioandweegee3.storagedisks.client.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;

public class StorageBarrelController extends CottonCraftingController {

    public StorageBarrelController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(null, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));
        // TODO Auto-generated constructor stub
    }
    
}