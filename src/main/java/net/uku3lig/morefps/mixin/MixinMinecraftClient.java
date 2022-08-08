package net.uku3lig.morefps.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @ModifyConstant(method = "render", constant = @Constant(intValue = 260))
    public int modifyMaxFps(int max) {
        return 1000;
    }
}
