package com.dikiytechies.rotp_bb.init.power.non_stand.vampirism;

import com.dikiytechies.rotp_bb.action.non_stand.BloodRefill;
import com.github.standobyte.jojo.action.non_stand.NonStandAction;
import com.github.standobyte.jojo.action.non_stand.VampirismAction;
import net.minecraftforge.fml.RegistryObject;

import static com.github.standobyte.jojo.init.power.ModCommonRegisters.ACTIONS;

import static com.github.standobyte.jojo.init.power.non_stand.vampirism.ModVampirismActions.VAMPIRISM_BLOOD_GIFT;


public class AddonVampirismActions {

    public static void loadRegistryObjects() {}


    public static final RegistryObject<VampirismAction> VAMPIRISM_BLOOD_REFILL = ACTIONS.register("vampirism_blood_refill",
            () -> new BloodRefill(new NonStandAction.Builder().holdToFire(5,false).heldWalkSpeed(0.75f).shiftVariationOf(VAMPIRISM_BLOOD_GIFT)));
}
