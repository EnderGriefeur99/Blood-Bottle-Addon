package com.dikiytechies.rotp_bb;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;


public class BBAddonConfig {
    public static final ForgeConfigSpec.ConfigValue<Integer> bottleDrinkPenalty;
    public static final ForgeConfigSpec.ConfigValue<Integer> fillBottleMultiplier;
    public static final ForgeConfigSpec.ConfigValue<Integer> maxBottleHungerValue;

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Blood Bottle Configuration");
        bottleDrinkPenalty = BUILDER.comment("\nAdds percentage difference between bottle fill ability cost and blood bottle's blood energy value\nrecommended: 0-100%, Default = 0%")
                .translation("jojo.config.bottleDrinkPenalty")
                .define("bottleDrainPenalty",0);
        fillBottleMultiplier = BUILDER.comment("\nChanges in percents stored blood value\nrecommended: 1-1000%, Default = 100%")
                .translation("jojo.config.fillBottleMultiplier")
                .define("fillBottleMultiplier",100);
        maxBottleHungerValue = BUILDER.comment("\nChanges in percents max vampire energy bar value till what you can drink\nrecommended: 1-100%, Default = 65%")
                .translation("jojo.config.maxBottleHungerValue")
                .define("maxBottleHungerValue",65);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
