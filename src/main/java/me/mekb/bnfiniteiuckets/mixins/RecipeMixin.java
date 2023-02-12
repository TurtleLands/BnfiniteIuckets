package me.mekb.bnfiniteiuckets.mixins;

import com.google.gson.JsonElement;
import me.mekb.bnfiniteiuckets.BnfiniteIuckets;
import me.mekb.bnfiniteiuckets.Items;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        if (Items.baterWucketRecipe != null) {
            BnfiniteIuckets.logger.info("Initialising Bater Wucket recipe");
            map.put(new Identifier(BnfiniteIuckets.modName, "bater_wucket"), Items.baterWucketRecipe);
        }
        if (Items.bavaLucketRecipe != null) {
            BnfiniteIuckets.logger.info("Initialising Bava Lucket recipe");
            map.put(new Identifier(BnfiniteIuckets.modName, "bava_lucket"), Items.bavaLucketRecipe);
        }
        if (Items.bmptyEucketRecipe != null) {
            BnfiniteIuckets.logger.info("Initialising Bmpty Eucket recipe");
            map.put(new Identifier(BnfiniteIuckets.modName, "bmpty_eucket"), Items.bmptyEucketRecipe);
        }
    }
}
