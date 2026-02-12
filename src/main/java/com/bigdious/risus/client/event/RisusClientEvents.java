package com.bigdious.risus.client.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.MawGutsScreen;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.armor.*;
import com.bigdious.risus.client.model.block.BloodWyrmHeadModel;
import com.bigdious.risus.client.model.entity.*;
import com.bigdious.risus.client.model.entity.player.AngelWingsModel;
import com.bigdious.risus.client.model.entity.player.LeftHandPlayerModel;
import com.bigdious.risus.client.model.entity.player.RightHandPlayerModel;
import com.bigdious.risus.client.model.entity.player.ThreadWingsModel;
import com.bigdious.risus.client.particle.*;
import com.bigdious.risus.client.render.*;
import com.bigdious.risus.client.render.block.*;
import com.bigdious.risus.client.render.creature.*;
import com.bigdious.risus.client.render.item.LitterItemRenderer;
import com.bigdious.risus.client.render.player.AngelWingsLayer;
import com.bigdious.risus.client.render.player.HandOfGreedLayer;
import com.bigdious.risus.client.render.player.ThreadWingsLayer;
import com.bigdious.risus.compat.curios.renderers.HandCuriosRenderer;
import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.entity.RisusBoat;
import com.bigdious.risus.init.*;
import com.bigdious.risus.items.armor.*;
import com.bigdious.risus.items.weapons.ScytheItem;
import com.bigdious.risus.items.weapons.ThousandBladeItem;
import com.bigdious.risus.network.OpenBookPacket;
import com.bigdious.risus.network.ScopePacket;
import com.bigdious.risus.network.SummonGreatnessPacket;
import com.bigdious.risus.util.RisusSkullType;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import javax.annotation.Nullable;
import java.util.Objects;

public class RisusClientEvents {

	private static final RenderType MONOLITH_PORTAL = RenderType.create("risus:monolith_portal", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, true, false, RenderType.CompositeState.builder().setShaderState(RenderStateAccessor.getEndPortal()).setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(TheEndPortalRenderer.END_SKY_LOCATION, false, false).add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build()).createCompositeState(false));
	public static boolean isSpyGlassModeActive;
	private static final KeyMapping OPEN_BOOK_KEY = new KeyMapping(
		"keybind.researchers_notes_open",
		KeyConflictContext.IN_GAME,
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_R,
		"key.categories.misc");
	private static final KeyMapping SUMMON_GREATNESS = new KeyMapping(
		"keybind.summon_greatness",
		KeyConflictContext.IN_GAME,
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_G,
		"key.categories.misc");
	private static final KeyMapping SPYGLASS_MODE = new KeyMapping(
		"keybind.spyglass_mode",
		KeyConflictContext.IN_GAME,
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_Z,
		"key.categories.misc");

	public static void initEvents(IEventBus bus) {
		bus.addListener(RegisterKeyMappingsEvent.class, event -> {
			event.register(OPEN_BOOK_KEY);
			event.register(SUMMON_GREATNESS);
			event.register(SPYGLASS_MODE);
		});
		bus.addListener(RisusClientEvents::clientSetup);
		bus.addListener(RisusClientEvents::registerParticleFactories);
		bus.addListener(RisusClientEvents::registerEntityLayers);
		bus.addListener(RisusClientEvents::registerSkullModel);
		bus.addListener(RisusClientEvents::registerOverlays);
		bus.addListener(RisusClientEvents::registerEntityRenderers);
		bus.addListener(RisusClientEvents::registerScreens);
		bus.addListener(RisusClientEvents::registerBlockColors);
		bus.addListener(RisusClientEvents::registerItemColors);
		bus.addListener(RisusClientEvents::registerClientExtensions);
		bus.addListener(EntityRenderersEvent.AddLayers.class, RisusClientEvents::attachRenderLayers);
		bus.addListener(ColorHandler::registerItemColors);
		bus.addListener(RisusClientEvents::registerClientReloadListeners);

		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killScreenWithAmnesia);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::killHandWithAmnesia);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderHearts);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::remoteOpenBook);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::summonGreatness);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::clientTick);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderHandOfGreed);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::noMovementOnStool);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::spyGlassMode);
		NeoForge.EVENT_BUS.addListener(RisusClientEvents::setSpyglassMode);
//		NeoForge.EVENT_BUS.addListener(RisusClientEvents::renderExBurning);
		bus.addListener(RegisterClientExtensionsEvent.class, event -> event.registerItem(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return new LitterItemRenderer();
			}
		}, RisusItems.LITTER.get()));
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.SOURCE_BLOOD.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(RisusFluids.FLOWING_BLOOD.get(), RenderType.translucent());

		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(RisusSkullType.BLOODWYRM, Risus.prefix("textures/entity/bloodwyrm_head.png"));

			Sheets.addWoodType(RisusBlocks.BONDKNOT_TYPE);

			ItemProperties.register(RisusItems.ANGEL_WINGS.get(), Risus.prefix("broken"), (stack, level, entity, seed) -> AngelWingsItem.isFlyEnabled(stack) ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.ANGEL_WINGS.get(), Risus.prefix("ashen"), (stack, level, entity, seed) -> stack.getHoverName().getString().equalsIgnoreCase("ashen wings") ? 1.0F : 0.0F);
			ItemProperties.register(RisusItems.ANGEL_WINGS.get(), Risus.prefix("oily"), (stack, level, entity, seed) -> stack.getHoverName().getString().equalsIgnoreCase("oily wings") ? 1.0F : 0.0F);
			ItemProperties.register(RisusItems.THOUSAND_BLADE.get(), Risus.prefix("charged"), (stack, level, entity, seed) -> {
				if (entity == null || entity.getUseItem() != stack) {
					return 0.0F;
				} else {
					return (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F > 0.9F ? 1.0F : 0.0F;
				}
			});
			ItemProperties.register(RisusItems.CRESCENT_DISASTER.get(), Risus.prefix("croissant"), (stack, level, entity, seed) ->
				stack.getHoverName().getString().equalsIgnoreCase("croissant disaster") ? 1.0F : 0.0F);
			ItemProperties.register(RisusItems.CRESCENT_DISASTER.get(), Risus.prefix("charged"), (stack, level, entity, seed) -> {
				if (entity == null || entity.getUseItem() != stack) {
					return 0.0F;
				} else {
					return (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 10.0F > 0.9F ? 1.0F : 0.0F;
				}
			});
			ItemProperties.register(RisusItems.WARHORN.get(), Risus.prefix("toot"), (stack, level, entity, i) ->
				entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
			ItemProperties.register(RisusItems.WARHORN.get(), Risus.prefix("filled"), (stack, level, entity, i) ->
				stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY).potion().potion().isEmpty() ? 0 : 1);
			ItemProperties.register(RisusItems.WARHORN.get(), Risus.prefix("active"), (stack, level, entity, i) ->
				entity != null && entity.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 1 : 0);

			ItemProperties.register(RisusItems.HEXHORN.get(), Risus.prefix("toot"), (stack, level, entity, i) ->
				entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
			ItemProperties.register(RisusItems.HEXHORN.get(), Risus.prefix("filled"), (stack, level, entity, i) ->
				stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY).potion().potion().isEmpty() ? 0 : 1);
			ItemProperties.register(RisusItems.HEXHORN.get(), Risus.prefix("active"), (stack, level, entity, i) ->
				entity != null && entity.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 1 : 0);

			ItemProperties.register(RisusItems.SCYTHE.get(), Risus.prefix("noanim"), (stack, level, entity, i) -> RisusConfig.customWeaponAnims ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.SOUL_SCYTHE.get(), Risus.prefix("noanim"), (stack, level, entity, i) -> RisusConfig.customWeaponAnims ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.FIRE_SCYTHE.get(), Risus.prefix("noanim"), (stack, level, entity, i) -> RisusConfig.customWeaponAnims ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.CINDERGLEE_SCYTHE.get(), Risus.prefix("noanim"), (stack, level, entity, i) -> RisusConfig.customWeaponAnims ? 0.0F : 1.0F);

			ItemProperties.register(RisusItems.SINNER_ROBES_HELMET.get(), Risus.prefix("upgraded"), (stack, level, entity, i) -> stack.get(RisusDataComponents.ABILITY_VARIANT)==null ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.SINNER_ROBES_CHESTPLATE.get(), Risus.prefix("upgraded"), (stack, level, entity, i) -> stack.get(RisusDataComponents.ABILITY_VARIANT)==null ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.SINNER_ROBES_LEGGINGS.get(), Risus.prefix("upgraded"), (stack, level, entity, i) -> stack.get(RisusDataComponents.ABILITY_VARIANT)==null ? 0.0F : 1.0F);
			ItemProperties.register(RisusItems.SINNER_ROBES_BOOTS.get(), Risus.prefix("upgraded"), (stack, level, entity, i) -> stack.get(RisusDataComponents.ABILITY_VARIANT)==null ? 0.0F : 1.0F);
		});
	}

	private static void registerParticleFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(RisusParticles.ALTERATION.get(), AlterationParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.ALTERATION_FINISHED.get(), AlterationFinishedParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.DRIPPING_JOY.get(), RisusDripParticle.JoyHangProvider::new);
		event.registerSpriteSet(RisusParticles.FALLING_JOY.get(), RisusDripParticle.JoyFallProvider::new);
		event.registerSpriteSet(RisusParticles.LANDING_JOY.get(), RisusDripParticle.JoyLandProvider::new);
		event.registerSpriteSet(RisusParticles.DRIPPING_BLOOD.get(), RisusDripParticle.BloodDripHangProvider::new);
		event.registerSpriteSet(RisusParticles.FALLING_BLOOD.get(), RisusDripParticle.BloodDripFallProvider::new);
		event.registerSpriteSet(RisusParticles.LANDING_BLOOD.get(), RisusDripParticle.BloodDripLandProvider::new);
		event.registerSpriteSet(RisusParticles.DRIPPING_CREAM.get(), RisusDripParticle.CreamDripHangProvider::new);
		event.registerSpriteSet(RisusParticles.FALLING_CREAM.get(), RisusDripParticle.CreamDripFallProvider::new);
		event.registerSpriteSet(RisusParticles.LANDING_CREAM.get(), RisusDripParticle.CreamDripLandProvider::new);
		event.registerSpriteSet(RisusParticles.JOYFLAME.get(), FlameParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.TOOTHICAL.get(), ToothicalParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.RISUS_SOUL_PARTICLE.get(), RisusSoulParticle.EmissiveProvider::new);
		event.registerSpriteSet(RisusParticles.RISUS_SOUL_PARTICLE.get(), RisusSoulParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.BLOODSLASH_TRAIL.get(), RisusSoulParticle.EmissiveProvider::new);
		event.registerSpriteSet(RisusParticles.BLOODSLASH_TRAIL.get(), RisusSoulParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), FieryOrganicParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.DESTINED_DEATH_PARTICLE.get(), ToothicalParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.BLOCK_ORGANIC_PARTICLE.get(), BlockOrganicParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.BLOOD.get(), BloodParticle.Factory::new);
		event.registerSpriteSet(RisusParticles.BLOOD_BIT.get(), BloodBitParticle.Factory::new);
		event.registerSpriteSet(RisusParticles.RISING_SMILE.get(), RisingSmileParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.SLEEPY.get(), SleepyParticle.Provider::new);
		event.registerSpriteSet(RisusParticles.STARS.get(), SuspendedTownParticle.HappyVillagerProvider::new);
		event.registerSpecial(RisusParticles.MOB_EFFECT_ICON.get(),new MobEffectIconParticle.Provider());
	}

	private static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(RisusMenuType.MAW_GUTS.get(), MawGutsScreen::new);
	}

	private static void registerRenderTypes(RegisterNamedRenderTypesEvent event) {
		//TODO must wait on forge to allow us to add custom render types to the chunk buffer
		//event.register("monolith_portal", MONOLITH_PORTAL, RenderType.entitySolid(TheEndGatewayRenderer.END_PORTAL_LOCATION));
	}


	private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, i) -> getter != null && pos != null ? BiomeColors.getAverageGrassColor(getter, pos) : GrassColor.get(0.5D, 1.0D), RisusBlocks.MIRAGE_GRASS_BLOCK.get());
	}

	private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, i) -> GrassColor.get(0.5D, 1.0D), RisusBlocks.MIRAGE_GRASS_BLOCK.get());
	}

	private static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (RisusBoat.Type boatType : RisusBoat.Type.values()) {
			event.registerLayerDefinition(RisusBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(RisusBoatRenderer.createGutsBoatModelName(boatType), GutsBoatModel::createBodyModel);
		}
		event.registerLayerDefinition(RisusModelLayers.ANGEL, AngelModel::create);
		event.registerLayerDefinition(RisusModelLayers.LICKER, LickerModel::create);
		event.registerLayerDefinition(RisusModelLayers.BABY_SPIDER, BabySpiderModel::create);
		event.registerLayerDefinition(RisusModelLayers.HEX, HexModel::createBodyLayer);
		event.registerLayerDefinition(RisusModelLayers.HOLDER, HolderModel::create);
		event.registerLayerDefinition(RisusModelLayers.MAW, MawModel::create);
		event.registerLayerDefinition(RisusModelLayers.THROWN_AXE, ThrownAxeModel::create);
		event.registerLayerDefinition(RisusModelLayers.BLOODSLASH, BloodSlashModel::create);
		event.registerLayerDefinition(RisusModelLayers.WEAVER, WeaverModel::create);
		event.registerLayerDefinition(RisusModelLayers.WEAVER_CORE, WeaverModel::create);
		event.registerLayerDefinition(RisusModelLayers.LOVER, LoverModel::create);
		event.registerLayerDefinition(RisusModelLayers.SINGER, SingerModel::create);
		event.registerLayerDefinition(RisusModelLayers.STALKER, StalkerModel::create);
		event.registerLayerDefinition(RisusModelLayers.BLOODWYRM_HEAD, BloodWyrmHeadModel::create);
		event.registerLayerDefinition(RisusModelLayers.QUESTION_MARK, QuestionMarkModel::create);
		event.registerLayerDefinition(RisusModelLayers.MEMORY1, MemoryRenderer::createBaseLayer);
		event.registerLayerDefinition(RisusModelLayers.GREAT_STOOL, StoolModel::create);
		event.registerLayerDefinition(RisusModelLayers.DEPTH_VASE, DepthVaseRenderer::createBaseLayer);
		event.registerLayerDefinition(RisusModelLayers.WEAVING_MECHANISM, WeavingMechanismRenderer::createBaseLayer);
		event.registerLayerDefinition(RisusModelLayers.LITTER, LitterModel::create);
		event.registerLayerDefinition(RisusModelLayers.RIGHT_HAND_OF_GREED, RightHandPlayerModel::create);
		event.registerLayerDefinition(RisusModelLayers.LEFT_HAND_OF_GREED, LeftHandPlayerModel::create);
		event.registerLayerDefinition(RisusModelLayers.THREAD_WINGS, ThreadWingsModel::create);
		event.registerLayerDefinition(RisusModelLayers.ANGEL_WINGS, AngelWingsModel::create);
		event.registerLayerDefinition(RisusModelLayers.CROWN_OF_BONES_OUTER, () -> LayerDefinition.create(CrownOfBonesModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.CROWN_OF_BONES_INNER, () -> LayerDefinition.create(CrownOfBonesModel.addPieces(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32));
		//I know this is a lot, but if they aren't kept separate it doesn't work
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_HELMET_OUTER, () -> LayerDefinition.create(SinnerRobeHelmetModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_HELMET_INNER, () -> LayerDefinition.create(SinnerRobeHelmetModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_CHESTPLATE_OUTER, () -> LayerDefinition.create(SinnerRobeChestplateModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_CHESTPLATE_INNER, () -> LayerDefinition.create(SinnerRobeChestplateModel.addPieces(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_LEGGINGS_OUTER, () -> LayerDefinition.create(SinnerRobeLeggingsModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_LEGGINGS_INNER, () -> LayerDefinition.create(SinnerRobeLeggingsModel.addPieces(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_BOOTS_OUTER, () -> LayerDefinition.create(SinnerRobeBootsModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(RisusModelLayers.SINNER_ROBES_BOOTS_OUTER, () -> LayerDefinition.create(SinnerRobeBootsModel.addPieces(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32));
	}

	private static void attachRenderLayers(EntityRenderersEvent.AddLayers event) {
		//thanks Tama
		for (EntityType<?> type : event.getEntityTypes()) {
			var renderer = event.getRenderer(type);
			if (renderer instanceof LivingEntityRenderer<?, ?> living) {
				attachRenderLayers(living);
			}
		}

		event.getSkins().forEach(renderer -> {
			LivingEntityRenderer<Player, EntityModel<Player>> skin = event.getSkin(renderer);
			attachRenderLayers(Objects.requireNonNull(skin));
		});
	}

	private static <T extends LivingEntity, M extends EntityModel<T>> void attachRenderLayers(LivingEntityRenderer<T, M> renderer) {
		renderer.addLayer(new AngelWingsLayer<>(renderer));
		renderer.addLayer(new HandOfGreedLayer<>(renderer));
		renderer.addLayer(new ThreadWingsLayer<>(renderer));
	}

	private static void registerSkullModel(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(RisusSkullType.BLOODWYRM, new BloodWyrmHeadModel(event.getEntityModelSet().bakeLayer(RisusModelLayers.BLOODWYRM_HEAD)));
	}

	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RisusEntities.ANGEL.get(), AngelRenderer::new);
		event.registerEntityRenderer(RisusEntities.HEX.get(), HexRenderer::new);
		event.registerEntityRenderer(RisusEntities.HOLDER.get(), HolderRenderer::new);
		event.registerEntityRenderer(RisusEntities.MAW.get(), MawRenderer::new);
		event.registerEntityRenderer(RisusEntities.THROWN_AXE.get(), ThrownAxeRenderer::new);
		event.registerEntityRenderer(RisusEntities.BLOODSLASH.get(), BloodSlashRenderer::new);
		event.registerEntityRenderer(RisusEntities.ENDLESS_PEARL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(RisusEntities.BLOODWYRM_BREATH.get(), BloodwyrmBreathEntityRenderer::new);
		event.registerEntityRenderer(RisusEntities.WEAVER.get(), WeaverRenderer::new);
		event.registerEntityRenderer(RisusEntities.LOVER.get(), LoverRenderer::new);
		event.registerEntityRenderer(RisusEntities.SINGER.get(), SingerRenderer::new);
		event.registerEntityRenderer(RisusEntities.LICKER.get(), LickerRenderer::new);
		event.registerEntityRenderer(RisusEntities.BABY_SPIDER.get(), BabySpiderRenderer::new);
		event.registerEntityRenderer(RisusEntities.STALKER.get(), StalkerRenderer::new);
		event.registerEntityRenderer(RisusEntities.QUESTION_MARK.get(), QuestionMarkRenderer::new);
		event.registerEntityRenderer(RisusEntities.GREAT_STOOL.get(), StoolRenderer::new);
		event.registerEntityRenderer(RisusEntities.EGG_SAC.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(RisusEntities.LITTER.get(), LitterRenderer::new);

		event.registerEntityRenderer(RisusEntities.BOAT.get(), (context) -> new RisusBoatRenderer(context, false));
		event.registerEntityRenderer(RisusEntities.GUTS_BOAT.get(), (context) -> new RisusBoatRenderer(context, true));
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SKULL.get(), SkullBlockRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_HANGING_SIGN.get(), HangingSignRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RISUS_CAMPFIRE.get(), CampfireRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.ALTERATION_CATALYST.get(), AlterationCatalystRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.DEPTH_VASE.get(), DepthVaseRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.WEAVING_MECHANISM.get(), WeavingMechanismRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.DISPLAY_NOTCH.get(), DisplayNotchRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.TESSERACT.get(), TesseractRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.MEMORY.get(), MemoryRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.RITUAL.get(), RitualBlockEntityRenderer::new);
		event.registerBlockEntityRenderer(RisusBlockEntities.SPAWNER.get(), RisusSpawnerRenderer::new);
	}

	private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(ThousandBladeItem.ItemExtensions.INSTANCE, RisusItems.THOUSAND_BLADE.get());
		event.registerItem(ScytheItem.ItemExtensions.INSTANCE, RisusItems.SCYTHE.get(), RisusItems.SOUL_SCYTHE.get(), RisusItems.CINDERGLEE_SCYTHE.get(), RisusItems.FIRE_SCYTHE.get());
		event.registerItem(RisusSpecialItemRenderer.CLIENT_ITEM_EXTENSION, RisusBlocks.DEPTH_VASE.asItem());
		event.registerItem(RisusSpecialItemRenderer.CLIENT_ITEM_EXTENSION, RisusBlocks.WEAVING_MECHANISM.asItem());
		event.registerItem(
			new RisusSimpleArmorRenderer(HumanoidArmorModel::new, RisusModelLayers.CROWN_OF_BONES_INNER, RisusModelLayers.CROWN_OF_BONES_OUTER),
			RisusItems.CROWN_OF_BONES.get()
		);
		event.registerItem(
			new SinnerRobeHelmetItem.ArmorRender(),
			RisusItems.SINNER_ROBES_HELMET.get()
		);
		event.registerItem(
			new SinnerRobeChestplateItem.ArmorRender(),
			RisusItems.SINNER_ROBES_CHESTPLATE.get()
			);
		event.registerItem(
			new SinnerRobeLeggingsItem.ArmorRender(),
			RisusItems.SINNER_ROBES_LEGGINGS.get()
			);
		event.registerItem(
			new SinnerRobeBootsItem.ArmorRender(),
			RisusItems.SINNER_ROBES_BOOTS.get()
		);
		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public ResourceLocation getStillTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_flow");
			}

			@Override
			public ResourceLocation getOverlayTexture() {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "block/blood_flow");
			}

			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/misc/blood_fluid_block.png");
			}

			@Override
			public int getTintColor() {
				return 0xFFE60E07;
			}

			@Override
			public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(54f / 255f, 4f / 255f, 4f / 255f);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(0f);
				RenderSystem.setShaderFogEnd(6f);
			}
		}, RisusFluids.BLOOD_FLUID_TYPE.get());
	}

	private static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(new RisusSimpleArmorRenderer.ResourceReloadListener());
	}

	private static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerAbove(VanillaGuiLayers.CAMERA_OVERLAYS, ResourceLocation.fromNamespaceAndPath(Risus.MODID, "eye_overlay"), (guiGraphics, deltaTracker) -> {
			Minecraft minecraft = Minecraft.getInstance();
			ResourceLocation overlay = ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/eye_overlay.png");
			LocalPlayer player = minecraft.player;
			if (player != null && player.getInventory().getArmor(3).is(RisusTags.Items.EYE)) {
				minecraft.gui.renderTextureOverlay(guiGraphics, overlay, 1.0F);
			}
		});
	}

	private static void killScreenWithAmnesia(RenderGuiLayerEvent.Pre event) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA)) {
			event.setCanceled(true);
		}
	}

	private static void killHandWithAmnesia(RenderHandEvent event) {
		if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(RisusMobEffects.AMNESIA)) {
			event.setCanceled(true);
		}
	}

	private static void renderHearts(PlayerHeartTypeEvent event) {
		if (event.getEntity().hasEffect(RisusMobEffects.EXBURN)) {
			event.setType(Gui.HeartType.valueOf("RISUS_EXBURN"));
		}
		if (event.getEntity().hasEffect(RisusMobEffects.DESTINED_DEATH)) {
			event.setType(Gui.HeartType.valueOf("RISUS_DEATH"));
		}
		if (event.getEntity().hasEffect(RisusMobEffects.BLOODCLOGGED)) {
			event.setType(Gui.HeartType.valueOf("RISUS_BLOODCLOGGED"));
		}
	}

	private static void remoteOpenBook(InputEvent.Key event) {
		if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null) {
			if (event.getKey() == OPEN_BOOK_KEY.getKey().getValue() && OPEN_BOOK_KEY.consumeClick()) {
				PacketDistributor.sendToServer(OpenBookPacket.INSTANCE);
			}
		}
	}

	private static void summonGreatness(InputEvent.Key event) {
		if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null) {
			if (event.getKey() == SUMMON_GREATNESS.getKey().getValue() && SUMMON_GREATNESS.consumeClick()) {
				PacketDistributor.sendToServer(SummonGreatnessPacket.INSTANCE);
			}
		}
	}

	private static void spyGlassMode(InputEvent.Key event) {
		if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null) {
			if (event.getKey() == SPYGLASS_MODE.getKey().getValue() && SPYGLASS_MODE.consumeClick()) {
				PacketDistributor.sendToServer(ScopePacket.INSTANCE);
				isSpyGlassModeActive = !isSpyGlassModeActive;
			}
		}
	}

;

//	public static class CheckWhispers {
//		public static void getWhispers(Player player) {
//			int i = player.getRandom().nextInt(999);
//			if (I18n.exists("entity.risus.thrown_axe.message" + i)) {
//				 player.displayClientMessage(Component.translatable("entity.risus.thrown_axe.message" + i).withStyle(ChatFormatting.DARK_RED), true);
//			}
//		}
//	}

	public static class RenderStateAccessor extends RenderStateShard {

		public RenderStateAccessor(String p_110161_, Runnable p_110162_, Runnable p_110163_) {
			super(p_110161_, p_110162_, p_110163_);
		}

		public static ShaderStateShard getEndPortal() {
			return RENDERTYPE_END_PORTAL_SHADER;
		}
	}

	private static void clientTick(ClientTickEvent.Post event) {
		Minecraft mc = Minecraft.getInstance();

		if (!mc.isPaused()) {
			AnimationRenderHelper.animate();
		}
	}

	public static void noMovementOnStool(MovementInputUpdateEvent event) {
		if (event.getEntity().hasEffect(RisusMobEffects.GREATNESS)) {
			Input input = event.getInput();
			input.up = false;
			input.down = false;
			input.left = false;
			input.right = false;
			input.forwardImpulse = 0;
			input.leftImpulse = 0;
			input.jumping = false;
		}
	}

	private static boolean curiosForArm(LivingEntity entity) {
		if (ModList.get().isLoaded("curios")) {
			var handler = entity.getCapability(CuriosCapability.INVENTORY);
			if (handler == null) return false;
			var s = handler.findCurios(RisusItems.HAND_OF_GREED.get());
			if (s.isEmpty()) return false; else return true;
		}
		return false;
	}

	private static void renderHandOfGreed(RenderArmEvent event) {
		if (!event.isCanceled() && event.getArm() == HumanoidArm.RIGHT) {
			if (ModList.get().isLoaded("curios") && curiosForArm(event.getPlayer())) {
			CuriosApi.getCurio(RisusItems.HAND_OF_GREED.toStack()).flatMap(iCurio -> CuriosRendererRegistry.getRenderer(iCurio.getStack().getItem())).ifPresent(renderer -> {
				RightHandPlayerModel model = ((HandCuriosRenderer) renderer).model;
				model.rightArmPose = HumanoidModel.ArmPose.EMPTY;
				model.attackTime = 0.0F;
				model.crouching = false;
				model.swimAmount = 0.0F;
				model.setupArmSize(event.getPlayer().getSkin().model().id().equals("slim"));
				model.setupAnim(event.getPlayer(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
				model.renderToBuffer(event.getPoseStack(), event.getMultiBufferSource().getBuffer(HandCuriosRenderer.RENDER_TYPE), event.getPackedLight(), OverlayTexture.NO_OVERLAY);
				});
			}
			if (event.getPlayer().getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT) != null && event.getPlayer().getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT).equals("hand_of_greed")) {
				Minecraft mc = Minecraft.getInstance();
				LocalPlayer player = mc.player;

				RightHandPlayerModel model = new RightHandPlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.RIGHT_HAND_OF_GREED));
				model.rightArmPose = HumanoidModel.ArmPose.EMPTY;
				model.attackTime = 0.0F;
				model.crouching = false;
				model.swimAmount = 0.0F;
				model.setupArmSize(event.getPlayer().getSkin().model().id().equals("slim"));
				model.setupAnim(event.getPlayer(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
				model.renderToBuffer(event.getPoseStack(), event.getMultiBufferSource().getBuffer(HandOfGreedLayer.LEFT_RENDER_TYPE), event.getPackedLight(), OverlayTexture.NO_OVERLAY);

			}
		}
	}

	private static void setSpyglassMode (ComputeFovModifierEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getArmor(3).has(RisusDataComponents.ABILITY_VARIANT) && Objects.equals(player.getInventory().getArmor(3).get(RisusDataComponents.ABILITY_VARIANT), "spyglass") && isSpyGlassModeActive) {
			event.setNewFovModifier(0.1F);
		}
	}
}
