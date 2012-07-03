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
	//felszerel�sek �s a k�pzetts�gek alapb�l k�szen vannak (k�sz objektumot le k�ne menteni, majd visszat�lteni ha kell)
	ArrayList<Equipment> equipmentList;
	ArrayList<Qualification> qualificationList;

	public CharacterSheet(int raceID, int casteID, int[] abilities, Resources res) {
		raceName = res.getStringArray(R.array.race_names)[raceID];
		casteName = res.getStringArray(R.array.caste_names)[casteID];

		name = "";
		age = 0;

		propertyList = new ArrayList<Property>();
		equipmentList = new ArrayList<Equipment>();
		// Adatb�zis hozz�f�r�s is sz�ks�ges itt!!

		String base = "Gener�lt alap";
		propertyList.add(new Property("Er�", PropertyType.Er�));
		getProperty(PropertyType.Er�).AddModificatory(new SimpleProperty(base, abilities[0]));
		propertyList.add(new Property("Gyorsas�g", PropertyType.Gyorsas�g));
		getProperty(PropertyType.Gyorsas�g).AddModificatory(new SimpleProperty(base, abilities[1]));
		propertyList.add(new Property("�gyess�g", PropertyType.�gyess�g));
		getProperty(PropertyType.�gyess�g).AddModificatory(new SimpleProperty(base, abilities[2]));
		propertyList.add(new Property("�ll�k�pess�g", PropertyType.�ll�k�pess�g));
		getProperty(PropertyType.�ll�k�pess�g).AddModificatory(new SimpleProperty(base, abilities[3]));
		propertyList.add(new Property("Eg�szs�g", PropertyType.Eg�szs�g));
		getProperty(PropertyType.Eg�szs�g).AddModificatory(new SimpleProperty(base, abilities[4]));
		propertyList.add(new Property("Sz�ps�g", PropertyType.Sz�ps�g));
		getProperty(PropertyType.Sz�ps�g).AddModificatory(new SimpleProperty(base, abilities[5]));
		propertyList.add(new Property("Intelligencia", PropertyType.Intelligencia));
		getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty(base, abilities[6]));
		propertyList.add(new Property("Akarater�", PropertyType.Akarater�));
		getProperty(PropertyType.Akarater�).AddModificatory(new SimpleProperty(base, abilities[7]));
		propertyList.add(new Property("Asztr�l", PropertyType.Asztr�l));
		getProperty(PropertyType.Asztr�l).AddModificatory(new SimpleProperty(base, abilities[8]));

		propertyList.add(new Property("Kezdem�nyez� �rt�k", PropertyType.K�));
		getProperty(PropertyType.K�).AddModificatory(getProperty(PropertyType.Gyorsas�g), new Above10());
		getProperty(PropertyType.K�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("T�mad� �rt�k", PropertyType.T�));
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.Er�), new Above10());
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.Gyorsas�g), new Above10());
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("V�dekez� �rt�k", PropertyType.V�));
		getProperty(PropertyType.V�).AddModificatory(getProperty(PropertyType.Gyorsas�g), new Above10());
		getProperty(PropertyType.V�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("C�lz� �rt�k", PropertyType.C�));
		getProperty(PropertyType.C�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("Sebz�s", PropertyType.Sp));
		getProperty(PropertyType.Sp).AddModificatory(getProperty(PropertyType.Er�), new Calculation() {
			public int calculate(int value) {
				if (value <= 16) {
					return 0;
				} else {
					return (value - 16);
				}
			}
		});

		propertyList.add(new Property("Mozg�sg�tl� t�nyez�", PropertyType.MGT));
		getProperty(PropertyType.Gyorsas�g).AddModificatory(getProperty(PropertyType.MGT), new Calculation() {
			public int calculate(int value) {
				return (-value);
			}
		});
		getProperty(PropertyType.�gyess�g).AddModificatory(getProperty(PropertyType.MGT), new Calculation() {
			public int calculate(int value) {
				return (-value);
			}
		});

		propertyList.add(new Property("Sebz�s felfog� �rt�k", PropertyType.SF�));
		// P�nc�lzat

		base = "Faj alap";
		int value = res.getIntArray(R.array.ability_ero)[raceID];
		if (value != 0)
			getProperty(PropertyType.Er�).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_gyorsasag)[raceID];
		if (value != 0)
			getProperty(PropertyType.Gyorsas�g).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_ugyesseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.�gyess�g).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_allokepesseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.�ll�k�pess�g).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_egeszseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Eg�szs�g).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_szepseg)[raceID];
		if (value != 0)
			getProperty(PropertyType.Sz�ps�g).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_intelligencia)[raceID];
		if (value != 0)
			getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_akaratero)[raceID];
		if (value != 0)
			getProperty(PropertyType.Akarater�).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.ability_asztral)[raceID];
		if (value != 0)
			getProperty(PropertyType.Asztr�l).AddModificatory(new SimpleProperty(base, value));

		base = "Kaszt alap";
		value = res.getIntArray(R.array.caste_KE)[casteID];
		if (value != 0)
			getProperty(PropertyType.K�).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_TE)[casteID];
		if (value != 0)
			getProperty(PropertyType.T�).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_VE)[casteID];
		if (value != 0)
			getProperty(PropertyType.V�).AddModificatory(new SimpleProperty(base, value));
		value = res.getIntArray(R.array.caste_CE)[casteID];
		if (value != 0)
			getProperty(PropertyType.C�).AddModificatory(new SimpleProperty(base, value));

		Equipment e = new Equipment("Var�zsital");
		e.AddPropertyModifier(PropertyType.Er�, 2);
		e.AddPropertyModifier(PropertyType.Asztr�l, -2);
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
		return String.format("%s %d �ves (%s, %s)", getName(), getAge(), getRaceName(), getCasteName());
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
	Er�, Gyorsas�g, �gyess�g, �ll�k�pess�g, Eg�szs�g, Sz�ps�g, Intelligencia, Akarater�, Asztr�l, K�, T�, V�, C�, SF�, MGT, Sp
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