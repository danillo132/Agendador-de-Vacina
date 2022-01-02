package agenda.vacina.controller;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import agenda.vacina.model.Agendamento;
import agenda.vacina.model.Ubs;
import agenda.vacina.model.Usuario;
import agenda.vacina.repository.AgendamentoRepository;
import agenda.vacina.repository.UbsRepository;
import agenda.vacina.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UbsRepository ubsRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroConta")
	public ModelAndView iniciaCadastro() {
		ModelAndView modelAndView = new ModelAndView("/cadastroConta");
		modelAndView.addObject("userobj", new Usuario());
		return modelAndView;
	}
	
	@PostMapping(value = "/cadastrarUsuario")
	public ModelAndView cadastrarUsuario(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("/cadastroSucesso");
		modelAndView.addObject("userobj", usuario);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(usuario.getSenha());
		usuario.setSenha(result);
		usuarioRepository.save(usuario);
		
		modelAndView.addObject("userobj", new Usuario());
		return modelAndView;
	}
	
	@GetMapping(value = "/Agendamentos")
	public ModelAndView agendamentos(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView andView = new ModelAndView("/Agendamentos");
			
		
		Agendamento dose1 = agendamentoRepository.doses(usuario.getId(), "dose1");
		Agendamento dose2 = agendamentoRepository.doses(usuario.getId(), "dose2"); 
		Agendamento dose3 = agendamentoRepository.doses(usuario.getId(), "dose3"); 
	
		
		andView.addObject("dose1obj", dose1);
		andView.addObject("dose2obj", dose2);
		andView.addObject("dose3obj", dose3);
		
	
		
		
		return andView;
	}
	
	@GetMapping(value = "/AgendarVacina")
	public ModelAndView cadastrarAgendamentos(@AuthenticationPrincipal Usuario usuario) {
		Usuario usuario2 = usuarioRepository.findById(usuario.getId()).get(); 
		ModelAndView andView = new ModelAndView("/AgendarVacina");
		andView.addObject("ubses", ubsRepository.findAll());
		andView.addObject("agenobj", new Agendamento());
		andView.addObject("userobj", usuario2);
		return andView;
	}
	
	@PostMapping(value = "**/cadastrarAgendamento")
	public ModelAndView cadastraAgendamento(@AuthenticationPrincipal Usuario usuario, Agendamento agendamento) {
		ModelAndView modelAndView = new ModelAndView("/cadastroAgendamentoSucesso");
		agendamento.setUsuario(usuario);
		Random gerador = new Random();
		
		Ubs ubs = ubsRepository.findUbsByName(agendamento.getUbs());
		
		agendamento.setEndereco(ubs.getEndereco());
		agendamento.setNumero(Integer.valueOf(ubs.getNumero()));
		agendamento.setBairro(ubs.getBairro());
		
		agendamento.setProtocolo(String.valueOf(Math.abs(gerador.nextInt())));
		agendamento.setStatus("Agendado");
		modelAndView.addObject("agenobj", agendamento);
		agendamentoRepository.save(agendamento);
		
		
		
		
		
		modelAndView.addObject("agenobj", new Agendamento());
		
		return modelAndView;
	}
	
	@GetMapping(value = "/editaragendamento/{idagendamento}")
	public ModelAndView editaragendamento(@PathVariable("idagendamento") Long idagendamento) {
		Agendamento agendamento = agendamentoRepository.findById(idagendamento).get();
		Ubs ubs = ubsRepository.findUbsByName(agendamento.getUbs());
		ModelAndView andView = new ModelAndView("/AgendarVacina");
		
		andView.addObject("agenobj", agendamento);
		andView.addObject("ubses", ubs);
		
		return andView;
	}
	
	@GetMapping(value = "/deletaragendamento/{idagendamento}")
	public ModelAndView deletaragendamento(@PathVariable("idagendamento") Long idagendamento,@AuthenticationPrincipal Usuario usuario) {
		
		agendamentoRepository.deleteById(idagendamento);
		ModelAndView andView = new ModelAndView("/Agendamentos");
		
		Agendamento dose1 = agendamentoRepository.doses(usuario.getId(), "dose1");
		Agendamento dose2 = agendamentoRepository.doses(usuario.getId(), "dose2"); 
		Agendamento dose3 = agendamentoRepository.doses(usuario.getId(), "dose3"); 
	
		
		andView.addObject("dose1obj", dose1);
		andView.addObject("dose2obj", dose2);
		andView.addObject("dose3obj", dose3);
		
		return andView;
	}
	
	
}



