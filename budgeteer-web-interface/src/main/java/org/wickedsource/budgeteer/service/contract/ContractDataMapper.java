package org.wickedsource.budgeteer.service.contract;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wickedsource.budgeteer.persistence.budget.BudgetEntity;
import org.wickedsource.budgeteer.persistence.contract.ContractEntity;
import org.wickedsource.budgeteer.persistence.contract.ContractFieldEntity;
import org.wickedsource.budgeteer.persistence.invoice.InvoiceEntity;
import org.wickedsource.budgeteer.persistence.project.ProjectContractField;
import org.wickedsource.budgeteer.service.AbstractMapper;
import org.wickedsource.budgeteer.service.budget.BudgetBaseData;
import org.wickedsource.budgeteer.service.invoice.InvoiceBaseData;
import org.wickedsource.budgeteer.service.invoice.InvoiceDataMapper;
import org.wickedsource.budgeteer.web.components.fileUpload.FileUploadModel;

import java.util.*;

@Component
public class ContractDataMapper extends AbstractMapper<ContractEntity, ContractBaseData>{

    @Autowired
    private InvoiceDataMapper invoiceDataMapper;

    @Override
    public ContractBaseData map(ContractEntity entity) {
        if(entity == null)
            return null;
        ContractBaseData result = new ContractBaseData();
        result.setContractName(entity.getName());
        result.setContractId(entity.getId());
        result.setBudget(entity.getBudget());
        result.setInternalNumber(entity.getInternalNumber());
        result.setProjectId(entity.getProject().getId());
        result.setType(entity.getType());
        result.setStartDate(entity.getStartDate());
        result.setFileModel(new FileUploadModel(entity.getFileName(), entity.getFile(), entity.getLink()));
        result.setTaxRate(entity.getTaxRate() == null ? 0.0 : entity.getTaxRate().doubleValue());

        Map<String, DynamicAttributeField> contractAttributes = new HashMap<String, DynamicAttributeField>();
        for(ProjectContractField projectContractField:  entity.getProject().getContractFields()){
            contractAttributes.put(projectContractField.getFieldName(), new DynamicAttributeField(projectContractField.getFieldName(), ""));
        }
        for(ContractFieldEntity fieldEntity : entity.getContractFields()){
            contractAttributes.put(fieldEntity.getField().getFieldName(), new DynamicAttributeField(fieldEntity.getField().getFieldName(), fieldEntity.getValue()));
        }
        result.setContractAttributes(new ArrayList<DynamicAttributeField>(contractAttributes.values()));

        result.setBelongingBudgets(new LinkedList<BudgetBaseData>());
        for(BudgetEntity budgetEntity : entity.getBudgets()){
            result.getBelongingBudgets().add(new BudgetBaseData(budgetEntity.getId(), budgetEntity.getName()));
        }

        result.setBelongingInvoices(new LinkedList<InvoiceBaseData>());
        for(InvoiceEntity invoiceEntity: entity.getInvoices()){
            result.getBelongingInvoices().add(invoiceDataMapper.map(invoiceEntity));
        }

        return result;
    }

    public List<ContractBaseData> map(List<ContractEntity> entityList){
        List<ContractBaseData> result = new LinkedList<ContractBaseData>();
        for(ContractEntity entity : entityList){
            result.add(map(entity));
        }
        return result;
    }
}
