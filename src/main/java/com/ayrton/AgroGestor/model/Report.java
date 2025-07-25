package com.ayrton.AgroGestor.model;

import com.ayrton.AgroGestor.enums.ReportType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "reports")
@Data
public class Report {

    @Id
    private String id;
    private ReportType type;
    private LocalDateTime generatedAt;
    private Map<String, Object> data;
}
