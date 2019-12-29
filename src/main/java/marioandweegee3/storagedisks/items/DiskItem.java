package marioandweegee3.storagedisks.items;

import net.minecraft.item.Item;

public class DiskItem extends Item {
    public final int rows, columns;

    public DiskItem(Settings settings, int rows, int columns) {
        super(settings);
        this.rows = rows;
        this.columns = columns;
    }

}