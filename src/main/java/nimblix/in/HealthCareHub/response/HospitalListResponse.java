package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalListResponse {

    private String status;
    private String message;
    private List<HospitalSpecializationResponse> data;

}