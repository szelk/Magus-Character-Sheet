package workplace.example;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class CharacterSheet {
	ArrayList<Property> propertyList;
	ArrayList<Equipment> equipmentList;

	// property-nek kéne egy õsosztály, ami csak értétkeket tárol
	public CharacterSheet() {
		propertyList = new ArrayList<Property>();
		equipmentList = new ArrayList<Equipment>();
		// Generals
		int[] array = new int[] { 12, 13, 12, 11, 12, 13, 12, 11, 12 };
		int[] array2 = new int[] { 5, 30, 70, 10 };

		// Adatbázis hozzáférés is szükséges itt!!

		propertyList.add(new Property("Erõ", PropertyType.Erõ));
		getProperty(PropertyType.Erõ).AddModificatory(new SimpleProperty("alap", array[0]));
		propertyList.add(new Property("Gyorsaság", PropertyType.Gyorsaság));
		getProperty(PropertyType.Gyorsaság).AddModificatory(new SimpleProperty("alap", array[1]));
		propertyList.add(new Property("Ügyesség", PropertyType.Ügyesség));
		getProperty(PropertyType.Ügyesség).AddModificatory(new SimpleProperty("alap", array[2]));
		propertyList.add(new Property("Állóképesség", PropertyType.Állóképesség));
		getProperty(PropertyType.Állóképesség).AddModificatory(new SimpleProperty("alap", array[3]));
		propertyList.add(new Property("Egészség", PropertyType.Egészség));
		getProperty(PropertyType.Egészség).AddModificatory(new SimpleProperty("alap", array[4]));
		propertyList.add(new Property("Szépség", PropertyType.Szépség));
		getProperty(PropertyType.Szépség).AddModificatory(new SimpleProperty("alap", array[5]));
		propertyList.add(new Property("Intelligencia", PropertyType.Intelligencia));
		getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty("alap", array[6]));
		propertyList.add(new Property("Akaraterõ", PropertyType.Akaraterõ));
		getProperty(PropertyType.Akaraterõ).AddModificatory(new SimpleProperty("alap", array[7]));
		propertyList.add(new Property("Asztrál", PropertyType.Asztrál));
		getProperty(PropertyType.Asztrál).AddModificatory(new SimpleProperty("alap", array[8]));

		propertyList.add(new Property("Kezdeményezõ érték", PropertyType.KÉ));
		getProperty(PropertyType.KÉ).AddModificatory(new SimpleProperty("Kaszt alap", array2[0]));
		getProperty(PropertyType.KÉ).AddModificatory(getProperty(PropertyType.Gyorsaság), new Above10());
		getProperty(PropertyType.KÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Támadó érték", PropertyType.TÉ));
		getProperty(PropertyType.TÉ).AddModificatory(new SimpleProperty("Kaszt alap", array2[1]));
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Erõ), new Above10());
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Gyorsaság), new Above10());
		getProperty(PropertyType.TÉ).AddModificatory(getProperty(PropertyType.Ügyesség), new Above10());

		propertyList.add(new Property("Védekezõ érték", PropertyType.VÉ));
		getProperty(PropertyType.VÉ).AddModificatory(new SimpleProperty("Kaszt alap", array2[2]));

		propertyList.add(new Property("Célzó érték", PropertyType.CÉ));
		getProperty(PropertyType.CÉ).AddModificatory(new SimpleProperty("Kaszt alap", array2[3]));

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
	Erõ, Gyorsaság, Ügyesség, Állóképesség, Egészség, Szépség, Intelligencia, Akaraterõ, Asztrál, KÉ, TÉ, VÉ, CÉ, SFÉ, MGT
}

interface BaseProperty {
	int getValue();

	String getName();
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

	public String getName() {
		return name;
	}

}

class Qualification implements BaseProperty {
	String name;
	QualificationType type;
	int qualificationID;
	
	public Qualification(String name, QualificationType type){
		
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