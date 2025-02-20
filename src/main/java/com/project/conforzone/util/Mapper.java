package com.project.conforzone.util;

import com.project.conforzone.model.*;
import com.project.conforzone.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public UserModelDto toUserDTO(UserModel user){
        return modelMapper.map(user, UserModelDto.class);
    }
    public UserModel toUser(UserModelDto userDTO){
        return modelMapper.map(userDTO, UserModel.class);
    }
    public PurchaseBookingModelDto toPurchaseBookingModelDto(PurchaseBookingModel purchaseBookingModel){
        return modelMapper.map(purchaseBookingModel, PurchaseBookingModelDto.class);
    }
    public PurchaseBookingModel toPurchaseBookingModel(PurchaseBookingModelDto purchaseBookingModelDto){
        return modelMapper.map(purchaseBookingModelDto, PurchaseBookingModel.class);
    }
    public ServiceAdditionalMetersModelDto toServiceAdditionalMettersModelDto(ServiceAdditionalMetersModel serviceAdditionalMettersModel){
        return modelMapper.map(serviceAdditionalMettersModel, ServiceAdditionalMetersModelDto.class);
    }
    public ServiceAdditionalMetersModel toServiceAdditionalMettersModel(ServiceAdditionalMetersModelDto serviceAdditionalMettersModelDto){
        return modelMapper.map(serviceAdditionalMettersModelDto, ServiceAdditionalMetersModel.class);
    }
    public SpecificServiceModelDto toSpecificModelDto(SpecificServiceModel specificServiceModel){
        return modelMapper.map(specificServiceModel, SpecificServiceModelDto.class);
    }
    public SpecificServiceModel toSpecificModel(SpecificServiceModelDto specificServiceModelDto){
        return modelMapper.map(specificServiceModelDto, SpecificServiceModel.class);
    }
    public ServiceModelDto toServiceModelDto(ServiceModel serviceModel){
        return modelMapper.map(serviceModel, ServiceModelDto.class);
    }
    public ServiceModel toServiceModel(ServiceModelDto serviceModelDto){
        return modelMapper.map(serviceModelDto, ServiceModel.class);
    }
}
