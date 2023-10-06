package com.barab.transportnotes.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {

    private Long id;
    private String name;
    private String address;
    private String comment;

}
