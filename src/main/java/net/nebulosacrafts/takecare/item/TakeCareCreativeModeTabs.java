package net.nebulosacrafts.takecare.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nebulosacrafts.takecare.TakeCare;

public class TakeCareCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister
                    .create(Registries.CREATIVE_MODE_TAB, TakeCare.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAKE_CARE_TAB =
            CREATIVE_MODE_TABS.register("takecare_tab",
                    () -> CreativeModeTab.builder().icon(
                    () -> new ItemStack(ModItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.takecare_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.SAPPHIRE.get());
                                pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                                pOutput.accept(ModItems.BANDAID.get());
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
