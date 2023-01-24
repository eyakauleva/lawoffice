package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.web.validation.CreateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "Review information")
public class ReviewDto {

    @Schema(description = "Review's unique identifier")
    private Long id;

    @NotBlank(groups = CreateGroup.class, message = "Review's description cannot be blank")
    @Size(max = 1000, message = "Review's description max length is 1000")
    @Schema(description = "Review's description")
    private String description;

    @NotNull(groups = CreateGroup.class, message = "Review's grade cannot be null")
    @Range(groups = CreateGroup.class, min = 1, max = 10, message = "Grade must be from 0 to 10")
    @Schema(description = "Review's grade")
    private Integer grade;

    @Schema(description = "Time when review was created")
    private LocalDateTime reviewTime;

    @NotNull(groups = CreateGroup.class, message = "Review must contain a client")
    @Valid
    @Schema(description = "User who created review")
    private UserDto client;

}
