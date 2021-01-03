package com.company.factoryprogram_web.data;

import com.company.factoryprogram_web.dto.RequiredAvailablePartsDto;
import com.company.factoryprogram_web.dto.RequiredPartsDto;
import com.company.factoryprogram_web.dto.AvailabilityDto;

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
        var requiredQty = repo.getRequiredQuantityForConfiguration(confId);
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

    public Iterable<RequiredAvailablePartsDto> getRequiredAndAvailableParts(int configurationId) {
        var configuration = repo.getConfiguration(configurationId);
        var confId = configuration.getId();
        var requiredQty = repo.getRequiredQuantityForConfiguration(confId);
        List<RequiredAvailablePartsDto> itemsRequiredParts = new ArrayList<>();

        for (var qty :
                requiredQty) {
            itemsRequiredParts.add(new RequiredAvailablePartsDto(qty.getPart().getId(),
                    qty.getPart().getPartName(),
                    qty.getPart().getPartRefNumber(),
                    qty.getQtyRequired(),
                    qty.getStorage().getAvailQty()));
        }
        return itemsRequiredParts;
    }


    public Iterable<AvailabilityDto> checkAvailability(int configurationId) {
        var configuration = repo.getConfiguration(configurationId);
        var confId = configuration.getId();

        var resultItem = repo.getAvailabilityOfParts(confId);

        List<AvailabilityDto> listItems = new ArrayList<>();

        int i = 1;
        for (var checkAvailability :
                resultItem) {
            listItems.add(new AvailabilityDto(checkAvailability, i));
            ++i;
        }
        return listItems;
    }




}
