package net.nebulosacrafts.tutorialmod.item.custom.medic;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HealingSyringeItem extends Item {

    public HealingSyringeItem(Properties pProperties) {
        super(pProperties);
    }

    // Si se usa en alguien:
    @Override
    public @NotNull InteractionResult interactLivingEntity(
            @NotNull ItemStack pStack, Player pPlayer,
            @NotNull LivingEntity pInteractionTarget,
            @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.level().isClientSide){
            if (pInteractionTarget instanceof Animal target) {
                ItemStack stack = pPlayer.getItemInHand(pUsedHand);

                target.heal(4.0F); // cura 2 corazones

                // consumirlo
                if (!pPlayer.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    // Para usarlo en uno mismo:
    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        ItemStack stack = player.getItemInHand(pContext.getHand());

        // Marca que empezó a “cargar” para este jugador
        player.startUsingItem(pContext.getHand());

        Objects.requireNonNull(player).heal(4.0F); // cura 2 corazones

        // consumirlo
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 1000; // tiempo máximo de carga
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW; // animación de carga
    }

}
