package net.mcreator.underytaleneo.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ExpProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(),
					event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		{
			double _setval = sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1;
			sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.HP = _setval;
				capability.syncPlayerVariables(sourceentity);
			});
		}
		UnderytaleneoModVariables.MapVariables.get(world).ENEMYHP = Math
				.ceil((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2);
		UnderytaleneoModVariables.MapVariables.get(world).syncData(world);
		{
			double _setval = (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP + UnderytaleneoModVariables.MapVariables.get(world).ENEMYHP;
			sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.EXP = _setval;
				capability.syncPlayerVariables(sourceentity);
			});
		}
		while ((sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).MAXEXP < (sourceentity
						.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP) {
			if ((sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP >= (sourceentity
							.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).MAXEXP) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("underytaleneo:loveup")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("underytaleneo:loveup")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				{
					double _setval = (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).LOVE + 1;
					sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.LOVE = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				{
					double _setval = (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP
							- (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UnderytaleneoModVariables.PlayerVariables())).MAXEXP;
					sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.EXP = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				{
					double _setval = Math.ceil((sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).MAXEXP * 1.5);
					sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.MAXEXP = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				{
					double _setval = (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).POINTS + 1;
					sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.POINTS = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				{
					double _setval = (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UnderytaleneoModVariables.PlayerVariables())).MAXEXP
							- (sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UnderytaleneoModVariables.PlayerVariables())).EXP;
					sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.NEXTEXP = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
									_level.getServer(), null).withSuppressedOutput(),
							(("attribute @p generic.max_health base set 1".replace("@p", sourceentity.getDisplayName().getString())).replace("1",
									new java.text.DecimalFormat("##.##")
											.format((sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
													.orElse(new UnderytaleneoModVariables.PlayerVariables())).LOVE + 19))));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
									_level.getServer(), null).withSuppressedOutput(),
							(("attribute @p generic.attack_damage base set 1".replace("@p", sourceentity.getDisplayName().getString())).replace("1",
									new java.text.DecimalFormat("##.##").format(
											Math.round((sourceentity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
													.orElse(new UnderytaleneoModVariables.PlayerVariables())).LOVE / 2 + 1)))));
			}
		}
	}
}
