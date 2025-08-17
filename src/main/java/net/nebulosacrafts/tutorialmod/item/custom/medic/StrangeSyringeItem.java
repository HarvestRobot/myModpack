package net.nebulosacrafts.tutorialmod.item.custom.medic;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StrangeSyringeItem extends Item {

    public StrangeSyringeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(
            @NotNull ItemStack pStack, Player pPlayer,
            @NotNull LivingEntity pInteractionTarget,
            @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.level().isClientSide){
            if (pInteractionTarget instanceof Animal target) {
                ItemStack stack = pPlayer.getItemInHand(pUsedHand);

                //el efecto es aleatorio, puede no ocurrir
                float roll = pPlayer.getRandom().nextFloat();

                if (roll < 0.3f) {
                    // 30% -> efecto de velocidad
                    target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 50));
                } else if (roll < 0.6f) {
                    // otro 30% -> solo se ejecuta si NO entró en el primero
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200));
                }

                // consumirlo
                if (!pPlayer.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
