package me.mekb.bnfiniteiuckets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class Recipe {
    public enum RecipeType {
        Shaped, Shapeless
    }

    public static JsonObject getRecipe(@NotNull RecipeType type, String[][] recipe, @NotNull String resultItem, int resultCount) {
        JsonObject json = new JsonObject();
        json.addProperty("type", type == RecipeType.Shaped ? "minecraft:crafting_shaped" : "minecraft:crafting_shapeless");
        HashMap<String, Character> keys = new HashMap<>();
        JsonArray pattern = new JsonArray();
        JsonObject key = new JsonObject();
        char letterI = 'a';
        for (String[] column : recipe) {
            StringBuilder row = new StringBuilder();
            for (String item : column) {
                if (item == null) {
                    row.append(" ");
                    continue;
                }
                if (!keys.containsKey(item)) {
                    keys.put(item, letterI);
                    JsonObject keyI = new JsonObject();
                    if (item.startsWith("#")) {
                        keyI.addProperty("tag", item.substring(1));
                    } else {
                        keyI.addProperty("item", item);
                    }
                    key.add(letterI + "", keyI);
                    letterI++;
                }
                char letter = keys.get(item);
                row.append(letter);
            }
            pattern.add(row.toString());
        }
        JsonObject result = new JsonObject();
        result.addProperty("item", resultItem);
        result.addProperty("count", resultCount);
        json.add("pattern", pattern);
        json.add("key", key);
        json.add("result", result);
        return json;
    }
}
