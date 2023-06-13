import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Trainset trainset = new Trainset();
        Klasyfikator klasyfikator = new Klasyfikator();
        List<String> testsetlist = new ArrayList<>();
        List<Collection<Long>> listalong = new ArrayList<>();
        ArrayList<ArrayList<Double>> biglist = new ArrayList<>();
        ArrayList<Double> smalllist;
        int stop1 = 0;
        double wszystkiekwiaty = 0.0;
        double dobrzeprzypisanekwiaty=  0.0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj alfa");
        String a = scanner.nextLine();
        Double alfa = Double.parseDouble(a);
        System.out.println("Podaj sciezke train-set");
        String trainsetpath = scanner.nextLine();
        while(stop1 != 1){
            System.out.println("Podaj sciezke test-set");
            String testset = scanner.nextLine();
            testsetlist.add(testset);
            System.out.println("dowolny liczba - kontynuuj podawanie");
            System.out.println("1 - wyjdz");
            stop1 = Integer.parseInt(scanner.nextLine());
        }
        
        AbstractMap.SimpleImmutableEntry<ArrayList<Double>, Double> perceptron = klasyfikator.stworzPerceptron(trainset.returnTrainset(trainsetpath), alfa);
        Integer dok = 0;
        Integer size = 0;
        for(String testset : testsetlist){
            dok = dok + klasyfikator.sprawdzDokladnosc(trainset.returnTrainset(testset), perceptron).getValue();
            size = size + trainset.returnTrainset(testset).size();
        }
        System.out.println(perceptron);
        System.out.println(dok);
        System.out.println(size);
        System.out.println("Dokladnosc: " + ((double)(dok)/(double)(size))*100 + "%" );

        stop1 = 0;
        while(stop1!=1){
            System.out.println("Podaj wektor do sprawdzenia(np: 0.1,0.2,0.3)");
            String wek = scanner.nextLine();
            smalllist = Arrays.stream(wek.split(","))
                    .map(p -> Double.parseDouble(p))
                    .collect(Collectors.toCollection(ArrayList::new));
            biglist.add(smalllist);
            System.out.println("zostal przydzielony: " + klasyfikator.sprawdzDokladnosc(trainset.returnTrainset(trainsetpath), perceptron, smalllist));
            System.out.println("1 - wyjdz");
            System.out.println("dowolny - kontynuuj");
            stop1 = Integer.parseInt(scanner.nextLine());
            smalllist.clear();
            biglist.clear();
        }

    }
}
