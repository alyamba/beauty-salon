package com.cp.salon.service;

import com.cp.salon.entity.MasterEntity;
import com.cp.salon.entity.SlotEntity;
import com.cp.salon.exeption.MasterExeption;
import com.cp.salon.model.Master;
import com.cp.salon.model.Procedure;
import com.cp.salon.model.Slot;
import com.cp.salon.repository.MasterRepository;
import com.cp.salon.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MasterService {
    @Autowired
    MasterRepository masterRepository;

    @Autowired
    SlotRepository slotRepository;

    public void add(MasterEntity master){
        masterRepository.save(master);
    }

    public void remove(MasterEntity master){
        masterRepository.deleteById(master.getId());
    }

    public void edit(MasterEntity master){
        masterRepository.save(master);
    }

    public List<Master> getAll(){
        List<Master> masters = new ArrayList<>();
        Iterable<MasterEntity> masterEntities = masterRepository.findAll();
        for(MasterEntity masterEntity: masterEntities){
            masters.add(Master.toModel(masterEntity));
        }
        return masters;
    }

    public MasterEntity getById(Long masterId) throws MasterExeption {
        MasterEntity master = masterRepository.findOneById(masterId).orElse(null);
        if(master == null){
            throw new MasterExeption("Мастер с данным id не найден");
        }
        return master;
    }

    public List<Procedure> getProcedureByMaster(MasterEntity master) throws MasterExeption {
        Optional<MasterEntity> masterEntity = masterRepository.findById(master.getId());
        if(masterEntity.isPresent()){
            return Master.toModel(master).getProcedures();
        }else {
            throw new MasterExeption("Процедуры для заданного мастера не заданы");
        }
    }

    public void disableSlot(Long masterId, Date startDate, Integer slotSize) throws MasterExeption {
        MasterEntity master = masterRepository.findById(masterId).get();
        for(Integer i = 0; i < slotSize; i++){
            SlotEntity slotEntity = new SlotEntity();
            Date date = new Date(startDate.getTime());
            date.setHours(date.getHours() + i);
            slotEntity.setDate(date);
            master.removeSlot(slotEntity);
        }
        masterRepository.save(master);
    }

    public void addSlot(Long masterId, Date startDate, Integer slotSize) throws MasterExeption {
        MasterEntity master = masterRepository.findById(masterId).get();

        for(Integer i = 0; i < slotSize; i++){
            Date date = new Date(startDate.getTime());
            date.setHours(date.getHours() + i);
            SlotEntity slot = slotRepository.findFirstByDate(date);
            SlotEntity slotEntity = slotRepository.findFirstByDate(date);
            if(slotEntity == null){
                throw new MasterExeption("Создайте необходимые слоты");
            }
            master.addSlot(slot);
        }

        masterRepository.save(master);
    }

    public void addWeekSlot(Long masterId, Date startDate) throws MasterExeption {
        MasterEntity master = masterRepository.findFirstById(masterId);

        if(master == null){
            throw new MasterExeption("Мастер не найден");
        }

        startDate.setHours(9);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        for(Integer i = 0; i < 5; i++){
            for(Integer j = 0; j < 8; j++){
                Date date = new Date(startDate.getTime());
                date.setDate(date.getDate() + i);
                date.setHours(date.getHours() + j);
                SlotEntity slotEntity = slotRepository.findFirstByDate(date);
                if(slotEntity == null){
                    throw new MasterExeption("Создайте необходимые слоты");
                }
                master.addSlot(slotEntity);
            }
        }

        masterRepository.save(master);
    }

}
