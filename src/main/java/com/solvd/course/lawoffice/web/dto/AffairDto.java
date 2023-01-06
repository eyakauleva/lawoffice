package com.solvd.course.lawoffice.web.dto;

import com.solvd.course.lawoffice.domain.enums.AffairStatus;
import com.solvd.course.lawoffice.web.validation.CreateGroup;
import com.solvd.course.lawoffice.web.validation.UpdateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AffairDto {
    @NotNull(groups = UpdateGroup.class, message = "Affair id cannot be null")
    private Long id;
    @NotNull(groups = UpdateGroup.class, message = "Affair must contain a client")
    @Valid
    private UserDto user;
    @NotBlank(message = "Affair name cannot be blank")
    @Size(max = 45, groups = {CreateGroup.class, UpdateGroup.class}, message = "Affair's name max length is 45 symbols")
    private String name;
    @NotNull(groups = UpdateGroup.class, message = "Affair status cannot be null")
    private AffairStatus status;
    @NotBlank(message = "Affair description cannot be blank")
    @Size(max = 45, groups = {CreateGroup.class, UpdateGroup.class}, message = "Affair's description max length is 1000 symbols")
    private String description;
    @NotNull(groups = UpdateGroup.class, message = "Affair start date cannot be null")
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    @NotEmpty(message = "Affair must contain at least one lawyer")
    @Valid
    private List<LawyerDto> lawyers;
}
