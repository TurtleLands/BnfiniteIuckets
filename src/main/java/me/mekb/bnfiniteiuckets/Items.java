package me.mekb.bnfiniteiuckets;

import com.google.gson.JsonElement;
import me.mekb.bnfiniteiuckets.items.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static JsonElement baterWucketRecipe;
    public static JsonElement bavaLucketRecipe;
    public static JsonElement bmptyEucketRecipe;

    private Items() {}
    public static BaterWucket baterWucket;
    public static BavaLucket bavaLucket;
    public static BmptyEucket bmptyEucket;

    public static void registerItems() {
        baterWucket = new BaterWucket();
        bavaLucket  = new BavaLucket();
        bmptyEucket = new BmptyEucket();
        BaterWucket.registerCauldronBehavior();
        BavaLucket .registerCauldronBehavior();
        BmptyEucket.registerCauldronBehavior();
        Registry.register(Registry.ITEM, new Identifier(BnfiniteIuckets.modName, "bater_wucket"), Items.baterWucket);
        Registry.register(Registry.ITEM, new Identifier(BnfiniteIuckets.modName, "bava_lucket"),  Items.bavaLucket);
        Registry.register(Registry.ITEM, new Identifier(BnfiniteIuckets.modName, "bmpty_eucket"), Items.bmptyEucket);
        baterWucketRecipe = BaterWucket.getRecipe(BnfiniteIuckets.modName + ":bater_wucket");
        bavaLucketRecipe  = BavaLucket .getRecipe(BnfiniteIuckets.modName + ":bava_lucket");
        bmptyEucketRecipe = BmptyEucket.getRecipe(BnfiniteIuckets.modName + ":bmpty_eucket");
    }
}
