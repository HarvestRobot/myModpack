package net.nebulosacrafts.tutorialmod.item.custom.medic;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class UsableSyringe extends Item {
    public UsableSyringe(Properties pProperties) {
        super(pProperties);
    }

    // Si se usa en alguien:
    @Override
    public @NotNull InteractionResult interactLivingEntity(
            @NotNull ItemStack pStack, Player pPlayer,
            @NotNull LivingEntity pInteractionTarget,
            @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.level().isClientSide){
            //solo voy a curar a las criaturas no malvadas
            if (isNonHostile(pInteractionTarget)) {
                ItemStack stack = pPlayer.getItemInHand(pUsedHand);

                targetedAction(pPlayer, pInteractionTarget, stack);
            }
        }
        return InteractionResult.SUCCESS;
    }

    // Para usarlo en uno mismo:
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Inicia la animación de carga
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(stack);
    }

    // También permitir uso clicando en bloque
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            player.startUsingItem(context.getHand());
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {

            personalAction(player, stack);

        }
        return stack;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW; // animación de carga
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 25; //duración máxima de la carga
    }

    /**
     * The action the syringe performs on yourself when consuming
     * @param player the player holding the syringe
     */
    public void personalAction(Player player, ItemStack stack){
        //empty for overriding
    }

    /**
     * The action the syringe performs on a target when using
     * @param target the entity to target
     */
    public void targetedAction(Player player, LivingEntity target, ItemStack stack){
        //empty for overriding
    }

    private boolean isNonHostile(LivingEntity entity) {
        if (entity instanceof Player || entity instanceof Animal || entity instanceof TamableAnimal || entity instanceof WaterAnimal){
            return true;
        }

        //no queremos los zombies aldeanos
        return entity instanceof AgeableMob && !(entity instanceof Monster);
    }


}
