package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

import java.util.Optional;

public class SpriteReferenceGenerator extends SpriteSourceProvider {
    public SpriteReferenceGenerator(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, Risus.MODID);
    }

    @Override
    protected void addSources() {
        this.atlas(BLOCKS_ATLAS)
                .addSource(new SingleFile(Risus.prefix("entity/crescent_disaster"), Optional.empty()))
                .addSource(new SingleFile(Risus.prefix("entity/unawakened_vessel"), Optional.empty()));
    }
}
