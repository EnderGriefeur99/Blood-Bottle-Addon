package com.dikiytechies.rotp_bb.action.non_stand;

import com.dikiytechies.rotp_bb.BBAddonConfig;
import com.dikiytechies.rotp_bb.item.ModItems;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.non_stand.NonStandAction;
import com.github.standobyte.jojo.action.non_stand.VampirismAction;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import static com.github.standobyte.jojo.client.sound.ClientTickingSoundsHelper.playEntitySound;


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
            ItemStack bottle = new ItemStack(ModItems.BOTTLE.get());
            if (player.getMainHandItem().equals(bottle)){
                player.addItem(bottle);
            } else if (player.getMainHandItem() == ItemStack.EMPTY && !player.inventory.contains(bottle)) {
                player.setItemInHand(Hand.MAIN_HAND, bottle);
            }
            if (user instanceof PlayerEntity && !player.isCreative()) {
                offhandItem.shrink(1);
                if (!player.addItem(bottle)) {
                    player.drop(bottle, false);
                }
            }
            power.consumeEnergy((power.getMaxEnergy() / 10) / 100 * BBAddonConfig.fillBottleMultiplier.get());
            playEntitySound(user, SoundEvents.BOTTLE_FILL, 0.75f, 1.0F, false);
        }
    }

    @Override
    protected int maxCuringStage() { return 3; }
}
