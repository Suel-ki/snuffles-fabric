package io.github.suel_ki.snuffles.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class FuelBlockItem extends BlockItem {
    int burnTime;

    public FuelBlockItem(Block block, int burnTime, Properties properties) {
        super(block, properties);
        this.burnTime = burnTime;
    }

    public int getBurnTime() {
        return this.burnTime;
    }
}