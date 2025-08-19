package net.nebulosacrafts.tutorialmod.item.custom.medic;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Syringe that heals 2 hearts to someone or yourself when used
 * Will only be used if you/target are damaged.
 */
public class HealingSyringeItem extends UsableSyringe {

    public HealingSyringeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void personalAction(Player player, ItemStack stack){
        if(player.isHurt()){
            player.heal(4.0F); // cura 2 corazones

            if (!player.getAbilities().instabuild) {
                stack.shrink(1); // consumir jeringa
            }
        }
    }

    @Override
    public void targetedAction(Player player, LivingEntity target, ItemStack stack) {

        if (target.getHealth() < target.getMaxHealth()) {
            target.heal(4.0F);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1); // consumir jeringa
            }
        }
    }
}
