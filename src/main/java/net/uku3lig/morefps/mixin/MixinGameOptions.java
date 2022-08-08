package net.uku3lig.morefps.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.client.option.GameOptions.getGenericValueText;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    private final SimpleOption<Integer> maxFps = new SimpleOption<>("options.framerateLimit", SimpleOption.emptyTooltip(),
            (optionText, value) -> getGenericValueText(optionText, (value == 1000 ? Text.translatable("options.framerateLimit.max") : Text.translatable("options.framerate", value))),
            new SimpleOption.ValidatingIntSliderCallbacks(1, 100).withModifier(value -> value * 10, value -> value / 10),
            Codec.intRange(10, 1000), 120,
            value -> MinecraftClient.getInstance().getWindow().setFramerateLimit(value));

    @Inject(method = "getMaxFps", at = @At("RETURN"), cancellable = true)
    public void moreFps(CallbackInfoReturnable<SimpleOption<Integer>> cir) {
        cir.setReturnValue(maxFps);
    }
}
