package net.nebulosacrafts.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder()
            .fast()
            .nutrition(2)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1f).build();
            // se le puede añadir una probabilidad de que ocurra el efecto a la comida, por ejemplo, aquí hay un 10% de probabilidades de que te dé velocidad
}
