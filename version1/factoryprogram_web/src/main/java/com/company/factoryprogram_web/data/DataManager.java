package com.company.factoryprogram_web.data;

import com.company.factoryprogram_web.dto.RequiredPartsDto;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private PartRepository repo;

    public DataManager() {
        repo = new PartRepository();
    }

    public Iterable<RequiredPartsDto> getRequiredParts(int configurationId) {
        var configuration = repo.getConfiguration(configurationId);

        var confId = configuration.getId();

        var requiredQty = repo.getConfigurationQuantity(confId);

        List<RequiredPartsDto> items = new ArrayList<>();

        for (var qty :
                requiredQty) {

            items.add(new RequiredPartsDto(qty.getPart().getId(),
                    qty.getPart().getPartName(),
                    qty.getPart().getPartRefNumber(),
                    qty.getQtyRequired()));
        }
        return items;
    }

}
