package net.uku3lig.morefps.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.Option;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.Control;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(SodiumGameOptionPages.class)
public class MixinSodiumGameOptionPages {
    @Redirect(method = "general", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl$Builder;setControl(Ljava/util/function/Function;)Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl$Builder;", ordinal = 6), remap = false, require = 0)
    private static OptionImpl.Builder<GameOptions, Integer> increaseMaxFpsLimit(OptionImpl.Builder<GameOptions, Integer> instance, Function<Option<Integer>, Control<Integer>> control) {
        return instance.setControl(option -> new SliderControl(option, 10, 1000, 10, formatter()));
    }

    private static ControlValueFormatter formatter() {
        return v -> (v == 1000) ? Text.translatable("options.framerateLimit.max").getString() : Text.translatable("options.framerate", v).getString();
    }

    private MixinSodiumGameOptionPages() {}
}
