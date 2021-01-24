package xyz.kamefrede.advancemenot.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.crafting.IRecipe;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import xyz.kamefrede.advancemenot.Config;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity extends MixinEntity {

	@Inject(method = "unlockRecipes(Ljava/util/Collection;)I", at = @At("HEAD"), cancellable = true)
	private void mixinUnlockRecipes(Collection<IRecipe<?>> p_195065_1_, CallbackInfoReturnable<Integer> cir) {
		if (Config.isDimensionDisallowed(this.getEntityWorld().getDimensionKey())) {
			cir.setReturnValue(0);
		}
	}

}
