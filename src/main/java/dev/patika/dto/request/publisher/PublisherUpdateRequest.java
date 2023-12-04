package dev.patika.dto.request.publisher;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @Positive (message = "Id field must be positive")
    private int id;
    @NotNull (message = "Name field cannot be null")
    @NotEmpty (message = "Name field cannot be empty")
    private String name;
}
