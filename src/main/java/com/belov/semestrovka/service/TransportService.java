package com.belov.semestrovka.service;

import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.dao.TransportDAO;
import com.belov.semestrovka.database.dao.impl.UniversalTransportDaoImpl;
import com.belov.semestrovka.database.entity.*;

import java.time.Month;
import java.util.*;

public class TransportService {
    public static double calculateAverageFare(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        double totalFare = 0;
        int count = 0;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            totalFare += t.getFare();
            count++;
        }
        if (count > 0) {
            return totalFare / count;
        }
        return 0;
    }

    public static double calculateAverageStops(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        int totalStops = 0;
        int count = 0;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            totalStops += t.getTransportStopList().size();
            count++;
        }
        if (count > 0) {
            return (double) totalStops / count;
        }
        return 0;
    }

    public static double calculateMinFare(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        double minFare = Double.MAX_VALUE;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            double fare = t.getFare();
            if (fare < minFare) {
                minFare = fare;
            }
        }
        return minFare;
    }

    public static double calculateMaxFare(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        double maxFare = Double.MIN_VALUE;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            double fare = t.getFare();
            if (fare > maxFare) {
                maxFare = fare;
            }
        }
        return maxFare;
    }

    public static double calculateAverageFarePercentage(Transport transport, City city) {
        double averageFare = calculateAverageFare(transport, city);
        double minFare = calculateMinFare(transport, city);
        double maxFare = calculateMaxFare(transport, city);

        if (maxFare == minFare) {
            return 100.0;
        } else if (maxFare != 0) {
            return (averageFare - minFare) / (maxFare - minFare) * 100;
        } else {
            throw new ArithmeticException("Cannot divide by zero");
        }
    }


    public static double calculateAverageStopsPercentage(Transport transport, City city) {
        double averageStop = calculateAverageStops(transport, city);
        double minStop = calculateMinStops(transport, city);
        double maxStop = calculateMaxStops(transport, city);

        if (maxStop == minStop) {
            return 100.0;
        } else if (maxStop != 0) {
            return (averageStop - minStop) / (maxStop - minStop) * 100;
        } else {
            throw new ArithmeticException("Cannot divide by zero");
        }
    }

    public static int calculateMinStops(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        int minStops = Integer.MAX_VALUE;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            int stops = t.getTransportStopList().size();
            if (stops < minStops) {
                minStops = stops;
            }
        }
        return minStops;
    }

    public static int calculateMaxStops(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        int maxStops = Integer.MIN_VALUE;
        for (Transport t : transportDAO.getByCity(city.getId())) {
            int stops = t.getTransportStopList().size();
            if (stops > maxStops) {
                maxStops = stops;
            }
        }
        return maxStops;
    }

    private static UniversalTransportDaoImpl getTransportDAO(Transport transport) {
        if (transport instanceof Bus) {
            return DAOFabric.getBusTransportDAO();
        } else if (transport instanceof Plane) {
            return DAOFabric.getPlaneTransportDAO();
        } else if (transport instanceof Train) {
            return DAOFabric.getTrainTransportDAO();
        } else if (transport instanceof Trolley) {
            return DAOFabric.getTrolleyTransportDAO();
        }
        throw new IllegalArgumentException("Invalid transport type");
    }

    public static int[] getTransportType(Transport transport, City city) {
        int[] types = new int[3];
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        for (Transport t : transportDAO.getByCity(city.getId())) {
            if (t.getTransportType() == Transport.TransportType.REGULAR) {
                types[0] ++;
            } else if (t.getTransportType() == Transport.TransportType.ORDERED) {
                types[1]++;
            } else {
                types[2]++;
            }
        }
        return types;
    }

    public static List<Transport> getAllTransportForCity(Transport transport, City city) {
        TransportDAO<Transport> transportDAO = getTransportDAO(transport);
        return transportDAO.getByCity(city.getId());
    }

    public static Transport findTransportWithLatestCreatedAt(List<Transport> transports) {
        return transports.stream()
                .max(Comparator.comparing(Transport::getCreatedAt))
                .orElseThrow(() -> new RuntimeException("No transports found"));
    }

    public static List<TransportStop> getTransportStops(List<Transport> transports) {
        List<TransportStop> transportStops = new ArrayList<>();
        Set<Integer> tsIds = new HashSet<>();
        for (Transport t : transports) {
            for (TransportStop ts : t.getTransportStopList()) {
                if (!tsIds.contains(ts.getId())) {
                    tsIds.add(ts.getId());
                    transportStops.add(ts);
                }
            }
        }
        return transportStops;
    }

    public static Map<String, Integer> getMonthDictionary(List<Transport> transports) {
        Map<String, Integer> monthDict = new HashMap<>();
        monthDict.put("Янв", 0);
        monthDict.put("Фев", 0);
        monthDict.put("Мар", 0);
        monthDict.put("Апр", 0);
        monthDict.put("Май", 0);
        monthDict.put("Июн", 0);
        monthDict.put("Июл", 0);
        monthDict.put("Авг", 0);
        monthDict.put("Сен", 0);
        monthDict.put("Окт", 0);
        monthDict.put("Ноя", 0);
        monthDict.put("Дек", 0);

        for (Transport t : transports) {
            Month createdAtMonth = t.getCreatedAt().getMonth();
            switch (createdAtMonth) {
                case JANUARY:
                    monthDict.put("Янв", monthDict.get("Янв") + 1);
                    break;
                case FEBRUARY:
                    monthDict.put("Фев", monthDict.get("Фев") + 1);
                    break;
                case MARCH:
                    monthDict.put("Мар", monthDict.get("Мар") + 1);
                    break;
                case APRIL:
                    monthDict.put("Апр", monthDict.get("Апр") + 1);
                    break;
                case MAY:
                    monthDict.put("Май", monthDict.get("Май") + 1);
                    break;
                case JUNE:
                    monthDict.put("Июн", monthDict.get("Июн") + 1);
                    break;
                case JULY:
                    monthDict.put("Июл", monthDict.get("Июл") + 1);
                    break;
                case AUGUST:
                    monthDict.put("Авг", monthDict.get("Авг") + 1);
                    break;
                case SEPTEMBER:
                    monthDict.put("Сен", monthDict.get("Сен") + 1);
                    break;
                case OCTOBER:
                    monthDict.put("Окт", monthDict.get("Окт") + 1);
                    break;
                case NOVEMBER:
                    monthDict.put("Ноя", monthDict.get("Ноя") + 1);
                    break;
                case DECEMBER:
                    monthDict.put("Дек", monthDict.get("Дек") + 1);
                    break;
                default:
                    break;
            }
        }
        return monthDict;
    }
}