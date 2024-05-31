package ge.vippay.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDTO {
    private String name;
    private String email;
    private String service;
}
