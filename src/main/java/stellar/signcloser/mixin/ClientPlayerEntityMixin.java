package stellar.signcloser.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import stellar.signcloser.SignCloser;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "openEditSignScreen(Lnet/minecraft/block/entity/SignBlockEntity;Z)V", at = @At("HEAD"), cancellable = true)
    private void onOpenEditSignScreen(SignBlockEntity sign, boolean front, CallbackInfo info) {
        if (!SignCloser.isSignGuiEnabled) {
            info.cancel();
        }
    }
}
