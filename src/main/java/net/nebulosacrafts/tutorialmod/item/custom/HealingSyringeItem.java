package net.nebulosacrafts.tutorialmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HealingSyringeItem extends Item {

    public HealingSyringeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(
            @NotNull ItemStack pStack, Player pPlayer,
            @NotNull LivingEntity pInteractionTarget,
            @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.level().isClientSide){
            if (pInteractionTarget instanceof Player target) {
                target.heal(4.0F); // cura 2 corazones
                pStack.hurtAndBreak(1, pPlayer,
                        player -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
