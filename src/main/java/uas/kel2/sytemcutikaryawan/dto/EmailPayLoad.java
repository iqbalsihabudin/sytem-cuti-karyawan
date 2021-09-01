package uas.kel2.sytemcutikaryawan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailPayLoad {
    String from;
    String to;
    String subject;
    String text;

}
