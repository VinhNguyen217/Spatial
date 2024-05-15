package com.java.spatial.controller;

import com.java.spatial.service.SpatialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/spatial")
public class SpatialController {

    private final SpatialService spatialService;

    public SpatialController(SpatialService spatialService) {
        this.spatialService = spatialService;
    }

    @GetMapping("/connect")
    public ResponseEntity<?> connectPostGIS() throws IOException {
        spatialService.connectPostGis();
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/public-layer")
    public ResponseEntity<?> publicLayer(@RequestParam String tableName) {
        return ResponseEntity.ok(spatialService.publicLayer(tableName));
    }

}
