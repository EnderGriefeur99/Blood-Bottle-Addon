package com.dikiytechies.rotp_bb.action.non_stand;

import com.dikiytechies.rotp_bb.BBAddonConfig;
import com.dikiytechies.rotp_bb.init.InitSounds;
import com.dikiytechies.rotp_bb.item.ModItems;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.non_stand.NonStandAction;
import com.github.standobyte.jojo.action.non_stand.VampirismAction;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import com.github.standobyte.jojo.action.Action;


public class BloodRefill extends VampirismAction {
    public BloodRefill(NonStandAction.Builder builder) { super(builder); }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        ItemStack offhandItem = user.getOffhandItem();
        if (!offhandItem.getItem().equals(Items.GLASS_BOTTLE)) {
            return conditionMessage("no_volume");
        }
        if (power.getEnergy() < (power.getMaxEnergy() / 10) / 100 * BBAddonConfig.fillBottleMultiplier.get()) {
            return conditionMessage("no_energy_vampirism");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            ItemStack offhandItem = user.getOffhandItem();
            PlayerEntity player = (PlayerEntity) user;
            player.addItem(new ItemStack(ModItems.BOTTLE.get()));
            if (user instanceof PlayerEntity && !player.isCreative()) {
                offhandItem.shrink(1);
            }
            power.consumeEnergy((power.getMaxEnergy() / 10) / 100 * BBAddonConfig.fillBottleMultiplier.get());
            user.playSound(InitSounds.BOTTLE_FILL.get(), 12.0f, 1.0F);
        }
    }

    @Override
    protected int maxCuringStage() { return 3; }
}
