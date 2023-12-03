package dev.patika.dto.request.author;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    @NotNull
    @NotEmpty
    private String name;
    /*@JsonFormat(pattern="yyyy-MM-dd")*/
    private LocalDate birthDate;
    private String country;
}
