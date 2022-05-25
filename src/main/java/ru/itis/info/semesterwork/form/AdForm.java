package ru.itis.info.semesterwork.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.info.semesterwork.entity.Ad;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdForm {

    private String header;
    private String description;
    private String contact;
    private String price;
    private String creatorLogin;
    private Ad.Status status = Ad.Status.ACTIVE;
}
