package com.barab.transportnotes.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDto {

        private Long id;
        private String source;
        private String destination;
        private String transportStatus;
        private String comment;

}
