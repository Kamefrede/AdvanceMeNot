package xyz.kamefrede.advancemenot.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import xyz.kamefrede.advancemenot.Config;

@Mixin(PlayerAdvancements.class)
public class MixinPlayersAdvancement {

	@Shadow
	private ServerPlayerEntity player;

	@Inject(method = "unlockDefaultAdvancements(Lnet/minecraft/advancements/AdvancementManager;)V", at = @At("HEAD"), cancellable = true)
	private void mixinUnlockDefaultAdvancements(AdvancementManager manager, CallbackInfo ci) {
		if (Config.isDimensionDisallowed(this.player.getEntityWorld().getDimensionKey())) {
			ci.cancel();
		}
	}

	@Inject(method = "grantCriterion(Lnet/minecraft/advancements/Advancement;Ljava/lang/String;)Z", at = @At("HEAD"), cancellable = true)
	private void mixinGrantCriterion(Advancement advancement, String criterionKey, CallbackInfoReturnable<Boolean> ci) {
		if (Config.isDimensionDisallowed(this.player.getEntityWorld().getDimensionKey())) {
			ci.setReturnValue(false);
		}
	}
}
