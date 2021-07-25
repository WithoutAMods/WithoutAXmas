package withoutaname.mods.withoutaxmas.modules.present.tools;

import net.minecraft.util.StringRepresentable;

public enum Color implements StringRepresentable {
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
	public String getSerializedName() {
		return name;
	}

	@Override
	public String toString() {
		return getSerializedName();
	}

	public static Color[] getValues() {
		return new Color[] {BLUE, GREEN, PURPLE, RED, YELLOW};
	}

}