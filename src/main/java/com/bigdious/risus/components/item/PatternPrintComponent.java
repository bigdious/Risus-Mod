package com.bigdious.risus.components.item;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PatternPrintComponent {
	public static final PatternPrintComponent EMPTY = new PatternPrintComponent(List.of());
	final List<BlockPos> positions;
	public static final Codec<PatternPrintComponent> CODEC = BlockPos.CODEC.listOf().xmap(PatternPrintComponent::new, (instance) -> instance.positions);
	public static final StreamCodec<ByteBuf, PatternPrintComponent> STREAM_CODEC = BlockPos.STREAM_CODEC.apply(ByteBufCodecs.list()).map(PatternPrintComponent::new, (instance) -> instance.positions);

	PatternPrintComponent(List<BlockPos> positions) {
		this.positions = positions;
	}

	public int hashCode() {
		int i = 0;

		for(BlockPos pos : this.positions) {
			i = i * 31 + Objects.hash(pos);
		}
		return i;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else {
			boolean var10000;
			if (other instanceof PatternPrintComponent) {
				PatternPrintComponent ppComponent = (PatternPrintComponent)other;
				var10000 =  listMatches(this.positions, ppComponent.positions);
			} else {
				var10000 = false;
			}

			return var10000;
		}
	}

	public static boolean listMatches(List<BlockPos> list, List<BlockPos> other) {
		if (list.size() != other.size()) {
			return false;
		} else {
			for(int i = 0; i < list.size(); ++i) {
				if (list.get(i) == other.get(i)) {
					return false;
				}
			}

			return true;
		}
	}

	public Iterable<BlockPos> positions() {
		return this.positions;
	}

	public static class Mutable {
		private final List<BlockPos> positions;

		public Mutable(PatternPrintComponent contents) {
			this.positions = new ArrayList<>(contents.positions);
		}

		public PatternPrintComponent.Mutable clearPositions() {
			this.positions.clear();
			return this;
		}

		public int addPosition(BlockPos pos) {
			this.positions.add(pos);
			return 1;
		}

		public PatternPrintComponent toImmutable() {
			return new PatternPrintComponent(List.copyOf(this.positions));
		}
	}


}
