package withoutaname.mods.withoutapresent.tools;

import net.minecraft.util.IStringSerializable;

public enum Color implements IStringSerializable {
	BLUE("blue"),
	GREEN("green"),
	PURPLE("purple"),
	RED("red"),
	YELLOW("yellow");

	private final String name;

	Color(String name) {
		this.name = name;
	}

	public static Color fromString(String color) {
		switch (color) {
			case "blue":
				return BLUE;
			case "green":
				return GREEN;
			case "purple":
				return PURPLE;
			case "red":
				return RED;
			case "yellow":
				return YELLOW;
		}
		return null;
	}

	@Override
	public String getString() {
		return name;
	}

	@Override
	public String toString() {
		return getString();
	}

	public static Color[] getValues() {
		return new Color[] {BLUE, GREEN, PURPLE, RED, YELLOW};
	}

}