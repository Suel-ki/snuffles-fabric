package com.github.suel_ki.snuffles.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class FuelBlockItem extends BlockItem {
    int burnTime;

    public FuelBlockItem(Block block, int burnTime, Settings settings) {
        super(block, settings);
        this.burnTime = burnTime;
    }

    public int getBurnTime() {
        return this.burnTime;
    }
}