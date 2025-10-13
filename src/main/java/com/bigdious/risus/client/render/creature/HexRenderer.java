package com.bigdious.risus.client.render.creature;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.HexModel;
import com.bigdious.risus.entity.creatures.Hex;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vex;

public class HexRenderer extends MobRenderer<Hex, HexModel> {
	private static final ResourceLocation HEX_LOCATION = Risus.prefix("textures/entity/hex.png");
	private static final ResourceLocation HEX_CHARGING_LOCATION = Risus.prefix("textures/entity/hex_charging.png");

	public HexRenderer(EntityRendererProvider.Context context) {
		super(context, new HexModel(context.bakeLayer(RisusModelLayers.HEX)), 0.0F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	protected int getBlockLightLevel(Hex entity, BlockPos pos) {
		return 15;
	}

	public ResourceLocation getTextureLocation(Hex entity) {
		return entity.isCharging() ? HEX_CHARGING_LOCATION : HEX_LOCATION;
	}
}
