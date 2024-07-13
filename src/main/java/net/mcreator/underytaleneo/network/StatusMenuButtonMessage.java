
package net.mcreator.underytaleneo.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.underytaleneo.world.inventory.StatusMenuMenu;
import net.mcreator.underytaleneo.procedures.STRADDProcedure;
import net.mcreator.underytaleneo.procedures.RESACHProcedure;
import net.mcreator.underytaleneo.procedures.MAGADDProcedure;
import net.mcreator.underytaleneo.procedures.CHAACHProcedure;
import net.mcreator.underytaleneo.procedures.AGIADDProcedure;
import net.mcreator.underytaleneo.UnderytaleneoMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StatusMenuButtonMessage {
	private final int buttonID, x, y, z;

	public StatusMenuButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public StatusMenuButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(StatusMenuButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(StatusMenuButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = StatusMenuMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			STRADDProcedure.execute(entity);
		}
		if (buttonID == 1) {

			MAGADDProcedure.execute(entity);
		}
		if (buttonID == 2) {

			AGIADDProcedure.execute(entity);
		}
		if (buttonID == 3) {

			RESACHProcedure.execute(entity);
		}
		if (buttonID == 4) {

			CHAACHProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		UnderytaleneoMod.addNetworkMessage(StatusMenuButtonMessage.class, StatusMenuButtonMessage::buffer, StatusMenuButtonMessage::new,
				StatusMenuButtonMessage::handler);
	}
}
