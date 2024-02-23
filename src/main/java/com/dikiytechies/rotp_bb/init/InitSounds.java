package com.dikiytechies.rotp_bb.init;

import java.util.function.Supplier;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;
import com.dikiytechies.rotp_bb.AddonMain;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AddonMain.MOD_ID);
    public static final RegistryObject<SoundEvent> BOTTLE_FILL = SOUNDS.register("bottle_fill",
            () -> new SoundEvent(new ResourceLocation("item.bottle.fill"))); // I'm done with fixing this (help)
}
