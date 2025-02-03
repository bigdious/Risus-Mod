package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class RisusDataMaps extends DataMapProvider {

	public RisusDataMaps(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}
	@Override
	protected void gather() {
		var fuels = this.builder(NeoForgeDataMaps.FURNACE_FUELS);
		fuels.add(RisusItems.ORGANIC_MATTER, new FurnaceFuel(200), false);
		fuels.add(RisusItems.ORGANIC_MATTER_BLOCK, new FurnaceFuel(2000), false);
		fuels.add(RisusItems.ASHEN_REMAINS, new FurnaceFuel(400), false);
		fuels.add(RisusItems.SMILING_REMAINS, new FurnaceFuel(400), false);
		fuels.add(RisusItems.SPREADING_REMAINS, new FurnaceFuel(300), false);
	}
}
