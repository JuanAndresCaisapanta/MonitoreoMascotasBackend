package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.DeviceDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Services.DeviceService;
@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@GetMapping
	public ResponseEntity<List<Device>> read() {

		List<Device> device = deviceService.read();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(device);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Device> getById(@PathVariable("id") int id) {

		Device device = deviceService.getOne(id).get();
		if (device == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(device);

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Validated DeviceDto deviceDto, BindingResult bindingResult) {

		Device device = new Device();
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es incorrecto"), HttpStatus.BAD_REQUEST);
		device.setCode(deviceDto.getCode());
		device.setCreation_date(deviceDto.getCreation_date());
		device.setUpdate_date(deviceDto.getUpdate_date());
		device.setUsers(deviceDto.getUsers());
		deviceService.create(device);
		return new ResponseEntity<>(new Message("Dispositivo creado"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DeviceDto deviceDto,
			BindingResult bindingResult) {

		Device device = deviceService.getOne(id).get();
		if (!deviceService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es obligatorio"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(deviceDto.getCode()))
			return new ResponseEntity<>(new Message("El codigo es incorrecto"), HttpStatus.BAD_REQUEST);
		device.setCode(deviceDto.getCode());
		device.setUpdate_date(deviceDto.getUpdate_date());
		deviceService.update(device);
		return new ResponseEntity<>(new Message("Dispositivo actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!deviceService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		deviceService.delete(id);
		return new ResponseEntity<>(new Message("Dispositivo borrado"), HttpStatus.OK);

	}

}
