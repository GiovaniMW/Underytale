package net.mcreator.underytaleneo.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

public class STRADDProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).POINTS > 0) {
			{
				double _setval = (entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new UnderytaleneoModVariables.PlayerVariables())).STRENGHT + 1;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.STRENGHT = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new UnderytaleneoModVariables.PlayerVariables())).POINTS - 1;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.POINTS = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			STR1GAProcedure.execute(entity);
		}
	}
}