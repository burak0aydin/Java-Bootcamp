import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*Tree tree = new Tree("Quercus", "alba", true);
        Flower flower = new Flower("Narcissus", "jonquilla", true);
        Rose rose = new Rose("Rosa", "villosa", false);

        Plant[] plants = {tree, flower, rose};
        printPlants(plants);*/

        Hybrid hybridBus = new Hybrid(45, 1200, Electric.HIGH_VOLTAGE, 150, 1);
        CNGBus cngBus = new CNGBus(50, 1000, 200, 2);
        ElectricBus electricBus = new ElectricBus(55, 900, Electric.LOW_VOLTAGE);

        ArrayList<Bus> busList = new ArrayList<Bus>();
        busList.add(hybridBus);
        busList.add(cngBus);
        busList.add(electricBus);

        for (Bus bus : busList) {
            if (bus instanceof Hybrid) {
                System.out.println("Emission Tier: " + ((Hybrid)bus).getEmissionTier() + ", ID:" + bus.getID());
            }

            if (bus instanceof CNGBus) {
                System.out.println("Emission Tier: " + ((CNGBus)bus).getEmissionTier() + ", ID:" + bus.getID());
            }

            if (bus instanceof ElectricBus) {
                System.out.println("Voltage: " + ((ElectricBus)bus).getVoltage());
            }
        }

    }

    public static void printPlants(Plant[] plants) {
        for (Plant plant : plants) {
            plant.printPlant();
        }
    }
}

abstract class Plant {
    private String genus, species;
    private boolean isAnnual;

    Plant(String genus, String species, boolean isAnnual) {
        this.genus = genus;
        this.species = species;
        this.isAnnual = isAnnual;
    }

    String getGenus() { return genus; }
    String getSpecies() { return species; }
    boolean isAnnual() { return isAnnual; }
    void isAnnual(boolean isAnnual) { this.isAnnual = isAnnual; }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + getGenus() + " " + getSpecies() + " Annual:" + isAnnual();
    }

    void printPlant() {
        System.out.println(this.toString());
    }
}

class Tree extends Plant {
    private String barkColor, leafType;

    Tree(String genus, String species, boolean isAnnual) { 
        super(genus, species, isAnnual);

        setBarkColor("brown");
        setLeafType("round");
    }

    Tree(String genus, String species, boolean isAnnual, String barkColor, String leafType) {
        super(genus, species, isAnnual);

        setBarkColor(barkColor);
        setLeafType(leafType);
    }

    String getBarkColor() { return barkColor; }
    void setBarkColor(String barkColor) { this.barkColor = barkColor; }
    String getleafType() { return leafType; }
    void setLeafType(String leafType) { this.leafType = leafType; }

    @Override
    public String toString() {
        return super.toString() + " Bark Color:" + getBarkColor() + " Leaf Type:" + getleafType();
    }
}

class Flower extends Plant {
    private String petalColor;

    Flower(String genus, String species, boolean isAnnual) { 
        super(genus, species, isAnnual);

        setPetalColor("purple");
    }

    Flower(String genus, String species, boolean isAnnual, String petalColor) {
        super(genus, species, isAnnual);

        setPetalColor(petalColor);
    }

    String getPetalColor() { return petalColor; }
    void setPetalColor(String petalColor) { this.petalColor = petalColor; }

    @Override
    public String toString() {
        return super.toString() + " " + getPetalColor();
    }
}

class Rose extends Flower {
    private boolean isHybrid;

    Rose(String genus, String species, boolean isAnnual) {
        super(genus, species, isAnnual);
    }

    Rose(String genus, String species, boolean isAnnual, String petalColor) {
        super(genus, species, isAnnual, petalColor);
    }

    boolean isHybrid() { return isHybrid; }
    void setHybrid(boolean isHybrid) { this.isHybrid = isHybrid; }

    @Override
    public String toString() { 
        return super.toString() + " Hybrid:" + isHybrid();
    }
}

abstract class Bus {
	private int ID;
	private int capacity;
	private double cost;
	private static int nextID= 1;
	
	Bus(int capacity, double cost) {
		ID= nextID++;
		this.capacity = capacity;
		this.cost = cost;
	}
	
	abstract public double getAccel();
	final int getID() {return ID;}
	int getCapacity() {return capacity;}
	double getCost() {return cost;}
}


interface LiquidFuel {
    double getRange();
    int getEmissionTier();
}

interface Electric {
    double getVoltage();
    double HIGH_VOLTAGE = 600;
    double LOW_VOLTAGE = 480;
}

class Hybrid extends Bus implements LiquidFuel, Electric {
    private double voltage, range;
    private int emissionTier;

    Hybrid(int capacity, double cost, double voltage, double range, int emissionTier) {
        super(capacity, cost);

        this.voltage = voltage;
        this.range = range;
        this.emissionTier = emissionTier;
    }

    @Override
    public double getAccel() {
        return 4.0;
    }

    @Override
    public int getEmissionTier() {
        return this.emissionTier;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public double getVoltage() {
        return this.voltage;
    }
}

class CNGBus extends Bus implements LiquidFuel {
    private double range;
    private int emissionTier;

    CNGBus(int capacity, double cost, double range, int emissionTier) {
        super(capacity, cost);

        this.range = range;
        this.emissionTier = emissionTier;
    }

    @Override
    public double getAccel() {
        return 3.0;
    }

    @Override
    public int getEmissionTier() {
        return this.emissionTier;
    }

    @Override
    public double getRange() {
        return this.range;
    }
}

class ElectricBus extends Bus implements Electric {
    private double voltage;

    ElectricBus(int capacity, double cost, double voltage) {
        super(capacity, cost);
        this.voltage = voltage;
    }

    @Override
    public double getAccel() {
        return 5.0;
    }

    @Override
    public double getVoltage() {
        return this.voltage;
    }
}