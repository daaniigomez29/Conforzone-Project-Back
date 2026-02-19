package com.project.conforzone.services.impl;

import com.project.conforzone.exception.GlobalException;
import com.project.conforzone.model.SpecificServiceModel;
import com.project.conforzone.model.dto.SpecificServiceModelDto;
import com.project.conforzone.repository.SpecificServiceRepository;
import com.project.conforzone.services.SpecificServiceModelService;
import com.project.conforzone.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecificServiceModelServiceImpl implements SpecificServiceModelService {
    private final SpecificServiceRepository specificServiceRepository;
    private final Mapper modelMapper;
    @Override
    public List<SpecificServiceModelDto> getAllSpecificServices() {
        return specificServiceRepository.findAll().stream().map(modelMapper::toSpecificModelDto).toList();
    }

    @Override
    public List<SpecificServiceModelDto> getAllOfferSpecificServices() {
        return specificServiceRepository.findByOfferTrue().
                stream()
                //.sorted(Comparator.comparing(SpecificServiceModel::getFirstPrice))
                .map(modelMapper::toSpecificModelDto).toList();
    }

    @Override
    public List<SpecificServiceModelDto> getSpecificServicesBySlug(String slug) {
        Pattern numberPattern = Pattern.compile("de hasta ([0-9.]+)");

        return Optional.of(specificServiceRepository.getSpecificServicesBySlug(slug))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new GlobalException("No se encontraron servicios"))
                .stream()
                .filter(specificServiceModel -> !specificServiceModel.isOffer())
                .sorted(Comparator.comparingDouble((SpecificServiceModel s) -> {
                    if (s.getName() == null) return Double.MIN_VALUE;
                    Matcher m = numberPattern.matcher(s.getName());
                    if (m.find()) {
                        // convierte "12.000" -> 12000
                        return Double.parseDouble(m.group(1).replace(".", ""));
                    } else {
                        return Double.MIN_VALUE; // si no hay número, lo ponemos al final
                    }
                })) // de mayor a menor
                .map(modelMapper::toSpecificModelDto)
                .toList();
    }

    @Override
    public SpecificServiceModelDto getSpecificServiceById(Integer id) {
        return specificServiceRepository.findById(id).map(modelMapper::toSpecificModelDto).orElseThrow(() -> new GlobalException("El servicio específico no existe"));
    }

    @Override
    public SpecificServiceModelDto getOfferSpecificServiceById(Integer id) {
        return Optional.of(specificServiceRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(SpecificServiceModel::isOffer)
                .map(modelMapper::toSpecificModelDto)
                .orElseThrow(() -> new GlobalException("La oferta no se encuentra disponible"));

    }

    @Override
    public SpecificServiceModelDto getSpecificServiceBySlugAndId(String slug, Integer id) {
        return specificServiceRepository.getSpecificServiceBySlugAndId(slug, id).filter(specificServiceModel -> !specificServiceModel.isOffer()).map(modelMapper::toSpecificModelDto).orElseThrow(() -> new GlobalException("El servicio específico no existe o su etiqueta no coincide"));
    }

    @Override
    public SpecificServiceModelDto addSpecificService(SpecificServiceModelDto specificServiceModelDto) {
        if (!specificServiceRepository.existsByName(specificServiceModelDto.getName())){
            specificServiceModelDto.setFirstPrice(specificServiceModelDto.getFirstPrice());
            specificServiceModelDto.setSecondPrice(specificServiceModelDto.getSecondPrice());
            specificServiceModelDto.setBookingPrice(specificServiceModelDto.getBookingPrice());
            specificServiceModelDto.setPricePerMeter(specificServiceModelDto.getPricePerMeter());
            specificServiceModelDto.setOffer(specificServiceModelDto.isOffer());
            specificServiceModelDto.setSlug(specificServiceModelDto.getSlug());
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
            specificServiceModelDto.setSlug(specificServiceModelDto.getSlug());
            return modelMapper.toSpecificModelDto(specificServiceRepository.save(editedSpecificModel));
        } else {
            throw new GlobalException("El servicio específico no existe o su id es erróneo");
        }
    }
}
