package com.example.mymod.neoforge

import com.example.mymod.MyMod
import net.neoforged.fml.common.Mod

@Mod(MyMod.MOD_ID)
class MyModNeoForge {
    init {
        MyMod.initCommon()
        // NeoForge-specific event hooks
    }
}
