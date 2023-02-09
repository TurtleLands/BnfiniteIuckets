package me.mekb.bnfiniteiuckets;

import me.mekb.bnfiniteiuckets.items.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class Items {
    private Items() {}
    public static BaterWucket baterWucket;
    public static BavaLucket bavaLucket;
    public static BmptyEucket bmptyEucket;

    public static void registerItems() {
        baterWucket = new BaterWucket();
        bavaLucket = new BavaLucket();
        bmptyEucket = new BmptyEucket();
        BaterWucket.registerCauldronBehavior();
        BavaLucket.registerCauldronBehavior();
        BmptyEucket.registerCauldronBehavior();
        Registry.register(Registry.ITEM, new Identifier("bnfiniteiuckets", "bater_wucket"), Items.baterWucket);
        Registry.register(Registry.ITEM, new Identifier("bnfiniteiuckets", "bava_lucket"), Items.bavaLucket);
        Registry.register(Registry.ITEM, new Identifier("bnfiniteiuckets", "bmpty_eucket"), Items.bmptyEucket);
    }
}
