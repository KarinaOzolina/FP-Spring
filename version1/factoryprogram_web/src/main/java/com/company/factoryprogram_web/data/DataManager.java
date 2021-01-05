package com.company.factoryprogram_web.data;

import com.company.factoryprogram_web.dto.OrderUpdateDto;
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
                    qty.getStorage().getAvailQty(),
                    getAvailability(qty)));
        }

        return itemsRequiredParts;
    }


    public String getAvailability(RequiredQuantity requiredQuantity) {
        int resultAvailQuantity = requiredQuantity.getStorage().getAvailQty();
        int requiredQ = requiredQuantity.getQtyRequired();

        if (resultAvailQuantity < requiredQ) {
            return "Not enough";
        } else {
            return "Enough";
        }
    }


//    public int orderParts(Storage storage) {
//        int storageAvailQty = storage.getAvailQty();
//        RequiredQuantity rq = new RequiredQuantity();
//        int requiredQty = rq.getQtyRequired();
//
//        if (storageAvailQty >= requiredQty) {
//            return storageAvailQty - requiredQty;
//        } else {
//            return storageAvailQty;
//        }
//    }

//    public String getAvailability(Storage storage) {
//        int resultAvailQuantity = storage.getAvailQty();
//        int requiredQ = storage.getRequiredQuantity().getQtyRequired();
//
//        if (resultAvailQuantity < requiredQ) {
//            return "Not enough";
//        } else {
//            return "Enough";
//        }
//    }

    public Iterable<OrderUpdateDto> getUpdateResults(int configurationId) {
//        var product = repo.getProduct(productId);
//        var categoryId = product.getCategory().getId();

        var configuration = repo.getConfiguration(configurationId);
        var confId = configuration.getId();

//        var limitations = repo.getCategoryLimitations(categoryId);
//        var testResults = repo.getTestResultItem(productId);

        var requiredQty = repo.getRequiredQuantityForConfiguration(confId);
        var quantityAfterUpdate = repo.getAvailablePartsAfterUpdate(confId);

        List<OrderUpdateDto> items = new ArrayList<>();

        for (var reqQty :
                requiredQty) {
//            items.add(mapPartUpdate(requiredQuantity, updatedQuantity));
        }

        return items;
    }



    private OrderUpdateDto mapPartUpdate(RequiredQuantity requiredQuantity, Iterable<UpdatedPartItem> updatedQuantity) {

        List<UpdatedPartItem> items = new ArrayList<>();

        for (var updatedQty :
                updatedQuantity) {
            items.add(updatedQty);
        }

        var updateItem = items.stream().filter(i -> i.getId() ==
                requiredQuantity.getPart().getId()).findFirst();


        var availQty = 0;
        var id =0;

        if (updateItem.isPresent()) {
            var update = updateItem.get();
            id = update.getId();
            availQty = update.getAvailQty();
        }

        return new OrderUpdateDto(id, availQty);
    }



}
