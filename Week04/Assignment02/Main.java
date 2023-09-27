import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Vehicle m = new Motorcycle();
        Vehicle c = new Car();

        //Vehicle x = ((Bus)c).accelerate(20); VERY IMPORTANT, TRY TO UNDERSTAND THIS. UNCOMMENT AND TRY. WHY ERROR

        Car c1 = new Car();
        Car c2 = new Car(320, 16);
        Motorcycle m1 = new Motorcycle();
        Motorcycle m2 = new Motorcycle(180, 14);
        Bus b1 = new Bus();
        Bus b2 = new Bus(140, 6);

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(c1);
        vehicles.add(c2);
        vehicles.add(b1);
        vehicles.add(b2);
        vehicles.add(m1);
        vehicles.add(m2);

        GasStation.fuelUpAll(vehicles);


        System.out.println(c.toString());
        ((Car)c).accelerate(30);
        System.out.println(c.toString());

        ((Car)c).accelerate(70);
        System.out.println(c.toString());
        ((Car)c).decelerate(50);
        System.out.println(c.toString());
        ((Car)c).decelerate(10);
        System.out.println(c.toString());
        ((Car)c).decelerate(0);
        System.out.println(c.toString());
        ((Car)c).decelerate();
        System.out.println(c.toString());
        
    }
}

class Vehicle {

    
    Engine engine;
    protected int speed;
    private int maxSpeed;
    private int changeInSpeed;
    protected int gasTankCapacity;
    protected double currentGasInTank;

    class Engine {
        private int gearCount;
        private int currentGear = 0;

        Engine(int numberOfGears) {
            gearCount = numberOfGears;
        }

        public int getGearCount() {
            return gearCount;
        }

        public int getCurrentGear() {
            return currentGear;
        }

        /*
        * I don't want users to shift up or down by themselves
        * I want it to happen automatically
        * but if i make these methodes private, i cannot set use them in vehicle class
        * and if i make these methods public, user can change shifts
        * to prevent user from changing the shifts themselves and to make it automatically happen
        * Engine class is inside the vehicle class
        */
        public void shiftUp() {
            currentGear++;
        }

        public void shiftDown() {
            currentGear--;
        }

        @Override
        public String toString() {
            return "Gear: " + currentGear + ", GearCount: " + gearCount;
        }
    }

    Vehicle(int numberOfGears, int maxSpeed, int changeInSpeed, int gasTank) {
        engine = new Engine(numberOfGears);
        this.maxSpeed = maxSpeed;
        this.changeInSpeed = changeInSpeed;
        this.gasTankCapacity = gasTank;
        this.currentGasInTank = gasTank - new Random().nextInt(5, 15);
    }

    private final void setGear() {
        if ((int)((maxSpeed / engine.getGearCount()) * (engine.getCurrentGear())) < speed) {
            engine.shiftUp();
        } else if ((int)((maxSpeed / engine.getGearCount()) * (engine.getCurrentGear() -1)) > speed)  {
            engine.shiftDown();
        }
    }

    protected Vehicle accelerate() {
        speed += changeInSpeed; // i prefer this way but commenting this line and doing it in subclass works as well
        setGear();
        consumeGas();

        if (this.getGasPercentage() < 0.15) {
            System.out.println("Low Gas level...");
        }
        return this;
    }

    protected Vehicle decelerate() {
        speed -= changeInSpeed;
        setGear();
        consumeGas();

        if (this.getGasPercentage() < 0.15) {
            System.out.println("Low Gas level...");
        }

        return this;
    }

    protected void setChangeInSpeed(int changeInSpeed) {
        if (changeInSpeed < 0) {
            System.out.println("Cannot");
            return;
        }

        this.changeInSpeed = changeInSpeed;
    }

    public int getChangeInSpeed() {
        return this.changeInSpeed;
    }

    public double getGasPercentage() {
        return this.currentGasInTank / this.gasTankCapacity;
    }

    private void consumeGas() {
        if (this instanceof Car) {
            this.currentGasInTank -= (2 * this.engine.getCurrentGear());
        }

        if (this instanceof Motorcycle) {
            this.currentGasInTank -= (3 * this.engine.getCurrentGear());
        }

        if (this instanceof Bus) {
            this.currentGasInTank -= (5 * this.engine.getCurrentGear());
        }
    }

    public int getSpeed() {
        return speed;
    }

    public double refuel() {
        return 0.0;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "= CurrentSpeed: " + this.speed + ", ChangeInSpeed: " + this.changeInSpeed + ", MaxSpeed: " + this.maxSpeed + ", Engine= " + engine.toString() + ", GasPercent: " + this.getGasPercentage();
    }
}

class Car extends Vehicle {
    private static final int NUMBER_OF_GEARS = 6;
    private static final int GAS_TANK_CAPACITY = 50;
    private int doorCount = 4;

    Car() {
        this(280, 11);
    }

    Car(int maxSpeed, int changeInSpeed) {
        super(NUMBER_OF_GEARS, maxSpeed, changeInSpeed, GAS_TANK_CAPACITY);
    }

    Car(int maxSpeed, int changeInSpeed, int doorCount) {
        this(maxSpeed, changeInSpeed);
        this.doorCount = doorCount;
    }

    public Car accelerate(int changeInSpeed) { // can be done like this but i would like to do it in superclass, will keep it because override example
        setChangeInSpeed(changeInSpeed);
        super.accelerate();

        return this;
    }

    public Car decelerate(int changeInSpeed) {
        setChangeInSpeed(changeInSpeed);
        super.decelerate();

        return this;
    }

    public double refuel() {
        double gasRecieved = 50 / GasStation.costPerLiter; // Hep 50₺ lik alıyor
        this.currentGasInTank += gasRecieved;

        return gasRecieved;
    }

    @Override
    public String toString() {
        return super.toString() + ", DoorCount: " + this.doorCount;
    }
}

class Bus extends Vehicle {
    private static final int NUMBER_OF_GEARS = 11;
    private static final int GAS_TANK_CAPACITY = 80;
    private int standingPassengerCount = 36;

    Bus() {
        this(110, 5);
    }

    Bus(int maxSpeed, int changeInSpeed) {
        super(NUMBER_OF_GEARS, maxSpeed, changeInSpeed, GAS_TANK_CAPACITY);
    }

    Bus(int maxSpeed, int changeInSpeed, int standingPassengerCount) {
        this(maxSpeed, changeInSpeed);
        this.standingPassengerCount = standingPassengerCount;
    }

    protected Bus accelerate(int changeInSpeed) { // can be done like this but i would like to do it in superclass, will keep it because override example
        setChangeInSpeed(changeInSpeed);
        super.accelerate();

        return this;
    }

    protected Bus decelerate(int changeInSpeed) {
        setChangeInSpeed(changeInSpeed);
        super.decelerate();

        return this;
    }

    public double refuel() {
        double gasRecieved = (0.8 - this.getGasPercentage()) * this.gasTankCapacity; // Hep 80%'ye kadar dolduruyor
        this.currentGasInTank += gasRecieved;
        return gasRecieved;
    }

    @Override
    public String toString() {
        return super.toString() + ", StandingPassengerCount: " + this.standingPassengerCount;
    }
}

class Motorcycle extends Vehicle {
    private static final int NUMBER_OF_GEARS = 4;
    private static final int GAS_TANK_CAPACITY = 35;
    private String color = "Red";

    Motorcycle() {
        this(220, 8);
    }

    Motorcycle(int maxSpeed, int changeInSpeed) {
        super(NUMBER_OF_GEARS, maxSpeed, changeInSpeed, GAS_TANK_CAPACITY);
    }

    Motorcycle(int maxSpeed, int changeInSpeed, String color) {
        this(maxSpeed, changeInSpeed);
        this.color = color;
    }

    protected Motorcycle accelerate(int changeInSpeed) { // can be done like this but i would like to do it in superclass, will keep it because override example
        setChangeInSpeed(changeInSpeed);
        super.accelerate();

        return this;
    }

    protected Motorcycle decelerate(int changeInSpeed) {
        setChangeInSpeed(changeInSpeed);
        super.decelerate();

        return this;
    }

    public double refuel() {
        double gasRecieved = (1 - this.getGasPercentage()) * this.gasTankCapacity; // Hep Fulluyor
        this.currentGasInTank += gasRecieved;
        return gasRecieved;
    }

    @Override
    public String toString() {
        return super.toString() + ", Color: " + this.color;
    }
}

// FuelUp

class GasStation {
    final static double costPerLiter = 20.40;

    static void fuelUpAll(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            double fueledGasAmount = vehicle.refuel();
            System.out.println(vehicle.toString() + " PAID: " + fueledGasAmount * costPerLiter);
        }
    }
}