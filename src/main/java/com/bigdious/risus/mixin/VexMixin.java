package com.bigdious.risus.mixin;

import com.bigdious.risus.entity.creatures.Hex;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.monster.Vex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Vex.class)
public abstract class VexMixin {
	@WrapWithCondition(method = "tick",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/entity/monster/Vex;noPhysics:Z",
			ordinal = 0
		)
	)
	private boolean dontSetForOurChild(Vex instance, boolean value) {
		return !(instance instanceof Hex);
	}
}
