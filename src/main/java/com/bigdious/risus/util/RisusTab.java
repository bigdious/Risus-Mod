package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class RisusTab  extends CreativeModeTab {
	public static final RisusTab INSTANCE = new RisusTab();

	public RisusTab() {
		super(Risus.MODID);
		this.hideTitle();
		this.setBackgroundImage(Risus.prefix("textures/gui/tab_risus.png"));
	}

	private static final ResourceLocation RISUS_TABS = Risus.prefix("textures/gui/tabs.png");
	@Override
	public ResourceLocation getTabsImage() {
		return RISUS_TABS;
	}

	@Nonnull
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(RisusItems.SMILE.get());
	}
}