package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class RisusStructures {
	public static final ResourceKey<Structure> GRASSY_MAW = registerKey("grassy_maw");
	public static final ResourceKey<Structure> SANDY_MAW = registerKey("sandy_maw");
	public static final ResourceKey<Structure> ENDY_MAW = registerKey("endy_maw");
	public static final ResourceKey<Structure> FAMILY_TREE = registerKey("family_tree");
	public static final ResourceKey<Structure> ALTERATION_SITE = registerKey("alteration_site");
	public static final ResourceKey<Structure> FLOWER_FIELD = registerKey("flower_field");

	public static ResourceKey<Structure> registerKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE, Risus.prefix(name));
	}
}
