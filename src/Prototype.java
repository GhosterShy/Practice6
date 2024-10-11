import java.util.ArrayList;
import java.util.List;


interface ICloneable<T> {
    T clone();
}

class Weapon implements ICloneable<Weapon> {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName(){
        return name;
    }

    public int getDamage(){
        return damage;
    }

    @Override
    public Weapon clone(){
        return new Weapon(this.name, this.damage);
    }

    @Override
    public String toString() {
        return "Weapon{name='" + name + "', damage=" + damage + "}";
    }
}


class Armor implements ICloneable<Armor> {
    private String name;
    private int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public Armor clone() {
        return new Armor(this.name, this.defense);
    }

    @Override
    public String toString() {
        return "Armor{name='" + name + "', defense=" + defense + "}";
    }
}


class Skill implements ICloneable<Skill> {
    private String name;
    private String type; // "Magic" или "Physical"
    private int power;

    public Skill(String name, String type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    @Override
    public Skill clone() {
        return new Skill(this.name, this.type, this.power);
    }

    @Override
    public String toString() {
        return "Skill{name='" + name + "', type='" + type + "', power=" + power + "}";
    }
}




class Character implements ICloneable<Character> {
    private String name;
    private int health;
    private int strength;
    private int agility;
    private int intelligence;
    private Weapon weapon;
    private Armor armor;
    private List<Skill> skills;

    public Character(String name, int health, int strength, int agility, int intelligence) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.skills = new ArrayList<>();
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    @Override
    public Character clone() {
        Character clonedCharacter = new Character(this.name, this.health, this.strength, this.agility, this.intelligence);


        if (this.weapon != null) {
            clonedCharacter.equipWeapon(this.weapon.clone());
        }
        if (this.armor != null) {
            clonedCharacter.equipArmor(this.armor.clone());
        }


        for (Skill skill : this.skills) {
            clonedCharacter.addSkill(skill.clone());
        }

        return clonedCharacter;
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', health=" + health + ", strength=" + strength +
                ", agility=" + agility + ", intelligence=" + intelligence + ", weapon=" + weapon +
                ", armor=" + armor + ", skills=" + skills + "}";
    }
}


public class Prototype {
    public static void main(String[] args) {
        Character originalCharacter = new Character("Warrior", 100, 15, 10, 5);


        Weapon sword = new Weapon("Sword", 10);
        Armor shield = new Armor("Shield", 5);
        originalCharacter.equipWeapon(sword);
        originalCharacter.equipArmor(shield);


        originalCharacter.addSkill(new Skill("Slash", "Physical", 7));
        originalCharacter.addSkill(new Skill("Fireball", "Magic", 10));


        Character clonedCharacter = originalCharacter.clone();


        clonedCharacter.equipWeapon(new Weapon("Axe", 12));
        clonedCharacter.addSkill(new Skill("Heal", "Magic", 8));


        System.out.println("Original Character: " + originalCharacter);
        System.out.println("Cloned Character: " + clonedCharacter);


    }
}
