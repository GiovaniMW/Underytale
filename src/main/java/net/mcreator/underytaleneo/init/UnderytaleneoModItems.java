
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.underytaleneo.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.underytaleneo.item.OverwriteItem;
import net.mcreator.underytaleneo.item.EmptyGunItem;
import net.mcreator.underytaleneo.UnderytaleneoMod;

public class UnderytaleneoModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, UnderytaleneoMod.MODID);
	public static final RegistryObject<Item> OVERWRITE = REGISTRY.register("overwrite", () -> new OverwriteItem());
	public static final RegistryObject<Item> EMPTY_GUN = REGISTRY.register("empty_gun", () -> new EmptyGunItem());
}
