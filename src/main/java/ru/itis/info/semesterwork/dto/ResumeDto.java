package ru.itis.info.semesterwork.dto;

import lombok.*;
import ru.itis.info.semesterwork.entity.Resume;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResumeDto {

    private Long id;
    private String header;
    private String description;
    private String contact;

    public static ResumeDto to(Resume resume) {
        return ResumeDto.builder().id(resume.getId()).contact(resume.getContact())
                .description(resume.getDescription())
                .header(resume.getHeader()).build();
    }
}
