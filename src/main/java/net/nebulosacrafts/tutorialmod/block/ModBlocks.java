package net.nebulosacrafts.tutorialmod.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nebulosacrafts.tutorialmod.TutorialMod;
import net.nebulosacrafts.tutorialmod.block.custom.SoundBlock;
import net.nebulosacrafts.tutorialmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock(
            "sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
    //Si en vez de properties.copy ponemos properties.of, podemos modificar características del bloque como
    //el sonido, strength, etc. Estos parámetros se pueden ver accediendo a IRON_BLOCK. Luego también se pueden
    // overridear como se hace aquí.

    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock(
            "raw_sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    // Como esto es DropExperienceBlock, el UniformInt significa que cuando minemos este bloque vamos a obtener entre 3
    // y 6 de experiencia
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    // Ahora vamos a añadir aquí las variantes de las ores para las diferentes piedras que ofrece el juego
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(5f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> END_STONE_SAPPHIRE_ORE = registerBlock("end_stone_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .strength(5f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock(
            "sound_block", () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    // FURNITURE

    public static final RegistryObject<Block> SAPPHIRE_STAIRS = registerBlock(
            "sapphire_stairs", () -> new StairBlock(() -> ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS)));
    public static final RegistryObject<Block> SAPPHIRE_TRAPDOOR = registerBlock(
            "sapphire_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)
                    .sound(SoundType.AMETHYST)
                    .noOcclusion(), BlockSetType.IRON));

    public static final RegistryObject<Block> SAPPHIRE_DOOR = registerBlock(
            "sapphire_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_DOOR)
                    .sound(SoundType.AMETHYST)
                    .noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<Block> SAPPHIRE_SLAB = registerBlock(
            "sapphire_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_BUTTON = registerBlock(
            "sapphire_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock(
            "sapphire_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST), BlockSetType.IRON));
    public static final RegistryObject<Block> SAPPHIRE_FENCE = registerBlock(
            "sapphire_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_FENCE_GATE = registerBlock(
            "sapphire_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST), SoundEvents.CHAIN_PLACE, SoundEvents.BARREL_CLOSE));
    public static final RegistryObject<Block> SAPPHIRE_WALL = registerBlock(
            "saphire_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockToRegister){
        RegistryObject<T> blockToReturn = BLOCKS.register(name, blockToRegister);
        registerBlockItem(name, blockToReturn);
        return blockToReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
