package tn.enis.localisationfog.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enis.localisationfog.dto.DeviceAuthenticationDto;
import tn.enis.localisationfog.dto.DeviceAuthenticationResponseDto;
import tn.enis.localisationfog.dto.DeviceLocalizationDto;
import tn.enis.localisationfog.model.Device;
import tn.enis.localisationfog.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceService {

    private final ModelMapper modelMapper;
    private DeviceRepository deviceRepository;

    public DeviceAuthenticationResponseDto registerDevice (DeviceAuthenticationDto device) {
        return modelMapper.map(deviceRepository.save(modelMapper.map(device, Device.class)), DeviceAuthenticationResponseDto.class);
    }

    public Optional<DeviceAuthenticationResponseDto> findDeviceByNameAndPassword(String name, String password) {
        Optional<Device> device = deviceRepository.findByNameAndPassword(name, password);

        if (device.isPresent()) {
            DeviceAuthenticationResponseDto responseDto = new DeviceAuthenticationResponseDto();
            responseDto.setId(device.get().getId());
            // Vous devriez ajouter d'autres propriétés de responseDto ici le cas échéant
            return Optional.of(responseDto);
        } else {
            // Si le device n'est pas trouvé, vous pouvez renvoyer Optional.empty()
            return Optional.empty();
        }
    }

    public DeviceLocalizationDto addDevice (DeviceLocalizationDto device) {
        Optional<Device> device1 = deviceRepository.findById(device.getId());
        if (device1.isPresent()){
            device1.get().setLatitude(device.getLatitude());
            device1.get().setLongitude(device.getLongitude());
            return modelMapper.map(deviceRepository.save(device1.get()),DeviceLocalizationDto.class);
        }else {
            return null;
        }

    }

    public List<Device> findAllDevices () {
        return deviceRepository.findAll();
    }

    public Optional<Device> findDeviceById (Long id) {
        return deviceRepository.findById(id);
    }


    public Device updateDevice (Device device) {
        return deviceRepository.save(device);
    }

    @Transactional
    public void deleteDevice (Long id) {
        deviceRepository.deleteById(id);
    }
}
