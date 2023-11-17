package com.belov.semestrovka.database.dao.impl;

        import com.belov.semestrovka.database.dao.DAO;
        import com.belov.semestrovka.database.entity.TransportStop;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class TransportStopDaoImpl implements DAO<TransportStop> {
    private final Connection connection;
    private static final String SELECT_TRANSPORT_STOP_BY_ID = "SELECT * FROM transport_stops WHERE id = ?";
    private static final String SELECT_TRANSPORT_STOP_BY_PARAMETER = "SELECT * FROM transport_stops WHERE %s = ?";
    private static final String SELECT_ALL_TRANSPORT_STOPS = "SELECT * FROM transport_stops";
    private static final String INSERT_TRANSPORT_STOP = "INSERT INTO transport_stops (name, address, opening_time, capacity, has_roof, has_phone_charging, city_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TRANSPORT_STOP = "UPDATE transport_stops SET name = ?, address = ?, opening_time = ?, capacity = ?, has_roof = ?, has_phone_charging = ?, city_id = ? WHERE id = ?";
    private static final String DELETE_TRANSPORT_STOP = "DELETE FROM transport_stops WHERE id = ?";

    public TransportStopDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransportStop getById(int id) {
        TransportStop transportStop = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TRANSPORT_STOP_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transportStop = mapResultSetToTransportStop(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportStop;
    }

    @Override
    public TransportStop getByParameter(String p, String v) {
        TransportStop transportStop = null;
        String query = String.format(SELECT_TRANSPORT_STOP_BY_PARAMETER, p);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, v);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transportStop = mapResultSetToTransportStop(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportStop;
    }

    @Override
    public List<TransportStop> getAll() {
        List<TransportStop> transportStopList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TRANSPORT_STOPS)) {
            while (resultSet.next()) {
                TransportStop transportStop = mapResultSetToTransportStop(resultSet);
                transportStopList.add(transportStop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportStopList;
    }

    @Override
    public void save(TransportStop entity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TRANSPORT_STOP, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getOpeningTime());
            statement.setInt(4, entity.getCapacity());
            statement.setBoolean(5, entity.hasRoof());
            statement.setBoolean(6, entity.hasPhoneCharging());
            statement.setInt(7, entity.getCityId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int transportStopId = generatedKeys.getInt(1);
                entity.setId(transportStopId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TransportStop entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TRANSPORT_STOP)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getOpeningTime());
            statement.setInt(4, entity.getCapacity());
            statement.setBoolean(5, entity.hasRoof());
            statement.setBoolean(6, entity.hasPhoneCharging());
            statement.setInt(7, entity.getCityId());
            statement.setInt(8, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TransportStop entity) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TRANSPORT_STOP)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TransportStop mapResultSetToTransportStop(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String openingTime = resultSet.getString("opening_time");
        int capacity = resultSet.getInt("capacity");
        boolean hasRoof = resultSet.getBoolean("has_roof");
        boolean hasPhoneCharging = resultSet.getBoolean("has_phone_charging");
        int cityId = resultSet.getInt("city_id");

        return new TransportStop(id, name, address, openingTime, capacity, cityId, hasRoof, hasPhoneCharging);
    }
}