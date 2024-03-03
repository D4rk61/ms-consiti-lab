package dev.jose.mssqsspring1.domain.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;
    private String name;
    private Integer price;
}
