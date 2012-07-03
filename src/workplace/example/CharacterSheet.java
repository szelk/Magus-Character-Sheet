package workplace.example;

import java.util.ArrayList;

import android.content.res.Resources;

public class CharacterSheet {
	int raceID;
	String raceName;
	int casteID;
	String casteName;

	String name;
	int age;

	ArrayList<Property> propertyList;
	//felszerelések és a képzettségek alapból készen vannak (kész objektumot le kéne menteni, majd visszatölteni ha kell)
	ArrayList<Equipment> equipmentList;
	ArrayList<Qualification> qualificationList;

	public CharacterSheet(int raceID, int casteID, int[] abilities, Resources res) {
		raceName = res.getStringArray(R.array.race_names)[raceID];
		casteName = res.getStringArray(R.array.caste_names)[casteID];

		name = "";
		age = 0;

		propertyList = new ArrayList<Property>();
		equipmentList = new ArrayList<Equipment>();
		// Adatbázis hozzáférés is szükséges itt!!

		String base = "Generált alap";
		propertyList.add(new Property("Erõ", PropertyType.Erõ));
		getProperty(PropertyType.Erõ).AddModificatory(new SimpleProperty(base, abilities[0]));
		propertyList.add(new Property("Gyorsaság", PropertyType.Gyorsaság));
		getProperty(PropertyType.Gyorsaság).AddModificatory(new SimpleProperty(base, abilities[1]));
		propertyList.add(new Property("Ügyesség", PropertyType.Ügyesség));
		getProperty(PropertyType.Ügyesség).AddModificatory(new SimpleProperty(base, abilities[2]));
		propertyList.add(new Property("Állóképesség", PropertyType.Állóképesség));
		getProperty(PropertyType.Állóképesség).AddModificatory(new SimpleProperty(base, abilities[3]));
		propertyList.add(new Property("Egészség", PropertyType.Egészség));
		getProperty(PropertyType.Egészség).AddModificatory(new SimpleProperty(base, abilities[4]));
		propertyList.add(new Property("Szépség", PropertyType.Szépség));
		getProperty(PropertyType.Szépség).AddModificatory(new SimpleProperty(base, abilities[5]));
		propertyList.add(new Property("Intelligencia", PropertyType.Intelligencia));
		getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty(base, abilities[6]));
		propertyList.add(new Property("Akaraterõ", PropertyType.Akaraterõ));
		getProperty(PropertyType.Akaraterõ).AddModificatory(new SimpleProperty(base, abilities[7]));
		propertyList.add(new Property("Asztrál", PropertyType.Asztrál));
		getProperty(PropertyType.Asztrál).AddModificatory(new SimpleProperty(base, abilities[8]));

		propertyList.add(new Property("Kezdeményezõ érték", PropertyType.KÉ));
		getProperty(PropertyType.KÉ).AddModificatory(getProperty(PropertyType.Gyorsaság), new Above10());
		getProperty(PropertyType.KÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Támadó érték", PropertyType.TÉ));
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Erõ), new Above10());
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Gyorsaság), new Above10());
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Védekezõ érték", PropertyType.VÉ));
		getProperty(PropertyType.VÉ).AddModificatory(getProperty(PropertyType.Gyorsaság), new Above10());
		getProperty(PropertyType.VÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Célzó érték", PropertyType.CÉ));
		getProperty(PropertyType.CÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Sebzés", PropertyType.Sp));
		getProperty(PropertyType.Sp).AddModificatory(getProperty(PropertyType.Erõ), new Calculation() {
			public int calculate(int value) {
				if (value <= 16) {
					return 0;
				} else {
					return (value - 16);
				}
			}
		});

		propertyList.add(new Property("Mozgásgátló tényezõ", PropertyType.MGT));
		getProperty(PropertyType.Gyorsaság).AddModificatory(getProperty(PropertyType.MGT), new Calculation() {
			public int calculate(int value) {
				return (-value);
			}
		});
		getProperty(PropertyType.Ügyesség).AddModificatory(getProperty(PropertyType.MGT), new Calculation() {
			public int calculate(int value) {
				return (-value);
			}
		});

		propertyList.add(new Property("Sebzés felfogó érték", PropertyType.SFÉ));
		// Páncélzat

		base = "Faj alap";
		int value = res.getIntArray(R.array.ability_ero)[raceID];
		if (value != 0)
			getProperty(PropertyType.Erõ).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_gyorsasag)[raceID];
		if (value != 0)
			getProperty(PropertyType.Gyorsaság).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_ugyesseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Ügyesség).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_allokepesseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Állóképesség).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_egeszseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Egészség).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_szepseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Szépség).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_intelligencia)[raceID];
		if (value != 0)
			getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_akaratero)[raceID];
		if (value != 0)
			getProperty(PropertyType.Akaraterõ).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_asztral)[raceID];
		if (value != 0)
			getProperty(PropertyType.Asztrál).AddModificatory(new SimpleProperty(base, value));

		base = "Kaszt alap";
		value = res.getIntArray(R.array.caste_KE)[casteID];
		if (value != 0)
			getProperty(PropertyType.KÉ).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_TE)[casteID];
		if (value != 0)
			getProperty(PropertyType.TÉ).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_VE)[casteID];
		if (value != 0)
			getProperty(PropertyType.VÉ).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_CE)[casteID];
		if (value != 0)
			getProperty(PropertyType.CÉ).AddModificatory(new SimpleProperty(base, value));

		Equipment e = new Equipment("Varázsital");
		e.AddPropertyModifier(PropertyType.Erõ, 2);
		e.AddPropertyModifier(PropertyType.Asztrál, -2);
		this.AddEquipment(e);
	}

	public Property getProperty(PropertyType type) {
		for (Property property : propertyList) {
			if (property.getPropertyType() == type) {
				return property;
			}
		}
		return null;
	}

	public String getRaceName() {
		return raceName;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return String.format("%s %d éves (%s, %s)", getName(), getAge(), getRaceName(), getCasteName());
	}

	public void AddEquipment(Equipment e) {
		equipmentList.add(e);
		Property[] properties = e.getProperties();
		for (Property property : properties) {
			getProperty(property.getPropertyType()).AddModificatory(property);
		}
	}

	public void Hurting(int damage, DamageType type) {
		// COMMENT: operating with "Heal" class
	}

	enum DamageType {
		Simple, OnlyPain, OnlyHeal
	}
}

enum PropertyType {
	Erõ, Gyorsaság, Ügyesség, Állóképesség, Egészség, Szépség, Intelligencia, Akaraterõ, Asztrál, KÉ, TÉ, VÉ, CÉ, SFÉ, MGT, Sp
}

interface BaseProperty {
	int getValue();

	String getName();

	// void setOnRefreshListener();
}

class SimpleProperty implements BaseProperty {
	String name;
	int baseValue;

	public SimpleProperty(String name, int baseValue) {
		this.name = name;
		this.baseValue = baseValue;
	}

	public int getValue() {
		return baseValue;
	}

	public String getName() {
		return name;
	}
}

class Property implements BaseProperty {
	String name;
	PropertyType type;
	ArrayList<BaseProperty> propertyList;
	ArrayList<Calculation> calculationList;

	public Property(String name, PropertyType type) {
		this.name = name;
		this.type = type;
		this.propertyList = new ArrayList<BaseProperty>();
		this.calculationList = new ArrayList<Calculation>();
	}

	public int getValue() {
		int sum = 0;

		for (int i = 0; i < propertyList.size(); i++) {
			Calculation c = calculationList.get(i);
			int value = propertyList.get(i).getValue();
			if (c == null) {
				sum += value;
			} else {
				sum += c.calculate(value);
			}
		}

		return sum;
	}

	public PropertyType getPropertyType() {
		return type;
	}

	public void AddModificatory(BaseProperty p, Calculation c) {
		propertyList.add(p);
		calculationList.add(c);
	}

	public void AddModificatory(BaseProperty p) {
		AddModificatory(p, null);
	}

	public String getSubProperties() {
		String sum = "";
		for (int i = 0; i < propertyList.size(); i++) {
			Calculation c = calculationList.get(i);

			int value = propertyList.get(i).getValue();
			if (c != null) {
				value = c.calculate(value);
			}
			if (value != 0) {
				sum += propertyList.get(i).getName() + ": ";
				sum += String.valueOf(value);
				sum += "\n";
			}
		}
		return sum;
	}

	public String getName() {
		return name;
	}
}

class Qualification implements BaseProperty {
	String qualificationName;
	int qualificationID;

	public Qualification(int qualificationID) {

	}

	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}

enum QualificationType {
	Percentage, Grade
}

interface Calculation {
	int calculate(int value);
}

class Above10 implements Calculation {

	public int calculate(int value) {
		if (value <= 10) {
			return 0;
		} else {
			return (value - 10);
		}
	}

}

interface OnRefreshListener {
	void OnRefresh();
}

class Equipment {
	String name;
	ArrayList<Property> propertyList;

	public Equipment(String name) {
		this.name = name;
		this.propertyList = new ArrayList<Property>();
	}

	public void AddPropertyModifier(PropertyType type, int value) {
		Property property = new Property(name, type);
		property.AddModificatory(new SimpleProperty(name, value));
		propertyList.add(property);
	}

	public Property[] getProperties() {
		return propertyList.toArray(new Property[0]);
	}
}