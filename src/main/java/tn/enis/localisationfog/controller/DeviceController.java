package tn.enis.localisationfog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enis.localisationfog.dto.DeviceAuthenticationDto;
import tn.enis.localisationfog.dto.DeviceAuthenticationResponseDto;
import tn.enis.localisationfog.dto.DeviceLocalizationDto;
import tn.enis.localisationfog.model.Device;
import tn.enis.localisationfog.service.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/devices")
@CrossOrigin("*")
@AllArgsConstructor
public class DeviceController {
    private DeviceService deviceService;


    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices(){
        List<Device> devices = deviceService.findAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable Long id){
        Optional<Device> device = deviceService.findDeviceById(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<DeviceLocalizationDto> addDevice(@Valid @RequestBody DeviceLocalizationDto device){
//        DeviceLocalizationDto addDevice = deviceService.addDevice(device);
//        return new ResponseEntity<>(addDevice,HttpStatus.CREATED);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable("id") Long id){
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @PostMapping("/register")
    public ResponseEntity<DeviceAuthenticationResponseDto> registerDevice(@Valid @RequestBody DeviceAuthenticationDto device){
        DeviceAuthenticationResponseDto addDevice = deviceService.registerDevice(device);
        return new ResponseEntity<>(addDevice,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Optional<DeviceAuthenticationResponseDto>> loginDevice(@Valid @RequestBody DeviceAuthenticationDto device){
        Optional<DeviceAuthenticationResponseDto> localizationDto = deviceService.findDeviceByNameAndPassword(device.getName(), device.getPassword());
        return new ResponseEntity<>(localizationDto,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<DeviceLocalizationDto> updateDevice(@RequestBody DeviceLocalizationDto device){
        DeviceLocalizationDto updateClient = deviceService.addDevice(device);
        return new ResponseEntity<>(updateClient,HttpStatus.OK);
    }
/*    @PostMapping("/login")
    public ResponseEntity<DeviceAuthenticationResponseDto> loginDevice(@Valid @RequestBody DeviceAuthenticationDto device){
        DeviceAuthenticationResponseDto localizationDto = deviceService.findDeviceByNameAndPassword(device.getName(), device.getPassword());
        return new ResponseEntity<>(localizationDto,HttpStatus.ACCEPTED);
    }*/
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable Long id){
//        Optional<Device> device = deviceService.findDeviceById(id);
//        return new ResponseEntity<>(device, HttpStatus.OK);
//    }
}
