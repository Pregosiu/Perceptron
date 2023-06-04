import java.util.ArrayList;
import java.util.AbstractMap.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Klasyfikator {



    public static SimpleImmutableEntry<ArrayList<Double>, Double> stworzFunkcje(int ilosc){
        ArrayList<Double> wektor = new ArrayList<>();
        Double prog = Math.random()*10-5;
        for (int i = 0; i < ilosc; i++) {
            wektor.add(Math.random()*10-5);
        }
        return new SimpleImmutableEntry(wektor, prog);
    }

    public static SimpleImmutableEntry<ArrayList<Double>, Double> stworzPerceptron(ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> trainset, Double alfa){
        int ilosc = trainset.get(1).getValue().size();
        SimpleImmutableEntry<ArrayList<Double>, Double> funkcja = stworzFunkcje(ilosc);
        ArrayList<Double> nowafunkcja = funkcja.getKey();
        Double prog = funkcja.getValue();
        Double c;
        Double y;
        int d;
        Double g;
        String dec = "";
        String dec2 = "";
//        ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> nazwy = trainset.stream()
//                .map(x -> x.getKey())
//                .distinct()
//
//                .collect(Collectors.toCollection(ArrayList::new));

        List<String> nazwy = trainset.stream()
                .map(x -> x.getKey())
                .distinct()
                .collect(Collectors.toList());

        String type1 = nazwy.get(0);
        String type2 = nazwy.get(1);
        for (int j = 0; j < 2; j++) {
            for (SimpleImmutableEntry<String, ArrayList<Double>> wektorwlabel : trainset){
                g = 0.0;

                for (int i = 0; i < ilosc; i++) {
                    g = g + wektorwlabel.getValue().get(i)*nowafunkcja.get(i);
                }
                g = g - prog;
                if(g>=0){
                    dec = type1;
                }
                else{
                    dec = type2;
                }
                if(!wektorwlabel.getKey().equals(dec)){
                    d = wektorwlabel.getKey() == type1? 1:0;
                    y =  dec == type1? 1.0:0.0;

                    System.out.println("UCZE");
                }
                else{
                    y = 1.0;
                    d = 1;
                }
                ArrayList<Double> nowafunkcja1 = new ArrayList<>();
                for(int i = 0; i < ilosc; i++){
                    nowafunkcja1.add(nowafunkcja.get(i) + ((d-y)*alfa*wektorwlabel.getValue().get(i)));
                }
                nowafunkcja=nowafunkcja1;
                prog = prog-(d-y)*alfa;
            }
        }




        return new SimpleImmutableEntry<>(nowafunkcja, prog);
    }
    public static SimpleImmutableEntry<String, Integer> sprawdzDokladnosc(ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> testset, SimpleImmutableEntry<ArrayList<Double>, Double> perceptron){
        int ilosc = testset.get(1).getValue().size();
        SimpleImmutableEntry<ArrayList<Double>, Double> funkcja = stworzFunkcje(ilosc);
        Double g;
        String dec = "";
        int count = 0;
        List<String> nazwy = testset.stream()
                .map(x -> x.getKey())
                .distinct()
                .collect(Collectors.toList());

        String type1 = nazwy.get(0);
        for (SimpleImmutableEntry<String, ArrayList<Double>> wektorwlabel : testset){
            g = 0.0;

            for (int i = 0; i < ilosc; i++) {
                g = g + wektorwlabel.getValue().get(i)*perceptron.getKey().get(i);
            }
            g = g - perceptron.getValue();
            if(g>=0){
                dec = type1;
            }
            else {
            }
            if(!wektorwlabel.getKey().equals(dec)){

            }
            else{
                count++;
            }
        }
        return new SimpleImmutableEntry<String, Integer>(testset.get(0).getKey(), count);
    }

    public static String sprawdzDokladnosc(ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> testset, SimpleImmutableEntry<ArrayList<Double>, Double> perceptron, ArrayList<Double> dosprawdzeniawek){
        int ilosc = testset.get(1).getValue().size();
        SimpleImmutableEntry<ArrayList<Double>, Double> funkcja = stworzFunkcje(ilosc);
        ArrayList<Double> nowafunkcja = funkcja.getKey();
        Double prog = funkcja.getValue();
        Double g;
        String dec = "";
        String dec1 = "";
        int count = 0;

        SimpleImmutableEntry<String, Integer> a;
        SimpleImmutableEntry<String, Integer> b;

        List<String> nazwy = testset.stream()
                .map(x -> x.getKey())
                .distinct()
                .collect(Collectors.toList());

        String type1 = nazwy.get(0);
        String type2 = nazwy.get(1);



            g = 0.0;

            for (int i = 0; i < ilosc; i++) {
                g = g + testset.get(0).getValue().get(0)*perceptron.getKey().get(i);
            }
            g = g - perceptron.getValue();
            if(g>=0){
                dec = type1;
            }


        g = 0.0;

        for (int i = 0; i < ilosc; i++) {
            g = g + dosprawdzeniawek.get(i)*perceptron.getKey().get(i);
        }
        g = g - perceptron.getValue();
        if(g>=0){
            return dec;
        }
        else{
            return type2;
        }




    }

    //Do proj1

//    public static Double liczEuklides(ArrayList<Double> wektor1, ArrayList<Double> wektor2){
//        Double wynik = 0.0;
//        for (int i = 0; i < wektor1.size(); i++) {
//            wynik = wynik + Math.pow(wektor1.get(i)-wektor2.get(i),2);
//        }
//        return wynik;
//    }

//    public static Map<Object, Long> klasyfikuj(ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> trainset, ArrayList<ArrayList<Double>> testset, int k){
//        Map<Object, Long> aa;
//        aa = testset.stream()
//                .map(x -> trainset.stream()
//                        .map(s -> new SimpleImmutableEntry(s.getKey(),liczEuklides(x,s.getValue())))
//                        .sorted((o1,o2) -> {
//                            if((Double)(o1.getValue()) > (Double) (o2.getValue())){
//                                return 1;
//                            }
//                            if((Double)(o2.getValue()) > (Double) (o1.getValue())){
//                                return -1;
//                            }
//                            if((o1.getValue()) == (o2.getValue())){
//                                return 1;
//                            }
//                            return 1;
//                        })
//                        .limit(k)
//                        .collect(Collectors.toCollection(ArrayList<SimpleImmutableEntry>::new)))
//                .map(x-> x.stream()
//                        .collect(Collectors.groupingBy(s -> s.getKey(), Collectors.counting())))
//                .map(x-> x.entrySet().stream()
//                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                        .limit(1)
//                        .collect(Collectors.toMap(s -> s.getKey(), a -> a.getValue())))
//                .collect(Collectors.groupingBy(x-> x.keySet(), Collectors.counting()));
//        return aa;
//    }
}
