package com.bigdious.risus.items;

import com.bigdious.risus.entity.Litter;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class LitterNanoInjectorItem extends Item {


	public LitterNanoInjectorItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
		BlockState blockState = level.getBlockState(context.getClickedPos());
		if(blockState.is(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS) && context.getPlayer() != null) {
			level.setBlock(context.getClickedPos(), Blocks.AIR.defaultBlockState(), 3);
			for (int i = 0; i < 10; i++) {
				double x = context.getClickedPos().getX();
				double y = context.getClickedPos().getY();
				double z = context.getClickedPos().getZ();

				int edge = level.random.nextInt(12);
				double offsetX = 0, offsetY = 0, offsetZ = 0;

				switch (edge) {
					case 0:  offsetX = Math.random(); offsetY = 0; offsetZ = 0; break;  // Bottom front edge
					case 1:  offsetX = Math.random(); offsetY = 0; offsetZ = 1; break;  // Bottom back edge
					case 2:  offsetX = Math.random(); offsetY = 1; offsetZ = 0; break;  // Top front edge
					case 3:  offsetX = Math.random(); offsetY = 1; offsetZ = 1; break;  // Top back edge
					case 4:  offsetX = 0; offsetY = Math.random(); offsetZ = 0; break;  // Left front vertical
					case 5:  offsetX = 1; offsetY = Math.random(); offsetZ = 0; break;  // Right front vertical
					case 6:  offsetX = 0; offsetY = Math.random(); offsetZ = 1; break;  // Left back vertical
					case 7:  offsetX = 1; offsetY = Math.random(); offsetZ = 1; break;  // Right back vertical
					case 8:  offsetX = 0; offsetY = 0; offsetZ = Math.random(); break;  // Bottom left horizontal
					case 9:  offsetX = 1; offsetY = 0; offsetZ = Math.random(); break;  // Bottom right horizontal
					case 10: offsetX = 0; offsetY = 1; offsetZ = Math.random(); break;  // Top left horizontal
					case 11: offsetX = 1; offsetY = 1; offsetZ = Math.random(); break;  // Top right horizontal
				}

				level.addParticle(ParticleTypes.END_ROD,
					x + offsetX,
					y + offsetY,
					z + offsetZ,
					0.0D,
					-0.05D,
					0.0D);
			}
			// play villager transformation sound
			level.playSound(null, context.getClickedPos(), SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.PLAYERS);

			Litter summonedLitter = new Litter(RisusEntities.LITTER.get(), level, context.getPlayer().getUUID());
			summonedLitter.setBlockState(blockState);
			summonedLitter.moveTo(context.getClickedPos(), 0.0F, 0.0F);
			level.addFreshEntity(summonedLitter);
			return InteractionResult.SUCCESS;
		}
		else {
			// send actionbar message with "this doesn't seem to do anything.." in red
			if (level.isClientSide && context.getPlayer() != null) {
				MutableComponent message = Component.literal("This doesn't seem to do anything..")
					.withStyle(style -> style.withColor(TextColor.fromRgb(0xAA0000)));
				context.getPlayer().displayClientMessage(message, true);
			}
			return InteractionResult.FAIL;
		}
	}

}
