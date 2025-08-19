package net.nebulosacrafts.tutorialmod.item.custom.medic;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class StrangeSyringeItem extends UsableSyringe {

    public StrangeSyringeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void targetedAction(Player player, LivingEntity target, ItemStack stack) {
        //el efecto es aleatorio, puede no ocurrir
        double roll = Math.random();

        if (roll < 0.3f) { //30% probabilidades
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 50));
        } else if (roll < 0.6f) { //otro 30%, pero solo entra si no entró en el anterior
            target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200));
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1); // consumir jeringa
        }
    }

    @Override
    public void personalAction(Player player, ItemStack stack) {
        //el efecto es aleatorio, puede no ocurrir
        double roll = Math.random();

        if (roll < 0.3f) { //30% probabilidades
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 50));
        } else if (roll < 0.6f) { //otro 30%, pero solo entra si no entró en el anterior
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200));
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1); // consumir jeringa
        }
    }
}
