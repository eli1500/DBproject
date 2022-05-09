package app.ui;

import app.rdg.Sklad;

import java.util.List;

public class VypisStattistikuPodlaAtributov {
    private static final VypisStattistikuPodlaAtributov INSTANCE = new VypisStattistikuPodlaAtributov();

    public static VypisStattistikuPodlaAtributov getInstance() { return INSTANCE; }

    private VypisStattistikuPodlaAtributov() { }

    public void print(List<List<Integer>> pole) {
        if (pole == null) {
            throw new NullPointerException("Pole nemoze byt nullove");
        }
        System.out.println("--------------------------------");
        for(var pol : pole) {

            System.out.print("Pocet predanych produktov:        ");
            System.out.println(pol.get(0));
            System.out.print("Pocet jeho atributov:             ");
            System.out.println(pol.get(1));
            System.out.println("--------------------------------");
        }


    }
}
