package net.mcreator.underytaleneo.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

import java.util.HashMap;

public class StatusShow3Procedure {
	public static boolean execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return false;
		if ((guistate.containsKey("checkbox:DistributePoints") ? ((Checkbox) guistate.get("checkbox:DistributePoints")).selected() : false)
				&& (guistate.containsKey("checkbox:Status") ? ((Checkbox) guistate.get("checkbox:Status")).selected() : false)) {
			{
				boolean _setval = true;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.STATUSDISTRIBUTION = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			return true;
		} else {
			{
				boolean _setval = false;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.STATUSDISTRIBUTION = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		return false;
	}
}
