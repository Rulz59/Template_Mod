package com.example.mymod.fabric

import com.example.mymod.MyMod
import net.fabricmc.api.ModInitializer

object MyModFabric : ModInitializer {
    override fun onInitialize() {
        MyMod.initCommon()
        // Fabric-specific event hooks
    }
}
