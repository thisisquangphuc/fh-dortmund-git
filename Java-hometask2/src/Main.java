

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creating an array of Car objects
        Car[] carArray = new Car[]{
                new Car(1, "BMW", "X3", 2019, "Gray", 45000, "STU910"),
                new Car(2, "Audi", "A4", 2018, "White", 35000, "VWX111"),
                new Car(3, "Mercedes-Benz", "C-Class", 2021, "Black", 50000, "YZA222"),
                new Car(4, "Nissan", "Altima", 2022, "Blue", 24000, "BCD333"),
                new Car(5, "Kia", "Optima", 2020, "Red", 23000, "EFG444"),
                new Car(6, "Hyundai", "Sonata", 2018, "Silver", 21000, "HIJ555"),
                new Car(7, "Subaru", "Outback", 2019, "Green", 30000, "KLM666"),
                new Car(8, "Volkswagen", "Jetta", 2017, "Yellow", 19000, "NOP777"),
                new Car(9, "Mazda", "CX-5", 2020, "Brown", 26000, "QRS888")
        };

        // a list of cars of a given brand 
        String brandToFilter = "BMW";
        List<Car> filteredCars = getCarsByBrand(carArray, brandToFilter);

        System.out.println("Cars of brand " + brandToFilter + ":");
        for (Car car : filteredCars) {
            System.out.println(car);
        }
        
        // b) a list of cars of a given model that have been in use for more than n years
        String modelToFilter = "CX-5";
        int nYears = 2;
        List<Car> oldModelCars = getCarsByModelOlderThan(carArray, modelToFilter, nYears);

        System.out.println("\nCars of model " + modelToFilter + " older than " + nYears + " years:");
        for (Car car : oldModelCars) {
            System.out.println(car);
        }

        // c) a list of cars of a given year of manufacture, the price of which is higher than the specified one.
        int yearToFilter = 2018;
        double minPrice = 20000;
        List<Car> expensiveCars = getCarsByYearAndPrice(carArray, yearToFilter, minPrice);

        System.out.println("\nCars from the year " + yearToFilter + " with a price higher than " + minPrice + ":");
        for (Car car : expensiveCars) {
            System.out.println(car);
        }
    }

    public static List<Car> getCarsByBrand(Car[] cars, String brand) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }
    
    public static List<Car> getCarsByModelOlderThan(Car[] cars, String model, int n) {
        List<Car> result = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYearOfManufacture() > n)) {
                result.add(car);
            }
        }
        return result;
    }

    public static List<Car> getCarsByYearAndPrice(Car[] cars, int year, double price) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYearOfManufacture() == year && car.getPrice() > price) {
                result.add(car);
            }
        }
        return result;
    }
}
