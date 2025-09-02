package com.example.mymod

object MyMod {
    const val MOD_ID = "mymod"

    fun initCommon() {
        println("Common init for $MOD_ID")
        // Shared setup: register items, blocks, networking abstractions
    }
}
