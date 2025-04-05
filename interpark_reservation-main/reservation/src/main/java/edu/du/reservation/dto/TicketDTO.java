package edu.du.reservation.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TicketDTO {
    private String t_id;
    private String u_name;
    private String p_title;
    private String p_place;
    private Date p_date;
    private String r_spot;
}
