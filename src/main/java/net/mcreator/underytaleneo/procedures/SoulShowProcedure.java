package net.mcreator.underytaleneo.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

import java.util.HashMap;

public class SoulShowProcedure {
	public static boolean execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return false;
		if (guistate.containsKey("checkbox:Soul") ? ((Checkbox) guistate.get("checkbox:Soul")).selected() : false) {
			{
				boolean _setval = true;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SOULSHOW = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			return true;
		} else {
			{
				boolean _setval = false;
				entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SOULSHOW = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		return false;
	}
}
