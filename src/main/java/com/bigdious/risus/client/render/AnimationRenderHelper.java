package com.bigdious.risus.client.render;

public class AnimationRenderHelper {
	public static int rotation;

	public static void animate() {
		tickRotation();
	}
	private static void tickRotation() {
		if (rotation >1728000) rotation = 0; else rotation++;
	}

}
