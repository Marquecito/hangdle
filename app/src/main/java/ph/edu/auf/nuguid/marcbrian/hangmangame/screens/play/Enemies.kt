package ph.edu.auf.nuguid.marcbrian.hangmangame.screens.play

val Enemies = listOf(
    Enemy(
        name = "Goblin",
        attack = 20,
        maxHealth = 20,
        words = listOf(
            "Sneaky", "Greedy", "Nasty", "Ambush", "Feral", 
            "Filthy", "Cunning", "Shifty", "Crafty", "Pillage",
            "Rogue", "Goblin", "Grimy", "Scuttle", "Rabble",
            "Rascal", "Bully", "Bicker", "Sneer", "Sneak",
            "Grasper", "Wily", "Shifty", "Nutter", "Whittle"
        )
    ),
    Enemy(
        name = "Gobbler",
        attack = 40,
        maxHealth = 40,
        words = listOf(
            "Hungry", "Glutton", "Devour", "Beastly", "Rabble",
            "Feast", "Guzzle", "Gobble", "Nibble",
            "Snatch", "Satiate", "Indulge", "Greedy", "Diner",
            "Gourmet", "Snack", "Taster", "Binge", "Porker", "Craving",
            "Savor", "Feeder", "Chomp", "Relish", "Overeat"
        )
    ),
    Enemy(
        name = "Orc",
        attack = 40,
        maxHealth = 80,
        words = listOf(
            "Savage", "Brutal", "Raider", "Berserk", "Grunt", 
            "Warrior", "Feral", "Mighty", "Crude",
            "Fighter", "Gnasher", "Sturdy", "Brawler", "Rancor",
            "Brawler", "Brawler", "Ravage", "Giant", "Warlord",
            "Terror", "Troop", "Conquer", "Havoc", "Rumble"
        )
    ),
    Enemy(
        name = "Troll",
        attack = 40,
        maxHealth = 160,
        words = listOf(
            "Giant", "Hulking", "Clubber", "Stoner", 
            "Bridge", "Bully", "Brawn", "Boggle", "Brawl", "Smash",
            "Fumble", "Grumpy", "Shatter",
            "Cranky", "Mighty", "Pummel", "Rumble",
            "Bashful", "Brutish", "Clumsy", "Brawny"
        )
    ),
    Enemy(
        name = "Necromancer",
        attack = 50,
        maxHealth = 320,
        words = listOf(
            "Darken", "Raise", "Curse", "Sorcery", "Wraith",
            "Unholy", "Gloomy", "Fright", "Casket", "Spooky", "Phantom",
            "Haunted", "Spirit",
            "Shadowy", "Sorrow", "Chill", "Eerie",
            "Miasma", "Shroud", "Cursed", "Ghostly", "Veil"
        )
    ),
    Enemy(
        name = "Dragon",
        attack = 100,
        maxHealth = 500,
        words = listOf(
            "Scales", "Flame", "Winged", "Terror", "Inferno", "Clawed",
            "Fierce", "Mythic", "Titanic",
            "Roaring", "Fang", "Ancient", "Hoard", "Serpent", "Flying",
            "Beastly", "Scaled", "Wyrm", "Havoc", 
            
            "Scorching"
        )
    ),
    Enemy(
        name = "Dark Elf",
        attack = 125,
        maxHealth = 550,
        words = listOf(
            "Shadow", "Swift", "Agile", "Mystic", "Poison", "Blade",
            "Elven", "Silent", "Cunning", "Rogue", "Whisper",
            "Dagger", "Vile", "Shifty", "Arcane", "Hollow", "Flicker",
            "Fey", "Enchant", "Wicked", "Cloak", "Dread",
            "Sleight", "Tricksy", "Ravish", "Subtle", "Cunning"
        )
    ),
    Enemy(
        name = "Lich King",
        attack = 150,
        maxHealth = 600,
        words = listOf(
            "Frozen", "Grave", "Undead", "Crown", "Ruler", "Frost",
            "Dread", "Cursed", "Phantom", "Shade", "Gloom",
            "Risen", "Scepter", "Throne", "Darkly", "Majesty",
            "Vile", "Haunted", "Spirit", "Chill", "Grim", "Doomed",
            "Mourn", "Fallen", "Cloaked"
        )
    ),
    Enemy(
        name = "Demon Lord",
        attack = 200,
        maxHealth = 700,
        words = listOf(
            "Inferno", "Fiery", "Hellish", "Wrath", "Horned", "Abyss",
            "Chaos", "Satanic", "Wicked",
            "Dreaded", "Demonic", "Malice", "Spawn", "Vile", "Fury",
            "Ruinous", "Malevol", "Tempest", "Havoc", 
            "Cursed", "Savage", "Ravage", "Unholy", "Horrid"
        )
    ),
    Enemy(
        name = "The Herb",  // Final boss
        attack = 250,
        maxHealth = 1000,
        words = listOf(
            "Toxic", "Venom", "Spines", "Poison", "Thorny",
            "Deadly", "Blight", "Fungal", "Decay", "Stench", "Noxious",
            "Putrid", "Gremlin", "Foul",
            "Miasma", "Lethal", "Shrivel", "Wither",
            "Dread", "Vile", "Noxious", "Malign"
        )
    )
)