package com.bigdious.risus.tileentity;

import javax.annotation.Nullable;

import com.bigdious.risus.block.RisusBlocks;
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
       super(RisusTileEntities.BLOODWYRM.get());
    }

    @Override
	public void tick() {
       BlockState blockstate = this.getBlockState();
       if (blockstate.matchesBlock(RisusBlocks.BLOODWYRM_HEAD.get()) || blockstate.matchesBlock(RisusBlocks.BLOODWYRM_WALL_HEAD.get())) {
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
    @Override
	@Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
       return new SUpdateTileEntityPacket(this.pos, 4, this.getUpdateTag());
    }
 }