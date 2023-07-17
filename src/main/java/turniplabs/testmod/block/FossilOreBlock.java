package turniplabs.testmod.block;

import net.minecraft.src.*;

import java.util.Random;

public class FossilOreBlock extends Block
{

    public FossilOreBlock(int i, Material material) {
        super(i, material);
    }

    public static boolean probability(Random rand, double percent) {
        return true; // percent > 0 && rand.nextInt(100) <= percent;
    }

    @Override
    public int idDropped(int i, Random random) {
        return Item.bone.itemID;
    }

    @Override
    public int quantityDropped(int metadata, Random random) {
        return 2 + random.nextInt(2);
    }
}
