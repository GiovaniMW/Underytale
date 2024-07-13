package net.mcreator.underytaleneo.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

import java.util.HashMap;

public class SETLOVEProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			double _setval = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("text:lovesetup") ? ((EditBox) guistate.get("text:lovesetup")).getValue() : "");
			entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.LOVE = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
