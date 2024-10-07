package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.attachment.ExBurnAttachment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RisusDataAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Risus.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<ExBurnAttachment>> EX_BURN = ATTACHMENT_TYPES.register("ex_burn", () -> AttachmentType.builder(ExBurnAttachment::new).serialize(ExBurnAttachment.CODEC).build());
	}
