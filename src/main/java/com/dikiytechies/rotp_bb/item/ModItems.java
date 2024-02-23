package com.dikiytechies.rotp_bb.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import com.dikiytechies.rotp_bb.AddonMain;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AddonMain.MOD_ID);


    public static final RegistryObject<Item> BOTTLE = ITEMS.register("blood_bottle", () -> new
            DrinkableItem(new Item.Properties().stacksTo(16).tab(AddonMain.GROUP).
            food(new Food.Builder().saturationMod(10).nutrition(2).alwaysEat().build())));


    public static void register(IEventBus modEventbus) {
        ITEMS.register(modEventbus);
    }
}
