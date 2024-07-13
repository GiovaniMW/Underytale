package net.mcreator.underytaleneo.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UnderytaleneoModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		UnderytaleneoMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new,
				SavedDataSyncMessage::handler);
		UnderytaleneoMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			clone.LOVE = original.LOVE;
			clone.NAME = original.NAME;
			clone.EXP = original.EXP;
			clone.STRENGHT = original.STRENGHT;
			clone.AGILITY = original.AGILITY;
			clone.RESISTANCE = original.RESISTANCE;
			clone.INTELLIGENCE = original.INTELLIGENCE;
			clone.CHARISMA = original.CHARISMA;
			clone.HP = original.HP;
			clone.POINTS = original.POINTS;
			clone.STATUSCHECKBOX = original.STATUSCHECKBOX;
			clone.MAXEXP = original.MAXEXP;
			clone.STATUSDISTRIBUTION = original.STATUSDISTRIBUTION;
			clone.SOULSHOW = original.SOULSHOW;
			clone.NEXTEXP = original.NEXTEXP;
			clone.SOUL = original.SOUL;
			clone.START = original.START;
			clone.NAMEGOOD = original.NAMEGOOD;
			if (!event.isWasDeath()) {
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getPlayer().level);
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (mapdata != null)
					UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (worlddata != null)
					UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "underytaleneo_worldvars";
		public String LPTLUP = "\"\"";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			LPTLUP = nbt.getString("LPTLUP");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putString("LPTLUP", LPTLUP);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "underytaleneo_mapvars";
		public double ENEMYHP = 0.0;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			ENEMYHP = nbt.getDouble("ENEMYHP");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("ENEMYHP", ENEMYHP);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e),
						MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		public int type;
		public SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			if (this.data instanceof MapVariables _mapvars)
				_mapvars.read(buffer.readNbt());
			else if (this.data instanceof WorldVariables _worldvars)
				_worldvars.read(buffer.readNbt());
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("underytaleneo", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double LOVE = 1.0;
		public String NAME = "\"\"";
		public double EXP = 0.0;
		public double STRENGHT = 0.0;
		public double AGILITY = 0.0;
		public double RESISTANCE = 0.0;
		public double INTELLIGENCE = 0.0;
		public double CHARISMA = 0.0;
		public double HP = 0.0;
		public double POINTS = 0.0;
		public boolean STATUSCHECKBOX = false;
		public double MAXEXP = 10.0;
		public boolean STATUSDISTRIBUTION = false;
		public boolean SOULSHOW = false;
		public double NEXTEXP = 0.0;
		public double SOUL = 0;
		public boolean START = false;
		public boolean NAMEGOOD = true;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				UnderytaleneoMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("LOVE", LOVE);
			nbt.putString("NAME", NAME);
			nbt.putDouble("EXP", EXP);
			nbt.putDouble("STRENGHT", STRENGHT);
			nbt.putDouble("AGILITY", AGILITY);
			nbt.putDouble("RESISTANCE", RESISTANCE);
			nbt.putDouble("INTELLIGENCE", INTELLIGENCE);
			nbt.putDouble("CHARISMA", CHARISMA);
			nbt.putDouble("HP", HP);
			nbt.putDouble("POINTS", POINTS);
			nbt.putBoolean("STATUSCHECKBOX", STATUSCHECKBOX);
			nbt.putDouble("MAXEXP", MAXEXP);
			nbt.putBoolean("STATUSDISTRIBUTION", STATUSDISTRIBUTION);
			nbt.putBoolean("SOULSHOW", SOULSHOW);
			nbt.putDouble("NEXTEXP", NEXTEXP);
			nbt.putDouble("SOUL", SOUL);
			nbt.putBoolean("START", START);
			nbt.putBoolean("NAMEGOOD", NAMEGOOD);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			LOVE = nbt.getDouble("LOVE");
			NAME = nbt.getString("NAME");
			EXP = nbt.getDouble("EXP");
			STRENGHT = nbt.getDouble("STRENGHT");
			AGILITY = nbt.getDouble("AGILITY");
			RESISTANCE = nbt.getDouble("RESISTANCE");
			INTELLIGENCE = nbt.getDouble("INTELLIGENCE");
			CHARISMA = nbt.getDouble("CHARISMA");
			HP = nbt.getDouble("HP");
			POINTS = nbt.getDouble("POINTS");
			STATUSCHECKBOX = nbt.getBoolean("STATUSCHECKBOX");
			MAXEXP = nbt.getDouble("MAXEXP");
			STATUSDISTRIBUTION = nbt.getBoolean("STATUSDISTRIBUTION");
			SOULSHOW = nbt.getBoolean("SOULSHOW");
			NEXTEXP = nbt.getDouble("NEXTEXP");
			SOUL = nbt.getDouble("SOUL");
			START = nbt.getBoolean("START");
			NAMEGOOD = nbt.getBoolean("NAMEGOOD");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.LOVE = message.data.LOVE;
					variables.NAME = message.data.NAME;
					variables.EXP = message.data.EXP;
					variables.STRENGHT = message.data.STRENGHT;
					variables.AGILITY = message.data.AGILITY;
					variables.RESISTANCE = message.data.RESISTANCE;
					variables.INTELLIGENCE = message.data.INTELLIGENCE;
					variables.CHARISMA = message.data.CHARISMA;
					variables.HP = message.data.HP;
					variables.POINTS = message.data.POINTS;
					variables.STATUSCHECKBOX = message.data.STATUSCHECKBOX;
					variables.MAXEXP = message.data.MAXEXP;
					variables.STATUSDISTRIBUTION = message.data.STATUSDISTRIBUTION;
					variables.SOULSHOW = message.data.SOULSHOW;
					variables.NEXTEXP = message.data.NEXTEXP;
					variables.SOUL = message.data.SOUL;
					variables.START = message.data.START;
					variables.NAMEGOOD = message.data.NAMEGOOD;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
