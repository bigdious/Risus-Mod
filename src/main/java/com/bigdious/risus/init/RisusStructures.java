//package com.bigdious.risus.init;
//
//import com.bigdious.risus.Risus;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.structure.Structure;
//import net.minecraft.world.level.levelgen.structure.StructureType;
//import net.neoforged.neoforge.registries.DeferredRegister;
//
//public class RisusStructures {
//	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Risus.MODID);
//	public static final ResourceKey<Structure> GRASSY_MAW = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath);
//	public static final ResourceKey<Structure> SANDY_MAW = registerKey("sandy_maw");
//	public static final ResourceKey<Structure> ENDY_MAW = registerKey("endy_maw");
//	public static final ResourceKey<Structure> FAMILY_TREE = registerKey("family_tree");
//	public static final ResourceKey<Structure> ALTERATION_SITE = registerKey("alteration_site");
//	public static final ResourceKey<Structure> FLOWER_FIELD = registerKey("flower_field");
//
//	public static ResourceKey<Structure> registerKey(String name) {
//		return ResourceKey.create(Registries.STRUCTURE, Risus.prefix(name));
//	}
//}
