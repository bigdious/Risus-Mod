package com.bigdious.risus.data;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataMaps;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.util.LoverConversion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {

	public DataMapGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}
	@Override
	protected void gather() {
		var fuels = this.builder(NeoForgeDataMaps.FURNACE_FUELS);
		fuels.add(RisusItems.ORGANIC_MATTER, new FurnaceFuel(200), false);
		fuels.add(RisusBlocks.ORGANIC_MATTER_BLOCK.asItem().builtInRegistryHolder(), new FurnaceFuel(2000), false);
		fuels.add(RisusBlocks.ASHEN_REMAINS.asItem().builtInRegistryHolder(), new FurnaceFuel(400), false);
		fuels.add(RisusBlocks.SMILING_REMAINS.asItem().builtInRegistryHolder(), new FurnaceFuel(400), false);
		fuels.add(RisusBlocks.SPREADING_REMAINS.asItem().builtInRegistryHolder(), new FurnaceFuel(300), false);

		var conversions = this.builder(RisusDataMaps.LOVER_CONVERSION);
		conversions.add(EntityType.CREEPER.builtInRegistryHolder(), new LoverConversion(RisusEntities.STALKER.get()), false);
		conversions.add(EntityType.ENDERMAN.builtInRegistryHolder(), new LoverConversion(RisusEntities.SINGER.get()), false);
		conversions.add(EntityType.SPIDER.builtInRegistryHolder(), new LoverConversion(RisusEntities.LICKER.get()), false);
	}
}
