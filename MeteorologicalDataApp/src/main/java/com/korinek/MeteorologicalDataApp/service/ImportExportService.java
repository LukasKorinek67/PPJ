package com.korinek.MeteorologicalDataApp.service;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.model.Measurement;
import com.korinek.MeteorologicalDataApp.utils.CsvParser;
import com.korinek.MeteorologicalDataApp.utils.Timestamp;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImportExportService {

    private final MeasurementService measurementService;
    private static final Logger log = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    public ImportExportService(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    public void importFile(MultipartFile multipartFile, City city) throws IOException {
        if(multipartFile == null) {
            return;
        }
        String csv = new String(multipartFile.getBytes());
        List<Measurement> measurements = CsvParser.parseCSV(csv, city.getName());
        for (Measurement measurement:measurements) {
            System.out.println(measurement);
            measurementService.addNewMeasurement(measurement);
        }
    }

    public String exportFile(City city) {
        List<Measurement> measurements = measurementService.getAllMeasurementsByCity(city);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Date;Weather;Weather Description;Temperature;Feels Like Temperature;Pressure;Humidity;Visibility;Wind Speed;Cloudiness\n");

        for (Measurement measurement : measurements) {
            stringBuilder.append(Timestamp.getDateFromTimestamp(measurement.getTimestamp())).append(";");
            stringBuilder.append(measurement.getWeather()).append(";");
            stringBuilder.append(measurement.getWeatherDescription()).append(";");
            stringBuilder.append(measurement.getTemperature()).append(";");
            stringBuilder.append(measurement.getFeelsLikeTemperature()).append(";");
            stringBuilder.append(measurement.getPressure()).append(";");
            stringBuilder.append(measurement.getHumidity()).append(";");
            stringBuilder.append(measurement.getVisibility()).append(";");
            stringBuilder.append(measurement.getWindSpeed()).append(";");
            stringBuilder.append(measurement.getCloudiness()).append(";");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
