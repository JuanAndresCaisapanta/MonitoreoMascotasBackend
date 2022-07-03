package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Repositories.EstablishmentRepository;

@Service
@Transactional
public class EstablishmentService {
	@Autowired
	EstablishmentRepository establishmentRepository;
	@Autowired
	JavaMailSender javaMailSender;

	public List<Establishment> read() {
		return (List<Establishment>) establishmentRepository.findAll();
	}

	public void create(Establishment establishment) {
		establishmentRepository.save(establishment);
	}

	public void update(Establishment establishment) {
		establishmentRepository.save(establishment);
	}

	public void delete(int id) {
		establishmentRepository.deleteById(id);
	}

	public Optional<Establishment> getOne(int id) {
		return establishmentRepository.findById(id);
	}

	public boolean existsById(int id) {
		return establishmentRepository.existsById(id);
	}
	
	public List<Pet> getPets(String name, Long id){
		return establishmentRepository.getPets(name, id);
	}

	public void sendEmail(String fromEmail, String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(fromEmail);
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
