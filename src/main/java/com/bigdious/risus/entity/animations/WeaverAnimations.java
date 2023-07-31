package com.bigdious.risus.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class WeaverAnimations {
    public static final AnimationDefinition WEAVER_LEAP = AnimationDefinition.Builder.withLength(0.48f)
            .addAnimation("rightLegs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(0f, 0.00042f, 15f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("leftLegs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(-17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.04f,KeyframeAnimations.degreeVec(67.17393f, 4.00551f, -67.45345f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.08f,KeyframeAnimations.degreeVec(88.16338f, 17.31925F, -89.07979f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.2f,KeyframeAnimations.degreeVec(89.25314f, 60.61861f, -89.03328f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(90f,  75f, -90f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.28f,KeyframeAnimations.degreeVec(91.75238f, 59.96742f, -88.97431f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.32f,KeyframeAnimations.degreeVec(90.00123f, 45.61091f, -88.053f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.36f,KeyframeAnimations.degreeVec(95.68818f, 31.29521f, -93.44856f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.4f,KeyframeAnimations.degreeVec(91.32143f, 18.63967f, -91.47254f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.44f,KeyframeAnimations.degreeVec(98.15111f, 6.7195f, -98.6628f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("top", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.12f,KeyframeAnimations.degreeVec(0f, 0f, 20f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(0f, 0f, -21.67f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bottom", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.12f,KeyframeAnimations.degreeVec(0f, 0f, -21.25f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(0f, 0f, 32.5f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(0f, 0f, 2.5f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("memoryCore", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(-30f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("entireBody", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.24f,KeyframeAnimations.degreeVec(-67.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48f,KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)))

            .build();
}
