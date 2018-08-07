#!/usr/bin/env python3
with open("src/de/computercamp/rpg/resources/MessageBundle.properties", "rt", encoding="utf-8") as fp:
    names = []
    for line in fp:
        name = line.partition("=")[0]
        names.append(name)

with open("src/de/computercamp/rpg/resources/Messages.java", "wt", encoding="utf-8") as fp:
    fragment1 = "\n".join(f"    public static String {name};" for name in names)
    fragment2 = "\n".join(f"        {name} = bundle.getString(\"{name}\");" for name in names)
    fp.write(f"""package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {{
    private static ResourceBundle bundle;
    public static Locale locale;

{fragment1}

    private Messages() {{
    }}

    private static void reloadStrings() {{
        bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", locale);
{fragment2}
    }}

    static {{
        locale = Locale.getDefault();
        reloadStrings();
    }}

    public static void changeLanguage(Locale locale) {{
        Messages.locale = locale;
        reloadStrings();
    }}
}}
""")
