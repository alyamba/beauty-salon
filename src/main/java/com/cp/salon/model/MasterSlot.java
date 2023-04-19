package com.cp.salon.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MasterSlot {
    private Long masterId;
    private Date startDate;
    private Integer slotSize;
}
