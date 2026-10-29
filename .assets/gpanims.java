// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 5.2.2
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class great_preacherAnimation {
	public static final AnimationDefinition wing_flap_idle = AnimationDefinition.Builder.withLength(3.5F).looping()
		.addAnimation("LeftWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-13.0843F, 38.3386F, -16.1913F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftWingMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-31.5521F, 50.1557F, -39.9067F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftWingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-12.7049F, 33.5103F, -20.0256F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightWing", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-13.0843F, -38.3386F, 16.1913F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightWingMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-31.5521F, -50.1557F, 39.9067F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightWingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-12.7049F, -33.5103F, 20.0256F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition sword_summon_swing = AnimationDefinition.Builder.withLength(18.0833F)
		.addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.3811F, -8.6492F, -4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-2.8811F, -8.6492F, -4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-10.3811F, -8.6492F, -4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-74.8529F, -29.8343F, 3.2997F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-74.8529F, -29.8343F, 3.2997F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-51.814F, -28.4518F, -8.0448F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6657F, KeyframeAnimations.degreeVec(-51.814F, -28.4518F, -8.0448F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6667F, KeyframeAnimations.degreeVec(-51.814F, -28.4518F, -8.0448F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.625F, KeyframeAnimations.degreeVec(-53.0197F, -43.4406F, -10.6702F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6657F, KeyframeAnimations.degreeVec(-53.0197F, -43.4406F, -10.6702F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6667F, KeyframeAnimations.degreeVec(-53.0197F, -43.4406F, -10.6702F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.9583F, KeyframeAnimations.degreeVec(-21.9881F, -2.0494F, -24.3414F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0F, KeyframeAnimations.degreeVec(-15.1127F, 0.3609F, -31.8401F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0417F, KeyframeAnimations.degreeVec(-9.7758F, 12.1041F, -36.4652F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.1667F, KeyframeAnimations.degreeVec(-1.2259F, 36.4701F, -38.7568F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-1.2259F, 36.4701F, -38.7568F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-1.2259F, 36.4701F, -38.7568F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftGrowthMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(1.1271F, -15.4699F, -13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(1.1271F, -15.4699F, -13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-41.3729F, -15.4699F, -13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-41.3729F, -15.4699F, -13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-36.2496F, -5.4918F, -13.9584F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-36.2496F, -5.4918F, -13.9584F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-36.2496F, -5.4918F, -13.9584F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftGrowthBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-6.2966F, -5.2219F, 7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-6.2966F, -5.2219F, 7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-26.2966F, -5.2219F, 7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-26.2966F, -5.2219F, 7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-68.5375F, -3.648F, 6.0543F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-68.5375F, -3.648F, 6.0543F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-68.5375F, -3.648F, 6.0543F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftPalm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-69.8026F, -81.0632F, 52.236F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-69.8026F, -81.0632F, 52.236F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(60.1626F, -46.3039F, -97.7307F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(60.1626F, -46.3039F, -97.7307F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(82.4287F, -36.8741F, -66.7779F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(82.4287F, -36.8741F, -66.7779F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(82.4287F, -36.8741F, -66.7779F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftThumb", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-48.5517F, -54.1571F, 11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-48.5517F, -54.1571F, 11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-48.5517F, -54.1571F, 11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-48.6886F, -27.5627F, 1.8196F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-48.6886F, -27.5627F, 1.8196F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-48.6886F, -27.5627F, 1.8196F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftThumbBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftPointer", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(11.0376F, 15.2275F, 7.4036F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(11.0376F, 15.2275F, 7.4036F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(11.0376F, 15.2275F, 7.4036F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftPointerBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 55.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(3.9572F, -13.3177F, 41.1581F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(3.9572F, -13.3177F, 41.1581F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(3.9572F, -13.3177F, 41.1581F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftMiddleBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftRing", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-1.3808F, -27.0566F, 56.361F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-1.3808F, -27.0566F, 56.361F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-1.3808F, -27.0566F, 56.361F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LeftRingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 62.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 62.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 62.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.3811F, 8.6492F, 4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-2.8811F, 8.6492F, 4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-10.3811F, 8.6492F, 4.9616F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-74.8529F, 29.8343F, -3.2997F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-74.8529F, 29.8343F, -3.2997F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-74.8529F, 29.8343F, -3.2997F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6657F, KeyframeAnimations.degreeVec(-74.8529F, 29.8343F, -3.2997F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6667F, KeyframeAnimations.degreeVec(-74.8529F, 29.8343F, -3.2997F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.625F, KeyframeAnimations.degreeVec(-81.0394F, 47.7913F, -1.8313F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6657F, KeyframeAnimations.degreeVec(-81.0394F, 47.7913F, -1.8313F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6667F, KeyframeAnimations.degreeVec(-81.0394F, 47.7913F, -1.8313F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0F, KeyframeAnimations.degreeVec(-34.4672F, 4.2681F, 15.162F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0833F, KeyframeAnimations.degreeVec(-21.067F, -10.08F, 25.0873F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.1667F, KeyframeAnimations.degreeVec(-14.8899F, -26.4891F, 39.6493F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-14.8899F, -26.4891F, 39.6493F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("Smile", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightGrowthMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(1.1271F, 15.4699F, 13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(1.1271F, 15.4699F, 13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-41.3729F, 15.4699F, 13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-41.3729F, 15.4699F, 13.2833F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-42.6565F, 14.216F, 8.2758F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6657F, KeyframeAnimations.degreeVec(-42.6565F, 14.216F, 8.2758F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6667F, KeyframeAnimations.degreeVec(-42.6565F, 14.216F, 8.2758F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.8333F, KeyframeAnimations.degreeVec(-43.2521F, 13.5497F, 5.7937F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-43.2521F, 13.5497F, 5.7937F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-43.2521F, 13.5497F, 5.7937F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightGrowthBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-6.2966F, 5.2219F, -7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-6.2966F, 5.2219F, -7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(-26.2966F, 5.2219F, -7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-26.2966F, 5.2219F, -7.9936F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-26.3781F, -1.7736F, -10.7008F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-26.3781F, -1.7736F, -10.7008F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-26.3781F, -1.7736F, -10.7008F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightPalm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-69.8026F, 81.0632F, -52.236F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-69.8026F, 81.0632F, -52.236F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(60.16F, 46.3F, 97.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(60.16F, 46.3F, 97.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(43.3031F, 18.8419F, 51.0912F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(43.3031F, 18.8419F, 51.0912F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(43.3031F, 18.8419F, 51.0912F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightThumb", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-48.5517F, 54.1571F, -11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-48.5517F, 54.1571F, -11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-48.5517F, 54.1571F, -11.271F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-57.4522F, 43.4462F, -34.4071F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-57.4522F, 43.4462F, -34.4071F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-57.4522F, 43.4462F, -34.4071F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightThumbBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightPointer", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(1.2104F, 18.0038F, -46.8381F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(1.2104F, 18.0038F, -46.8381F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(1.2104F, 18.0038F, -46.8381F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightPointerBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.37F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.37F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.37F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightMiddle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 32.5F, -72.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 32.5F, -72.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 32.5F, -72.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightMiddleBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightRing", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(-15.1913F, 18.2138F, -94.5633F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-15.1913F, 18.2138F, -94.5633F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-15.1913F, 18.2138F, -94.5633F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("RightRingBottom", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("Sword", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.degreeVec(0.0F, 90.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.5417F, KeyframeAnimations.degreeVec(0.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6657F, KeyframeAnimations.degreeVec(0.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6667F, KeyframeAnimations.degreeVec(0.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.625F, KeyframeAnimations.degreeVec(20.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6657F, KeyframeAnimations.degreeVec(20.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6667F, KeyframeAnimations.degreeVec(20.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.1667F, KeyframeAnimations.degreeVec(-90.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.degreeVec(-90.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.degreeVec(-90.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.degreeVec(-90.0F, -180.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("Sword", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.4583F, KeyframeAnimations.posVec(0.0F, 17.0F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.5417F, KeyframeAnimations.posVec(0.0F, 46.0F, -16.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6657F, KeyframeAnimations.posVec(0.0F, 46.0F, -16.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.6667F, KeyframeAnimations.posVec(0.0F, 46.0F, -16.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.625F, KeyframeAnimations.posVec(0.0F, 47.0F, 17.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6657F, KeyframeAnimations.posVec(0.0F, 47.0F, 17.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.6667F, KeyframeAnimations.posVec(0.0F, 47.0F, 17.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.75F, KeyframeAnimations.posVec(0.0F, 45.36F, -9.94F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.7917F, KeyframeAnimations.posVec(0.0F, 42.51F, -26.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.8333F, KeyframeAnimations.posVec(0.0F, 35.52F, -45.03F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.875F, KeyframeAnimations.posVec(0.0F, 25.26F, -62.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.9167F, KeyframeAnimations.posVec(0.0F, 10.69F, -78.43F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.9583F, KeyframeAnimations.posVec(0.0F, -2.31F, -92.26F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0F, KeyframeAnimations.posVec(0.0F, -19.38F, -99.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0417F, KeyframeAnimations.posVec(0.0F, -44.14F, -108.77F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0833F, KeyframeAnimations.posVec(0.0F, -61.34F, -112.08F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.125F, KeyframeAnimations.posVec(0.0F, -79.74F, -112.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.1667F, KeyframeAnimations.posVec(0.0F, -96.0F, -111.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.posVec(0.0F, -96.0F, -111.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.posVec(0.0F, -96.0F, -111.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.posVec(0.0F, -96.0F, -111.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("Sword", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(7.4583F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.5417F, KeyframeAnimations.scaleVec(4.0F, 4.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.1657F, KeyframeAnimations.scaleVec(4.0F, 4.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.1667F, KeyframeAnimations.scaleVec(4.0F, 4.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2073F, KeyframeAnimations.scaleVec(4.0F, 4.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.2083F, KeyframeAnimations.scaleVec(4.0F, 4.0F, 4.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.0417F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}