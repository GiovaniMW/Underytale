
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.underytaleneo.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import net.mcreator.underytaleneo.world.inventory.StatusMenuMenu;
import net.mcreator.underytaleneo.world.inventory.StarterMenuMenu;
import net.mcreator.underytaleneo.world.inventory.OVERWRITEGUIMenu;
import net.mcreator.underytaleneo.world.inventory.NameGuiMenu;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UnderytaleneoModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<StatusMenuMenu> STATUS_MENU = register("status_menu",
			(id, inv, extraData) -> new StatusMenuMenu(id, inv, extraData));
	public static final MenuType<OVERWRITEGUIMenu> OVERWRITEGUI = register("overwritegui",
			(id, inv, extraData) -> new OVERWRITEGUIMenu(id, inv, extraData));
	public static final MenuType<StarterMenuMenu> STARTER_MENU = register("starter_menu",
			(id, inv, extraData) -> new StarterMenuMenu(id, inv, extraData));
	public static final MenuType<NameGuiMenu> NAME_GUI = register("name_gui", (id, inv, extraData) -> new NameGuiMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
