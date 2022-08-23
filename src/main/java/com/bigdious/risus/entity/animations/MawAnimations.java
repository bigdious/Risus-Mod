package com.bigdious.risus.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class MawAnimations {
	public static final AnimationDefinition MAW_BITE = AnimationDefinition.Builder.withLength(1.04f)
			.addAnimation("bigPincer1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(0f, 0f, -25f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(0f, 0f, 27f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(-27f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(0f, 0f, 25f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(0f, 0f, -27f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(-25f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(27f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(-25f, 14f, 26f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(20f, 1f, -19f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer6", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(-25f, -12.5f, -25f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(20f, 0f, 20f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer7", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(25f, 15f, -25f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(-20f, 1f, 20f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bigPincer8", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.44f, KeyframeAnimations.degreeVec(25f, -11.5f, 25f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.68f, KeyframeAnimations.degreeVec(-20f, -1.5f, -20f), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))).build();
}
