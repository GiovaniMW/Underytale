package net.mcreator.underytaleneo.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

public class StatusShow2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).STATUSCHECKBOX == true) {
			return true;
		}
		return false;
	}
}
