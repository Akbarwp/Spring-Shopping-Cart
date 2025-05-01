package com.spring.springshoppingcart.request;

import java.math.BigDecimal;

import com.spring.springshoppingcart.model.Category;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String brand;

    @NotBlank
    @DecimalMin("0.00")
    private BigDecimal price;

    @NotBlank
    @Min(0)
    private int inventory;

    @NotBlank
    @Size(max = 255)
    @Nullable
    private String description;

    @NotBlank
    private Category category;
}
