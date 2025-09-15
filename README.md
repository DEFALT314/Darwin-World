# Darwin-World 🐌

**Darwin-World** is a Java desktop application simulating a dynamic ecosystem based on Charles Darwin's theory of natural selection. Users can customize the map, animal, and simulation parameters, watch the ecosystem evolve in real time, and analyze detailed statistical data.

This project was developed as part of the **Object-Oriented Programming course** at **AGH University of Krakow, Computer Science**.


## Authors

* **Konrad Idzik** [\[DEFALT314\]](https://github.com/DEFALT314)
* **Kacper Wąchała** [\[LeoonKameleon\]](https://github.com/LeoonKameleon)

## Project Overview

Darwin-World models a world where digital organisms roam, feed, reproduce, and evolve. Animals consume grass, pass their genes to offspring, and experience mutations that gradually alter their movement patterns. Natural selection shapes the population: individuals may die of hunger, while plants regrow at a constant rate.

The environment is a two-dimensional map shaped like a globe. The central “equatorial” region features dense jungle, while the surrounding steppe has slower-growing grass, creating varying survival conditions. Some simulation variants include predators that hunt herbivores, adding further dynamics to the ecosystem.


## Simulation Workflow
![Recording](https://github.com/user-attachments/assets/71f310dd-207a-4831-a20d-84bd4b63af62)

Each simulation step follows these stages:

1. Remove animals with insufficient energy
2. Move all animals according to their current direction
3. Feed animals that land on grass-covered tiles
4. Reproduce animals occupying the same tile
5. Grow grass according to the configured daily rate
6. Update statistics for animals and the ecosystem

**Animal statistics include:**

* Genome and currently active gene
* Energy level
* Grass eaten
* Number of children
* Lifespan and day of death

**Simulation statistics include:**

* Total number of animals and grass tiles
* Most common genomes
* Average energy of living animals
* Average lifespan and offspring count
* Number of available empty spaces

These statistics allow for detailed tracking of evolutionary trends in the population.

## Features

### Configurable Simulation Parameters

The application allows users to adjust initial settings before starting:

* Map height and width
* Initial number of animals and grass
* Grass growth rate per day
* Animal energy levels, reproduction costs, and mutation rates
* Genome length and mutation type
* Map type:
  * **Earth** – map edges loop horizontally; top and bottom are impassable poles
  * **Poles** – animal loose more energy on top and bottom

Settings can be saved and loaded, and simulation statistics can be exported as CSV logs.

### Real-Time Visualization

* Observe animals moving and interacting with their environment
* Pause, restart, or run multiple simulations asynchronously
* Track individual animals and population trends


## Technologies

* **Java 21** – core simulation logic
* **JavaFX** – graphical user interface
* **Gradle** – build automation and dependency management



## Installation & Running

1. Clone the repository:

```bash
git clone https://github.com/DEFALT314/Darwin-World.git
cd Darwin-World
```

2. Build the project using Gradle:

```bash
./gradlew build
```

3. Run the application:

```bash
./gradlew run
```
