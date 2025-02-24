package com.project.conforzone.config;


import com.project.conforzone.model.PurchaseBookingModel;
import com.project.conforzone.model.ServiceAdditionalMetersModel;
import com.project.conforzone.model.UserModel;
import com.project.conforzone.model.dto.PurchaseBookingModelDto;
import com.project.conforzone.model.dto.ServiceAdditionalMetersModelDto;
import com.project.conforzone.model.dto.UserModelDto;
import com.project.conforzone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    /**
     * Realiza configuracion inicial de authenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configurar el ModelMapper
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(PurchaseBookingModel.class, PurchaseBookingModelDto.class)
                .addMappings(mapper -> {
                    mapper.map(PurchaseBookingModel::getUserPurchase, PurchaseBookingModelDto::setUserPurchaseDto);
                    mapper.map(PurchaseBookingModel::getServiceAdditionalMeters, PurchaseBookingModelDto::setServiceAdditionalMetersDto);
                });

        modelMapper.createTypeMap(UserModel.class, UserModelDto.class);

        modelMapper.createTypeMap(ServiceAdditionalMetersModel.class, ServiceAdditionalMetersModelDto.class)
                .addMappings(mapper -> {
                mapper.map(ServiceAdditionalMetersModel::getSpecificService, ServiceAdditionalMetersModelDto::setSpecificServiceDto);
                });
        return modelMapper;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Inicializa BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Obtiene detalles a partir de un correo
     */
    @Bean
    public UserDetailsService userDetailService(){
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
