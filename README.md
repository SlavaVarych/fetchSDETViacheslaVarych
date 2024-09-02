# fetchSDETViacheslavVarych
> This project is intended to run location requests.

### Getting local copy of the project
- Git Clone the project:
```sh
$ cd <Path to git folder>
$ git clone https://github.com/SlavaVarych/fetchSDETViacheslavVarych
```
- You should now see the folders

| Folder name | Description                                                                    |
|-------------|--------------------------------------------------------------------------------|
| main        | contains the program classes and location result package with .txt file inside |
| results     | contains locationResultData.txt file with last run location results            |


### Compiling the fetchSDETViacheslavVarych using maven
```sh
$ mvn compile
```

### Running program using maven
```sh

# the actual command to run program:
$ mvn exec:java
```

## After the program is completed
- The results of the requested data will be shown in the terminal
- The results of the requested data will be written results/locationResultData.txt file

