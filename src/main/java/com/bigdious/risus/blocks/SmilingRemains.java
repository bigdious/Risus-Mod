package com.bigdious.risus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SmilingRemains extends Block {
    public SmilingRemains() {
        super(Block.Properties.create(Material.SAND)
            .hardnessAndResistance( 5.0f, 6.0f)
                .sound(SoundType.NETHER_WART)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL));
    }
}
