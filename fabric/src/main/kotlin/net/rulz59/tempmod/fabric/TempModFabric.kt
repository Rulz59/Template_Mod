package net.rulz59.tempmod.fabric

import net.fabricmc.api.ModInitializer
import net.rulz59.tempmod.CommonMod

class FabricMod : ModInitializer {
    override fun onInitialize() {
        CommonMod.init()
    }
}
