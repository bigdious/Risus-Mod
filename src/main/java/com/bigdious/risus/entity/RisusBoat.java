package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class RisusBoat extends Boat {

	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(RisusBoat.class, EntityDataSerializers.INT);

	public RisusBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
		this.blocksBuilding = true;
	}

	public RisusBoat(Level level, double x, double y, double z) {
		this(RisusEntities.BOAT.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public RisusBoat.Type getRisusBoatType() {
		return RisusBoat.Type.byId(this.getEntityData().get(BOAT_TYPE));
	}

	@Override
	public Item getDropItem() {
		return switch (this.getRisusBoatType()) {
			case BONDKNOT -> RisusItems.BONDKNOT_BOAT.get();
		};
	}

	public void setRisusBoatType(RisusBoat.Type boatType) {
		this.getEntityData().set(BOAT_TYPE, boatType.ordinal());
	}
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BOAT_TYPE, Type.BONDKNOT.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getRisusBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setRisusBoatType(RisusBoat.Type.getTypeFromString(compound.getString("Type")));
		}
	}


	public enum Type {
		BONDKNOT(RisusBlocks.BONDKNOT_PLANKS.get(), "bondknot");

		private final String name;
		private final Block block;

		Type(Block block, String name) {
			this.name = name;
			this.block = block;
		}

		public String getName() {
			return this.name;
		}

		public Block asPlank() {
			return this.block;
		}

		public String toString() {
			return this.name;
		}

		public static RisusBoat.Type byId(int id) {
			RisusBoat.Type[] aRisusBoatEntity$type = values();
			if (id < 0 || id >= aRisusBoatEntity$type.length) {
				id = 0;
			}

			return aRisusBoatEntity$type[id];
		}

		public static RisusBoat.Type getTypeFromString(String nameIn) {
			RisusBoat.Type[] boatTypeArray = values();

			for (Type type : boatTypeArray) {
				if (type.getName().equals(nameIn)) {
					return type;
				}
			}

			return boatTypeArray[0];
		}
	}
}
