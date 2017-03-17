package com.asid.algorithms.database;

import com.asid.algorithms.entity.Airline;
import com.asid.algorithms.entity.Airport;
import com.asid.algorithms.entity.Route;
import com.asid.foundation.datastructure.list.CustomArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class DataLoaderImpl implements DataLoader {

    private InmemmoryDataBase inmemmoryDataBase = InmemmoryDataBase.getInstance();

    @Override
    public void loadAirports(String pathToFile){
         /* (TODO Lab No. 1) Please introduce a sensible implementation */
        inmemmoryDataBase.setAirports(new CustomArrayList<>());
        Scanner scanner = null;

/*
        URL url = Thread.currentThread().getContextClassLoader().getResource(pathToFile);
        File file = null;
        if (url != null) {
            file = new File(url.getPath());
        }
*/

        //jeśli path jest innej postaci (cała ścieżka do pliku)

        File file = new File(pathToFile);


        if (file != null) {
            try {
                scanner = new Scanner(file,"Cp1252");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<String> lines = new ArrayList<>();

        if (scanner != null) {
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }

        Iterator<String> iterator = lines.iterator();
        String line;
        while(iterator.hasNext()){
            //for each line
            line = iterator.next();
            if(line.charAt(0) != '#'){
                StringTokenizer stringTokenizer = new StringTokenizer(line);

                String iataCode = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO IATA CODE";
                double latitude = stringTokenizer.hasMoreTokens() ? Double.parseDouble(stringTokenizer.nextToken()) : 0;
                double longtitude = stringTokenizer.hasMoreTokens() ? Double.parseDouble(stringTokenizer.nextToken()) : 0;
                String name = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO NAME";
                while(stringTokenizer.hasMoreTokens()){
                    name += " " + stringTokenizer.nextToken();
                }

                Airport newAirport = new Airport(iataCode, latitude, longtitude, name);
                inmemmoryDataBase.appendAirport(newAirport);
            }
        }
    }

    @Override
    public void loadAirline(String pathToFile) {
         /* (TODO Lab No. 1) Please introduce a sensible implementation */

        inmemmoryDataBase.setAirlines(new CustomArrayList<>());
        Scanner scanner = null;

/*
        URL url = Thread.currentThread().getContextClassLoader().getResource(pathToFile);
        File file = null;
        if (url != null) {
            file = new File(url.getPath());
        }
*/

        //jeśli path jest innej postaci (cała ścieżka do pliku)

        File file = new File(pathToFile);


        if (file != null) {
            try {
                scanner = new Scanner(file,"Cp1252");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<String> lines = new ArrayList<>();

        if (scanner != null) {
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }

        Iterator<String> iterator = lines.iterator();
        String line;
        while(iterator.hasNext()){
            //for each line
            line = iterator.next();
            if(line.charAt(0) != '#'){
                StringTokenizer stringTokenizer = new StringTokenizer(line);

                String code = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO CODE";
                String name = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO NAME";
                while(stringTokenizer.hasMoreTokens()){
                    name += " " + stringTokenizer.nextToken();
                }

                Airline newAirline = new Airline(code, name);
                inmemmoryDataBase.appendAirline(newAirline);
            }
        }
    }

    @Override
    public void loadRoute(String pathToFile) {
         /* (TODO Lab No. 1) Please introduce a sensible implementation */
        inmemmoryDataBase.setRoutes(new CustomArrayList<Route>());
        //CustomArrayList<Route> routes = new CustomArrayList<>();
        Scanner scanner = null;

/*
        URL url = Thread.currentThread().getContextClassLoader().getResource(pathToFile);
        File file = null;
        if (url != null) {
            file = new File(url.getPath());
        }
*/

        //jeśli path jest innej postaci (cała ścieżka do pliku)

        File file = new File(pathToFile);


        if (file != null) {
            try {
                scanner = new Scanner(file,"Cp1252");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<String> lines = new ArrayList<>();

        if (scanner != null) {
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }

        Iterator<String> iterator = lines.iterator();
        String line;
        while(iterator.hasNext()){
            //for each line
            line = iterator.next();
            if(line.charAt(0) != '#'){
                StringTokenizer stringTokenizer = new StringTokenizer(line);

                String airlineCode = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO AIRLINE CODE";
                String originCode = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO ORIGIN";
                String destinationCode = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "NO DESTINATION";

                Airline airline = getAirline(airlineCode);
                Airport origin = getAirport(originCode);
                Airport destination = getAirport(destinationCode);

                if(airline != null && origin != null && destination != null){
                    Route route = new Route(airline, origin, destination);
                    if(!routeIsPresent(route)){
                        inmemmoryDataBase.appendRoutes(route);
                    }
                }
            }
        }
    }


    @Override
    public void loadAllData(String pathToAirportsFile, String pathToAirlinesFile, String pathToRoutesFile) {
        loadAirports(pathToAirportsFile);
        loadAirline(pathToAirlinesFile);
        loadRoute(pathToRoutesFile);
    }


    private Airline getAirline(String airlineCode){
        Airline returnAirline = null;
        Iterator<Airline> airlineIterator = inmemmoryDataBase.getAirlines().iterator();
        while(returnAirline == null && airlineIterator.hasNext()){
            Airline currentAirline = airlineIterator.next();
            if(currentAirline.getCode().equals(airlineCode)){
                returnAirline = currentAirline;
            }
        }
        return returnAirline;
    }

    private Airport getAirport(String airportCode){
        Airport returnAirport = null;
        Iterator<Airport> airportIterator = inmemmoryDataBase.getAirports().iterator();
        while(returnAirport == null && airportIterator.hasNext()){
            Airport currentAirport = airportIterator.next();
            if(currentAirport.getIataCode().equals(airportCode)){
                returnAirport = currentAirport;
            }
        }
        return returnAirport;
    }

    private boolean routeIsPresent(Route route){
        boolean isPresent = false;
        Iterator<Route> routeIterator = inmemmoryDataBase.getRoutes().iterator();
        while(!isPresent && routeIterator.hasNext()){
            if(routeIterator.next().equals(route)){
                isPresent = true;
            }
        }
        return isPresent;
    }
}
