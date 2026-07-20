package com.bigdious.risus.data.modonomicon;

import com.bigdious.risus.data.modonomicon.categories.StructuresCategory;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCommandModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.datagen.book.demo.ConditionalCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.FeaturesCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.FormattingCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.IndexModeCategory;
import com.klikli_dev.modonomicon.datagen.book.demo.features.ConditionRootEntry;
import net.minecraft.resources.ResourceLocation;

public class ResearchersNotesBook extends SingleBookSubProvider {
	public static final String ID = "researchers_notes";

	public ResearchersNotesBook(String modid, ModonomiconLanguageProvider lang) {
		super(ID, modid, lang);
	}

	@Override
	protected BookModel additionalSetup(BookModel book) {
		var commandEntryCommand = BookCommandModel.create(this.modLoc("test_command"), "/give @s minecraft:apple 1")
			.withPermissionLevel(2)
			.withSuccessMessage("modonomicon.command.test_command.success");
		this.add(commandEntryCommand.getSuccessMessage(), "You got an apple, because reading is cool!");

		var commandEntryLinkCommand = BookCommandModel.create(this.modLoc("test_command2"), "/give @s minecraft:wheat 1")
			.withPermissionLevel(2)
			.withSuccessMessage("modonomicon.command.test_command2.success")
			.withAllowedEntry("modonomicon:features/command");
		this.add(commandEntryLinkCommand.getSuccessMessage(), "You got wheat, because clicking is cool!");

		return book.withModel(ResourceLocation.fromNamespaceAndPath("risus", "researchers_notes"))
			.withBookTextOffsetX(5)
			.withBookTextOffsetY(0) //no top offset
			.withBookTextOffsetWidth(-5)
			.withCommand(commandEntryCommand)
			.withCommand(commandEntryLinkCommand)
			.withAllowOpenBooksWithInvalidLinks(true);
	}

	@Override
	protected void registerDefaultMacros() {

	}

	@Override
	protected void generateCategories() {

		var structures = this.add(new StructuresCategory(this).generate());
		var featuresCategory = this.add(new FeaturesCategory(this).generate());
		var formattingCategory = this.add(new FormattingCategory(this).generate());

		var conditionalCategory = this.add(new ConditionalCategory(this).generate())
			.withCondition(this.condition().entryRead(this.modLoc(FeaturesCategory.ID, ConditionRootEntry.ID)));

		var indexModeCategory = this.add(new IndexModeCategory(this).generate());
	}

	@Override
	protected String bookName() {
		return "Researcher's Notes";
	}

	@Override
	protected String bookTooltip() {
		return "WE UPGRADING BOIS";
	}
}
