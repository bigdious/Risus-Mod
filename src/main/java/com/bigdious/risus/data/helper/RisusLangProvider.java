package com.bigdious.risus.data.helper;

import com.bigdious.risus.Risus;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class RisusLangProvider extends LanguageProvider {

	private final PackOutput output;
	public final Map<String, String> upsideDownEntries = new HashMap<>();

	public RisusLangProvider(PackOutput output) {
		super(output, Risus.MODID, "en_us");
		this.output = output;
	}

	@Override
	public void add(String key, String value) {
		super.add(key, value);
		List<LangFormatSplitter.Component> splitEnglish = LangFormatSplitter.split(value);
		this.upsideDownEntries.put(key, LangConversionHelper.convertComponents(splitEnglish));
	}

	public void addBiome(ResourceKey<Biome> biome, String name) {
		this.add("biome.risus." + biome.location().getPath(), name);
	}

	public void addBannerPattern(String patternPrefix, String patternName, DyeColor... excludedColors) {
		this.add("item.risus." + patternPrefix + "_banner_pattern", "Banner Pattern");
		this.add("item.risus." + patternPrefix + "_banner_pattern.desc", patternName);
		List<DyeColor> excluded = Arrays.stream(excludedColors).toList();
		for (DyeColor color : DyeColor.values()) {
			if (!excluded.contains(color)) {
				this.add("block.minecraft.banner.risus." + patternPrefix + "." + color.getName(), WordUtils.capitalize(color.getName().replace('_', ' ')) + " " + patternName);
			}
		}
	}

	public void addMusicDisc(DeferredItem<Item> disc, String description) {
		this.addItem(disc, "Music Disc");
		this.add(Util.makeDescriptionId("jukebox_song", disc.get().components().get(DataComponents.JUKEBOX_PLAYABLE).song().key().location()), description);
	}

	public void addStructure(ResourceKey<Structure> biome, String name) {
		this.add("structure.risus." + biome.location().getPath(), name);
	}

	public void addAdvancement(String key, String title, String desc) {
		this.add("advancement.risus." + key, title);
		this.add("advancement.risus." + key + ".desc", desc);
	}

	public void addEntityAndEgg(DeferredHolder<EntityType<?>, ? extends EntityType<?>> entity, String name) {
		this.addEntityType(entity, name);
		this.add("item.risus." + entity.getId().getPath() + "_spawn_egg", name + " Spawn Egg");
	}

	public void addDeathMessage(String key, String name) {
		this.add("death.attack.risus." + key, name);
	}

	public void addTrim(String key, String name) {
		this.add("trim_material.risus." + key, name + " Material");
	}

	public void translateListOfStrings(String baseKey, String... entries) {
		for (int i = 0; i < entries.length; i++) {
			this.add(baseKey + i, entries[i]);
		}
	}

	public void translateTag(TagKey<?> tag, String name) {
		this.add(String.format("tag.%s.%s.%s", tag.registry().location().getPath(), tag.location().getNamespace(), tag.location().getPath().replace('/', '.')), name);
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		//generate normal lang file
		CompletableFuture<?> languageGen = super.run(cache);
		ImmutableList.Builder<CompletableFuture<?>> futuresBuilder = new ImmutableList.Builder<>();
		futuresBuilder.add(languageGen);

		//generate en_ud file
		JsonObject upsideDownFile = new JsonObject();
		this.upsideDownEntries.forEach(upsideDownFile::addProperty);
		futuresBuilder.add(DataProvider.saveStable(cache, upsideDownFile, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(Risus.MODID).resolve("lang").resolve("en_ud.json")));

		return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
	}
}
