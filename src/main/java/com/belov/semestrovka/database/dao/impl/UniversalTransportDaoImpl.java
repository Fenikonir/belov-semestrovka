package com.belov.semestrovka.database.dao.impl;

import com.belov.semestrovka.database.dao.DAO;
import com.belov.semestrovka.database.dao.DAOFabric;
import com.belov.semestrovka.database.dao.TransportDAO;
import com.belov.semestrovka.database.entity.City;
import com.belov.semestrovka.database.entity.Transport;
import com.belov.semestrovka.database.entity.TransportQueries;
import com.belov.semestrovka.database.entity.TransportStop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversalTransportDaoImpl implements DAO<Transport>, TransportDAO<Transport> {

    private final Connection connection;


    public UniversalTransportDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public String getSQLScript(TransportQueries t) {
        return "";
    }

    @Override
    public Transport getById(int id) {
        Transport transport = null;
        try (PreparedStatement statement = connection.prepareStatement(getSQLScript(TransportQueries.SELECT_TRANSPORT_BY_ID))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transport = mapResultSetToTransports(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transport;
    }

    @Override
    public Transport getByParameter(String p, String v) {
        Transport transport = null;
        String query = String.format(getSQLScript(TransportQueries.SELECT_TRANSPORT_BY_PARAMETER), p);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transport = mapResultSetToTransports(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transport;
    }

    @Override
    public List<Transport> getAll() {
        List<Transport> transportList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getSQLScript(TransportQueries.SELECT_ALL_TRANSPORTS))) {
            while (resultSet.next()) {
                Transport train = mapResultSetToTransports(resultSet);
                transportList.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportList;
    }

    @Override
    public void save(Transport entity) {
        try (PreparedStatement statement = connection.prepareStatement(getSQLScript(TransportQueries.INSERT_TRANSPORT))) {
            statement.setString(1, entity.getTransportNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setInt(3, (int) entity.getFare());
            statement.setString(4, entity.getTransportType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transport entity) {
        try (PreparedStatement statement = connection.prepareStatement(getSQLScript(TransportQueries.UPDATE_TRANSPORT))) {
            statement.setString(1, entity.getTransportNumber());
            statement.setInt(2, entity.getCity().getId());
            statement.setInt(3, (int) entity.getFare());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Transport entity) {
        try (PreparedStatement statement = connection.prepareStatement(getSQLScript(TransportQueries.DELETE_TRANSPORT))) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transport> getByCity(int cityId) {
        List<Transport> transportList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getSQLScript(TransportQueries.SELECT_TRANSPORTS_BY_CITY))) {
            System.out.println(getSQLScript(TransportQueries.SELECT_TRANSPORTS_BY_CITY));
            statement.setInt(1, cityId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transport train = mapResultSetToTransports(resultSet);
                transportList.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportList;
    }

    private Transport mapResultSetToTransports(ResultSet resultSet) throws SQLException {
        Transport transport = new Transport();
        transport.setId(resultSet.getInt("id"));
        transport.setTransportNumber(resultSet.getString(String.format("%s_number", getSQLScript(TransportQueries.TR_TYPE))));
        City city = new City();
        city.setId(resultSet.getInt("city_id"));
        transport.setCity(city);
        transport.setFare(resultSet.getInt("fare"));
        transport.setLastModified(resultSet.getTimestamp("last_modified").toLocalDateTime());
        transport.setTransportType(Transport.TransportType.valueOf(resultSet.getString("transport_type")));
        transport.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
        List<TransportStop> transportStopList = new ArrayList<>();
        Array transportStopsArray = resultSet.getArray("transport_stop_ids");
        if (transportStopsArray != null) {
            Integer[] transportStops = (Integer[]) transportStopsArray.getArray();
            for (Integer transportStopId : transportStops) {
                TransportStop transportStop = (TransportStop) DAOFabric.getTransportStopsDAO().getById(transportStopId);
                if (transportStop != null) {
                    transportStopList.add(transportStop);
                }
            }
        }

        transport.setTransportStopList(transportStopList);
        return transport;
    }
}