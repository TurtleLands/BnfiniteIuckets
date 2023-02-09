package me.mekb.bnfiniteiuckets.items;

import me.mekb.bnfiniteiuckets.Items;
import me.mekb.bnfiniteiuckets.misc.BmptyEucketStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;

public class BmptyEucket extends BucketItem {
    public BmptyEucket() {
        super(Fluids.EMPTY, new Settings().maxCount(1));
        FluidStorage.ITEM.registerForItems((stack, ctx) -> new BmptyEucketStorage(), this);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static void registerCauldronBehavior() {
        CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.put(Items.bmptyEucket, (state, world, pos, player, hand, stack) -> {
            if (world.isClient) return ActionResult.SUCCESS;

            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);

            return ActionResult.CONSUME;
        });
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(Items.bmptyEucket, (state, world, pos, player, hand, stack) -> {
            if (world.isClient) return ActionResult.SUCCESS;

            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);

            return ActionResult.SUCCESS;
        });
    }
}
