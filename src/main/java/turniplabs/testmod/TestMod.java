package turniplabs.testmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.testmod.block.*;

public class TestMod implements ModInitializer {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static int _ID = 1000;

    //Blocks
    public static final Block oreFossilStone = BlockHelper.createBlock(MOD_ID, new FossilOreBlock(_ID, Material.rock), "fossiloreblock",
            "fossiloreblock.png",Block.soundStoneFootstep, 3f, 4f, 0);

    //WorldGen
    public static final WorldGenerator fossilOreGen = new WorldGenMinable(oreFossilStone.blockID,
            5, false);

    @Override
    public void onInitialize() {


    }
}
