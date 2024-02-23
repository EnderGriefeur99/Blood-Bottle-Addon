package com.dikiytechies.rotp_bb;

import com.dikiytechies.rotp_bb.init.power.non_stand.vampirism.AddonVampirismActions;
import com.dikiytechies.rotp_bb.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.github.standobyte.jojo.JojoMod.MAIN_TAB;

// Your addon's main file

@Mod(AddonMain.MOD_ID)
public class AddonMain {
    // The mod's id. Used quite often, mostly when creating ResourceLocation (objects).
    // Its value should match the "modid" entry in the META-INF/mods.toml file
    public static final ItemGroup GROUP = MAIN_TAB;
    public static final String MOD_ID = "rotp_bb";
    public static final Logger LOGGER = LogManager.getLogger();

    public AddonMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BBAddonConfig.SPEC, "jojo-bb-a-common.toml");


        // All DeferredRegister objects are registered here.
        // A DeferredRegister needs to be created for each type of objects that need to be registered in the game 
        // (see ForgeRegistries or JojoCustomRegistries)
        AddonVampirismActions.loadRegistryObjects();

    }
}
