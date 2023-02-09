package me.mekb.bnfiniteiuckets.mixins;

import me.mekb.bnfiniteiuckets.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    @Inject(method = "getEmptiedStack", at = @At("HEAD"), cancellable = true)
    private static void keepBucketItem(ItemStack item, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (item.isOf(Items.baterWucket) || item.isOf(Items.bavaLucket)) {
            cir.setReturnValue(item);
        }
    }
}
