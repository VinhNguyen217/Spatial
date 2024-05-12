package com.java.spatial;

import com.java.spatial.model.FeatureType;
import com.java.spatial.model.FeatureTypeRequest;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpatialService {

    @Autowired
    private RestTemplate restTemplate;

    public void connectPostGis() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("dbtype", "postgis");
        params.put("host", "localhost");
        params.put("port", 5432);
        params.put("schema", "public");
        params.put("database", "postgres");
        params.put("user", "postgres");
        params.put("passwd", "123456789");
        params.put("preparedStatements", true);
        params.put("encode functions", true);

        DataStore dataStore = DataStoreFinder.getDataStore(params);
        System.out.printf(dataStore.toString());
    }

    public String publicLayer(String tableName){
        String geoUserName = "admin";
        String geoPassword = "geoserver";
        String geoDomain = "http://localhost:8085/geoserver/rest";
        String geoWorkSpace = "spatial";
        String geoDataStore = "postgres";

        FeatureType featureType = FeatureType.builder()
                .name(tableName)
                .build();
        FeatureTypeRequest request = FeatureTypeRequest.builder()
                .featureType(featureType)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(geoUserName, geoPassword);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<FeatureTypeRequest> entity = new HttpEntity<>(request, headers);

        Object res = restTemplate.exchange("http://localhost:8085/geoserver/rest/workspaces/spatial/datastores/postgres/featuretypes", HttpMethod.POST, entity, String.class).getBody();
        return (String) res;
    }
}
