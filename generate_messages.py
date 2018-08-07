#!/usr/bin/env python3
with open("src/de/computercamp/rpg/resources/MessageBundle.properties", "rt", encoding="utf-8") as fp:
    names = []
    for line in fp:
        name = line.partition("=")[0]
        names.append(name)

with open("src/de/computercamp/rpg/resources/Messages.java", "wt", encoding="utf-8") as fp:
    fragment = "\n".join(f"    public static String {name} = bundle.getString(\"{name}\");" for name in names)
    fp.write(f"""package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {{
    private static ResourceBundle bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", Locale.getDefault());

{fragment}

    private Messages() {{
    }}
}}
""")
