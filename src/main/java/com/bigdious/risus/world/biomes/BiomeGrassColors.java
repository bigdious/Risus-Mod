package com.bigdious.risus.world.biomes;

import com.bigdious.risus.Risus;
import net.minecraft.util.Mth;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;

public class BiomeGrassColors {
	//copy from Twilight Forest
	public static void init() {
	}


	public static final BiomeSpecialEffects.GrassColorModifier COALIFICATION = make("coalification", (x, z, color) -> {
		double noise = (Biome.TEMPERATURE_NOISE.getValue(x * 0.0225D, z * 0.0225D, false) + 1D) / 2D;
		return blendColors(0xc43323, 0x5BC423, noise > 0.6D ? noise * 0.1D : noise);
	});

	public static int blendColors(int a, int b, double ratio) {
		int mask1 = 0x00FF00FF;
		int mask2 = 0xFF00FF00;

		int f2 = (int) (256 * ratio);
		int f1 = 256 - f2;

		return (((((a & mask1) * f1) + ((b & mask1) * f2)) >> 8) & mask1)
			| (((((a & mask2) * f1) + ((b & mask2) * f2)) >> 8) & mask2);
	}

	private static BiomeSpecialEffects.GrassColorModifier make(String name, BiomeSpecialEffects.GrassColorModifier.ColorModifier delegate) {
		name = Risus.prefix(name).toString();

		return BiomeSpecialEffects.GrassColorModifier.create(name, name, delegate);
	}
}

