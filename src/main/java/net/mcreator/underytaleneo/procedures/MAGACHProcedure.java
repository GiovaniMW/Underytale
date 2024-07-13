package net.mcreator.underytaleneo.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.underytaleneo.network.UnderytaleneoModVariables;

import java.util.Iterator;

public class MAGACHProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 1) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_1"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		} else if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 2) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_2"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		} else if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 3) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_3"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		} else if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 4) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_4"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		} else if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 5) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_5"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		} else if ((entity.getCapability(UnderytaleneoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UnderytaleneoModVariables.PlayerVariables())).INTELLIGENCE == 6) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("underytaleneo:mag_6"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		}
	}
}