package com.bigdious.risus.client.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class GreatPreacherAnimations {
	public static final AnimationDefinition wing_flap_idle = AnimationDefinition.Builder.withLength(3.5F).looping()
		.addAnimation("LeftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-13.0843F, 38.3386F, -16.1913F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.addAnimation("LeftWingMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-31.5521F, 50.1557F, -39.9067F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.addAnimation("LeftWingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-12.7049F, 33.5103F, -20.0256F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.addAnimation("RightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-13.0843F, -38.3386F, 16.1913F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.addAnimation("RightWingMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-31.5521F, -50.1557F, 39.9067F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.addAnimation("RightWingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-12.7049F, -33.5103F, 20.0256F), ModInterpolations.EASE_IN_OUT_QUADRATIC),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), ModInterpolations.EASE_IN_OUT_QUADRATIC)
		))
		.build();
}
