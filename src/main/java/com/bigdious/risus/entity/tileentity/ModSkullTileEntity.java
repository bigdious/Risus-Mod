package com.bigdious.risus.entity.tileentity;

import javax.annotation.Nullable;

import com.bigdious.risus.init.ModBlocks;
import com.bigdious.risus.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModSkullTileEntity extends TileEntity implements ITickableTileEntity {
    
    private int dragonAnimatedTicks;
    private boolean dragonAnimated;

    public ModSkullTileEntity() {
       super(ModTileEntities.BLOODWYRM);
    }

    public void tick() {
       BlockState blockstate = this.getBlockState();
       if (blockstate.isIn(ModBlocks.BLOODWYRM_HEAD) || blockstate.isIn(ModBlocks.BLOODWYRM_WALL_HEAD)) {
          if (this.world.isBlockPowered(this.pos)) {
             this.dragonAnimated = true;
             ++this.dragonAnimatedTicks;
          } else {
             this.dragonAnimated = false;
          }
       }

    }

    @OnlyIn(Dist.CLIENT)
    public float getAnimationProgress(float p_184295_1_) {
       return this.dragonAnimated ? (float)this.dragonAnimatedTicks + p_184295_1_ : (float)this.dragonAnimatedTicks;
    }
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
       return new SUpdateTileEntityPacket(this.pos, 4, this.getUpdateTag());
    }
 }