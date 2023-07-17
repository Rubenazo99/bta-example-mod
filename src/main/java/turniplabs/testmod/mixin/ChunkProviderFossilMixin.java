package turniplabs.testmod.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.testmod.TestMod;
import turniplabs.testmod.block.FossilOreBlock;

import java.util.Random;

@Mixin(value = ChunkProviderGenerateOverworld.class, remap = false)
public class ChunkProviderFossilMixin {

    private Random fossilRNG = new Random();

    @Shadow
    private NoiseGeneratorOctaves field_912_k;

    @Shadow
    private World worldObj;

    private WorldGenerator ore = TestMod.fossilOreGen;

    @Inject(method = "populate", at = @At(value = "RETURN"))
    private void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ, CallbackInfo ci) {

        // Chunk positions in blocks, at the corner of the chunk
        int preX = chunkX * 16;
        int preZ = chunkZ * 16;

        // Noise
        int magicValue = (int)((field_912_k.func_806_a((double)preX / 2, (double)preZ / 2) / 8.0 + fossilRNG.nextDouble() * 4.0 + 4.0) / 3.0);
        if (magicValue < 0) {
            magicValue = 0;
            TestMod.LOGGER.debug("Magic value: " + magicValue);
        }

        for (int i = 0; i < magicValue; ++i) {

            // 1% chance for the ore to generate
            if (!FossilOreBlock.probability(fossilRNG, 10)) {
                continue;
            }

            TestMod.LOGGER.debug("Fossil generated");
            int x = preX + fossilRNG.nextInt(16) + 8;
            int y = fossilRNG.nextInt(256);
            int z = preZ + fossilRNG.nextInt(16) + 8;
            ore.func_517_a(1.0, 1.0, 1.0);
            ore.generate(worldObj, fossilRNG, x, y, z);
        }
    }
}

