package workplace.example;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class CharacterSheet {
	ArrayList<Property> propertyList;
	ArrayList<Equipment> equipmentList;

	// property-nek k�ne egy �soszt�ly, ami csak �rt�tkeket t�rol
	public CharacterSheet() {
		propertyList = new ArrayList<Property>();
		equipmentList = new ArrayList<Equipment>();
		// Generals
		int[] array = new int[] { 12, 13, 12, 11, 12, 13, 12, 11, 12 };
		int[] array2 = new int[] { 5, 30, 70, 10 };

		// Adatb�zis hozz�f�r�s is sz�ks�ges itt!!

		propertyList.add(new Property("Er�", PropertyType.Er�));
		getProperty(PropertyType.Er�).AddModificatory(new SimpleProperty("alap", array[0]));
		propertyList.add(new Property("Gyorsas�g", PropertyType.Gyorsas�g));
		getProperty(PropertyType.Gyorsas�g).AddModificatory(new SimpleProperty("alap", array[1]));
		propertyList.add(new Property("�gyess�g", PropertyType.�gyess�g));
		getProperty(PropertyType.�gyess�g).AddModificatory(new SimpleProperty("alap", array[2]));
		propertyList.add(new Property("�ll�k�pess�g", PropertyType.�ll�k�pess�g));
		getProperty(PropertyType.�ll�k�pess�g).AddModificatory(new SimpleProperty("alap", array[3]));
		propertyList.add(new Property("Eg�szs�g", PropertyType.Eg�szs�g));
		getProperty(PropertyType.Eg�szs�g).AddModificatory(new SimpleProperty("alap", array[4]));
		propertyList.add(new Property("Sz�ps�g", PropertyType.Sz�ps�g));
		getProperty(PropertyType.Sz�ps�g).AddModificatory(new SimpleProperty("alap", array[5]));
		propertyList.add(new Property("Intelligencia", PropertyType.Intelligencia));
		getProperty(PropertyType.Intelligencia).AddModificatory(new SimpleProperty("alap", array[6]));
		propertyList.add(new Property("Akarater�", PropertyType.Akarater�));
		getProperty(PropertyType.Akarater�).AddModificatory(new SimpleProperty("alap", array[7]));
		propertyList.add(new Property("Asztr�l", PropertyType.Asztr�l));
		getProperty(PropertyType.Asztr�l).AddModificatory(new SimpleProperty("alap", array[8]));

		propertyList.add(new Property("Kezdem�nyez� �rt�k", PropertyType.K�));
		getProperty(PropertyType.K�).AddModificatory(new SimpleProperty("Kaszt alap", array2[0]));
		getProperty(PropertyType.K�).AddModificatory(getProperty(PropertyType.Gyorsas�g), new Above10());
		getProperty(PropertyType.K�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("T�mad� �rt�k", PropertyType.T�));
		getProperty(PropertyType.T�).AddModificatory(new SimpleProperty("Kaszt alap", array2[1]));
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.Er�), new Above10());
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.Gyorsas�g), new Above10());
		getProperty(PropertyType.T�).AddModificatory(getProperty(PropertyType.�gyess�g), new Above10());

		propertyList.add(new Property("V�dekez� �rt�k", PropertyType.V�));
		getProperty(PropertyType.V�).AddModificatory(new SimpleProperty("Kaszt alap", array2[2]));

		propertyList.add(new Property("C�lz� �rt�k", PropertyType.C�));
		getProperty(PropertyType.C�).AddModificatory(new SimpleProperty("Kaszt alap", array2[3]));

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
	Er�, Gyorsas�g, �gyess�g, �ll�k�pess�g, Eg�szs�g, Sz�ps�g, Intelligencia, Akarater�, Asztr�l, K�, T�, V�, C�, SF�, MGT
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