package com.cp.salon.service;

import com.cp.salon.entity.SlotEntity;
import com.cp.salon.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SlotService {
    @Autowired
    SlotRepository slotRepository;

    public void createSlotForNextWeek(Date startDate){
        for(Integer i = 0; i < 7; i++){
            for(Integer j = 9; j < 18; j++){
                SlotEntity slotEntity = new SlotEntity();
                Date date = new Date(startDate.getTime());
                date.setHours(j);
                date.setDate(date.getDate() + i);
                date.setMinutes(0);
                date.setSeconds(0);
                slotEntity.setDate(date);
                slotRepository.save(slotEntity);
            }
        }
    }
}
