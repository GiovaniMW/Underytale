
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.underytaleneo.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.underytaleneo.client.gui.StatusMenuScreen;
import net.mcreator.underytaleneo.client.gui.StarterMenuScreen;
import net.mcreator.underytaleneo.client.gui.OVERWRITEGUIScreen;
import net.mcreator.underytaleneo.client.gui.NameGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnderytaleneoModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(UnderytaleneoModMenus.STATUS_MENU, StatusMenuScreen::new);
			MenuScreens.register(UnderytaleneoModMenus.OVERWRITEGUI, OVERWRITEGUIScreen::new);
			MenuScreens.register(UnderytaleneoModMenus.STARTER_MENU, StarterMenuScreen::new);
			MenuScreens.register(UnderytaleneoModMenus.NAME_GUI, NameGuiScreen::new);
		});
	}
}
