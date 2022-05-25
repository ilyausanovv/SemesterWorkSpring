package ru.itis.info.semesterwork.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.info.semesterwork.entity.Ad;

@Builder
@Data
public class AdDto {

    private Long id;
    private String header;
    private String description;
    private String contact;
    private Long price;

    public static AdDto to(Ad ad) {
        return AdDto.builder().id(ad.getId())
                .header(ad.getHeader())
                .description(ad.getDescription())
                .contact(ad.getContact())
                .price(ad.getPrice()).build();
    }
}
