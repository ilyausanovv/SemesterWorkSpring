package ru.itis.info.semesterwork.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.info.semesterwork.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForm {

    private String header;
    private String description;
    private String contact;
    private String creatorLogin;
}
