package net.nebulosacrafts.tutorialmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nebulosacrafts.tutorialmod.TutorialMod;
import net.nebulosacrafts.tutorialmod.block.ModBlocks;

public class TutorialModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister
                    .create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab",
                    () -> CreativeModeTab.builder().icon(
                    () -> new ItemStack(ModItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.tutorial_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.SAPPHIRE.get());
                                pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                                pOutput.accept(ModItems.BANDAID.get());
                                pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                                pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());

                                pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                                pOutput.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                                pOutput.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                                pOutput.accept(ModBlocks.END_STONE_SAPPHIRE_ORE.get());
                                /*
                                Para poner todos los objetos de ModItems en la misma Tab ->
                                .displayItems((pParameters, pOutput) -> {
                                    for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                                        pOutput.accept(item.get());
                                    }
                                })
                                 */
                            })
                            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
