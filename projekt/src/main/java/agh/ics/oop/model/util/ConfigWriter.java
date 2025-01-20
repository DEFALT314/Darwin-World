package agh.ics.oop.model.util;

import agh.ics.oop.presenter.ConfigPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigWriter {
    public Boolean write(ConfigPresenter pres, String directoryName, String fileName) {
        if (directoryName == null || directoryName.trim().isEmpty() || fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        try (OutputStream out = new FileOutputStream(directoryName + File.separator + fileName + ".properties")) {
            Properties conf = new Properties();
            conf.setProperty("mapWidth", pres.mapWidth.getValue().toString());
            conf.setProperty("mapHeight", pres.mapHeight.getValue().toString());
            conf.setProperty("mapVariant", pres.mapVariant.getValue());
            conf.setProperty("startingPlantCount", pres.startingPlantCount.getValue().toString());
            conf.setProperty("plantEnergy", pres.plantEnergy.getValue().toString());
            conf.setProperty("plantsPerDay", pres.plantsPerDay.getValue().toString());
            conf.setProperty("plantGrowthVariant", pres.plantGrowthVariant.getValue());
            conf.setProperty("startingAnimalCount", pres.startingAnimalCount.getValue().toString());
            conf.setProperty("startingAnimalEnergy", pres.startingAnimalEnergy.getValue().toString());
            conf.setProperty("energyToBeFull", pres.energyToBeFull.getValue().toString());
            conf.setProperty("energyToReproduce", pres.energyToReproduce.getValue().toString());
            conf.setProperty("minMutation", pres.minMutation.getValue().toString());
            conf.setProperty("maxMutation", pres.maxMutation.getValue().toString());
            conf.setProperty("mutationVariant", pres.mutationVariant.getValue());
            conf.setProperty("genomeLength", pres.genomeLength.getValue().toString());
            conf.setProperty("animalBehaviourVariant", pres.animalBehaviourVariant.getValue());
            conf.setProperty("dayLength", pres.dayLength.getValue().toString());
            conf.setProperty("exportStats", String.valueOf(pres.exportStats.isSelected()));

            conf.store(out, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
