package me.mekb.bnfiniteiuckets.items;

import com.google.gson.JsonObject;
import me.mekb.bnfiniteiuckets.Items;
import me.mekb.bnfiniteiuckets.Recipe;
import me.mekb.bnfiniteiuckets.misc.BavaLucketStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.world.event.GameEvent;

public class BavaLucket extends BucketItem {
    public BavaLucket() {
        super(Fluids.LAVA, new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
        FluidStorage.ITEM.registerForItems((stack, ctx) -> new BavaLucketStorage(), this);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static void registerCauldronBehavior() {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(Items.bavaLucket, (state, world, pos, player, hand, stack) -> {
            if (world.isClient) return ActionResult.SUCCESS;

            world.setBlockState(pos, Blocks.LAVA_CAULDRON.getDefaultState());
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

            return ActionResult.SUCCESS;
        });
    }

    public static JsonObject getRecipe(String id) {
        return Recipe.getRecipe(Recipe.RecipeType.Shaped, new String[][] {
                { "minecraft:lava_bucket", null, "minecraft:lava_bucket" },
                {  null, "minecraft:lava_bucket", null },
        }, id, 1);
    }
}
