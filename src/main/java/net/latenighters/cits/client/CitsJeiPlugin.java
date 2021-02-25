package net.latenighters.cits.client;

import com.simibubi.create.Create;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.latenighters.cits.Cits;
import net.latenighters.cits.Registration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


@JeiPlugin
@SuppressWarnings("unused")
public class CitsJeiPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(Cits.MOD_ID, "jei_plugin");

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() { return ID; }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Registration.MORTAR_PESTLE.get()), new ResourceLocation(Create.ID, "milling"));
    }
}
