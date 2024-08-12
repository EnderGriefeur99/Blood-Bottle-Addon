package com.dikiytechies.rotp_bb.item;

import com.dikiytechies.rotp_bb.BBAddonConfig;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.power.ActionCooldownTracker;
import com.github.standobyte.jojo.power.bowcharge.BowChargeEffectInstance;
import com.github.standobyte.jojo.power.impl.nonstand.NonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.layout.ActionsLayout;
import com.github.standobyte.jojo.util.general.Container;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;

import javax.annotation.Nullable;
import java.util.Optional;

public class DrinkableItem extends Item {
    public DrinkableItem(Properties properties) {
        super(properties);
    }

    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public static final Item EMPTY_BOTTLE = Items.GLASS_BOTTLE;

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getItemInHand(hand);
        INonStandPower power = INonStandPower.getPlayerNonStandPower(player);
        if (power.getEnergy() <= power.getMaxEnergy() / 100 * BBAddonConfig.maxBottleHungerValue.get()) {
            player.startUsingItem(hand);
            return ActionResult.consume(item);
        }
        return ActionResult.fail(item);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entityLiving) {
        super.finishUsingItem(stack, world, entityLiving);

        if (INonStandPower.getNonStandPowerOptional(entityLiving).map(
                targetPower -> targetPower.givePower(ModPowers.VAMPIRISM.get())).orElse(false)) {
        } // <- world's best code steal
        INonStandPower power = INonStandPower.getPlayerNonStandPower((PlayerEntity) entityLiving); // <- shitty code
        power.addEnergy(
                ((power.getMaxEnergy() / 10) - (power.getMaxEnergy() / 1000 * BBAddonConfig.bottleDrinkPenalty.get()))
                        / 100 * BBAddonConfig.fillBottleMultiplier.get());

        if (stack.isEmpty())
            return new ItemStack(EMPTY_BOTTLE);
        if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).isCreative()) {
            ItemStack itemStack = new ItemStack(EMPTY_BOTTLE);
            PlayerEntity playerEntity = (PlayerEntity) entityLiving;
            if (!playerEntity.addItem(itemStack)) playerEntity.drop(itemStack, false);
        }
        return stack;
    }
}
