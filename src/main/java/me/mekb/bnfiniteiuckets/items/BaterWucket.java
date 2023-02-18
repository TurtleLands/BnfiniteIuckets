package me.mekb.bnfiniteiuckets.items;

import com.google.gson.JsonObject;
import me.mekb.bnfiniteiuckets.Items;
import me.mekb.bnfiniteiuckets.Recipe;
import me.mekb.bnfiniteiuckets.misc.BaterWucketStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class BaterWucket extends BucketItem {
    public BaterWucket() {
        super(Fluids.WATER, new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
        FluidStorage.ITEM.registerForItems((stack, ctx) -> new BaterWucketStorage(), this);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static void registerCauldronBehavior() {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(Items.baterWucket, (state, world, pos, player, hand, stack) -> {
            if (world.isClient) return ActionResult.SUCCESS;

            world.setBlockState(pos, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
            player.incrementStat(Stats.FILL_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

            return ActionResult.SUCCESS;
        });
    }

    public static JsonObject getRecipe(String id) {
        return Recipe.getRecipe(Recipe.RecipeType.Shaped, new String[][] {
            { "minecraft:water_bucket", null, "minecraft:water_bucket" },
            {  null, "minecraft:water_bucket", null },
        }, id, 1);
    }
}
