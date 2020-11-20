package io.github.kraleppa.animal;

import java.util.*;

public class Genotype {
    public final List<Integer> genotype = new ArrayList<>();
    private final Random random = new Random();

    public Genotype() {
        for (int i = 0; i < 32; i++){
            genotype.add(i, random.nextInt(8));
        }
        addMissingGenes();
    }

    public Genotype(Genotype parent1, Genotype parent2){
        for (int i = 0; i < 32; i++){
            genotype.add(i % 2 == 0 ? parent1.genotype.get(i) : parent2.genotype.get(i));
        }
        addMissingGenes();
    }

    private void insertGene(int gene){
        boolean[] genes = new boolean[8];
        for (int i = 0; i < genotype.size(); i++){
            if (genes[genotype.get(i)]){
                genotype.set(i, gene);
                break;
            }
            genes[genotype.get(i)] = true;
        }
    }


    private void addMissingGenes(){
        for (int i = 0; i < 8; i++){
            if (!genotype.contains(i)){
                insertGene(i);
            }
        }
        Collections.sort(genotype);
    }

    public int getRandomGene(){
        return genotype.get(random.nextInt(32));
    }


}
