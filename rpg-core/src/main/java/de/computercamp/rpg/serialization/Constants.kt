package de.computercamp.rpg.serialization

val MAGIC = "KB1000RPGSAVEGAMEv".toByteArray(Charsets.US_ASCII).toList().map { if (it < 1) (Math.abs(it.toInt()) and 256).toShort() else it.toShort() }.toShortArray()
