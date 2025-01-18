package agh.ics.oop.model.Stats;

import agh.ics.oop.model.Genomes.GenomesAbstract;

public record SimulationStatsRecord(
        int day,
        int animalCount,
        int plantCount,
        int emptyBoxesCount,
        double averageEnergy,
        double averageLifeLength,
        double averageChildrenCount,
        GenomesAbstract mostCommonGenome
) {
    @Override
    public String toString() {
        return formatStat("Day", day) +
                formatStat("Animal count", animalCount) +
                formatStat("Plant count", plantCount) +
                formatStat("Free tiles", emptyBoxesCount) +
                formatStat("Average lifespan", averageLifeLength, true) +
                formatStat("Average energy", averageEnergy, true) +
                formatStat("Average children count", averageChildrenCount, true) +
                formatStat("Most common genome", mostCommonGenome) +
                "\n";
    }

    private String formatStat(String label, Object value) {
        return label + ": " + value + "\n";
    }

    private String formatStat(String label, double value, boolean formatAsDecimal) {
        return label + ": " + (formatAsDecimal ? String.format("%.2f", value) : value) + "\n";
    }
}