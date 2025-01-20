package agh.ics.oop.model.util;

import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.presenter.ConfigPresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    ConfigPresenter presenter;
    File file;
    public ConfigLoader(ConfigPresenter presenter) {
        this.presenter = presenter;
    }

    public Boolean set(File file) {
        String extension = "";
        String fileName = file.getName();
        if (fileName.contains("."))
            extension = fileName.substring(fileName.lastIndexOf("."));
        if (extension.equals(".properties")) {
            this.file = file;
            return true;
        }
        return false;
    }
    public Boolean load() {
        try (InputStream in = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(in);
            presenter.mapWidth.getValueFactory().setValue(Integer.parseInt(prop.getProperty("mapWidth")));
            presenter.mapHeight.getValueFactory().setValue(Integer.parseInt(prop.getProperty("mapHeight")));
            presenter.mapVariant.setValue(prop.getProperty("mapVariant"));
            presenter.startingPlantCount.getValueFactory().setValue(Integer.parseInt(prop.getProperty("startingPlantCount")));
            presenter.plantEnergy.getValueFactory().setValue(Integer.parseInt(prop.getProperty("plantEnergy")));
            presenter.plantsPerDay.getValueFactory().setValue(Integer.parseInt(prop.getProperty("plantsPerDay")));
            presenter.plantGrowthVariant.setValue(prop.getProperty("plantGrowthVariant"));
            presenter.startingAnimalCount.getValueFactory().setValue(Integer.parseInt(prop.getProperty("startingAnimalCount")));
            presenter.startingAnimalEnergy.getValueFactory().setValue(Integer.parseInt(prop.getProperty("startingAnimalEnergy")));
            presenter.energyToBeFull.getValueFactory().setValue(Integer.parseInt(prop.getProperty("energyToBeFull")));
            presenter.energyToReproduce.getValueFactory().setValue(Integer.parseInt(prop.getProperty("energyToReproduce")));
            presenter.minMutation.getValueFactory().setValue(Integer.parseInt(prop.getProperty("minMutation")));
            presenter.maxMutation.getValueFactory().setValue(Integer.parseInt(prop.getProperty("maxMutation")));
            presenter.mutationVariant.setValue(prop.getProperty("mutationVariant"));
            presenter.genomeLength.getValueFactory().setValue(Integer.parseInt(prop.getProperty("genomeLength")));
            presenter.animalBehaviourVariant.setValue(prop.getProperty("animalBehaviourVariant"));
            presenter.dayLength.getValueFactory().setValue(Integer.parseInt(prop.getProperty("dayLength")));
            presenter.exportStats.setSelected(Boolean.parseBoolean(prop.getProperty("exportStats")));
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
