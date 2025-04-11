package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import com.project.conforzone.repository.SpecificServiceRepository;
import com.project.conforzone.services.SpecificServiceModelService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecificServiceModelServiceImpl implements SpecificServiceModelService {
    private final SpecificServiceRepository specificServiceRepository;
    private final Mapper modelMapper;
    @Override
    public List<SpecificServiceModelDto> getAllSpecificServices() {
        return specificServiceRepository.findAll().stream().map(modelMapper::toSpecificModelDto)
                .peek(specificServiceModelDto -> {
                    specificServiceModelDto.setFirstPrice(specificServiceModelDto.getFirstPrice() * 100);
                    specificServiceModelDto.setSecondPrice(specificServiceModelDto.getSecondPrice() * 100);
                    specificServiceModelDto.setBookingPrice(specificServiceModelDto.getBookingPrice() * 100);
                    specificServiceModelDto.setPricePerMeter(specificServiceModelDto.getPricePerMeter() * 100);
                })
                .toList();
    }

    @Override
    public List<SpecificServiceModelDto> getAllOfferSpecificServices() {
        return specificServiceRepository.findByOfferTrue().stream().map(modelMapper::toSpecificModelDto).toList();
    }

    @Override
    public List<SpecificServiceModelDto> getSpecificServicesBySlug(String slug) {
        return Optional.of(specificServiceRepository.getSpecificServicesBySlug(slug))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new GlobalException("No se encontraron servicios"))
                .stream()
                .map(modelMapper::toSpecificModelDto)
                .toList();
    }

    @Override
    public SpecificServiceModelDto getSpecificServiceById(Integer id) {
        return specificServiceRepository.findById(id).map(modelMapper::toSpecificModelDto).orElseThrow(() -> new GlobalException("El servicio específico no existe"));
    }

    @Override
    public SpecificServiceModelDto getSpecificServiceBySlugAndId(String slug, Integer id) {
        return specificServiceRepository.getSpecificServiceBySlugAndId(slug, id).map(modelMapper::toSpecificModelDto).orElseThrow(() -> new GlobalException("El servicio específico no existe o su etiqueta no coincide"));
    }

    @Override
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto specificServiceModelDto) {
        if (!specificServiceRepository.existsByName(specificServiceModelDto.getName())){
            specificServiceModelDto.setFirstPrice(specificServiceModelDto.getFirstPrice());
            specificServiceModelDto.setSecondPrice(specificServiceModelDto.getSecondPrice());
            specificServiceModelDto.setBookingPrice(specificServiceModelDto.getBookingPrice());
            specificServiceModelDto.setPricePerMeter(specificServiceModelDto.getPricePerMeter());
            return modelMapper.toSpecificModelDto(specificServiceRepository.save(modelMapper.toSpecificModel(specificServiceModelDto)));
        } else {
            throw new GlobalException("El servicio específico ya existe");
        }
    }

    @Override
    public SpecificServiceModelDto editSpecificService(SpecificServiceModelDto specificServiceModelDto, Integer idPath) {
        SpecificServiceModel editedSpecificModel = specificServiceRepository.findById(specificServiceModelDto.getId()).orElse(null);
        if (editedSpecificModel != null && Objects.equals(editedSpecificModel.getId(), idPath)){
            editedSpecificModel.setName(specificServiceModelDto.getName());
            editedSpecificModel.setDescription(specificServiceModelDto.getDescription());
            editedSpecificModel.setBookingPrice(specificServiceModelDto.getBookingPrice());
            editedSpecificModel.setFirstPrice(specificServiceModelDto.getFirstPrice());
            editedSpecificModel.setSecondPrice(specificServiceModelDto.getSecondPrice());
            editedSpecificModel.setAvailable(specificServiceModelDto.isAvailable());
            editedSpecificModel.setPricePerMeter(specificServiceModelDto.getPricePerMeter());
            editedSpecificModel.setOffer(specificServiceModelDto.isOffer());
            return modelMapper.toSpecificModelDto(specificServiceRepository.save(editedSpecificModel));
        } else {
            throw new GlobalException("El servicio específico no existe o su id es erróneo");
        }
    }
}
