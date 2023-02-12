package me.mekb.bnfiniteiuckets;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BnfiniteIuckets implements ModInitializer {
	public static String modName = "bnfiniteiuckets";
	public static final Logger logger = LoggerFactory.getLogger(modName);

	@Override
	public void onInitialize() {
		Items.registerItems();
	}
}
