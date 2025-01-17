package cn.nukkit.inventory;

import cn.nukkit.api.Since;
import cn.nukkit.item.Item;
import java.util.UUID;
import lombok.ToString;

@Since("1.4.0.0-PN")
@ToString
public class MultiRecipe implements Recipe {

    private final UUID id;

    @Since("1.4.0.0-PN")
    public MultiRecipe(UUID id) {
        this.id = id;
    }

    @Override
    public String getRecipeId() {
        return id.toString();
    }

    @Override
    public Item getResult() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerToCraftingManager(CraftingManager manager) {
        manager.registerMultiRecipe(this);
    }

    @Override
    public RecipeType getType() {
        return RecipeType.MULTI;
    }

    @Since("1.4.0.0-PN")
    public UUID getId() {
        return this.id;
    }
}
