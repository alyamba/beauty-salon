package com.cp.salon.service;

import com.cp.salon.entity.ProcedureEntity;
import com.cp.salon.model.Procedure;
import com.cp.salon.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureService {

    @Autowired
    ProcedureRepository procedureRepository;

    public List<Procedure> getByCategoryId(Long categoryId){
        List<Procedure> procedures = new ArrayList<Procedure>();
        List<ProcedureEntity> procedureEntities = procedureRepository.findAllByCategory_Id(categoryId);
        for(ProcedureEntity procedure: procedureEntities){
            procedures.add(Procedure.toModel(procedure));
        }
        return procedures;
    }

    public void add(ProcedureEntity procedure){
        procedureRepository.save(procedure);
    }

    public void remove(ProcedureEntity procedure){
        procedureRepository.deleteById(procedure.getId());
    }

    public void edit(ProcedureEntity procedure){
        procedureRepository.save(procedure);
    }

    public ProcedureEntity getById(Long procedureId) throws Exception {
        ProcedureEntity procedureEntity = procedureRepository.findFirstById(procedureId);
        if(procedureEntity == null){
            throw  new Exception("Процедура с данным id не найдена");
        }
        return procedureEntity;
    }

}
