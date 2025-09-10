package com.example.mymod.neoforge

// Import the common mod class from your shared project.
import com.example.mymod.MyMod

// Import the NeoForge Mod annotation. This is the key to registering your mod.
// The "unresolved reference" error came from this line not being able to find
// the 'net' package because the dependency was missing in the build.gradle.kts file.
import net.neoforged.fml.common.Mod

/**
 * This is the main class for your NeoForge mod. It's the entry point where
 * the game will load your mod and where you can register things like items,
 * blocks, and events.
 *
 * The `@Mod` annotation tells NeoForge your mod's ID.
 */
@Mod(MyMod.MOD_ID)
class MyModNeoForge {

    // This is the constructor for your mod class. NeoForge creates an instance of
    // this class when your mod is loaded.
    init {
        // We can call the common initialization code here.
        MyMod.initCommon()

        // You can add NeoForge-specific initialization or event registrations here.
        // For now, this is all you need to get the mod to load.
    }
}
