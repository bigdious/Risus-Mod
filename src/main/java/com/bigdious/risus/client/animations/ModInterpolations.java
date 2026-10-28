package com.bigdious.risus.client.animations;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class ModInterpolations {
	//stolen from Gizmo's treasure vault

	public static final AnimationChannel.Interpolation LINEAR = AnimationChannel.Interpolations.LINEAR;
	public static final AnimationChannel.Interpolation SPLINE = AnimationChannel.Interpolations.CATMULLROM;
	public static final InterpolationCreator STEP = (steps) -> easing(step(steps));
	public static final AnimationChannel.Interpolation EASE_IN_QUADRATIC = easing(easeIn(ModInterpolations::quadratic));
	public static final AnimationChannel.Interpolation EASE_OUT_QUADRATIC = easing(easeOut(ModInterpolations::quadratic));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_QUADRATIC = easing(easeInOut(ModInterpolations::quadratic));
	public static final AnimationChannel.Interpolation EASE_IN_CUBIC = easing(easeIn(ModInterpolations::cubic));
	public static final AnimationChannel.Interpolation EASE_OUT_CUBIC = easing(easeOut(ModInterpolations::cubic));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_CUBIC = easing(easeInOut(ModInterpolations::cubic));
	public static final AnimationChannel.Interpolation EASE_IN_QUARTIC = easing(easeIn(pow(4.0F)));
	public static final AnimationChannel.Interpolation EASE_OUT_QUARTIC = easing(easeOut(pow(4.0F)));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_QUARTIC = easing(easeInOut(pow(4.0F)));
	public static final AnimationChannel.Interpolation EASE_IN_QUINTIC = easing(easeIn(pow(5.0F)));
	public static final AnimationChannel.Interpolation EASE_OUT_QUINTIC = easing(easeOut(pow(5.0F)));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_QUINTIC = easing(easeInOut(pow(5.0F)));
	public static final AnimationChannel.Interpolation EASE_IN_EXPO = easing(easeIn(ModInterpolations::exp));
	public static final AnimationChannel.Interpolation EASE_OUT_EXPO = easing(easeOut(ModInterpolations::exp));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_EXPO = easing(easeInOut(ModInterpolations::exp));
	public static final AnimationChannel.Interpolation EASE_IN_CIRCLE = easing(easeIn(ModInterpolations::circle));
	public static final AnimationChannel.Interpolation EASE_OUT_CIRCLE = easing(easeOut(ModInterpolations::circle));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_CIRCLE = easing(easeInOut(ModInterpolations::circle));
	public static final InterpolationCreator EASE_IN_BACK = overshoot -> easing(back(overshoot));
	public static final InterpolationCreator EASE_OUT_BACK = overshoot -> easing(back(overshoot));
	public static final InterpolationCreator EASE_IN_OUT_BACK = overshoot -> easing(back(overshoot));
	public static final InterpolationCreator EASE_IN_BOUNCE = bounciness -> easing(bounce(bounciness));
	public static final InterpolationCreator EASE_OUT_BOUNCE = bounciness -> easing(bounce(bounciness));
	public static final InterpolationCreator EASE_IN_OUT_BOUNCE = bounciness -> easing(bounce(bounciness));
	public static final AnimationChannel.Interpolation EASE_IN_SINE = easing(easeIn(ModInterpolations::sine));
	public static final AnimationChannel.Interpolation EASE_OUT_SINE = easing(easeOut(ModInterpolations::sine));
	public static final AnimationChannel.Interpolation EASE_IN_OUT_SINE = easing(easeInOut(ModInterpolations::sine));
	public static final InterpolationCreator EASE_IN_ELASTIC = bounciness -> easing(elastic(bounciness));
	public static final InterpolationCreator EASE_OUT_ELASTIC = bounciness -> easing(elastic(bounciness));
	public static final InterpolationCreator EASE_IN_OUT_ELASTIC = bounciness -> easing(elastic(bounciness));

	private static AnimationChannel.Interpolation easing(Float2FloatFunction easing) {
		return (output, delta, keyframes, currentFrame, targetFrame, strength) -> {
			Vector3f vector3f = keyframes[currentFrame].target();
			Vector3f vector3f2 = keyframes[targetFrame].target();
			float eased = delta <= 0.0F ? 0.0F : (delta >= 1.0F ? 1.0F : easing.apply(delta));
			output.set(Mth.lerp(eased, vector3f.x(), vector3f2.x()), Mth.lerp(eased, vector3f.y(), vector3f2.y()), Mth.lerp(eased, vector3f.z(), vector3f2.z()));
			return output;
		};
	}

	private static Float2FloatFunction easeIn(Float2FloatFunction easing) {
		return easing;
	}

	private static Float2FloatFunction easeOut(Float2FloatFunction easing) {
		return (delta) -> 1.0F - easing.apply(1.0F - delta);
	}

	private static Float2FloatFunction easeInOut(Float2FloatFunction easing) {
		return (delta) -> (double)delta < 0.5 ? easing.apply(delta * 2.0F) * 0.5F : 1.0F - easing.apply((1.0F - delta) * 2.0F) * 0.5F;
	}

	private static float quadratic(float delta) {
		return delta * delta;
	}

	private static float cubic(float delta) {
		return delta * delta * delta;
	}

	private static float sine(float delta) {
		return 1.0F - (float)Math.cos(delta * 1.5707964F);
	}

	private static float circle(float delta) {
		return 1.0F - (float)Math.sqrt(1.0F - delta * delta);
	}

	private static float exp(float delta) {
		return (float)Math.pow(2.0, 10.0F * (delta - 1.0F));
	}

	private static Float2FloatFunction elastic(float bounciness) {
		return (delta) -> (float)(1.0 - Math.pow(Math.cos(delta * 1.5707964F), 3.0) * Math.cos((delta * bounciness) * Math.PI));
	}

	private static Float2FloatFunction bounce(float bounciness) {
		return (delta) -> (float)Math.min(Math.min(7.5625F * delta * delta, (30.25F * bounciness) * Math.pow(delta - 0.54545456F, 2.0) + 1.0 - bounciness), Math.min((121.0F * bounciness * bounciness) * Math.pow(delta - 0.8181818F, 2.0) + 1.0 - (bounciness * bounciness), (484.0F * bounciness * bounciness * bounciness) * Math.pow(delta - 0.95454544F, 2.0) + 1.0 - (bounciness * bounciness * bounciness)));
	}

	private static Float2FloatFunction back(float overshoot) {
		float n = overshoot * 1.70158F;
		return (delta) -> delta * delta * ((n + 1.0F) * delta - n);
	}

	private static Float2FloatFunction pow(float exponent) {
		return (delta) -> (float)Math.pow(delta, exponent);
	}

	private static Float2FloatFunction step(float steps) {
		float s = Math.max(1.0F, steps);
		return (delta) -> (float)(Math.floor(delta * s) / s);
	}

	interface InterpolationCreator {
		AnimationChannel.Interpolation configure(float var1);
	}
}
