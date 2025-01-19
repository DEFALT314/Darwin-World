package agh.ics.oop.model.Simulation;

public class SimulationConfig {
    private final int mapWidth;
    private final int mapHeight;
    private final String mapVariant;
    private final int startingPlantCount;
    private final int plantEnergy;
    private final int plantsPerDay;
    private final String plantGrowthVariant;
    private final int startingAnimalCount;
    private final int startingAnimalEnergy;
    private final int energyToBeFull;
    private final int energyToReproduce;
    private final int minMutation;
    private final int maxMutation;
    private final String mutationVariant;
    private final int genomeLength;
    private final String animalBehaviorVariant;
    private int dayLength;

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
        this.dayLength = 1000;
    }
    public SimulationConfig(int mapWidth, int mapHeight, String mapVariant, int startingPlantCount,
                            int plantEnergy, int plantsPerDay, String plantGrowthVariant,
                            int startingAnimalCount, int startingAnimalEnergy, int energyToBeFull,
                            int energyToReproduce, int minMutation, int maxMutation,
                            String mutationVariant, int genomeLength, String animalBehaviorVariant, int dayLength) {
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
        this.dayLength = dayLength;
    }




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

    public int getDayLength() {
        return dayLength;
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
