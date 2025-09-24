package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.HexModel;
import com.bigdious.risus.entity.creatures.Hex;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vex;

public class HexRenderer extends MobRenderer<Vex, VexModel> {
	private static final ResourceLocation HEX_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/illager/vex.png");
	private static final ResourceLocation HEX_CHARGING_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/illager/vex_charging.png");

	public HexRenderer(EntityRendererProvider.Context context) {
		super(context, new VexModel(context.bakeLayer(RisusModelLayers.HEX)), 0.3F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	protected int getBlockLightLevel(Vex entity, BlockPos pos) {
		return 15;
	}

	public ResourceLocation getTextureLocation(Vex entity) {
		return entity.isCharging() ? HEX_CHARGING_LOCATION : HEX_LOCATION;
	}
}
