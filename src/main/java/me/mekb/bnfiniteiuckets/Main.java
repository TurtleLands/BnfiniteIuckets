package me.mekb.bnfiniteiuckets;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		Items.registerItems();
	}
}
