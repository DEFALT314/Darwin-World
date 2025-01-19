package agh.ics.oop.model.Simulation;

public class SimulationConfig {
    private final int mapWidth;                  // szerokość mapy
    private final int mapHeight;                 // wysokość mapy
    private final String mapVariant;             // wariant mapy (np. prostokątna, toroidalna, itp.)
    private final int startingPlantCount;        // startowa liczba roślin
    private final int plantEnergy;               // energia zapewniana przez zjedzenie jednej rośliny
    private final int plantsPerDay;              // liczba roślin wyrastających każdego dnia
    private final String plantGrowthVariant;     // wariant wzrostu roślin (np. losowy, określony)
    private final int startingAnimalCount;       // startowa liczba zwierzaków
    private final int startingAnimalEnergy;      // startowa energia zwierzaków
    private final int energyToBeFull;            // energia konieczna, by uznać zwierzaka za najedzonego
    private final int energyToReproduce;         // energia rodziców zużywana na stworzenie potomka
    private final int minMutation;               // minimalna liczba mutacji u potomków
    private final int maxMutation;               // maksymalna liczba mutacji u potomków
    private final String mutationVariant;        // wariant mutacji (np. losowa, systematyczna)
    private final int genomeLength;              // długość genomu zwierzaków
    private final String animalBehaviorVariant;  // wariant zachowania zwierzaków (np. agresywne, pasywne)

    // Konstruktor klasy, który inicjalizuje wszystkie parametry symulacji
    public SimulationConfig(int mapWidth, int mapHeight, String mapVariant, int startingPlantCount,
                            int plantEnergy, int plantsPerDay, String plantGrowthVariant,
                            int startingAnimalCount, int startingAnimalEnergy, int energyToBeFull,
                            int energyToReproduce, int minMutation, int maxMutation,
                            String mutationVariant, int genomeLength, String animalBehaviorVariant) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.mapVariant = mapVariant;
        this.startingPlantCount = startingPlantCount;
        this.plantEnergy = plantEnergy;
        this.plantsPerDay = plantsPerDay;
        this.plantGrowthVariant = plantGrowthVariant;
        this.startingAnimalCount = startingAnimalCount;
        this.startingAnimalEnergy = startingAnimalEnergy;
        this.energyToBeFull = energyToBeFull;
        this.energyToReproduce = energyToReproduce;
        this.minMutation = minMutation;
        this.maxMutation = maxMutation;
        this.mutationVariant = mutationVariant;
        this.genomeLength = genomeLength;
        this.animalBehaviorVariant = animalBehaviorVariant;
    }

    // Gettery do odczytu wartości (bez setterów)

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public String getMapVariant() {
        return mapVariant;
    }

    public int getStartingPlantCount() {
        return startingPlantCount;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public int getPlantsPerDay() {
        return plantsPerDay;
    }

    public String getPlantGrowthVariant() {
        return plantGrowthVariant;
    }

    public int getStartingAnimalCount() {
        return startingAnimalCount;
    }

    public int getStartingAnimalEnergy() {
        return startingAnimalEnergy;
    }

    public int getEnergyToBeFull() {
        return energyToBeFull;
    }

    public int getEnergyToReproduce() {
        return energyToReproduce;
    }

    public int getMinMutation() {
        return minMutation;
    }

    public int getMaxMutation() {
        return maxMutation;
    }

    public String getMutationVariant() {
        return mutationVariant;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public String getAnimalBehaviorVariant() {
        return animalBehaviorVariant;
    }
}
