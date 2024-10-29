package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class RisusBannerPatterns {
	public static final ResourceKey<BannerPattern> SMILE = register("smile");


	private static ResourceKey<BannerPattern> register(String name) {
		return ResourceKey.create(Registries.BANNER_PATTERN, Risus.prefix(name));
	}

	public static void bootstrap(BootstrapContext<BannerPattern> context) {
		context.register(SMILE, new BannerPattern(Risus.prefix("smile"), "block.minecraft.banner.risus.smile"));
		}
}
