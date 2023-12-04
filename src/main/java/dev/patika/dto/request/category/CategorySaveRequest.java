package dev.patika.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {
    @NotNull (message = "Name field cannot be null")
    @NotEmpty (message = "Name field cannot be empty")
    private String name;
    private String description;
}
