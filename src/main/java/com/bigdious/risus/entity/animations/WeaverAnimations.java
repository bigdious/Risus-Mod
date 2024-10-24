package com.bigdious.risus.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class WeaverAnimations {
// testing animations

	//	public static final AnimationDefinition WEAVER_CORE_ROTATION = AnimationDefinition.Builder.withLength(4.12f)
//			.addAnimation("memoryShell_r1",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(2.08f, KeyframeAnimations.degreeVec(180f, 180f, 180f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(4.12f, KeyframeAnimations.degreeVec(360f, 360f, 360f),
//									AnimationChannel.Interpolations.LINEAR))).build();
//	public static final AnimationDefinition WEAVER_WALKING = AnimationDefinition.Builder.withLength(0.48f)
//			.addAnimation("rightLeg1",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.28f, KeyframeAnimations.degreeVec(15f, -10f, -7.5f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.32f, KeyframeAnimations.degreeVec(-1.17f, -24.97f, 2.76f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("rightLeg2",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.04f, KeyframeAnimations.degreeVec(27.29f, -17.13f, -19.15f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.08f, KeyframeAnimations.degreeVec(-1.63f, -26.32f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("rightLeg3",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.28f, KeyframeAnimations.degreeVec(32.9f, -7.65f, -20.42f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.32f, KeyframeAnimations.degreeVec(0f, -20f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("leftLeg1",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.04f, KeyframeAnimations.degreeVec(-15f, 10f, -7.5f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.08f, KeyframeAnimations.degreeVec(1.17f, 24.97f, 2.76f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("leftLeg2",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.28f, KeyframeAnimations.degreeVec(-27.29f, 17.13f, -19.15f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.32f, KeyframeAnimations.degreeVec(1.63f, 26.32f, 14.51f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("leftLeg3",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0.04f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.08f, KeyframeAnimations.degreeVec(-32.9f, 7.65f, -20.42f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.12f, KeyframeAnimations.degreeVec(0f, 20f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR))).build();
//	public static final AnimationDefinition WEAVER_MOUTH_IDLE = AnimationDefinition.Builder.withLength(3.64f)
//			.addAnimation("top",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(1.8f, KeyframeAnimations.degreeVec(0f, 0f, -4f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(3.64f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("bottom",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(1.8f, KeyframeAnimations.degreeVec(0f, 0f, 4f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(3.64f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR))).build();
//	public static final AnimationDefinition WEAVER_BITE = AnimationDefinition.Builder.withLength(0.48f)
//			.addAnimation("upperJaw",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.12f, KeyframeAnimations.degreeVec(0f, 0f, 20f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, -21.67f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("lowerJaw",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.12f, KeyframeAnimations.degreeVec(0f, 0f, -21.25f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 32.5f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR))).build()
//		;
	public static final AnimationDefinition WEAVER_ATTACK = AnimationDefinition.Builder.withLength(0.48f)
			.addAnimation("rightLegs",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 15f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLegs",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.24f, KeyframeAnimations.degreeVec(-17.5f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("head",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.04f, KeyframeAnimations.degreeVec(67.17f, 4.01f, -67.45f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.08f, KeyframeAnimations.degreeVec(88.16f, 17.32f, -89.08f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.2f, KeyframeAnimations.degreeVec(89.25f, 60.62f, -89.03f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(90f, 75f, -90f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.28f, KeyframeAnimations.degreeVec(91.75f, 59.97f, -88.97f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.32f, KeyframeAnimations.degreeVec(90f, 45.61f, -88.05f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.36f, KeyframeAnimations.degreeVec(95.69f, 31.3f, -93.45f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.4f, KeyframeAnimations.degreeVec(91.32f, 18.64f, -91.47f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.44f, KeyframeAnimations.degreeVec(98.15f, 6.72f, -98.66f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))

//
//			.addAnimation("body",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("memoryCore",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
//			.addAnimation("root",
//					new AnimationChannel(AnimationChannel.Targets.ROTATION,
//							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.24f, KeyframeAnimations.degreeVec(-67.5f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR),
//							new Keyframe(0.48f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//									AnimationChannel.Interpolations.LINEAR)))
			.build();
}
